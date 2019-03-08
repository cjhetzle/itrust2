package edu.ncsu.csc.itrust2.formtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc.itrust2.forms.admin.DeleteDiagnosisForm;
import edu.ncsu.csc.itrust2.forms.admin.DeleteHospitalForm;
import edu.ncsu.csc.itrust2.forms.admin.DeleteUserForm;
import edu.ncsu.csc.itrust2.forms.patient.AppointmentForm;

/**
 * Tests a bunch of form objects for coverage
 *
 * @author jmphipps
 *
 */
public class NewFormTest {

    /**
     * Tests the delete user form
     */
    @Test
    public void testDelUserForm () {
        final DeleteUserForm duf = new DeleteUserForm();
        duf.setConfirm( "confirm" );
        duf.setName( "name" );
        assertEquals( "name", duf.getName() );
        assertEquals( "confirm", duf.getConfirm() );

    }

    /**
     * Tests the delete hospital form
     */
    @Test
    public void testDelHospitalForm () {
        final DeleteHospitalForm dhf = new DeleteHospitalForm();
        dhf.setConfirm( "confirm" );
        dhf.setName( "name" );
        assertEquals( "name", dhf.getName() );
        assertEquals( "confirm", dhf.getConfirm() );

    }

    /**
     * Tests the delete diagnosis form
     */
    @Test
    public void testDelDiagForm () {
        final DeleteDiagnosisForm ddf = new DeleteDiagnosisForm();
        ddf.setConfirm( "confirm" );
        ddf.setIcdCode( "name" );
        assertEquals( "name", ddf.getIcdCode() );
        assertEquals( "confirm", ddf.getConfirm() );
    }

    /**
     * Tests the appointment form
     */
    @Test
    public void testApptForm () {
        final AppointmentForm af = new AppointmentForm();
        af.setAppointment( "appointment" );
        af.setAction( "action" );
        assertEquals( "appointment", af.getAppointment() );
        assertEquals( "action", af.getAction() );
    }

}
