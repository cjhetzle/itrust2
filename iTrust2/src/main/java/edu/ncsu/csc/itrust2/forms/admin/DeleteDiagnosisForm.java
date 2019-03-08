package edu.ncsu.csc.itrust2.forms.admin;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Form used for an Admin to delete a Diagnosis stored in the system
 *
 * @author Kai Presler-Marshall modified by jmphipps
 *
 */
public class DeleteDiagnosisForm {

    /**
     * ICD Code of the Diagnosis to delete
     */
    private String icdCode;

    /**
     * Whether the user selected to confirm their action
     */
    @NotEmpty
    private String confirm;

    /**
     * Retrieve the ICD Code from the form
     *
     * @return The ICD Code of the DiagnosisForm
     */
    public String getIcdCode () {
        return icdCode;
    }

    /**
     * Sets the ICD Code of the Diagnosis to delete
     *
     * @param icdCode
     *            The ICD Code to set
     */
    public void setIcdCode ( final String icdCode ) {
        this.icdCode = icdCode;
    }

    /**
     * Retrieve whether the user confirmed the delete action
     *
     * @return Confirmation
     */
    public String getConfirm () {
        return confirm;
    }

    /**
     * Whether or not the user confirmed the action
     *
     * @param confirm
     *            Confirmation
     */
    public void setConfirm ( final String confirm ) {
        this.confirm = confirm;
    }
}
