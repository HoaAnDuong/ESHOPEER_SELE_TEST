package PageObjects.EShopper;

import Common.Common.StringUltilities;
import Common.Constant.Constant;
import org.apache.commons.lang3.ArrayUtils;
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
    private final By tbiTotalProductPrice = By.xpath("//table[@class='table table-condensed']/tbody//td[@class='cart_total']/p");

    private final By lblTotalPrice = By.xpath("//table[@class='table table-condensed']/tbody/tr/td/li[text()='Tổng tiền :']/span");

    String btnRemoveProduct = "//div[@class='table-responsive cart_info']/form/table//td/p[text()='%s']/../following-sibling::td/a[@class='cart_quantity_delete']";
    String lblProductName = "//div[@class='table-responsive cart_info']//table[@class='table table-condensed']/tbody//td[@class='cart_description']/p[text()='%s']";

    private final By lblAlertSuccess = By.xpath("//div[@class='col-sm-9 padding-right']//div[@class='alert alert-success']");

    public String[] actualProductNames = {};
    public String[] actualProductPrices = {};
    public String[] actualTotalProductPrices = {};

    List<String> actualProductNameList = new ArrayList<>(Arrays.asList(actualProductNames));
    List<String> actualProductPriceList = new ArrayList<>(Arrays.asList(actualProductPrices));
    List<String> actualTotalProductPriceList = new ArrayList<>(Arrays.asList(actualProductPrices));

    //Elements
    public List<WebElement> getTbiProductNames(){
        return Constant.WEBDRIVER.findElements(tbiProductNames);
    }

    public List<WebElement> getTbiProductPrices(){
        return Constant.WEBDRIVER.findElements(tbiProductPrices);
    }

    public List<WebElement> getTbiTotalProductPrice(){
        return Constant.WEBDRIVER.findElements(tbiTotalProductPrice);
    }

    public WebElement getBtnRemoveItem(String productName){
        return Constant.WEBDRIVER.findElement(By.xpath(String.format(btnRemoveProduct, productName)));
    }

    public WebElement getLblAlertSuccess(){
        return Constant.WEBDRIVER.findElement(lblAlertSuccess);
    }

    public List<WebElement> getProductName(String productName){
        return Constant.WEBDRIVER.findElements(By.xpath(String.format(lblProductName, productName)));
    }

    public WebElement getLblTotalPrice(){
        return Constant.WEBDRIVER.findElement(lblTotalPrice);
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

    public String[] getActualTotalProductPrice(){
        for (WebElement actualTotalProductPrice : getTbiTotalProductPrice()){
            actualTotalProductPriceList.add(actualTotalProductPrice.getText());
        }

        actualTotalProductPrices = actualTotalProductPriceList.toArray(new String[actualTotalProductPriceList.size()]);
        return actualTotalProductPrices;
    }

    public void assertProductInfo(String[] actualProductInfo, String[] expectedProductInfo){
        for(int i = 0; i < actualProductInfo.length; i++){
            System.out.println("[actualString]: " + actualProductInfo[i]+ "| " + "[expectedString]: " + expectedProductInfo[i]);
            Assert.assertEquals(actualProductInfo[i], expectedProductInfo[i]);
        }
    }

    public void assertTotalProductPrice(String[] actualProductInfo, Integer[] expectedProductInfo){
        for(int i = 0; i < actualProductInfo.length; i++){
            System.out.println("[actualString]: " + actualProductInfo[i]+ "| " + "[expectedString]: " + StringUltilities.priceFormatter(expectedProductInfo[i],"đ"));
            Assert.assertEquals(actualProductInfo[i], StringUltilities.priceFormatter(expectedProductInfo[i],"đ"));
        }
    }

    public void removeProduct(String[] expectedProductNameArray, Integer[] removingItems){
        for (int i = 0; i < removingItems.length; i++) {
            this.getBtnRemoveItem(expectedProductNameArray[removingItems[i]]).click();
            System.out.println("Xoa san pham: " + expectedProductNameArray[removingItems[i]]);
        }

        Assert.assertEquals(this.getLblAlertSuccess().getText(), "Xóa sản phẩm thành công");
    }

    public void verifiedProductNotExist(String[] expectedProductNameArray, Integer[] removingItems){
        for(int i = 0; i < removingItems.length; i++){
            Assert.assertEquals(getProductName(expectedProductNameArray[removingItems[i]]).size(),0);
            System.out.println("Tim thay: " + getProductName(expectedProductNameArray[removingItems[i]]).size());
        }

    }

}
