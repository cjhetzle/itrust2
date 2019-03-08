package edu.ncsu.csc.itrust2.formtest;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import edu.ncsu.csc.itrust2.forms.patient.AppointmentRequestForm;
import edu.ncsu.csc.itrust2.models.enums.AppointmentType;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.enums.Status;
import edu.ncsu.csc.itrust2.models.persistent.AppointmentRequest;
import edu.ncsu.csc.itrust2.models.persistent.User;

/**
 * Tests AppointmentRequest and AppointmentRequestForm
 *
 * @author jmphipps
 *
 */
public class AppointmentTest {

    /**
     * Tests AppointmentRequest empty constructor Also tests constructing
     * AppointmentRequestForm from the appointment request Also tests
     * constructing AppointmentRequest from AppointmentRequestForm
     *
     * @throws ParseException
     */
    @Test
    public void testARConstructor () throws ParseException {
        final User p = new User( "name", "pass", Role.ROLE_PATIENT, 1 );
        final User hcp = new User( "hcp", "pass", Role.ROLE_HCP, 1 );

        final AppointmentRequest ar = new AppointmentRequest();
        ar.setStatus( Status.APPROVED );
        ar.setPatient( p );
        ar.setHcp( hcp );
        ar.setComments( "c" );
        ar.setType( AppointmentType.GENERAL_CHECKUP );
        final DateFormat df = new SimpleDateFormat( "dd/MM/yy HH:mm:ss" );
        final Calendar calobj = Calendar.getInstance();
        df.format( calobj.getTime() );
        ar.setDate( calobj );

        assertEquals( "c", ar.getComments() );
        assertEquals( Status.APPROVED, ar.getStatus() );
        assertEquals( p, ar.getPatient() );
        assertEquals( hcp, ar.getHcp() );
        assertEquals( AppointmentType.GENERAL_CHECKUP, ar.getType() );
        assertEquals( calobj, ar.getDate() );

        final AppointmentRequestForm arf = new AppointmentRequestForm( ar );
        assertEquals( p.getId(), arf.getPatient() );
        assertEquals( hcp.getId(), arf.getHcp() );
        assertEquals( "c", arf.getComments() );
        assertEquals( AppointmentType.GENERAL_CHECKUP.toString(), arf.getType() );
        assertEquals( Status.APPROVED.toString(), arf.getStatus() );

        // Tests creating AR from ARF
        arf.setDate( "12/12/2018" );
        final AppointmentRequest ar2 = new AppointmentRequest( arf );
        assertEquals( "c", ar2.getComments() );

    }

    /**
     * Tests ARF empty constructor
     */
    @Test
    public void testARFEmptyConstructor () {
        final AppointmentRequestForm arf = new AppointmentRequestForm();
        arf.setId( "id" );
        assertEquals( "id", arf.getId() );
    }
}
