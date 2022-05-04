package EShopper;

import Common.Common.Utilities;
import Common.Constant.Constant;
import PageObjects.EShopper.HomePage;
import PageObjects.EShopper.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AU05 extends TestBase{

    @Test(description = "AU05 - Xác minh người dùng ĐĂNG NHẬP với Email và mật khẩu đã đăng kí")
    public void AU05(){
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        System.out.println("BƯỚC 1: Điều hướng đến trang ESHOPPER");
        homePage.open();

        System.out.println("BƯỚC 2: Click vào nút 'Đăng Nhập'");
        homePage.clickLoginBtn();

        System.out.println("BƯỚC 3: Điền Email đã đăng ký vào trường Tài khoản");
        System.out.println("BƯỚC 4: Điền Password đã đăng ký vào trường Password");
        System.out.println("BƯỚC 5: Click vào nút Đăng Nhập");

        Utilities.scrollIntoView(loginPage.getTxtEmailAccount());
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Utilities.scrollIntoView(loginPage.getBtnLogout());
        String expectedMsg = "Đăng xuất";
        String actualMsg = loginPage.getLogoutButtonLabel();

        Assert.assertEquals(expectedMsg,actualMsg, "Người dùng không thể đăng nhập vào hệ thống");
    }
}
