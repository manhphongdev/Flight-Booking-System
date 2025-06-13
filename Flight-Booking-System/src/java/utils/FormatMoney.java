
package utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author manhphong
 */
public class FormatMoney {
    public static String formatNumber(double number) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.'); 
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##", symbols);
        return decimalFormat.format(number);
    }

}
