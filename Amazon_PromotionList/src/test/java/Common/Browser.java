package Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Browser extends Locators {

    public static String BrowserName;
    public static WebDriver objDriver;

    //Open de browser we want to use
    public WebDriver Open()
    {
        switch (BrowserName.toUpperCase()) {
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/selenium_driver/chromedriver.exe");
                objDriver = new ChromeDriver();
                objDriver.manage().window().maximize();
                objDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                break;
            case "EDGE":
                System.setProperty("webdriver.edge.driver", "./src/test/resources/selenium_driver/msedgedriver.exe");
                objDriver = new EdgeDriver();
                objDriver.manage().window().maximize();
                objDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                break;
            case "FIREFOX":
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/selenium_driver/chromedriver.exe");
                objDriver = new FirefoxDriver();
                objDriver.manage().window().maximize();
                objDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


            default:
                System.out.println("The browser is "+ BrowserName +" not supported.");
                break;
        }
        return objDriver;

    }
    //close the browser
    public void Close() {
        objDriver.close();
        objDriver.quit();
    }
    //navigate to the selected URL
    public void GoToUrl(String varURL){
        objDriver.get(varURL);
    }
}
