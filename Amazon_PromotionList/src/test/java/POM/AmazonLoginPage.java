package POM;

import Common.Standard;
import org.junit.Assert;

public class AmazonLoginPage extends Standard {

    //wait for the amazon page to fully load
    public void validateAmazonPage(){
        //waiting for the website to load
        WaitForLoad();
        //Waiting for the button to be clickable
        WaitForElementClickable(PromotionBtn);
    }
    //goes to the promotion site
    public void clickPromotions(){
        //clicking the promotion button on the menu
        Click(PromotionBtn);
        //waiting for the promotion site to load
        WaitForLoad();
        WaitForElement(PromotionHeader);
        //validating we are at the promotion site
        Assert.assertEquals("Promociones",getTitle(PromotionHeader));
    }

}
