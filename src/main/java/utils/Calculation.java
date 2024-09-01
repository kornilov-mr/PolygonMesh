package utils;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculation {


    public static double round(double value, int digits){
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(digits, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public static boolean CompareWithRound(double a,double b,int digits){
        if(round(a,digits)==round(b,digits)){
            return true;
        }
        return false;
    }

}
