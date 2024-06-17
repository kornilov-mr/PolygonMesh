package core.statistic;

import java.util.HashMap;
import java.util.Map;

public class FPSTracker {

    private static Map<String, Double> MPP = new HashMap<>(); //milliseconds per process
    private static double FPS;
    public static void addComponent(String name, double fpm){
        MPP.put(name,fpm);
    }
    public static void endWriting(){
        double allFpm=0;
        for(double fpm: MPP.values()){
            allFpm+=fpm;
        }
        MPP.put("all",allFpm);
        FPS=1000/allFpm;
    }
    public static void reset(){
        MPP.clear();
    }

    public static Map<String, Double> getMPP() {
        return MPP;
    }

    public static double getFPS() {
        return FPS;
    }
}
