package ru.work.utils;

import lombok.Builder;
import lombok.Getter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static ru.work.utils.Builder.backpack;

@Builder
@Getter
public class Item {
    private final String name;
    private final double cost;
    private static final double TAX = 8.01;

    //метод расчета стоимости товара с налогом
    public static String getFinalCostWithTax(Item item) {
        double cost = item.getCost() * TAX / 100 + item.getCost();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols() {{
            setDecimalSeparator('.');
        }});
        String result = decimalFormat.format(cost);
        return "$" + result;
    }
}
