package edu.ncsu.csc.itrust2.formtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.ncsu.csc.itrust2.forms.admin.UserForm;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.persistent.User;

/**
 * Tests User and UserForm
 *
 * @author jmphipps
 */
public class UserFormTest {
    /**
     * Tests the creation of a user if the password fields don't match in the
     * user form
     */
    @Test
    public void testDiffPass () {
        final UserForm uf = new UserForm( "user", "pass1", "HCP", "1" );
        uf.setPassword2( "pass2" );
        try {
            @SuppressWarnings ( "unused" )
            final User u = new User( uf );
        }
        catch ( final IllegalArgumentException e ) {
            assertEquals( "Passwords do not match!", e.getMessage() );
        }
    }

    /**
     * Tests the equals method in user
     */
    @SuppressWarnings ( "unlikely-arg-type" )
    @Test
    public void testEquals () {
        final int enabled = 1;
        final User u1 = new User( "user", "pass", Role.ROLE_HCP, enabled );
        final User u2 = new User( "user", "pass", Role.ROLE_HCP, enabled );
        final User u3 = new User( "user3", "pass", Role.ROLE_HCP, enabled );
        final User u4 = new User( "user", "pass4", Role.ROLE_HCP, enabled );
        final User u5 = new User( "user", "pass", Role.ROLE_PATIENT, enabled );

        final UserForm uf2 = new UserForm( u1 );
        assertEquals( u1.getUsername(), uf2.getUsername() );

        final User u6 = new User( "user", "pass", Role.ROLE_PATIENT, null );
        final User u7 = new User( "user", "pass", Role.ROLE_PATIENT, ( enabled - 1 ) );
        final User u8 = new User( "user", null, Role.ROLE_HCP, enabled );
        final User u9 = new User( null, "pass", Role.ROLE_HCP, enabled );

        assertEquals( "pass", u1.getPassword() );
        assertTrue( enabled - u1.getEnabled() == 0 );
        assertEquals( Role.ROLE_HCP, u1.getRole() );

        assertTrue( u1.equals( u1 ) ); // same object
        assertTrue( u1.equals( u2 ) ); // different objects, same values
        assertTrue( !u1.equals( u3 ) ); // u3 has different name
        assertTrue( !u1.equals( u4 ) ); // u4 has different pass
        assertTrue( !u1.equals( u5 ) ); // u5 has different role
        assertTrue( !u1.equals( null ) ); // if comparing against null

        assertTrue( !u1.equals( uf2 ) ); // comparing against different object
                                         // type
        assertTrue( !u6.equals( u1 ) ); // u6 has null enabled value
        assertTrue( !u1.equals( u7 ) ); // u7 has different enabled value
        assertTrue( !u8.equals( u1 ) ); // null pass for u8
        assertTrue( !u9.equals( u1 ) ); // null user for u9

    }

    /**
     * Tests the changepassword method in the user object
     */
    @Test
    public void testChangePass () {
        final User u = new User( "tcp", "$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.",
                Role.ROLE_PATIENT, 1 );
        final PasswordEncoder pe = new BCryptPasswordEncoder();
        u.changePass( "wrong", "newPass", "newPass" );
        assertTrue( pe.matches( "123456", u.getPassword() ) );
        u.changePass( "123456", "newPass", "newPass" );
        assertTrue( pe.matches( "newPass", u.getPassword() ) );
    }
}
