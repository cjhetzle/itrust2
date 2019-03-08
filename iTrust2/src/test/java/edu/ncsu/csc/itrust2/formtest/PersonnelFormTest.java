package edu.ncsu.csc.itrust2.formtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc.itrust2.forms.personnel.PersonnelForm;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.enums.State;
import edu.ncsu.csc.itrust2.models.persistent.Personnel;
import edu.ncsu.csc.itrust2.models.persistent.User;

/**
 * Tests Personnel and PersonnelForm
 *
 * @author jmphipps
 *
 */
public class PersonnelFormTest {

    /**
     * Tests the constructor that makes a form from a user. Also tests a couple
     * getters/setters
     */
    @Test
    public void testUserConstructor () {
        final User u = new User( "user", "pass", Role.ROLE_HCP, 1 );

        final PersonnelForm pf = new PersonnelForm( u );
        assertEquals( "user", pf.getSelf() );

        pf.setEnabled( "0" );
        assertEquals( "0", pf.getEnabled() );
        pf.setId( "newid" );
        assertEquals( "newid", pf.getId() );
    }

    /**
     * Tests Constructor that makes a form from a personnel object and
     * getters/setters in personnel
     */
    @Test
    public void testConstructor () {
        final User u = new User( "user", "pass", Role.ROLE_HCP, 1 );
        final Personnel p = new Personnel();
        p.setSelf( u );

        p.setEnabled( 1 );
        assertEquals( 1, (int) p.getEnabled() );
        p.setId( 5L );
        assertEquals( 5L, (long) p.getId() );
        p.setFirstName( "fn" );
        assertEquals( "fn", p.getFirstName() );
        p.setLastName( "ln" );
        assertEquals( "ln", p.getLastName() );
        p.setAddress1( "add" );
        assertEquals( "add", p.getAddress1() );
        p.setAddress2( "add2" );
        assertEquals( "add2", p.getAddress2() );
        p.setCity( "city" );
        assertEquals( "city", p.getCity() );
        p.setState( State.AK );
        assertEquals( State.AK, p.getState() );
        p.setZip( "12345" );
        assertEquals( "12345", p.getZip() );
        p.setPhone( "1234567890" );
        assertEquals( "1234567890", p.getPhone() );
        p.setSpecialty( "s" );
        assertEquals( "s", p.getSpecialty() );
        p.setEmail( "e@g.com" );
        assertEquals( "e@g.com", p.getEmail() );

        final PersonnelForm pf = new PersonnelForm( p );
        assertEquals( "add", pf.getAddress1() );
        assertEquals( "e@g.com", pf.getEmail() );
    }
}
