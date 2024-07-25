package core.statistic.FPS;

import java.util.*;

public class FPSTracker {

    private static Map<String, LinkedList<Double>> MPF = new HashMap<>(); //milliseconds per process
    public static void addComponent(String name, double fpm){
        if(MPF.get(name)==null){
            MPF.put(name,new LinkedList<Double>());
        }
        MPF.get(name).add(fpm);
        if(MPF.get(name).size()>=61){
            MPF.get(name).remove(0);
        }
    }


    public static String toHTMLString(){
        String htmlString = "Milliseconds:<br>";
        double millisecondsAverageSum=0;
        for(String name: MPF.keySet()){
            Iterator<Double> it = MPF.get(name).iterator();
            double millisecondsSum=0;
            while(it.hasNext()){
                millisecondsSum+= it.next();
            }
            double millisecondsAverage= millisecondsSum/MPF.get(name).size();
            millisecondsAverageSum+=millisecondsAverage;
            htmlString += name+"-"+String.format("%.4g%n",millisecondsAverage)+"<br>";
        }
        htmlString+="FPS:"+String.format("%.4g%n",1000/millisecondsAverageSum)+"<br>";
        return htmlString;
    }
}
