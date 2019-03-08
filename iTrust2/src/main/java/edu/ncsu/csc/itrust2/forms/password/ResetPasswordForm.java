package edu.ncsu.csc.itrust2.forms.password;

/**
 * Form for the reset password page
 *
 * @author jmphipps
 *
 */
public class ResetPasswordForm {
    /** username of user */
    private String name;
    /** code from token email */
    private String code;
    /** new password */
    private String newPass;
    /** repeat new password */
    private String repeat;

    /**
     * Empty hibernate constructor
     */
    public ResetPasswordForm () {

    }

    /**
     * Constructs a ResetPasswordForm with given parameters
     * 
     * @param name
     *            username of the person changing password
     * @param code
     *            code from the email token
     * @param newPass
     *            new password
     * @param repeat
     *            new password repeated
     */
    public ResetPasswordForm ( String name, String code, String newPass, String repeat ) {
        this.setName( name );
        this.setCode( code );
        this.setNewPass( newPass );
        this.setRepeat( repeat );
    }

    /**
     * Gets the username
     *
     * @return the name
     */
    public String getName () {
        return name;
    }

    /**
     * Sets the username
     *
     * @param name
     *            the name to set
     */
    public void setName ( String name ) {
        this.name = name;
    }

    /**
     * Gets the token code
     *
     * @return the code
     */
    public String getCode () {
        return code;
    }

    /**
     * Sets the token code
     *
     * @param code
     *            the code to set
     */
    public void setCode ( String code ) {
        this.code = code;
    }

    /**
     * Gets the new password
     *
     * @return the newPass
     */
    public String getNewPass () {
        return newPass;
    }

    /**
     * Sets the new password
     *
     * @param newPass
     *            the newPass to set
     */
    public void setNewPass ( String newPass ) {
        this.newPass = newPass;
    }

    /**
     * Gets the repeated password
     *
     * @return the repeat
     */
    public String getRepeat () {
        return repeat;
    }

    /**
     * Sets the repeated password
     *
     * @param repeat
     *            the repeat to set
     */
    public void setRepeat ( String repeat ) {
        this.repeat = repeat;
    }

}
