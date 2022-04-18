package POM;

import Common.Browser;
import Common.Standard;
import org.openqa.selenium.JavascriptExecutor;

public class AmazonPromotionPage extends Standard {

    //implement the lighting offer filter
    public void lightingOfferFilter(){
        //click the lighting offer button
        actionSelectClick(OffersBtn);
        //scroll all the way up to the header
        goToElement(PromotionHeader);
        //waiting for the grid to load
        WaitForLoad();
        WaitForElement(PromotionsGridLabel);
    }
    //print the promotions on the report
    public void getPromotionList (){

        listElementName(PromotionsGrid,"Promotion");
    }
}
