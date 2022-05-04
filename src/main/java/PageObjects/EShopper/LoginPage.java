package PageObjects.EShopper;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends GeneralPage{

    //Locator
    private final By btnLogout = By.xpath("//div[@class='col-sm-8']//ul[@class='nav navbar-nav']/li[4]/a[@href='http://localhost/SHOP_BAN_HANG_LARAVEL/logout-checkout']");

        //Locator for Login Form
    private final By lblLoginLabel = By.xpath("//div[@class='login-form']/h2");
    private final By txtEmailAccount = By.xpath("//div[@class='login-form']/form/input[@name='email_account']");
    private final By txtPasswordAccount = By.xpath("//div[@class='login-form']/form/input[@name='password_account']");
    private final By btnLoginSubmit = By.xpath("//div[@class='login-form']/form/button[@type='submit']");

        //Locator for Sign Up form
    private final By lblSignupLabel = By.xpath("//div/div[@class='signup-form']/h2");
    private final By txtCustomerName = By.xpath("//div/div[@class='signup-form']/form/input[@name='customer_name']");
    private final By txtCustomerEmail = By.xpath("//div/div[@class='signup-form']/form/input[@name='customer_email']");
    private final By txtCustomerPass = By.xpath("//div/div[@class='signup-form']/form/input[@name='customer_password']");
    private final By txtCustomerPhone = By.xpath("//div/div[@class='signup-form']/form/input[@name='customer_phone']");
    private final By btnSignupSubmit = By.xpath("//div/div[@class='signup-form']/form/button[text()='Đăng ký']");

    //Elements
        //Login Form Elements
    public WebElement getLblLoginLabel(){
        return Constant.WEBDRIVER.findElement(lblLoginLabel);
    }

    public WebElement getTxtEmailAccount(){
        return Constant.WEBDRIVER.findElement(txtEmailAccount);
    }

    public WebElement getTxtPasswordAccount(){
        return Constant.WEBDRIVER.findElement(txtPasswordAccount);
    }

    public WebElement getBtnLoginSubmit(){
        return Constant.WEBDRIVER.findElement(btnLoginSubmit);
    }

    public WebElement getBtnLogout(){
        return Constant.WEBDRIVER.findElement(btnLogout);
    }

        //SignUp Form Elements
    public WebElement getLblSignUpLabel(){
        return Constant.WEBDRIVER.findElement(lblSignupLabel);
    }

    public WebElement getTxtCustomerName(){
        return Constant.WEBDRIVER.findElement(txtCustomerName);
    }

    public WebElement getTxtCustomerEmail(){
        return Constant.WEBDRIVER.findElement(txtCustomerEmail);
    }

    public WebElement getTxtCustomerPass(){
        return Constant.WEBDRIVER.findElement(txtCustomerPass);
    }

    public WebElement getTxtCustomerPhone(){
        return Constant.WEBDRIVER.findElement(txtCustomerPhone);
    }

    public WebElement getBtnSignUpSubmit(){
        return Constant.WEBDRIVER.findElement(btnSignupSubmit);
    }


    //Methods
    public String getLoginLabel(){
        return this.getLblLoginLabel().getText();
    }

    public void login(String username, String password){
        PageFactory.initElements(Constant.WEBDRIVER, this);
        getTxtEmailAccount().sendKeys(username);
        getTxtPasswordAccount().sendKeys(password);
        getBtnLoginSubmit().click();
    }

    public String getLogoutButtonLabel(){
        return this.getBtnLogout().getText();
    }

    public String getSignUpLabel() {
        return this.getLblLoginLabel().getText();
    }

    public void sigup(String name, String userName, String passWord, String Phone){
        PageFactory.initElements(Constant.WEBDRIVER, this);
        getTxtCustomerName().sendKeys(name);
        getTxtCustomerEmail().sendKeys(userName);
        getTxtCustomerPass().sendKeys(passWord);
        getTxtCustomerPhone().sendKeys(Phone);
        getBtnSignUpSubmit().click();
    }
}
