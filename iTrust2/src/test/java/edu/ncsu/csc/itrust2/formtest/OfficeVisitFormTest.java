package edu.ncsu.csc.itrust2.formtest;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import edu.ncsu.csc.itrust2.forms.admin.HospitalForm;
import edu.ncsu.csc.itrust2.forms.hcp.OfficeVisitForm;
import edu.ncsu.csc.itrust2.models.enums.AppointmentType;
import edu.ncsu.csc.itrust2.models.enums.HouseholdSmokingStatus;
import edu.ncsu.csc.itrust2.models.enums.PatientSmokingStatus;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.enums.State;
import edu.ncsu.csc.itrust2.models.persistent.AppointmentRequest;
import edu.ncsu.csc.itrust2.models.persistent.Hospital;
import edu.ncsu.csc.itrust2.models.persistent.OfficeVisit;
import edu.ncsu.csc.itrust2.models.persistent.User;

/**
 * Tests OfficeVisitForm and OfficeVisit
 *
 * @author jmphipps
 *
 */
public class OfficeVisitFormTest {
    OfficeVisit        ov  = new OfficeVisit();
    final User         u   = new User( "username", "pass", Role.ROLE_PATIENT, 1 );
    final User         hcp = new User( "hcp", "pass2", Role.ROLE_HCP, 1 );
    Hospital           h   = new Hospital( "h", "add", "12345", "AK" );
    AppointmentRequest ap  = new AppointmentRequest();

    /**
     * Tests hospital form creation from hospital I put this here because
     * another class for a single simple test seems unnecessary
     */
    @Test
    public void testHospital () {
        final HospitalForm hf = new HospitalForm( h );
        assertEquals( "h", hf.getName() );
        assertEquals( "add", h.getAddress() );
        assertEquals( "12345", h.getZip() );
        assertEquals( State.AK, h.getState() );
    }

    /**
     * Tests getters and setters for office visit object
     */
    @Test
    public void testGettersAndSettersOV () {
        assertEquals( "add", h.getAddress() );
        assertEquals( "12345", h.getZip() );

        ov.setPatient( u );
        assertEquals( "username", ov.getPatient().getUsername() );
        ov.setHcp( hcp );
        assertEquals( "hcp", ov.getHcp().getUsername() );
        ov.setId( 5L );
        assertEquals( 5L, (long) ov.getId() );
        ov.setType( AppointmentType.GENERAL_CHECKUP );
        assertEquals( AppointmentType.GENERAL_CHECKUP, ov.getType() );
        ov.setHospital( h );
        assertEquals( "h", ov.getHospital().getName() );
        ov.setNotes( "notes" );
        assertEquals( "notes", ov.getNotes() );
        ap.setComments( "comment" );
        ov.setAppointment( ap );
        assertEquals( "comment", ov.getAppointment().getComments() );

    }

    /**
     * Tests the form object
     *
     * @throws ParseException
     */

    @Test
    public void testOVForm () throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy hh:mm aaa" );
        final Date parsedDate = sdf.parse( "01/01/2000" + " " + "02:02 am" );
        final Calendar c = Calendar.getInstance();

        c.setTime( parsedDate );
        ov.setDate( c );

        ov.setPatient( u );
        ov.setHcp( hcp );
        ov.setId( 5L );
        ov.setType( AppointmentType.GENERAL_CHECKUP );
        ov.setHospital( h );
        ov.setNotes( "notes" );
        ap.setComments( "comment" );
        ov.setAppointment( ap );
        ov.setDiagnosis( "J10" );
        assertEquals( "J10", ov.getDiagnosis().getIcdCode() );

        final OfficeVisitForm ovf = new OfficeVisitForm( ov );
        ovf.setId( "id" );
        ovf.setPreScheduled( "true" );
        ovf.setWeight( (float) 75.0 );
        ovf.setHeight( (float) 80.2 );
        ovf.setHeadCircumference( (float) 35 );
        ovf.setDiastolic( 75 );
        ovf.setSystolic( 75 );
        ovf.setHdl( 75 );
        ovf.setLdl( 75 );
        ovf.setTri( 101 );
        ovf.setHouseSmokingStatus( HouseholdSmokingStatus.INDOOR );
        ovf.setPatientSmokingStatus( PatientSmokingStatus.CURRENT_BUT_UNKNOWN );
        assertEquals( hcp.getUsername(), ovf.getHcp() );
        assertEquals( "75", ovf.getHdl().toString() );
        assertEquals( "101", ovf.getTri().toString() );

    }

    /**
     * Test the officevisit constructor that uses an office visit form as a
     * parameter
     *
     * @throws ParseException
     */
    /**
     * @Test public void testConstructor () throws ParseException { final
     *       SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy hh:mm aaa"
     *       ); final Date parsedDate = sdf.parse( "01/01/2000" + " " + "02:02
     *       am" ); final Calendar c = Calendar.getInstance(); c.setTime(
     *       parsedDate ); ov.setDate( c ); ov.setPatient( u ); ov.setHcp( hcp
     *       ); ov.setId( 5L ); ov.setType( AppointmentType.GENERAL_CHECKUP );
     *       ov.setHospital( h ); ov.setNotes( "notes" ); ap.setComments(
     *       "comment" ); ov.setAppointment( ap );
     *
     *       final OfficeVisitForm ovf = new OfficeVisitForm( ov ); ovf.setId(
     *       "id" ); ovf.setPreScheduled( "True" );
     *
     *       try {
     * @SuppressWarnings ( "unused" ) final OfficeVisit newOV = new OfficeVisit(
     *                   ovf ); } catch ( final IllegalArgumentException e ) {
     *                   assertEquals( "Marked as preschedule but no match can
     *                   be foundjava.lang.IndexOutOfBoundsException: Index: 0,
     *                   Size: 0", e.getMessage() ); } }
     */
}
