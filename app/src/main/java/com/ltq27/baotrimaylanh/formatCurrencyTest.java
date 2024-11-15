package com.ltq27.baotrimaylanh;

import java.text.NumberFormat;
import java.util.Locale;

public class formatCurrencyTest {
    public static String formatCurrency(double amount) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(amount);
    }
}
