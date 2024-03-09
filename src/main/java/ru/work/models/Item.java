package ru.work.models;

import lombok.Builder;
import lombok.Getter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Builder
@Getter
public class Item {
    private final String name;
    private final double cost;
    private static final double TAX = 8.01;

    //Метод расчета стоимости товара с налогом. Используется как ожидаемый результат в ответе метода сравнения.
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
