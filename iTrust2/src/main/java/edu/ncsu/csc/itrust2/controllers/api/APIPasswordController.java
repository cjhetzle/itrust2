package edu.ncsu.csc.itrust2.controllers.api;

import com.google.gson.Gson;
import edu.ncsu.csc.itrust2.forms.password.ResetPasswordForm;
import edu.ncsu.csc.itrust2.models.enums.TransactionType;
import edu.ncsu.csc.itrust2.models.persistent.Patient;
import edu.ncsu.csc.itrust2.models.persistent.Personnel;
import edu.ncsu.csc.itrust2.utils.LoggerUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Controller for resetting password
 *
 * @author jmphipps
 * @author ncluther
 */
@RestController
@SuppressWarnings ( { "rawtypes", "unchecked" } )
public class APIPasswordController extends APIController {

    private static final Gson GSON = new Gson();

    /**
     * Creates a password reset token for the given user (assuming it is found)
     * and sends it to the associated email
     *
     * @param username
     *            of user to create token for
     * @return success or failure
     */
    @PostMapping ( BASE_PATH + "/tokens" )
    @PreAuthorize ( "hasRole('ROLE_ANONYMOUS')" )
    public ResponseEntity createToken ( @RequestBody final String username ) {
        final Patient p = Patient.getPatient( username );
        final Personnel x = Personnel.getByName( username );
        if ( p != null ) {
            final String token = UUID.randomUUID().toString();
            final Calendar cal = Calendar.getInstance();
            cal.add( Calendar.MINUTE, 10 );
            final SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy hh:mm aa" );
            final String expireTime = sdf.format( cal.getTime() );
            p.setToken( token + ":" + System.currentTimeMillis() );
            p.save();
            final Email email = new EmailBuilder() // http://www.simplejavamail.org
                    .from( "iTrust2", "iTrust2.no.reply@gmail.com" )
                    .to( p.getFirstName() + " " + p.getLastName(), p.getEmail() ).subject( "Password Reset" )
                    .textHTML( "<h3>Password Reset Token: " + token + "</h3><br />"
                            + "This token will expire at: <strong>" + expireTime + "</strong>" )
                    .build();

            new Mailer( "smtp.gmail.com", 587, "iTrust2.no.reply@gmail.com", "a^3'a#TPzV", TransportStrategy.SMTP_TLS )
                    .sendMail( email );
            LoggerUtil.log( TransactionType.PASSWORD_RESETTOKENSENT,
                    SecurityContextHolder.getContext().getAuthentication().getName(), null,
                    "User requested reset token" );
            return new ResponseEntity( GSON.toJson( "Token sent to associated email" ), HttpStatus.OK );
        }
        if ( x != null ) {
            final String token = UUID.randomUUID().toString();
            final Calendar cal = Calendar.getInstance();
            cal.add( Calendar.MINUTE, 10 );
            final SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy hh:mm aa" );
            final String expireTime = sdf.format( cal.getTime() );
            x.setToken( token + ":" + System.currentTimeMillis() );
            x.save();
            final Email email = new EmailBuilder() // http://www.simplejavamail.org
                    .from( "iTrust2", "iTrust2.no.reply@gmail.com" )
                    .to( x.getFirstName() + " " + x.getLastName(), x.getEmail() ).subject( "Password Reset" )
                    .textHTML( "<h3>Password Reset Token: " + token + "</h3><br />"
                            + "This token will expire at: <strong>" + expireTime + "</strong>" )
                    .build();

            new Mailer( "smtp.gmail.com", 587, "iTrust2.no.reply@gmail.com", "a^3'a#TPzV", TransportStrategy.SMTP_TLS )
                    .sendMail( email );
            LoggerUtil.log( TransactionType.PASSWORD_RESETTOKENSENT,
                    SecurityContextHolder.getContext().getAuthentication().getName(), null,
                    "User requested reset token" );
            return new ResponseEntity( GSON.toJson( "Token sent to associated email" ), HttpStatus.OK );
        }
        return new ResponseEntity( GSON.toJson( "User not found." ), HttpStatus.BAD_REQUEST );
    }

    /**
     * Changes a user's password to the new input if the token is equal to the
     * one assigned and is not timed out.
     *
     * @param id
     *            the patients username
     * @param rpf
     *            the form containing the token, password, and username
     * @return success or failure
     */
    @PostMapping ( BASE_PATH + "/tokens/{id}" )
    public ResponseEntity resetPassword ( @PathVariable final String id, @RequestBody final ResetPasswordForm rpf ) {
        final Patient p = Patient.getPatient( id );
        final Personnel x = Personnel.getByName( id );
        if ( rpf.getNewPass().length() < 6 ) {
            return new ResponseEntity( GSON.toJson( "Password must be 6 or more characters." ), HttpStatus.BAD_REQUEST );
        } else if( !rpf.getNewPass().equals(rpf.getRepeat()) ) {
            return new ResponseEntity( GSON.toJson( "The passwords do not match" ), HttpStatus.BAD_REQUEST );
        }

        if ( p != null ) {
            final String[] tok = p.getToken().split( ":" );
            if ( !tok[0].equals( rpf.getCode() ) ) {
                return new ResponseEntity( GSON.toJson("Invalid Token"), HttpStatus.BAD_REQUEST );
            }
            if ( Long.parseLong( tok[1] ) + 600000 < System.currentTimeMillis() ) {
                return new ResponseEntity( GSON.toJson("The last request token timed out"), HttpStatus.BAD_REQUEST );
            }
            final PasswordEncoder pe = new BCryptPasswordEncoder();
            p.getSelf().setPassword( pe.encode( rpf.getRepeat() ) );
            p.getSelf().save();
            p.save();
            return new ResponseEntity( GSON.toJson( "Password changed" ), HttpStatus.OK );
        }
        if ( x != null ) {
            final String[] tok = x.getToken().split( ":" );
            if ( !tok[0].equals( rpf.getCode() ) ) {
                return new ResponseEntity( GSON.toJson( "Invalid Token" ), HttpStatus.BAD_REQUEST );
            }
            if ( Long.parseLong( tok[1] ) + 600000 < System.currentTimeMillis() ) {
                return new ResponseEntity( GSON.toJson( "The last request token timed out" ), HttpStatus.BAD_REQUEST );
            }
            final PasswordEncoder pe = new BCryptPasswordEncoder();
            x.getSelf().setPassword( pe.encode( rpf.getRepeat() ) );
            x.getSelf().save();
            x.save();

            LoggerUtil.log( TransactionType.PASSWORD_RESET,
                    SecurityContextHolder.getContext().getAuthentication().getName(), null,
                    "User reset their password" );
            return new ResponseEntity( GSON.toJson( "Password changed" ), HttpStatus.OK );
        }

        return new ResponseEntity( GSON.toJson( "User not found." ), HttpStatus.BAD_REQUEST );
    }
}
