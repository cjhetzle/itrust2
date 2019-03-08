package edu.ncsu.csc.itrust2.forms.admin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Form to be turned into a diagnosis
 *
 * @author jmphipps
 *
 */
public class DiagnosisForm {
    /**
     * name of the diagnosis
     */
    @NotEmpty
    @Length ( max = 255 )
    private String diagnosisName;

    /**
     * icd code of the diagnosis
     */
    @NotEmpty
    @Length ( max = 255 )
    private String icdCode;

    /**
     * Empty constructor for hibernate
     */
    public DiagnosisForm () {

    }

    /**
     * Creates a form from the user inputs
     *
     * @param name
     *            of the diagnosis
     * @param icd
     *            code of the diagnosis
     */
    public DiagnosisForm ( String name, String icd ) {
        this.setDiagnosisName( name );
        this.setIcdCode( icd );
    }

    /**
     * Gets the name of the diagnosis
     *
     * @return the name
     */
    public String getDiagnosisName () {
        return diagnosisName;
    }

    /**
     * Sets the name of the diagnosis
     *
     * @param name
     *            the name to set
     */
    public void setDiagnosisName ( String name ) {
        this.diagnosisName = name;
    }

    /**
     * Gets the icd code of the diagnosis
     *
     * @return the icd
     */
    public String getIcdCode () {
        return icdCode;
    }

    /**
     * Sets the icd code of the diagnosis
     *
     * @param icd
     *            the icd to set
     */
    public void setIcdCode ( String icd ) {
        this.icdCode = icd;
    }
}
