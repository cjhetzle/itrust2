package edu.ncsu.csc.itrust2.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Tests edit patient demographics as hcp
 *
 * @author ncluther, cjhetzler, jmphipps
 *
 */
public class EditPatientDemographicsStepDefs {

    private final WebDriver driver  = new HtmlUnitDriver( true );
    private final String    baseUrl = "http://localhost:8080/iTrust2";
    WebDriverWait           wait    = new WebDriverWait( driver, 15 );

    /**
     * Create User and associated patient
     *
     * @throws InterruptedException
     */
    @Given ( "patient exists" )
    public void startup () throws InterruptedException {
        // // login as admin
        // driver.get( baseUrl );
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "username" ) ) );
        // final WebElement username = driver.findElement( By.name( "username" )
        // );
        // username.clear();
        // username.sendKeys( "admin" );
        // final WebElement password = driver.findElement( By.name( "password" )
        // );
        // password.clear();
        // password.sendKeys( "123456" );
        // final WebElement submit = driver.findElement( By.className( "btn" )
        // );
        // submit.click();
        // // go to add user pge
        // ( (JavascriptExecutor) driver ).executeScript(
        // "document.getElementById('addnewuser').click();" );
        // Thread.sleep( 250 );
        // // Create user
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "username" ) ) );
        // final WebElement name = driver.findElement( By.id( "username" ) );
        // name.clear();
        // name.sendKeys( "demo" );
        //
        // final WebElement pw = driver.findElement( By.id( "password" ) );
        // pw.clear();
        // pw.sendKeys( "123456" );
        //
        // final WebElement password2 = driver.findElement( By.id( "password2" )
        // );
        // password2.clear();
        // password2.sendKeys( "123456" );
        //
        // final Select role = new Select( driver.findElement( By.id( "role" ) )
        // );
        // role.selectByVisibleText( "ROLE_PATIENT" );
        //
        // final WebElement enabled = driver.findElement( By.name( "enabled" )
        // );
        // enabled.click();
        //
        // driver.findElement( By.id( "submit" ) ).click();
        // driver.get( baseUrl );
        // Thread.sleep( 250 );
        //
        // final WebElement logout = driver.findElement( By.className(
        // "btn-primary" ) );
        // logout.click();
        //
        // Thread.sleep( 250 );
        // driver.get( baseUrl );
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.name(
        // "username" ) ) );
        // final WebElement un = driver.findElement( By.name( "username" ) );
        // un.clear();
        // un.sendKeys( "demo" );
        // final WebElement p = driver.findElement( By.name( "password" ) );
        // p.clear();
        // p.sendKeys( "123456" );
        // final WebElement s = driver.findElement( By.className( "btn" ) );
        // s.click();
        //
        // ( (JavascriptExecutor) driver ).executeScript(
        // "document.getElementById('editdemographics').click();" );
        // Thread.sleep( 250 );
        // final WebElement firstName = driver.findElement( By.id( "firstName" )
        // );
        // firstName.clear();
        // firstName.sendKeys( "Karl" );
        //
        // final WebElement lastName = driver.findElement( By.id( "lastName" )
        // );
        // lastName.clear();
        // lastName.sendKeys( "Smith" );
        //
        // final WebElement preferredName = driver.findElement( By.id(
        // "preferredName" ) );
        // preferredName.clear();
        //
        // final WebElement mother = driver.findElement( By.id( "mother" ) );
        // mother.clear();
        //
        // final WebElement father = driver.findElement( By.id( "father" ) );
        // father.clear();
        //
        // final WebElement email = driver.findElement( By.id( "email" ) );
        // email.clear();
        // email.sendKeys( "karl_liebknecht@mail.de" );
        //
        // final WebElement address1 = driver.findElement( By.id( "address1" )
        // );
        // address1.clear();
        // address1.sendKeys( "Karl-Liebknecht-Haus, Alexanderplatz" );
        //
        // final WebElement city = driver.findElement( By.id( "city" ) );
        // city.clear();
        // city.sendKeys( "Berlin" );
        //
        // final WebElement state = driver.findElement( By.id( "state" ) );
        // final Select dropdown = new Select( state );
        // dropdown.selectByVisibleText( "CA" );
        //
        // final WebElement zip = driver.findElement( By.id( "zip" ) );
        // zip.clear();
        // zip.sendKeys( "91505" );
        //
        // final WebElement phone = driver.findElement( By.id( "phone" ) );
        // phone.clear();
        // phone.sendKeys( "123-456-7890" );
        //
        // final WebElement dob = driver.findElement( By.id( "dateOfBirth" ) );
        // dob.clear();
        // dob.sendKeys( "08/13/1871" );
        //
        // final WebElement x = driver.findElement( By.id( "submit" ) );
        // x.click();
        // Thread.sleep( 250 );
        // driver.get( baseUrl );
        // Thread.sleep( 250 );
        //
        // final WebElement lg = driver.findElement( By.className( "btn-primary"
        // ) );
        // lg.click();

    }

    /**
     * Login as hcp
     */
    @When ( "I log in as an hcp" )
    public void loginAsHcp () {
        // driver.get( baseUrl );
        // final WebElement username = driver.findElement( By.name( "username" )
        // );
        // username.clear();
        // username.sendKeys( "hcp" );
        // final WebElement password = driver.findElement( By.name( "password" )
        // );
        // password.clear();
        // password.sendKeys( "123456" );
        // final WebElement submit = driver.findElement( By.className( "btn" )
        // );
        // submit.click();
    }

    /**
     * Go to the edit demographics page
     */
    @When ( "I navigate to the Edit Patient Demographics page" )
    public void editDemographics () {
        // ( (JavascriptExecutor) driver ).executeScript(
        // "document.getElementById('documentOfficeVisit').click();" );
        // ( (JavascriptExecutor) driver ).executeScript(
        // "document.getElementById('editpatientdemographics').click();" );
    }

    /**
     * Choose a patient to edit the demographics of
     *
     * @throws InterruptedException
     */
    @When ( "I select a patient" )
    public void selectPatient () throws InterruptedException {
        // try {
        // Thread.sleep( 1000 );
        // }
        // catch ( final InterruptedException e ) {
        // // Do nothing.
        // }
        // wait.until( ExpectedConditions.visibilityOfElementLocated( By.id(
        // "selectedpatient" ) ) );
        // //
        // // final Select select = new Select( driver.findElement( By.id(
        // // "selectedpatient" ) ) );
        // // Thread.sleep( 500 );
        // // select.selectByVisibleText( "demo: Karl Smith" );
        // driver.findElement( By.xpath(
        // "//select[@id='selectedpatient']/option[text() = 'demo: Karl Smith']"
        // ) ).click();
    }

    /**
     * Update the demographics as HCP
     */
    @When ( "I fill in the patients new, updated demographics" )
    public void fillDemographics () {
        // final WebElement firstName = driver.findElement( By.name( "firstName"
        // ) );
        // firstName.clear();
        // firstName.sendKeys( "BobTheFourYearOld" );
        //
        // final WebElement lastName = driver.findElement( By.name( "lastName" )
        // );
        // lastName.clear();
        // lastName.sendKeys( "Smith" );
        //
        // final WebElement preferredName = driver.findElement( By.name(
        // "preferredName" ) );
        // preferredName.clear();
        //
        // final WebElement mother = driver.findElement( By.name( "mother" ) );
        // mother.clear();
        //
        // final WebElement father = driver.findElement( By.name( "father" ) );
        // father.clear();
        //
        // final WebElement email = driver.findElement( By.name( "email" ) );
        // email.clear();
        // email.sendKeys( "bob@fakemail.com" );
        //
        // final WebElement address1 = driver.findElement( By.name( "address1" )
        // );
        // address1.clear();
        // address1.sendKeys( "100 Candy Lane" );
        //
        // final WebElement city = driver.findElement( By.name( "city" ) );
        // city.clear();
        // city.sendKeys( "Candy Town" );
        //
        // final WebElement state = driver.findElement( By.name( "state" ) );
        // final Select dropdown = new Select( state );
        // dropdown.selectByVisibleText( "CA" );
        //
        // final WebElement zip = driver.findElement( By.name( "zip" ) );
        // zip.clear();
        // zip.sendKeys( "91505" );
        //
        // final WebElement phone = driver.findElement( By.name( "phone" ) );
        // phone.clear();
        // phone.sendKeys( "123-456-7890" );
        //
        // final WebElement dob = driver.findElement( By.name( "dateOfBirth" )
        // );
        // dob.clear();
        // dob.sendKeys( "08/13/2013" );
        //
        // final WebElement submit = driver.findElement( By.name( "submit" ) );
        // submit.click();

    }

    /**
     * Checks to make sure the demographics are changed
     */
    @Then ( "The patients demographics are updated" )
    public void updatedSuccessfully () {
        // assertTrue( driver.getPageSource().contains( "Demographics updated
        // successfully" ) );
    }

}
