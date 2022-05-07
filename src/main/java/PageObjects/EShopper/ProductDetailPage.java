package PageObjects.EShopper;

import Common.Common.Utilities;
import Common.Constant.Constant;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class ProductDetailPage extends SearchResultPage{

    //Locator
    String lblDetailedProductName = "//div[@class='product-information']/h2[text()='%s']";
    String lblDetailedProductPrice = "//div[@class='product-information']/h2[text()='%s']/following-sibling::form//span/span[text()='%s']";

    String btnAddToCart =  "//div[@class='product-information']/h2[text()='%s']/following-sibling::form/input[@type='button']";

    private final By btnContinueWatch = By.xpath("//div[@class='sa-button-container']/button");

    //Elements
    public WebElement getLblDetailedProductName(String productName){
        return Constant.WEBDRIVER.findElement(By.xpath(String.format(lblDetailedProductName,productName)));
    }

    public WebElement getLblDetailedProductPrice(String productName, String productPrice){
        StringBuilder sb = new StringBuilder(productPrice);
        String replacedProductPrice = sb.deleteCharAt(productPrice.length() - 4).toString();
        return Constant.WEBDRIVER.findElement(By.xpath(String.format(lblDetailedProductPrice,productName,replacedProductPrice)));
    }

    public WebElement getBtnAddToCart(String productName){
        return Constant.WEBDRIVER.findElement(By.xpath(String.format(btnAddToCart,productName)));
    }

    public WebElement getBtnContinueWatch(){
        return Constant.WEBDRIVER.findElement(btnContinueWatch);
    }

    //Method
    public String getDetailedProductName(String productName){
        return this.getLblDetailedProductName(productName).getText();
    }

    public String getDetailedProductPrice(String productName, String productPrice){
        String s = this.getLblDetailedProductPrice(productName, productPrice).getText();
        StringBuilder sb = new StringBuilder(s);
        return sb.insert(productPrice.length() - 4, " ").toString();
    }
    public void addManyProductToCart(String[] productName, int numberOfItem) {
        PageFactory.initElements(Constant.WEBDRIVER, this);
        for (int i = 0; i < numberOfItem; i++){
            this.getTxtSearchBox().sendKeys(productName[i]);
            this.getBtnSearch().click();
            Utilities.scrollIntoView(this.getLblProductName(productName[i]));
            this.clickSearchedProduct(productName[i]);
            this.getBtnAddToCart(productName[i]).click();
            Constant.WEBDRIVER.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            this.getBtnContinueWatch().click();
        }
    }

    public void assertDetailedInfo(String[] expectedProductNames, String[] expectedProductPrices){
        PageFactory.initElements(Constant.WEBDRIVER, this);

        for (int i = 0; i < expectedProductNames.length; i++){
            this.getTxtSearchBox().sendKeys(expectedProductNames[i]);
            this.getBtnSearch().click();
            Utilities.scrollIntoView(this.getLblProductName(expectedProductNames[i]));
            this.clickSearchedProduct(expectedProductNames[i]);

        String actualProductName = this.getDetailedProductName(expectedProductNames[i]);
            System.out.println("[Expected Name]: " + expectedProductNames[i] + "| " + "[Actual Name]: " + actualProductName);
        String actualProductPrice = this.getDetailedProductPrice(expectedProductNames[i], expectedProductPrices[i]);
            System.out.println("[Expected Prices]: " + expectedProductPrices[i] + "| " + "[Actual Price]: " + actualProductPrice);

        Assert.assertEquals(actualProductName, expectedProductNames[i]);
        Assert.assertEquals(actualProductPrice, expectedProductPrices[i]);
        }
    }

}
