package edu.ncsu.csc.itrust2.forms.hcp;

import edu.ncsu.csc.itrust2.models.persistent.Drug;
import edu.ncsu.csc.itrust2.models.persistent.Patient;

/**
 * Form to collect and store data from the front end
 *
 * @author Nicholas Luther
 */
public class PrescriptionForm {

    /**
     * Drug to prescribe
     */
    private Drug drug;

    /**
     * Patient assigned the prescription
     */
    private Patient patient;

    /**
     * HCP that assigned the prescription
     */
    private String hcp;

    /**
     * Number of renewals
     */
    private Integer renewals;

    /**
     * Start date as a string
     */
    private String startDate;

    /**
     * End date as a string
     */
    private String endDate;

    /**
     * Dosage in milligrams
     */
    private Integer dosage;

    /**
     * Doctors notes
     */
    private String notes;

    /**
     * Empty constructor for Hibernate
     */
    public PrescriptionForm() {
    }

    /**
     * Retrieves the drug
     *
     * @return the drug
     */
    public Drug getDrug() {
        return drug;
    }

    /**
     * Set the drug
     *
     * @param drug the drug to set
     */
    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    /**
     * Retrieves the number of renewals
     *
     * @return the number of renewals
     */
    public int getRenewals() {
        return renewals;
    }

    /**
     * Set the number of renewals
     *
     * @param renewals the number of renewals
     */
    public void setRenewals(int renewals) {
        this.renewals = renewals;
    }

    /**
     * Retrieve the start date
     *
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Set the start date
     *
     * @param startDate the start date of the prescription
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieve the end date
     *
     * @return the end date of the prescription
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Set the end date
     *
     * @param endDate the end date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieve the doctors notes
     *
     * @return the doctors notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the doctors notes
     *
     * @param notes the doctors notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Retrieve the dosage
     *
     * @return the dosage
     */
    public int getDosage() {
        return dosage;
    }

    /**
     * Set the dosage
     *
     * @param dosage the dosage
     */
    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    /**
     * Retrieve the patient
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Retrieves the hcp's username
     *
     * @return the username
     */
    public String getHcp() {
        return hcp;
    }

    /**
     * Set the hcp's username
     *
     * @param hcp the username of the hcp
     */
    public void setHcp(String hcp) {
        this.hcp = hcp;
    }

    /**
     * Set the patient
     *
     * @param patient the patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
