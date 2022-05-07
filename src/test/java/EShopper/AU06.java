package EShopper;

import Common.Common.Utilities;
import PageObjects.EShopper.CartPage;
import PageObjects.EShopper.HomePage;
import PageObjects.EShopper.ProductDetailPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AU06 extends TestBase{

    HomePage homePage = new HomePage();
    CartPage cartPage = new CartPage();
    ProductDetailPage productDetailPage = new ProductDetailPage();

    @Test(description = "AU06 - Kiem tra nguoi dung co the xoa san pham trong gio hang", dataProvider = "data-providerAU06")
    public void AU06(String[] expectedProductNameArray,String[] expectedProductPriceArray,Integer[] removingItemArray){

        System.out.println("Tien Dieu Kien - San pham phai co trong gio hang ");
        homePage.open();
        productDetailPage.addManyProductToCart(expectedProductNameArray, expectedProductNameArray.length);

        System.out.println("BUOC 1: Di den gio hang");
        homePage.clickCartButton();
        String[] actualProductNames = cartPage.getActualProductNameList();
        String[] actualProductPrices = cartPage.getActualProductPriceList();
        cartPage.assertProductInfo(actualProductNames, expectedProductNameArray);
        cartPage.assertProductInfo(actualProductPrices, expectedProductPriceArray);

        System.out.println("BUOC 2: Xoa san pham");
        cartPage.removeProduct(expectedProductNameArray, removingItemArray);

        System.out.println("BUOC 3: Quan sat san pham bi xoa khoi gio hang");
        cartPage.verifiedProductNotExist(expectedProductNameArray, removingItemArray);
    }

    @DataProvider(name = "data-providerAU06")
    public Object[][] dataProvider(){
        ArrayList<String> expectedProductNameList = new ArrayList<>();
        ArrayList<String> expectedProductPriceList = new ArrayList<>();
        ArrayList<Integer> removingItemList = new ArrayList<>();

        String filePath = Utilities.getProjectPath()+"\\src\\main\\java\\DataObjects\\data.json";
        JsonObject jsonObject = JsonHelper.getJsonObject(filePath);
        JsonObject dataAU06 = jsonObject.getAsJsonObject("AU06");

        JsonArray expectedProductNames = dataAU06.getAsJsonArray("expectedProductNames");
        JsonArray expectedProductPrices = dataAU06.getAsJsonArray("expectedProductPrices");
        JsonArray removingItems = dataAU06.getAsJsonArray("removingItems");

        for (int i = 0; i < expectedProductNames.size(); i++){
            expectedProductNameList.add(expectedProductNames.get(i).getAsString());
            expectedProductPriceList.add(expectedProductPrices.get(i).getAsString());
        }

        for (int i = 0; i < removingItems.size(); i++){
            removingItemList.add(removingItems.get(i).getAsInt());
        }

        String[] expectedProductNameArray = expectedProductNameList.toArray(new String[expectedProductNameList.size()]);
        String[] expectedProductPriceArray = expectedProductPriceList.toArray(new String[expectedProductNameList.size()]);
        Integer[] removingItemArray = removingItemList.toArray(new Integer[removingItemList.size()]);

        Object[][] objects = new Object[][]{
                {expectedProductNameArray, expectedProductPriceArray, removingItemArray}
        };

        return objects;
    }
}
