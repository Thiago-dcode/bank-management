package utils;

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
