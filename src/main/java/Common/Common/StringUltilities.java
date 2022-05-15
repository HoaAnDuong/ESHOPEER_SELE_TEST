package Common.Common;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class StringUltilities {
    public static String generateRandomEmail() {
        String randomString = RandomStringUtils.randomAlphanumeric(3);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "test" + randomString + timestamp.getTime() + "@gmail.com";
    }

    public static String generatePhoneNum() {
        String randomNum = "0" + RandomStringUtils.randomNumeric(9);
        return randomNum;
    }

    public static String priceFormatter(long price, String currencyFormat){
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();


        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);
        BigDecimal bd = new BigDecimal(price);

        return formatter.format(bd.longValue()) + currencyFormat;
    }
}
