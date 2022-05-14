package EShopper;

import Common.Common.StringUltilities;
import Common.Common.Utilities;
import Common.Constant.Constant;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AU07 extends TestBase{

    @Test (description = "AU07 - Kiem tong tien trong gio hang co hien thi chinh xac")
    public void AU07(){

    }

    public static void getJson(){
        ArrayList<String> expectedProductNameList = new ArrayList<>();
        ArrayList<Integer> expectedProductQuantityList = new ArrayList<>();
        ArrayList<Integer> expectedProductPricesList = new ArrayList<>();
        ArrayList<Integer> expectedTotalProductPriceList = new ArrayList<>();

        long totalPrice = 0;

        String filePath = Utilities.getProjectPath()+"\\src\\main\\java\\DataObjects\\data.json";
        JsonObject jsonObject = JsonHelper.getJsonObject(filePath);
        JsonArray dataAU07 = jsonObject.getAsJsonArray("AU07");

        for (int i = 0; i < dataAU07.size(); i++){
            JsonObject productItem = dataAU07.get(i).getAsJsonObject();
            expectedProductNameList.add(productItem.get("productName").getAsString());
            expectedProductPricesList.add(productItem.get("productPrice").getAsInt());
            expectedProductQuantityList.add(productItem.get("productQuantity").getAsInt());
            expectedTotalProductPriceList.add(productItem.get("productQuantity").getAsInt() * productItem.get("productPrice").getAsInt());
        }

        String[] expectedProductNameArray = expectedProductNameList.toArray(new String[expectedProductNameList.size()]);
        Integer[] expectedProductQuantityArray = expectedProductQuantityList.toArray(new Integer[expectedProductQuantityList.size()]);
        Integer[] expectedProductPriceArray = expectedProductPricesList.toArray(new Integer[expectedProductPricesList.size()]);
        Integer[] expectedTotalProductPricesArray = expectedTotalProductPriceList.toArray(new Integer[expectedTotalProductPriceList.size()]);


        for (int i = 0; i < expectedProductNameArray.length; i++){
            System.out.println(expectedProductNameArray[i]);
            System.out.println(expectedProductQuantityArray[i]);
            System.out.println(expectedProductPriceArray[i]);
            System.out.println(StringUltilities.priceFormatter(expectedTotalProductPricesArray[i]));

            totalPrice += expectedProductPriceArray[i] * expectedProductQuantityArray[i];
        }

        System.out.println(StringUltilities.priceFormatter(totalPrice));
    }

    public static void main(String[] args) {
        AU07.getJson();
    }
}
