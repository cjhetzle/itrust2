package edu.ncsu.csc.itrust2.formtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import edu.ncsu.csc.itrust2.forms.hcp_patient.PatientForm;
import edu.ncsu.csc.itrust2.models.enums.BloodType;
import edu.ncsu.csc.itrust2.models.enums.Ethnicity;
import edu.ncsu.csc.itrust2.models.enums.Gender;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.enums.State;
import edu.ncsu.csc.itrust2.models.persistent.Patient;
import edu.ncsu.csc.itrust2.models.persistent.User;

/**
 * Tests the PatientForm class getters, setters and constructors.
 *
 * @author jmphipps
 */
public class PatientFormTest {
    /**
     * Tests the untested getters and setters in PatientForm
     */
    @Test
    public void testGettersAndSetters () {
        final PatientForm testPatient = new PatientForm();
        testPatient.setMother( "mom" );
        testPatient.setFather( "dad" );
        testPatient.setDateOfDeath( "00/00/0000" );
        testPatient.setCauseOfDeath( "dying" );
        testPatient.setId( (long) 7777 );

        assertEquals( "mom", testPatient.getMother() );
        assertEquals( "dad", testPatient.getFather() );
        assertEquals( "00/00/0000", testPatient.getDateOfDeath() );
        assertEquals( "dying", testPatient.getCauseOfDeath() );

    }

    /**
     * Tests the constructor that uses a Patient to construct a PatientForm
     *
     * @throws ParseException
     */
    @Test
    public void testConstructor () throws ParseException {
        final Patient p = new Patient();

        p.setFirstName( "first" );
        p.setPreferredName( "name" );
        p.setLastName( "last" );
        p.setEmail( "fn@x.com" );
        p.setAddress1( "add1" );
        p.setAddress2( "add2" );
        p.setCity( "city" );
        p.setZip( "12345" );
        p.setPhone( "1234567890" );
        p.setCauseOfDeath( "dying" );
        p.setState( State.AK );
        p.setBloodType( BloodType.ABNeg );
        p.setGender( Gender.Male );
        p.setEthnicity( Ethnicity.AfricanAmerican );

        final SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
        final Date parsedDate = sdf.parse( "01/01/2000" );
        final Calendar c = Calendar.getInstance();
        c.setTime( parsedDate );
        p.setDateOfBirth( c );
        p.setDateOfDeath( c );
        assertEquals( "Sat Jan 01 00:00:00 EST 2000", p.getDateOfBirth().getTime().toString() );

        assertEquals( "first", p.getFirstName() );
        assertEquals( "name", p.getPreferredName() );
        assertEquals( "last", p.getLastName() );
        assertEquals( "fn@x.com", p.getEmail() );
        assertEquals( "add1", p.getAddress1() );
        assertEquals( "add2", p.getAddress2() );
        assertEquals( "city", p.getCity() );
        assertEquals( "12345", p.getZip() );
        assertEquals( "1234567890", p.getPhone() );
        assertEquals( "dying", p.getCauseOfDeath() );
        assertNull( p.getMother() );
        assertNull( p.getFather() );
        assertEquals( State.AK, p.getState() );
        assertEquals( Gender.Male, p.getGender() );
        assertEquals( BloodType.ABNeg, p.getBloodType() );
        assertEquals( Ethnicity.AfricanAmerican, p.getEthnicity() );

        final User u = new User( "username", "pass", Role.ROLE_PATIENT, 1 );
        p.setSelf( u );

        final PatientForm pf = new PatientForm( p );
        assertEquals( "first", pf.getFirstName() );
        assertEquals( "name", pf.getPreferredName() );
        assertEquals( "last", pf.getLastName() );
        assertEquals( "fn@x.com", pf.getEmail() );
        assertEquals( "add1", pf.getAddress1() );
        assertEquals( "add2", pf.getAddress2() );
        assertEquals( "city", pf.getCity() );
        assertEquals( "12345", pf.getZip() );
        assertEquals( "1234567890", pf.getPhone() );
        assertEquals( "dying", pf.getCauseOfDeath() );
        assertNull( pf.getMother() );
        assertNull( pf.getFather() );
        assertEquals( "AK", pf.getState() );
        assertEquals( "Male", pf.getGender() );
        assertEquals( "AB-", pf.getBloodType() );
        assertEquals( "African American", pf.getEthnicity() );

        final Patient np = new Patient( pf );
        assertEquals( "Sat Jan 01 00:00:00 EST 2000", np.getDateOfDeath().getTime().toString() );
    }

    /**
     * Tests the other branches of the patient form constructor
     */
    @Test
    public void testParents () {
        final PatientForm nullpf = new PatientForm( null );
        assertNull( nullpf.getFirstName() );
        final Patient p = new Patient();

        p.setFirstName( "first" );
        final User u = new User( "username", "pass", Role.ROLE_PATIENT, 1 );
        p.setSelf( u );

        final User m = new User( "mom", "pass1", Role.ROLE_PATIENT, 1 );
        final User d = new User( "dad", "pass2", Role.ROLE_PATIENT, 1 );
        p.setMother( m );
        p.setFather( d );

        final PatientForm newpf = new PatientForm( p );

        assertEquals( "mom", newpf.getMother() );
        assertEquals( "dad", newpf.getFather() );

    }
}
