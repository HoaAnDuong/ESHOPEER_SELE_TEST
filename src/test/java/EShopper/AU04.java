package EShopper;

import Common.Common.StringUltilities;
import Common.Common.Utilities;
import Common.Constant.Constant;
import PageObjects.EShopper.HomePage;
import PageObjects.EShopper.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AU04 extends TestBase{

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    String name = Constant.FULLNAME;
    String signUpEmail = StringUltilities.generateRandomEmail();
    String signUpPass = Constant.SIGNUPPASS;
    String signUpPhone = StringUltilities.generatePhoneNum();

    @Test(description = "AU04 - Kiểm tra người dùng có thể Đăng Ký với thông tin hợp lệ")
    public void AU04(){
        System.out.println("BƯỚC 1: Điều hướng đến trang ESHOPPER");
        homePage.open();

        System.out.println("BƯỚC 2: Click vào 'Đăng nhập'");
        homePage.clickLoginBtn();

        System.out.println("BƯỚC 3: Điền thông tin hợp lệ vào Form Đăng Ký");
        System.out.println("BƯỚC 4: Click nút Đăng Ký");
        Utilities.scrollIntoView(loginPage.getLblSignUpLabel());
        loginPage.sigup(name, signUpEmail, signUpPass, signUpPhone);

        String actualMsg = loginPage.getLogoutButtonLabel();
        String expectedMsg = "Đăng xuất";

        Assert.assertEquals(expectedMsg, actualMsg, "Người dùng không thể đăng ký tài khoản với thông tin hợp lệ");
    }
}
