package edu.ncsu.csc.itrust2.formtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc.itrust2.forms.password.ChangePasswordForm;
import edu.ncsu.csc.itrust2.forms.password.ResetPasswordForm;

/**
 * Tests the forms for resetting and changing passwords
 *
 * @author jmphipps
 *
 */
public class ChangeAndResetPassFormTest {

    /**
     * Tests the empty constructor and getters and setters for change password
     * form
     */
    @Test
    public void testChangeGetAndSet () {
        final ChangePasswordForm cpf = new ChangePasswordForm();
        cpf.setOld( "old" );
        cpf.setNewPass( "new" );
        cpf.setRepeat( "repeat" );
        assertEquals( "old", cpf.getOld() );
        assertEquals( "new", cpf.getNewPass() );
        assertEquals( "repeat", cpf.getRepeat() );

    }

    /**
     * Tests the non empty constructor for change password form
     */
    @Test
    public void testChangeConstructor () {
        final ChangePasswordForm cpf = new ChangePasswordForm( "old", "new", "repeat" );
        assertEquals( "old", cpf.getOld() );
        assertEquals( "new", cpf.getNewPass() );
        assertEquals( "repeat", cpf.getRepeat() );
    }

    /**
     * Tests the empty constructor and getters/setters for reset password form
     */
    @Test
    public void testResetGetAndSet () {
        final ResetPasswordForm rpf = new ResetPasswordForm();
        rpf.setName( "name" );
        rpf.setCode( "code" );
        rpf.setNewPass( "new" );
        rpf.setRepeat( "repeat" );
        assertEquals( "name", rpf.getName() );
        assertEquals( "code", rpf.getCode() );
        assertEquals( "new", rpf.getNewPass() );
        assertEquals( "repeat", rpf.getRepeat() );
    }

    /**
     * Tests the non empty constructor for reset password form
     */
    @Test
    public void testResetConstructor () {
        final ResetPasswordForm rpf = new ResetPasswordForm( "name", "code", "new", "repeat" );
        assertEquals( "name", rpf.getName() );
        assertEquals( "code", rpf.getCode() );
        assertEquals( "new", rpf.getNewPass() );
        assertEquals( "repeat", rpf.getRepeat() );
    }
}
