package Common;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class Standard extends Browser{
    public static Wait<WebDriver> objFluentWait;
    public static WebDriverWait objExplicitWait;

    public Object GetFluentWait(final String pstrLocator)
    {
        try
        {
            // Waiting 30 seconds for an element to be present on the page, checking
            // for its presence once every 5 seconds.
            objFluentWait = new FluentWait<WebDriver>(Browser.objDriver)
                    .withTimeout(Duration.ofSeconds(30L))
                    .pollingEvery(Duration.ofSeconds(3L))
                    .ignoring(NoSuchElementException.class);

            //Get Web Element and perform action
            WebElement objElement = objFluentWait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.xpath(pstrLocator));
                }
            });

            return objElement;
        }
        catch(Exception e)
        {
            System.out.println("The element was ("+ pstrLocator +") not located in the page");
            return null;
        }
    }
    //Wait for the webelement to be present
    public void WaitForElement(final String pstrLocator)
    {
        objExplicitWait = new WebDriverWait(Browser.objDriver, 10);
        objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pstrLocator)));
    }
    //wait for the webelement to be clickable
    public void WaitForElementClickable(final String pstrLocator)
    {
        objExplicitWait = new WebDriverWait(Browser.objDriver, 10);
        objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pstrLocator)));
        objExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(pstrLocator)));
    }
    //wait for the page to fully load
    public void WaitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(Browser.objDriver, 30);
        wait.until(pageLoadCondition);
    }
    //allow to click an element present
    public boolean Click(final String pstrLocator)
    {
        try{
        WebElement objElement = (WebElement) GetFluentWait(pstrLocator);
        objExplicitWait = new WebDriverWait(Browser.objDriver, 10);
        objExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(pstrLocator)));
        objElement.click();
            return false;
        }
        catch (ElementNotSelectableException e){
            Report.fnLog(Status.FAIL, "Step - The Web Element: " + pstrLocator + " was not clickable as expected.", true);
            return false;
        }
    }
    //allow to click an element that is not visible
    public void actionSelectClick (final String pstrLocator){
        Actions builder = new Actions(Browser.objDriver);
        WebElement objElement = (WebElement) GetFluentWait(pstrLocator);
        Action seriesOfActions = builder.moveToElement(objElement).click().build();
        seriesOfActions.perform();
    }
    //Get the element title
    public String getTitle (final String pstrLocator){
        WebElement objElement = (WebElement) GetFluentWait(pstrLocator);
        objExplicitWait = new WebDriverWait(Browser.objDriver, 10);
        objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pstrLocator)));
        return objElement.getText();

    }

    /**
     * Search for the elements on a list and print them on the report
    *@param Locator
     *@param ListName
     */
    public void listElementName(String pstrLocator,String ListName) {
        WaitForElement(pstrLocator);
        List<WebElement> lsPosition = Browser.objDriver.findElements(By.xpath(pstrLocator));
        int cont = 1;
        if (lsPosition.size() > 0) {
            for (WebElement position : lsPosition) {
                //System.out.println(position.getAttribute("innerText").replace(" ",""));
                fnLog(Status.INFO,ListName+" "+cont+": "+position.getAttribute("innerText"),false);
                cont++;
            }
        } else {
            System.out.println("Promotions were not found");
        }
    }
    //do a scroll to locate a webelement
    public void goToElement(String pstrLocator){
        Actions builder = new Actions(Browser.objDriver);
        WebElement objElement = (WebElement) GetFluentWait(pstrLocator);
        Action seriesOfActions = builder.moveToElement(objElement).build();
        seriesOfActions.perform();
    }

}
