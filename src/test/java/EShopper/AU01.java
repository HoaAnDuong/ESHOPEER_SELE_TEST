package EShopper;

import Common.Common.Utilities;
import PageObjects.EShopper.CartPage;
import PageObjects.EShopper.HomePage;
import PageObjects.EShopper.ProductDetailPage;
import PageObjects.EShopper.SearchResultPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AU01 extends TestBase {

    HomePage homePage = new HomePage();
    CartPage cartPage = new CartPage();
    ProductDetailPage productDetailPage = new ProductDetailPage();


    @Test(description = "AU01 - Kiểm tra các sản phẩm trong giỏ hàng được hiển thị chính xác", dataProvider = "data-providerAU01")

    public void AU01(String[] expectedProductNameArray, String[] expectedProductPriceArray) throws InterruptedException {

        System.out.println("BƯỚC 1: Điều hướng đến trang ESHOPPER");
        homePage.open();

        System.out.println("BƯỚC 2: Click vào button giỏ hàng");
        homePage.clickCartButton();

        System.out.println("BƯỚC 3: Tìm và thêm sản phẩm vào giỏ hàng");
        productDetailPage.addManyProductToCart(expectedProductNameArray, expectedProductNameArray.length);

        System.out.println("BƯỚC 4: Đi đến giỏ hàng");
        productDetailPage.clickCartButton();

        String[] actualProductNames = cartPage.getActualProductNameList();
        String[] actualProductPrices = cartPage.getActualProductPriceList();

        cartPage.assertProductInfo(actualProductNames, expectedProductNameArray);
        cartPage.assertProductInfo(actualProductPrices, expectedProductPriceArray);

    }

    @DataProvider(name = "data-providerAU01")
    public Object[][] dataProvider(){
        ArrayList<String> expectedProductNameList = new ArrayList<>();
        ArrayList<String> expectedProductPriceList = new ArrayList<>();

        String filePath = Utilities.getProjectPath()+"\\src\\main\\java\\DataObjects\\data.json";
        JsonObject jsonObject = JsonHelper.getJsonObject(filePath);
        JsonObject dataAU01 = jsonObject.getAsJsonObject("AU01");

        JsonArray expectedProductNames = dataAU01.getAsJsonArray("expectedProductNames");
        JsonArray expectedProductPrices = dataAU01.getAsJsonArray("expectedProductPrices");

        for (int i = 0; i < expectedProductNames.size(); i++){
            expectedProductNameList.add(expectedProductNames.get(i).getAsString());
            expectedProductPriceList.add(expectedProductPrices.get(i).getAsString());
        }

        String[] expectedProductNameArray = expectedProductNameList.toArray(new String[expectedProductNameList.size()]);
        String[] expectedProductPriceArray = expectedProductPriceList.toArray(new String[expectedProductPriceList.size()]);

        Object[][] objects = new Object[][]{
                {expectedProductNameArray, expectedProductPriceArray}
        };

        return objects;
    }

}
