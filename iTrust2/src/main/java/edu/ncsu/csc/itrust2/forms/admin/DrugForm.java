package edu.ncsu.csc.itrust2.forms.admin;

import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Form for drugs to be processed from front end
 *
 * @author Nicholas Luther
 */
public class DrugForm {

    /**
     * The name of the drug
     */
    @NotEmpty
    @Length(max = 255)
    private String name;

    /**
     * The corresponding national drug code
     */
    @NotEmpty
    @Pattern(regexp = "[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9]")
    @Id
    private String ndc;

    /**
     * Blank constructor for AngularJS
     */
    public DrugForm() {
    }

    /**
     * Retrieve the name of the drug
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the drug
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieve the national drug code
     * @return the national drug code
     */
    public String getNdc() {
        return ndc;
    }

    /**
     * Set the national drug code
     * @param ndc the national drug code
     */
    public void setNdc(String ndc) {
        this.ndc = ndc;
    }
}
