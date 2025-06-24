package utils;

import java.text.NumberFormat;

public class CurrencyFormatter {
    public static String format(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(amount);
    }
}
