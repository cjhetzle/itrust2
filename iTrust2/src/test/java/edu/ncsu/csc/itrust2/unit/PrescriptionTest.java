package edu.ncsu.csc.itrust2.unit;

import edu.ncsu.csc.itrust2.forms.admin.DrugForm;
import edu.ncsu.csc.itrust2.forms.hcp.PrescriptionForm;
import edu.ncsu.csc.itrust2.forms.hcp_patient.PatientForm;
import edu.ncsu.csc.itrust2.models.enums.BloodType;
import edu.ncsu.csc.itrust2.models.enums.Ethnicity;
import edu.ncsu.csc.itrust2.models.enums.Gender;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.enums.State;
import edu.ncsu.csc.itrust2.models.persistent.Drug;
import edu.ncsu.csc.itrust2.models.persistent.Patient;
import edu.ncsu.csc.itrust2.models.persistent.Prescription;
import edu.ncsu.csc.itrust2.models.persistent.User;
import java.text.ParseException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * Tests the new Prescription and Drug classes
 *
 * @author Nicholas Luther
 */
public class PrescriptionTest {

    /**
     * Tests Prescription
     */
    @Test
    public void testPrescription() {
        DrugForm df = new DrugForm();
        df.setName("Drug1");
        df.setNdc("4444-4444-22");
        Drug d = new Drug(df);

        assertEquals(d.getName(), "Drug1");
        assertEquals(d.getId(), d.getNdc());
        assertEquals(d.getId(), "4444-4444-22");

        final User patient = new User("patientTestPatient", "123456", Role.ROLE_PATIENT, 1);
        patient.save();
        final PatientForm form = new PatientForm();
        form.setFirstName("patient");
        form.setPreferredName("patient");
        form.setLastName("mcpatientface");
        form.setEmail("bademail@ncsu.edu");
        form.setAddress1("Some town");
        form.setAddress2("Somewhere");
        form.setCity("placecity");
        form.setState(State.AL.getName());
        form.setZip("27606");
        form.setPhone("111-111-1111");
        form.setDateOfBirth("01/01/1901");
        form.setDateOfDeath("01/01/2001");
        form.setCauseOfDeath("Hit by a truck");
        form.setBloodType(BloodType.ABPos.getName());
        form.setEthnicity(Ethnicity.Asian.getName());
        form.setGender(Gender.Male.getName());
        form.setSelf(patient.getUsername());
        Patient testPatient;
        try {
            testPatient = new Patient(form);
            testPatient.save();
        } catch (ParseException ex) {
            fail();
        }

        PrescriptionForm pf = new PrescriptionForm();
        pf.setDrug(d);
        pf.setPatient(Patient.getPatient("patientTestPatient"));
        pf.setDosage(100);
        pf.setStartDate("01/01/2017");
        pf.setEndDate("01/01/2018");
        pf.setRenewals(0);
        pf.setNotes("Notes");
        try {
            Prescription p = new Prescription(pf);
            assertEquals(p.getDosage(), 100);
            assertEquals(p.getDrug(), d);
            assertEquals(p.getPatient(), Patient.getPatient("patientTestPatient").getSelf());
            assertEquals(p.getRenewals(), 0);
            assertEquals(p.getNotes(), "Notes");
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

    }
}
