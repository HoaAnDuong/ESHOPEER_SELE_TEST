package PageObjects.EShopper;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class GeneralPage {

    //Locators
    private final By btnLogin = By.xpath("//div[@class='col-sm-8']//ul[@class='nav navbar-nav']/li[4]/a[@href='http://localhost/SHOP_BAN_HANG_LARAVEL/dang-nhap']");
    private final By btnCart = By.xpath("//ul[@class='nav navbar-nav']/li/a[@href='http://localhost/SHOP_BAN_HANG_LARAVEL/gio-hang']");

    private final By txtSearchBox = By.xpath("//div[@class='search_box pull-right']/input[@type='text']");
    private final By btnSearch = By.xpath("//div[@class='search_box pull-right']/input[@type='submit']");

    //Elements
    protected WebElement getLnkLogin(){
        return Constant.WEBDRIVER.findElement(btnLogin);
    }

    protected WebElement getBtnCart(){
        return Constant.WEBDRIVER.findElement(btnCart);
    }

    protected WebElement getTxtSearchBox() {
        return Constant.WEBDRIVER.findElement(txtSearchBox);
    }

    protected WebElement getBtnSearch(){
        return Constant.WEBDRIVER.findElement(btnSearch);
    }

    //Methods
    public void clickLoginBtn(){
        this.getLnkLogin().click();
    }

    public void clickCartButton(){
        this.getBtnCart().click();
    }

    public void search(String searchingItem){
        PageFactory.initElements(Constant.WEBDRIVER, this);
        this.getTxtSearchBox().sendKeys(searchingItem);
        this.getBtnSearch().click();
    }

    public void open() {
        Constant.WEBDRIVER.navigate().to(Constant.ESHOPPER);
    }
}
