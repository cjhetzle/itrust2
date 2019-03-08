package edu.ncsu.csc.itrust2.forms.password;

/**
 * Creates form for the change password page
 *
 * @author jmphipps
 *
 */
public class ChangePasswordForm {
    /** old password */
    private String old;
    /** new password */
    private String newPass;
    /** repeat new password */
    private String repeat;

    /**
     * empty hibernate constructor
     */
    public ChangePasswordForm () {

    }

    /**
     * Creates a change password form with given values
     *
     * @param old
     *            password
     * @param newPass
     *            to change the password to
     * @param repeat
     *            must match newPass
     */
    public ChangePasswordForm ( String old, String newPass, String repeat ) {
        this.setOld( old );
        this.setNewPass( newPass );
        this.setRepeat( repeat );
    }

    /**
     * gets old password
     *
     * @return the old
     */
    public String getOld () {
        return old;
    }

    /**
     * sets the old password
     *
     * @param old
     *            the old to set
     */
    public void setOld ( String old ) {
        this.old = old;
    }

    /**
     * gets the new password
     *
     * @return the newPass
     */
    public String getNewPass () {
        return newPass;
    }

    /**
     * sets the new password
     *
     * @param newPass
     *            the newPass to set
     */
    public void setNewPass ( String newPass ) {
        this.newPass = newPass;
    }

    /**
     * gets the repeated new password
     *
     * @return the repeat
     */
    public String getRepeat () {
        return repeat;
    }

    /**
     * sets the repeated new password
     *
     * @param repeat
     *            the repeat to set
     */
    public void setRepeat ( String repeat ) {
        this.repeat = repeat;
    }

}
