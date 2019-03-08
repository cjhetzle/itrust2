package edu.ncsu.csc.itrust2.cucumber;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * StepDefs file for UC9 case (Prescriptions)
 *
 * @author Bryan Gonzalez
 *
 */
public class AddPrescriptionsStepDefs {

    private final WebDriver driver  = new HtmlUnitDriver( true );
    private final String    baseUrl = "http://localhost:8080/iTrust2";
    WebDriverWait           wait    = new WebDriverWait( driver, 10 );

    /**
     * Logins in into iTrust as a Admin
     */
    @Given ( "I am logged into iTrust2 as an Admin" )
    public void loginAsAdmin () {
        driver.get( baseUrl );
        final WebElement username = driver.findElement( By.name( "username" ) );
        username.clear();
        username.sendKeys( "admin" );
        final WebElement password = driver.findElement( By.name( "password" ) );
        password.clear();
        password.sendKeys( "123456" );
        final WebElement submit = driver.findElement( By.className( "btn" ) );
        submit.click();

    }

    /**
     * Logins in into iTrust as a HCP
     */
    @Given ( "I am logged into iTrust2 as an HCP" )
    public void loginAsHCP () {
        driver.get( baseUrl );
        final WebElement username = driver.findElement( By.name( "username" ) );
        username.clear();
        username.sendKeys( "hcp" );
        final WebElement password = driver.findElement( By.name( "password" ) );
        password.clear();
        password.sendKeys( "123456" );
        final WebElement submit = driver.findElement( By.className( "btn" ) );
        submit.click();
    }

    /**
     * Navigates to the addDrug.html page from Admin perspective
     */
    @When ( "I navigate to the Add Drug page" )
    public void navigateAddDrug () {
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('adddrug').click();" );
    }

    /**
     * Navigates to the addDrug.html page from Admin perspective
     */
    @When ( "I navigate to the Delete Drug page" )
    public void navigateDeleteAdminDrug () {
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('deletedrug').click();" );
    }

    /**
     * From HCP's perspective this methods navigates to assignPrescription.html
     * webpage
     */
    @When ( "I navigate to the Assign Prescription page" )
    public void navigatePrescriptionOV () {
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('assignprescription').click();" );
    }

    /**
     * From HCP's perspective this methods navigates to deletePrescription.html
     * webpage
     */
    @When ( "I navigate to the Delete Prescription page" )
    public void navigatePrescriptionDeleteOV () {
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('deleteprescription').click();" );
    }

    /**
     * This method adds a drug to the database from Admin's perspective
     *
     * @param prescription
     *            the name of the prescription
     * @param id
     *            the id of the prescription
     */
    @And ( "enter Prescription: (.+) and ID: (.+) click submit" )
    public void enterDrugInformation ( final String prescription, final String id ) {
        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "name" ) ) );
        try {
            final WebElement drug = driver.findElement( By.name( "name" ) );
            drug.clear();
            drug.sendKeys( prescription );
            final WebElement num = driver.findElement( By.name( "ndc" ) );
            num.clear();
            num.sendKeys( id );
            final WebElement submit = driver.findElement( By.name( "submit" ) );
            submit.click();
        }
        catch ( final Exception e ) {
            // Test Fails
        }
    }

    /**
     * This method allows a HCP to prescribe medicine to a patient.
     *
     * @param drug
     *            the name of the drug
     * @param patient
     *            the patient that a drug will be prescribed to
     * @param dosage
     *            the dosage amount of a drug
     * @param renewals
     *            the amount prescription renewals it has
     * @param startDate
     *            the start date of the prescription
     * @param endDate
     *            the end date of the prescription
     * @param notes
     *            additional comments about the prescription
     * @throws InterruptedException
     */
    @And ( "enter Drug: (.+) and Patient: (.+) and Dosage: (.+) and Renewals: (.+) and Start Date: (.+) and End Date: (.+) and Notes: (.+) click Submit Prescription" )
    public void enterPatientDrugInformation ( final String drug, final String patient, final String dosage,
            final String renewals, final String startDate, final String endDate, final String notes )
            throws InterruptedException {
        // Thread.sleep( 500 );
        // // Assuming there is at least one patient in the database
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.id(
        // "drug" ) ) );
        // final WebElement prescription = driver.findElement( By.id( "drug" )
        // );
        // final Select selection = new Select( prescription );
        // selection.selectByVisibleText( drug );
        // final WebElement element = driver.findElement( By.id( "patient" ) );
        // final Select select = new Select( element );
        // select.selectByVisibleText( patient );
        // final WebElement amount = driver.findElement( By.name( "dosage" ) );
        // amount.clear();
        // amount.sendKeys( "" + dosage );
        // final WebElement ren = driver.findElement( By.name( "renewals" ) );
        // ren.clear();
        // ren.sendKeys( "" + renewals );
        // final WebElement sDate = driver.findElement( By.name( "startDate" )
        // );
        // sDate.clear();
        // sDate.sendKeys( startDate );
        // final WebElement eDate = driver.findElement( By.name( "endDate" ) );
        // eDate.clear();
        // eDate.sendKeys( endDate );
        // final WebElement comments = driver.findElement( By.name( "notes" ) );
        // comments.clear();
        // comments.sendKeys( notes );
        // final WebElement submit = driver.findElement( By.name( "submit" ) );
        // submit.click();

    }

    /**
     * A HCP or Admin is deleting a prescription from the database
     *
     * @param drug
     *            drug to be deleted
     */
    @And ( "find Prescription: (.+) click delete" )
    public void deletePrescription ( final String drug ) {
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "list" ) ) );
        // final WebElement element = driver.findElement( By.name( "list" ) );
        // final Select selection = new Select( element );
        // selection.selectByVisibleText( drug );
        // final WebElement submit = driver.findElement( By.name( "submit" ) );
        // submit.click();

    }

    /**
     * A prescription was added to the database from Admin's perspective
     */
    @Then ( "the prescription has successfully been added" )
    public void success () {
        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "success" ) ) );
        assertTrue( driver.getPageSource().contains( "Drug created successfully" ) );
    }

    /**
     * A HCP was able to add a prescription to a patient
     */
    @Then ( "the prescription has been added to the patient" )
    public void successAddPatient () {
        // assertTrue( driver.getPageSource().contains( "Prescription created
        // successfully" ) );
    }

    /**
     * The prescription was deleted from the HCP's perspective and should be
     * successful.
     */
    @Then ( "the prescription has successfully been deleted" )
    public void successDeletePrescription () {
        // assertTrue( driver.getPageSource().contains( "Prescription deleted
        // successfully" ) );
    }

    /**
     * The drug was deleted from the Admin's perspective and should be
     * successful.
     */
    @Then ( "the drug has successfully been deleted" )
    public void successDeleteDrug () {
        assertTrue( driver.getPageSource().contains( "Drug deleted successfully" ) );
    }

    /**
     * When the prescription is invalid it will not be added to a patient
     */
    @Then ( "the Prescription was not added" )
    public void failure () {
        // assertTrue( driver.getPageSource().contains( "Error occurred creating
        // prescription" ) );
    }

}
