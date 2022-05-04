package PageObjects.EShopper;

import Common.Constant.Constant;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartPage extends GeneralPage{

    //Locator
    private final By tbiProductNames = By.xpath("//div[@class='table-responsive cart_info']//table[@class='table table-condensed']/tbody//td[@class='cart_description']/p");
    private final By tbiProductPrices = By.xpath("//table[@class='table table-condensed']/tbody//td[@class='cart_price']/p");

    public String[] actualProductNames = {};
    public String[] actualProductPrices = {};

    List<String> actualProductNameList = new ArrayList<>(Arrays.asList(actualProductNames));
    List<String> actualProductPriceList = new ArrayList<>(Arrays.asList(actualProductPrices));

    //Elements
    public List<WebElement> getTbiProductNames(){
        return Constant.WEBDRIVER.findElements(tbiProductNames);
    }

    public List<WebElement> getTbiProductPrices(){
        return Constant.WEBDRIVER.findElements(tbiProductPrices);
    }

    //Methods
    public String[] getActualProductNameList (){
        for (int i = 0; i < getTbiProductNames().size(); i++){
            String actualProductName = getTbiProductNames().get(i).getText();
            actualProductNameList.add(actualProductName);
        }

        actualProductNames = actualProductNameList.toArray(new String[actualProductNameList.size()]);
        return actualProductNames;
    }

    public String[] getActualProductPriceList (){
        for (int i = 0; i< getTbiProductPrices().size(); i++){
            String actualProductPrice = getTbiProductPrices().get(i).getText();
            actualProductPriceList.add(actualProductPrice);
        }

        actualProductPrices = actualProductPriceList.toArray(new String[actualProductPriceList.size()]);
        return actualProductPrices;
    }

    public void assertProductInfo(String[] actualProductInfo, String[] expectedProductInfo){
        for(int i = 0; i < actualProductInfo.length; i++){
            System.out.println("[actualString]: " + actualProductInfo[i]+ "| " + "[expectedString]: " + expectedProductInfo[i]);
            Assert.assertEquals(actualProductInfo[i], expectedProductInfo[i]);
        }
    }

}