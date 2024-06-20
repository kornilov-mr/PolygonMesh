package core.statistic;

import java.util.HashMap;
import java.util.Map;

public class FPSTracker {

    private static Map<String, Double> MPF = new HashMap<>(); //milliseconds per process
    private static double FPS;
    public static void addComponent(String name, double fpm){
        MPF.put(name,fpm);
    }
    public static void endWriting(){
        double allFpm=0;
        for(double fpm: MPF.values()){
            allFpm+=fpm;
        }
        MPF.put("all",allFpm);
        FPS=1000/allFpm;
    }
    public static void reset(){
        MPF.clear();
    }

    public static String toHTMLString(){
        String htmlString = "Milliseconds:<br>";
        for(String name: MPF.keySet()){
            htmlString += name+"-"+String.format("%.4g%n",MPF.get(name))+"<br>";
        }
        htmlString+="FPS:"+String.format("%.4g%n",FPS)+"<br>";
        return htmlString;
    }
}
