package com.game.shop;

import java.util.HashMap;
import java.util.Map;

public class ShopHistory {


    static String[] arrahHistory = new String[3200];
    static boolean[] array=new boolean[3200];


    public static void saveHistory(int id,String arrayFromValid){
        arrahHistory[id]=arrayFromValid;
        array[id]=true;
    }
    public static String[] getHistory(int id){
      return null;
    }
}
