package TestCases;

import Common.Browser;
import Common.Report;
import Common.Standard;
import POM.AmazonLoginPage;
import POM.AmazonPromotionPage;
import com.aventstack.extentreports.Status;
import org.junit.*;
import org.junit.rules.TestName;

public class TestScenario01 extends Standard {
    //creating the classes objects from the POM
    AmazonLoginPage AmazonLogin = new AmazonLoginPage();
    AmazonPromotionPage AmazonOffersList = new AmazonPromotionPage();


    @Rule
    public TestName TC_Name = new TestName();
    public String URL;

    @BeforeClass
    public static void beforeClass()
    {
        Report.fnSetupReport();
    }

    @Before
    public void setup()
    {
        Browser.BrowserName = "chrome";
        Open();
    }
    @Test
    public void PromotionList(){
        System.out.println("Test 1 Amazon Promotion List");
        try{
            Report.objTest = Report.objExtent.createTest("First Test");
            URL= "https://www.amazon.com.mx";
        //going to Amazon website
        GoToUrl(URL);
        fnLog(Status.INFO,"Validating we are at amazon.com.mx",false);
        AmazonLogin.validateAmazonPage();
        fnLog(Status.PASS,"We are at amazon.com.mx",true);
        fnLog(Status.INFO,"Going to Promotions",false);
        AmazonLogin.clickPromotions();
        fnLog(Status.PASS,"We are at the promotions site",true);
        fnLog(Status.INFO,"Applying lighting offers filter",false);
        AmazonOffersList.lightingOfferFilter();
        fnLog(Status.PASS,"Lighting Offer filter applied",true);
        fnLog(Status.INFO,"Getting Promotion List",false);
        AmazonOffersList.getPromotionList();


        }
        catch (Exception e){
            Report.fnLog(Status.FAIL, "The: " + TC_Name.getMethodName() + " was not executed successfully. \n Exception: " + e.getMessage() , false);
        }
    }




    @After
    public void tearDown()
    {
        Close();
        Report.fnCloseReport();
    }

}
