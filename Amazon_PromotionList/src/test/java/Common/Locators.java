package Common;

public class Locators extends Report {
//AmazonLoginPage Locators
    protected String PromotionBtn = "//a[contains(text(),'Promociones')]";
//AmazonPromotionsPage Locators
    protected String PromotionHeader = "//h1[contains(text(),'Promociones')]";
    protected String OffersBtn = "//span[contains(text(),'relámpago')]";
    protected String PromotionsGrid = "//div[@data-testid='grid-deals-container']//div[@class='DealContent-module__truncate_sWbxETx42ZPStTc9jwySW']";
    protected String PromotionsGridLabel = "//div[@aria-label='Cuadrícula de ofertas']";

}
