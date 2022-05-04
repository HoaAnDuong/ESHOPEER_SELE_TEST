package EShopper;

import Common.Common.Utilities;
import PageObjects.EShopper.HomePage;
import PageObjects.EShopper.ProductDetailPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AU02 extends TestBase{
    HomePage homePage = new HomePage();
    ProductDetailPage productDetailPage = new ProductDetailPage();

    @Test(description = "AU02 - Kiểm tra thông tin chi tiết sản phẩm tìm được", dataProvider = "data-providerAU02")
    public void AU02(String[] expectedProductNameArray, String[] expectedProductPriceArray){
        System.out.println("BUOC 1: Den trang EShoper");
        homePage.open();

        System.out.println("BƯỚC 2: Tìm kiếm sản phẩm cần kiểm tra");
        System.out.println("BƯỚC 3: Chọn sản phẩm tìm thấy");
        System.out.println("BƯỚC 4: Quan sát Thông tin sản phẩm trong trang chi tiết sản phẩm");

        productDetailPage.assertDetailedInfo(expectedProductNameArray, expectedProductPriceArray);


    }
    @DataProvider(name = "data-providerAU02")
    public Object[][] dataProvider(){
        ArrayList<String> expectedProductNameList = new ArrayList<>();
        ArrayList<String> expectedProductPriceList = new ArrayList<>();

        String filePath = Utilities.getProjectPath()+"\\src\\main\\java\\DataObjects\\data.json";
        JsonObject jsonObject = JsonHelper.getJsonObject(filePath);
        JsonObject dataAU02 = jsonObject.getAsJsonObject("AU02");

        JsonArray expectedProductNames = dataAU02.getAsJsonArray("expectedProductNames");
        JsonArray expectedProductPrices = dataAU02.getAsJsonArray("expectedProductPrices");

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
