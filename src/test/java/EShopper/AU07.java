package EShopper;

import Common.Common.StringUltilities;
import Common.Common.Utilities;
import PageObjects.EShopper.CartPage;
import PageObjects.EShopper.HomePage;
import PageObjects.EShopper.ProductDetailPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class AU07 extends TestBase{

    @Test (description = "AU07 - Kiem tong tien trong gio hang co hien thi chinh xac", dataProvider = "data-providerAU07")
    public void AU07(String[] expectedProductNameArray, Integer[] expectedProductQuantityArray, Integer[]expectedTotalProductPricesArray, long totalPrice){

        HomePage homePage = new HomePage();
        ProductDetailPage productDetailPage = new ProductDetailPage();
        CartPage cartPage = new CartPage();

        System.out.println("BUOC 1: Dieu huong den trang ESHOPPER");
        homePage.open();

        System.out.println("BUOC 2: Them nhieu hon 2 san pham vao gio hang");
        productDetailPage.addProductsToCart(expectedProductNameArray, expectedProductQuantityArray);

        System.out.println("BUOC 3: Di den trang gio hang");
        homePage.clickCartButton();
        Utilities.scrollIntoView(cartPage.getLblTotalPrice());

        cartPage.assertTotalProductPrice(cartPage.getActualTotalProductPrice(), expectedTotalProductPricesArray);
        Assert.assertEquals(cartPage.getLblTotalPrice().getText(), StringUltilities.priceFormatter(totalPrice, " VNĐ"));

    }

    @DataProvider(name = "data-providerAU07")
    public Object[][] dataProvider(){
        ArrayList<String> expectedProductNameList = new ArrayList<>();
        ArrayList<Integer> expectedProductQuantityList = new ArrayList<>();
        ArrayList<Integer> expectedTotalProductPriceList = new ArrayList<>();

        long totalPrice = 0;

        String filePath = Utilities.getProjectPath()+"\\src\\main\\java\\DataObjects\\data.json";
        JsonObject jsonObject = JsonHelper.getJsonObject(filePath);
        JsonArray dataAU07 = jsonObject.getAsJsonArray("AU07");

        for (int i = 0; i < dataAU07.size(); i++){
            JsonObject productItem = dataAU07.get(i).getAsJsonObject();
            expectedProductNameList.add(productItem.get("productName").getAsString());
            expectedProductQuantityList.add(productItem.get("productQuantity").getAsInt());
            expectedTotalProductPriceList.add(productItem.get("productQuantity").getAsInt() * productItem.get("productPrice").getAsInt());
        }

        String[] expectedProductNameArray = expectedProductNameList.toArray(new String[expectedProductNameList.size()]);
        Integer[] expectedProductQuantityArray = expectedProductQuantityList.toArray(new Integer[expectedProductQuantityList.size()]);
        Integer[] expectedTotalProductPricesArray = expectedTotalProductPriceList.toArray(new Integer[expectedTotalProductPriceList.size()]);

        for (Integer expectedTotalProductPrice : expectedTotalProductPricesArray){
            totalPrice += expectedTotalProductPrice;
        }

        Object[][] objects = new Object[][]{
                {expectedProductNameArray, expectedProductQuantityArray, expectedTotalProductPricesArray, totalPrice}
        };

        return objects;
    }

}
