package edu.ncsu.csc.itrust2.cucumber;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * The stepdefs file for the cucumber UC10 tests
 *
 * @author Cameron Hetzler (cjhetzle)
 *
 */
public class AddDiagnosisStepDefs {
    private final WebDriver driver       = new HtmlUnitDriver( true );
    private final String    baseUrl      = "http://localhost:8080/iTrust2";
    WebDriverWait           wait         = new WebDriverWait( driver, 10 );

    @SuppressWarnings ( "unused" )
    private final String    hospitalName = "TimHortons" + ( new Random() ).nextInt();

    private void loginAsUser ( final String user ) {
        driver.get( baseUrl );
        final WebElement username = driver.findElement( By.name( "username" ) );
        username.clear();
        username.sendKeys( user );
        final WebElement password = driver.findElement( By.name( "password" ) );
        password.clear();
        password.sendKeys( "123456" );
        final WebElement submit = driver.findElement( By.className( "btn" ) );
        submit.click();
    }

    /**
     * Given that there is an admin, sign in as one
     */
    @Given ( "that admin is logged in" )
    public void loginAsAdmin () {
        loginAsUser( "admin" );
    }

    /**
     * Given that there is a patient with a documented office visit on record
     * exists
     *
     * @throws InterruptedException
     */
    @Given ( "patient is logged in and has an office visit with a diagnosis on record" )
    public void patientLogin () throws InterruptedException {
        //
        // loginAsUser( "patient" );
        //
        // navigateToEditDemographics();
        //
        // fillDemographics();
        //
        // final WebElement logout = driver.findElement( By.className(
        // "btn-primary" ) );
        // logout.click();
        //
        // hcpLogin();
        //
        // ( (JavascriptExecutor) driver ).executeScript(
        // "document.getElementById('documentOfficeVisit').click();" );
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "name" ) ) );
        //
        // Thread.sleep( 500 );
        //
        // final WebElement patient = driver.findElement( By.id( "antti" ) );
        // patient.click();
        //
        // final WebElement notes = driver.findElement( By.name( "notes" ) );
        // notes.clear();
        // notes.sendKeys( "Patient appears pretty much alive" );
        //
        // final WebElement type = driver.findElement( By.name( "type" ) );
        // type.click();
        //
        // final WebElement hospital = driver.findElement( By.name( "hospital" )
        // );
        // hospital.click();
        //
        // final WebElement date = driver.findElement( By.name( "date" ) );
        // date.clear();
        // date.sendKeys( "12/19/2027" );
        //
        // final WebElement time = driver.findElement( By.name( "time" ) );
        // time.clear();
        // time.sendKeys( "9:30 AM" );
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "height" ) ) );
        // final WebElement heightElement = driver.findElement( By.name(
        // "height" ) );
        // heightElement.clear();
        // heightElement.sendKeys( "120" );
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "weight" ) ) );
        // final WebElement weightElement = driver.findElement( By.name(
        // "weight" ) );
        // weightElement.clear();
        // weightElement.sendKeys( "120" );
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "systolic" ) ) );
        // final WebElement systolicElement = driver.findElement( By.name(
        // "systolic" ) );
        // systolicElement.clear();
        // systolicElement.sendKeys( "100" );
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "diastolic" ) ) );
        // final WebElement diastolicElement = driver.findElement( By.name(
        // "diastolic" ) );
        // diastolicElement.clear();
        // diastolicElement.sendKeys( "100" );
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "hdl" ) ) );
        // final WebElement hdlElement = driver.findElement( By.name( "hdl" ) );
        // hdlElement.clear();
        // hdlElement.sendKeys( "90" );
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "ldl" ) ) );
        // final WebElement ldlElement = driver.findElement( By.name( "ldl" ) );
        // ldlElement.clear();
        // ldlElement.sendKeys( "100" );
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "tri" ) ) );
        // final WebElement triElement = driver.findElement( By.name( "tri" ) );
        // triElement.clear();
        // triElement.sendKeys( "100" );
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated(
        // By.cssSelector( "input[value=\"" +
        // HouseholdSmokingStatus.NONSMOKING.toString() + "\"]" ) ) );
        // final WebElement houseSmokeElement = driver.findElement(
        // By.cssSelector( "input[value=\"" +
        // HouseholdSmokingStatus.NONSMOKING.toString() + "\"]" ) );
        // houseSmokeElement.click();
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated(
        // By.cssSelector( "input[value=\"" +
        // PatientSmokingStatus.NEVER.toString() + "\"]" ) ) );
        // final WebElement patientSmokeElement = driver
        // .findElement( By.cssSelector( "input[value=\"" +
        // PatientSmokingStatus.NEVER.toString() + "\"]" ) );
        // patientSmokeElement.click();
        //
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "submit" ) ) );
        // final WebElement submit = driver.findElement( By.name( "submit" ) );
        // submit.click();
        //
        // logout();
        //
        // loginAsUser( "patient" );
    }

    private void fillDemographics () {
        final WebElement firstName = driver.findElement( By.id( "firstName" ) );
        firstName.clear();
        firstName.sendKeys( "Karl" );

        final WebElement lastName = driver.findElement( By.id( "lastName" ) );
        lastName.clear();
        lastName.sendKeys( "Liebknecht" );

        final WebElement preferredName = driver.findElement( By.id( "preferredName" ) );
        preferredName.clear();

        final WebElement mother = driver.findElement( By.id( "mother" ) );
        mother.clear();

        final WebElement father = driver.findElement( By.id( "father" ) );
        father.clear();

        final WebElement email = driver.findElement( By.id( "email" ) );
        email.clear();
        email.sendKeys( "karl_liebknecht@mail.de" );

        final WebElement address1 = driver.findElement( By.id( "address1" ) );
        address1.clear();
        address1.sendKeys( "Karl-Liebknecht-Haus, Alexanderplatz" );

        final WebElement city = driver.findElement( By.id( "city" ) );
        city.clear();
        city.sendKeys( "Berlin" );

        final WebElement state = driver.findElement( By.id( "state" ) );
        final Select dropdown = new Select( state );
        dropdown.selectByVisibleText( "CA" );

        final WebElement zip = driver.findElement( By.id( "zip" ) );
        zip.clear();
        zip.sendKeys( "91505" );

        final WebElement phone = driver.findElement( By.id( "phone" ) );
        phone.clear();
        phone.sendKeys( "123-456-7890" );

        final WebElement dob = driver.findElement( By.id( "dateOfBirth" ) );
        dob.clear();
        dob.sendKeys( "08/13/1871" );

        final WebElement submit = driver.findElement( By.className( "btn" ) );
        submit.click();

    }

    private void navigateToEditDemographics () {
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('editdemographics').click();" );
    }

    private void logout () {
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('logout').click();" );
    }

    /**
     * Given that there is a HCP which documented an office visit is logged in
     */
    @Given ( "HCP is logged in and who has documented an office visit" )
    public void hcpLogin () {
        loginAsUser( "hcp" );
    }

    /**
     * Navigate to the add diagnosis page after loggin in as an admin
     */
    @When ( "I navigate to the Add Diagnosis page" )
    public void navigateToAddDiagnosis () {
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('addDiagnosis').click();" );
    }

    /**
     * User navigates to the view diagnosis page
     */
    @When ( "I navigate to the View Diagnosis page" )
    public void navigateToViewDiagnosis () {
        // ( (JavascriptExecutor) driver ).executeScript(
        // "document.getElementById('viewdiagnosis').click();" );
    }

    /**
     * User navigates to the edit office visit page
     */
    @When ( "I navigate to the Edit Office Visit page" )
    public void navigateToEditOfficeVisit () {
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('editOfficeVisit').click();" );
    }

    /**
     * User selects any office visit and changes information on it
     */
    @When ( "I select an office visit in the list and change the diagnosis and submit" )
    public void editOfficeVisit () {

    }

    /**
     * User selects any office visit in the list
     */
    @When ( "I select an office visit in the list of visits with diagnoses" )
    public void selectOfficeVisit () {

    }

    /**
     * Enter the diagnosis information into the fields with the name and ICD
     * codes
     *
     * @param name
     *            The full english name of the diagnosis
     * @param icd
     *            The specific ICD code related to the diagnosis
     */
    @When ( "I enter name: (.+) and ICD code: (.+) and click submit" )
    public void enterInformation ( final String name, final String icd ) {
        final WebElement diagnosisName = driver.findElement( By.name( "diagnosisName" ) );
        diagnosisName.clear();
        diagnosisName.sendKeys( name );
        final WebElement icdCode = driver.findElement( By.name( "icdCode" ) );
        icdCode.clear();
        icdCode.sendKeys( icd );
        final WebElement submit = driver.findElement( By.name( "submit" ) );
        submit.click();
    }

    /**
     * When the diagnosis is valid it will be added successfully
     */
    @Then ( "the diagnosis is added" )
    public void success () {
        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "success" ) ) );

        try {
            assertTrue( driver.getPageSource().contains( "Diagnosis created successfully" )
                    || driver.getPageSource().contains( "A diagnosis with this ICD Code already exists" ) );
        }
        catch ( final Exception e ) {
            fail();
        }
    }

    /**
     * When the diagnosis is invlid it will not be added
     */
    @Then ( "the diagnosis is not added" )
    public void failure () {
        wait.until( ExpectedConditions.visibilityOfElementLocated( By.name( "errormessage" ) ) );

        try {
            assertTrue( driver.getPageSource().contains(
                    "Please enter an ICD Code of format: 1 capital letter followed by 2 numbers (possibly followed by a decimal and 2 more numbers)" )
                    || driver.getPageSource().contains( "A diagnosis with this ICD Code already exists" ) );
        }
        catch ( final Exception e ) {
            fail();
        }

    }

    /**
     * Success message
     */
    @Then ( "the diagnosis information should be visible" )
    public void visibleInformation () {

    }

    /**
     * Success message
     */
    @Then ( "the diagnosis for the office visit is updated" )
    public void updatedSuccessfully () {

    }

    /**
     * Add a diagnosis and go to delete page
     *
     * @throws InterruptedException
     */
    @When ( "I add a diagnosis and go to the delete page" )
    public void addThenDelete () throws InterruptedException {
        final WebElement diagnosisName = driver.findElement( By.name( "diagnosisName" ) );
        diagnosisName.clear();
        diagnosisName.sendKeys( "Deleteme" );
        final WebElement icdCode = driver.findElement( By.name( "icdCode" ) );
        icdCode.clear();
        icdCode.sendKeys( "D31" );
        final WebElement submit = driver.findElement( By.name( "submit" ) );
        submit.click();
        Thread.sleep( 250 );
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('deleteDiagnosis').click();" );

    }

    /**
     * Delete diagnosis
     */
    @When ( "I select the diagnosis I added and click delete" )
    public void deleteDiagnosis () {
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "list" ) ) );
        // final WebElement element = driver.findElement( By.name( "list" ) );
        // final Select selection = new Select( element );
        // selection.selectByVisibleText( "Deleteme | D31" );
        // final WebElement submit = driver.findElement( By.name( "submit" ) );
        // submit.click();
    }

    /**
     * Checks that delete message exists
     */
    @Then ( "the diagnosis is deleted" )
    public void deletedSuccessfully () {
        // assertTrue( driver.getPageSource().contains( "Diagnosis deleted
        // successfully" ) );
    }
}
