package edu.ncsu.csc.itrust2.controllers.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ncsu.csc.itrust2.forms.hcp_patient.PatientForm;
import edu.ncsu.csc.itrust2.models.enums.TransactionType;
import edu.ncsu.csc.itrust2.models.persistent.OfficeVisit;
import edu.ncsu.csc.itrust2.models.persistent.Patient;
import edu.ncsu.csc.itrust2.models.persistent.User;
import edu.ncsu.csc.itrust2.utils.LoggerUtil;

/**
 * Controller responsible for providing various REST API endpoints for the
 * Patient model.
 *
 * @author Kai Presler-Marshall
 *
 */
@RestController
@SuppressWarnings ( { "rawtypes", "unchecked" } )
public class APIPatientController extends APIController {

    /**
     * Retrieves and returns a list of all Patients stored in the system
     *
     * @return list of patients
     */
    @GetMapping ( BASE_PATH + "/patients" )
    public List<Patient> getPatients () {
        return Patient.getPatients();
    }

    /**
     * If you are logged in as a patient, then you can use this convenience
     * lookup to find your own information without remembering your id. This
     * allows you the shorthand of not having to look up the id in between.
     *
     * @return The patient object for the currently authenticated user.
     */
    @GetMapping ( BASE_PATH + "/patient" )
    @PreAuthorize ( "hasRole('ROLE_PATIENT')" )
    public ResponseEntity getPatient () {
        final User self = User.getByName( SecurityContextHolder.getContext().getAuthentication().getName() );
        final Patient patient = Patient.getPatient( self );
        return null == patient
                ? new ResponseEntity( "Could not find a patient entry for you, " + self.getUsername(),
                        HttpStatus.NOT_FOUND )
                : new ResponseEntity( patient, HttpStatus.OK );
    }

    /**
     * Retrieves and returns the Patient with the username provided
     *
     * @param username
     *            The username of the Patient to be retrieved, as stored in the
     *            Users table
     * @return response
     */
    @GetMapping ( BASE_PATH + "/patients/{username}" )
    public ResponseEntity getPatient ( @PathVariable ( "username" ) final String username ) {
        final Patient patient = Patient.getPatient( username );
        return null == patient ? new ResponseEntity( "No Patient found for username " + username, HttpStatus.NOT_FOUND )
                : new ResponseEntity( patient, HttpStatus.OK );
    }

    /**
     * Creates a new Patient record for a User from the RequestBody provided.
     *
     * @param patientF
     *            the Patient to be validated and saved to the database
     * @return response
     */
    @PostMapping ( BASE_PATH + "/patients" )
    public ResponseEntity createPatient ( @RequestBody final PatientForm patientF ) {
        try {
            final Patient patient = new Patient( patientF );
            if ( null != Patient.getPatient( patient.getSelf() ) ) {
                return new ResponseEntity( "Patient with the id " + patient.getSelf().getUsername() + " already exists",
                        HttpStatus.CONFLICT );
            }
            patient.save();
            return new ResponseEntity( patient, HttpStatus.OK );
        }
        catch ( final Exception e ) {
            return new ResponseEntity( "Could not create " + patientF.toString() + " because of " + e.getMessage(),
                    HttpStatus.BAD_REQUEST );
        }

    }

    /**
     * Updates the Patient with the id provided by overwriting it with the new
     * Patient record that is provided. If the ID provided does not match the ID
     * set in the Patient provided, the update will not take place
     *
     * @param id
     *            The username of the Patient to be updated
     * @param patientF
     *            The updated Patient to save
     * @return response
     */
    @PutMapping ( BASE_PATH + "/patients/{id}" )
    public ResponseEntity updatePatient ( @PathVariable final String id, @RequestBody final PatientForm patientF ) {
        try {
            // error checking
            if ( patientF.getFirstName() == null || patientF.getFirstName().equals( "" )
                    || !patientF.getFirstName().matches( "[A-Za-z-' ]{0,20}" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getLastName() == null || patientF.getLastName().equals( "" )
                    || !patientF.getLastName().matches( "[A-Za-z-' ]{0,30}" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getPreferredName() != null && !patientF.getPreferredName().matches( "[A-Za-z-' ]{0,20}" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getMother() != null && !patientF.getMother().matches( "[A-Za-z0-9]{0,20}" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getFather() != null && !patientF.getFather().matches( "[A-Za-z-0-9]{0,20}" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getEmail() == null || patientF.getEmail().equals( "" )
                    || !patientF.getEmail().matches( "[A-Za-z._@0-9]{0,30}" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getAddress1() == null || patientF.getAddress1().equals( "" )
                    || !patientF.getAddress1().matches( "[A-Za-z.0-9 ]{0,50}" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getAddress2() != null && !patientF.getAddress2().matches( "[A-Za-z.0-9 ]{0,50}" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getCity() == null || patientF.getCity().equals( "" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getZip() == null || patientF.getZip().equals( "" )
                    || !patientF.getZip().matches( "[0-9-]{5,10}" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getPhone() == null || patientF.getPhone().equals( "" )
                    || !patientF.getPhone().matches( "[0-9]{3}-[0-9]{3}-[0-9]{4}" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            if ( patientF.getDateOfBirth() == null || patientF.getDateOfBirth().equals( "" ) ) {
                return new ResponseEntity( HttpStatus.BAD_REQUEST );
            }
            final DateFormat df = new SimpleDateFormat( "mm/dd/yyyy" );
            df.parse( patientF.getDateOfBirth() );

            final Patient patient = new Patient( patientF );
            if ( null != patient.getSelf().getUsername() && !id.equals( patient.getSelf().getUsername() ) ) {
                return new ResponseEntity( "The ID provided does not match the ID of the Patient provided",
                        HttpStatus.CONFLICT );
            }
            final Patient dbPatient = Patient.getPatient( id );
            if ( null == dbPatient ) {
                return new ResponseEntity( "No Patient found for id " + id, HttpStatus.NOT_FOUND );
            }
            else {
                // Delete the patient so that the cache has to refresh.
                dbPatient.delete();
            }
            patient.save();
            LoggerUtil.log( TransactionType.HCP_PATIENT_DEMOGRAPHICS,
                    SecurityContextHolder.getContext().getAuthentication().getName() );
            return new ResponseEntity( patient, HttpStatus.OK );
        }
        catch ( final Exception e ) {
            return new ResponseEntity( "Could not update " + patientF.toString() + " because of " + e.getMessage(),
                    HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Retrieves a list of Diagnoses for a patient
     *
     * @return list of office visits with diagnoses for given patients
     */
    @GetMapping ( BASE_PATH + "/diagnosis/myDiagnoses" )
    @PreAuthorize ( "hasRole('ROLE_PATIENT')" )
    public List<OfficeVisit> getDiagnosesForPatient () {
        final User self = User.getByName( SecurityContextHolder.getContext().getAuthentication().getName() );
        final List<OfficeVisit> visits = OfficeVisit.getForPatient( self.getId() );
        final List<OfficeVisit> diagnoses = new ArrayList<OfficeVisit>();
        for ( int i = 0; i < visits.size(); i++ ) {
            if ( visits.get( i ).getDiagnosis() != null ) {
                diagnoses.add( visits.get( i ) );
            }

        }
        LoggerUtil.log( TransactionType.DIAGNOSIS_VIEW,
                SecurityContextHolder.getContext().getAuthentication().getName(), null, "Patient viewed diagnosis" );
        return diagnoses;
    }

}
