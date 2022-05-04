package EShopper;

import Common.Common.Utilities;
import PageObjects.EShopper.HomePage;
import PageObjects.EShopper.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AU03 extends TestBase{

    @Test(description = "AU03 - Xác minh Trang Đăng nhập được hiển thị chính xác khi nhấn nút 'Đăng Nhập'")
    public void AU03(){
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        System.out.println("BUOC 1: Điều hướng đến trang ESHOPPER");
        homePage.open();

        System.out.println("BUOC 2: Click vao nut 'Dang Nhap'");
        homePage.clickLoginBtn();
        Utilities.scrollIntoView(loginPage.getLblLoginLabel());

        String expectedMsg = "Đăng nhập tài khoản";
        String actualMSg = loginPage.getLoginLabel();

        Assert.assertEquals(actualMSg,expectedMsg,"Trang đăng nhập không hiển thị đúng như mong muốn.");
    }
}
