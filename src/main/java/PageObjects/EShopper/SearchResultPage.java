package PageObjects.EShopper;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends GeneralPage{
        //Locator
        String lblProductName = "//div[@class='productinfo text-center']/p[text()='%s']";
        String lblProductPrice = "//div[@class='productinfo text-center']/p[text()='%s']/preceding-sibling::h2";

        //Elements
        public WebElement getLblProductName(String productName){
            return Constant.WEBDRIVER.findElement(By.xpath(String.format(lblProductName,productName)));
        }

        public WebElement getLblProductPrice(String productPrice){
            return Constant.WEBDRIVER.findElement(By.xpath(String.format(lblProductPrice,productPrice)));
        }

        //Methods
        public void clickSearchedProduct(String productName){
            PageFactory.initElements(Constant.WEBDRIVER,this);
            this.getLblProductName(productName).click();
        }
}
