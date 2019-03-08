package edu.ncsu.csc.itrust2.cucumber;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * UC11 Acceptance Tests
 *
 * @author jmphipps
 *
 */
public class ChangePasswordStepDefs {
    private final WebDriver driver  = new HtmlUnitDriver( true );
    private final String    baseUrl = "http://localhost:8080/iTrust2";
    WebDriverWait           wait    = new WebDriverWait( driver, 10 );

    /**
     * Logs the patient in and goes to change password page
     *
     * @throws InterruptedException
     */
    @Given ( "patient is logged in and clicks the link to change their password" )
    public void navigateToChangePass () throws InterruptedException {
        driver.get( baseUrl );
        final WebElement username = driver.findElement( By.name( "username" ) );
        username.clear();
        username.sendKeys( "patient" );
        final WebElement password = driver.findElement( By.name( "password" ) );
        password.clear();
        password.sendKeys( "123456" );
        final WebElement submit = driver.findElement( By.className( "btn" ) );
        submit.click();

        Thread.sleep( 250 );
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('changepassword').click();" );
        Thread.sleep( 250 );

    }

    /**
     * Fill out the change pass page
     */
    @When ( "they fill in their old password and their new one twice and submit" )
    public void fillFields () {
        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "oldpass" ) ) );
        final WebElement oldpass = driver.findElement( By.name( "oldpass" ) );
        oldpass.clear();
        oldpass.sendKeys( "123456" );

        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "newpass" ) ) );
        final WebElement newpass = driver.findElement( By.name( "newpass" ) );
        newpass.clear();
        newpass.sendKeys( "123456" );

        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "repeat" ) ) );
        final WebElement repeat = driver.findElement( By.name( "repeat" ) );
        repeat.clear();
        repeat.sendKeys( "123456" );

        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "submit" ) ) );
        final WebElement submit = driver.findElement( By.name( "submit" ) );
        submit.click();
    }

    /**
     * Checks for success message
     */
    @Then ( "their password is successfully changed" )
    public void checkChange () {
        assertTrue( driver.getPageSource().contains( "Password successfully changed." ) );
    }

    /**
     * Improperly fill out password change form
     */
    @When ( "they fill in their old password improperly and their new one twice and submit" )
    public void wrongOldPass () {
        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "oldpass" ) ) );
        final WebElement oldpass = driver.findElement( By.name( "oldpass" ) );
        oldpass.clear();
        oldpass.sendKeys( "12345" );

        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "newpass" ) ) );
        final WebElement newpass = driver.findElement( By.name( "newpass" ) );
        newpass.clear();
        newpass.sendKeys( "abcdef" );

        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "repeat" ) ) );
        final WebElement repeat = driver.findElement( By.name( "repeat" ) );
        repeat.clear();
        repeat.sendKeys( "abcdef" );

        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "submit" ) ) );
        final WebElement submit = driver.findElement( By.name( "submit" ) );
        submit.click();
    }

    /**
     * Check that password wasn't changed
     */
    @Then ( "their password is not successfully changed" )
    public void checkUnchanged () {
        final WebElement message = driver.findElement( By.name( "errormessage" ) );
        assertTrue( !message.getText().contains( "Password successfully changed." ) );

    }

}
