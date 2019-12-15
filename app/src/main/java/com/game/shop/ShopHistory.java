package com.game.shop;

import java.util.HashMap;
import java.util.Map;

public class ShopHistory {

    ShopHistory(){

    }

    static String[][] arrahHistory = new String[15][15];
    static boolean[] array=new boolean[15];


    public void saveHistory(int id,String [] arrayFromValid){
        arrahHistory[id]=arrayFromValid;
        array[id]=true;
    }
    public static String[] getHistory(int id){
        if(array[id] == true) return arrahHistory[id];
        else return null;
    }
}
