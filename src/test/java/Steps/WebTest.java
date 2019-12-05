package Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebTest extends Base
{
    private String actualPrice = "";
    private String expectedPrice = "";
    private static Logger logger = Logger.getLogger(WebTest.class);

    static Fairy fairy = Fairy.create();
    Person person = fairy.person();

    @Given("^User navigates to digiturk website$")
    public void userNavigatesToDigiturkWebsite()
    {
        geturl();
        logger.info("Opening WebSite..");
    }

    @And("^User clicks on the subscribe button$")
    public void userClicksOnTheSubscribeButton()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@id=\"loading\"]")));
        WebDriverWait wait = new WebDriverWait(driver, 15);
        findElementClick("Subscribe",Pather.name);
        logger.info("Clicking Subscribe Button..");
    }

    @And("^Choose Monthly Pass with One Week Free Trial package$")
    public void chooseMonthlyPassWithOneWeekFreeTrialPackage()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@id=\"loading\"]")));
        actualPrice= findElement("(//span[@class=\"subscription-price-item price fz-14 fw-bold\"])[2]",Pather.xPath,"").getText();
        findElementClick("(//div[@class=\"subscription-package package-group open clearfix\"])[2]",Pather.xPath);
        logger.info("Choose Monthly Pass with One Week Free Trial package");
    }

    @And("^Validate package price$")
    public void validatePackagePrice()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@id=\"loading\"]")));
        PageScrolldown();
        expectedPrice =findElement("(//span[@class=\"price fz-14 fw-bold text-center\"])[3]",Pather.xPath,"").getText();
        findElementClick("//a[@data-btn-index=\"1\"]",Pather.xPath);
        assertEquals(actualPrice,expectedPrice);
        logger.info("Validate package price");

    }

    @And("^User enters a valid username$")
    public void userEntersAValidUsername()
    {
        try
        {
            Thread.sleep(4000);
            new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@id=\"loading\"]")));
            String firstName = person.getFirstName();
            findElement("FirstName",Pather.name,"").sendKeys(firstName);
            logger.info("User enters a valid username");
        } catch(InterruptedException e)
        {
            logger.debug("Username not entered ",e);
        }
    }

    @And("^User enters a valid lastname$")
    public void userEntersAValidLastname()
    {
        String lastName = person.getLastName();
        findElement("LastName",Pather.name,"").sendKeys(lastName);
        logger.info("User enters a valid lastname");
    }

    @And("^User enters a valid email$")
    public void userEntersAValidEmail()
    {
        String eMail = person.getEmail();
        findElement("EmailOrPhone",Pather.name,"").sendKeys(eMail);
        logger.info("User enters a valid email");
    }

    @And("^User enters a valid password$")
    public void userEntersAValidPassword()
    {
        findElement("password",Pather.id,"").sendKeys("q1w2e3r4");
        logger.info("User enters a valid password");
    }

    @When("^User clicks on the register button$")
    public void userClicksOnTheRegisterButton()
    {
        try
        {
            Thread.sleep(1000);
            PageScrolldown();
            findElementClick("//input[@value=\"CREATE ACCOUNT\"]",Pather.xPath);
            logger.info("User clicks on the register button");

        } catch(InterruptedException e)
        {
            logger.debug("Button couldn't click",e);
        }
    }

    @Then("^E-mail confirmation alert appears$")
    public void eMailConfirmationAlertAppears()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@id=\"loading\"]")));
        findElementClick("close",Pather.id);
        logger.info("E-mail confirmation alert appears");
    }

    @And("^Accept terms and conditions checkbox$")
    public void acceptTermsAndConditionsCheckbox()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@id=\"loading\"]")));
        findElementClick("//label[@for=\"checkTerms\"]",Pather.xPath);
        logger.info("Accept terms and conditions checkbox");
    }

    @And("^Click the PAY NOW button$")
    public void clickThePAYNOWButton()
    {
        findElementClick("pay-now",Pather.name);
        logger.info("Click the PAY NOW button");
    }

    @And("^Fill the Cardholder's name$")
    public void fillTheCardholderSName()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@id=\"loading\"]")));
        findElement("Ecom_Payment_Card_Name",Pather.id,"").sendKeys("Cengizhan Taş");
        logger.info("Fill the Cardholder's name");
    }

    @And("^Fill the card number$")
    public void fillTheCardNumber()
    {
        findElement("Ecom_Payment_Card_Number",Pather.id,"").sendKeys("1111222233334444");
        logger.info("Fill the card number");
    }

    @And("^Fill expire month$")
    public void fillExpireMonth()
    {
        findElementClick("//*[@id=\"Ecom_Payment_Card_ExpDate_Month\"]/option[5]",Pather.xPath);
        logger.info("Fill expire month");
    }

    @And("^Fill expire year$")
    public void fillExpireYear()
    {
        findElementClick("//*[@id=\"Ecom_Payment_Card_ExpDate_Year\"]/option[4]",Pather.xPath);
        logger.info("Fill expire year");
    }

    @And("^Fill card verification code$")
    public void fillCardVerificationCode()
    {
        findElement("Ecom_Payment_Card_Verification",Pather.id,"").sendKeys("111");
        logger.info("Fill card verification code");
    }

    @When("^Click confirm my payment$")
    public void clickConfirmMyPayment()
    {
        findElementClick("payment",Pather.name);
        logger.info("Click confirm my payment");
    }

    @Then("^Check error message$")
    public void checkErrorMessage()
    {
        String expectMessage = "Card number incorrect or incompatible";
        assertTrue("There has been an error..!",(driver.findElement(By.xpath("//td[@class=\"ncoltxtc\"]")).getText().equalsIgnoreCase(expectMessage)));
        logger.info("Check error message");
        try {
            Thread.sleep(2000);
        } catch (Exception e)
        {
            logger.debug("Thread Interrupt Exception!!",e);
        } finally {
            driver.quit();
        }


    }

}
