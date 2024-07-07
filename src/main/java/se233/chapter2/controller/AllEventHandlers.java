package se233.chapter2.controller;

import se233.chapter2.Launcher;

public class AllEventHandlers {
    public static void onRefresh(){
        try{
            Launcher.refreshPane();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
