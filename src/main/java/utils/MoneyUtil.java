package utils;

import java.text.DecimalFormat;

public class MoneyUtil {

    public static String format(double money) {

        String beforeStr = "$";
        String afterStr = String.format("%.2f", money);;
        if(money < 0){
            beforeStr = "-" + beforeStr;
            //remove the negative dash
            afterStr = afterStr.substring(1);
        }

        return beforeStr+ afterStr;

    }
    public static double round(double money, int places) {
        if(places <= 0 || money == 0) return money;
       return (double) Math.round(money * Math.pow(10,places)) /Math.pow(10,places);

    }
    public static String format(double money,String symbol){
        if(symbol == null){
            throw new IllegalArgumentException("symbol cannot be null");
        }
        String beforeStr = symbol;
        String afterStr = String.format("%.2f", money);;
        if(money < 0){
            beforeStr = "-" + beforeStr;
            //remove the negative dash
            afterStr = afterStr.substring(1);
        }

        return beforeStr+ afterStr;


    }
}
