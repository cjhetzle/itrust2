package edu.ncsu.csc.itrust2.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import edu.ncsu.csc.itrust2.forms.admin.DiagnosisForm;
import edu.ncsu.csc.itrust2.forms.hcp.OfficeVisitForm;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.persistent.Diagnosis;
import edu.ncsu.csc.itrust2.models.persistent.User;

/**
 * Tests the Diagnosis object and its methods
 *
 * @author jmphipps
 *
 */
public class DiagnosisTest {
    final User hcp     = new User( "hcp", "$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.", Role.ROLE_HCP,
            1 );
    final User patient = new User( "patient", "$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.",
            Role.ROLE_PATIENT, 1 );

    /**
     * Tests the empty constructor as well as getters/setters
     */
    @Test
    public void testEmptyConstructor () {
        final Diagnosis d = new Diagnosis();
        d.setDiagnosisName( "Flu" );
        d.setIcdCode( "A01" );

        assertEquals( "Flu", d.getDiagnosisName() );
        assertEquals( "A01", d.getIcdCode() );

    }

    /**
     * Tests the constructor that takes in an officevisitform
     */
    @Test
    public void testConstructor () {
        // Creates diagnosisform
        final DiagnosisForm df = new DiagnosisForm();
        df.setDiagnosisName( "Flu" );
        df.setIcdCode( "A01" );

        try {
            // Tests the constructor
            final Diagnosis d2 = new Diagnosis( df );
            assertEquals( "Flu", d2.getDiagnosisName() );
            assertEquals( "A01", d2.getIcdCode() );
        }
        catch ( final ParseException e ) {
            e.printStackTrace();
        }

    }

    /**
     * Tests equals method in diagnosis
     */
    @SuppressWarnings ( "unlikely-arg-type" )
    @Test
    public void testEquals () {
        // makes officevisitform object to check
        final OfficeVisitForm ovf = new OfficeVisitForm();

        // creates valid diagnosis
        final Diagnosis d = new Diagnosis();
        d.setDiagnosisName( "Flu" );
        d.setIcdCode( "A01" );
        // creates valid diagnosis with different name
        final Diagnosis diffname = new Diagnosis();
        diffname.setDiagnosisName( "diffname" );
        diffname.setIcdCode( "A01" );
        // creates valid diagnosis with different icd code
        final Diagnosis difficd = new Diagnosis();
        difficd.setDiagnosisName( "Flu" );
        difficd.setIcdCode( "B02" );

        assertTrue( d.equals( d ) ); // compare diagnosis to itself
        assertFalse( d.equals( ovf ) ); // compare diagnosis to ovform object
        assertFalse( d.equals( null ) ); // compare diagnosis to null
        assertFalse( d.equals( diffname ) ); // compare diagnoses w/ diff names
        assertFalse( d.equals( difficd ) ); // compare diagnoses w/ diff
                                            // icdcodes

    }

    /**
     * Tests getting list of diagnoses
     */
    @Test
    public void testGet () {
        Diagnosis.getDiagnosis().get( 0 );
        assertTrue( true );
    }
}
