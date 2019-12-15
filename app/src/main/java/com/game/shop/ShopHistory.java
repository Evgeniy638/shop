package com.game.shop;

import java.util.HashMap;
import java.util.Map;

public class ShopHistory {


    static String[][] arrahHistory = new String[3200][3200];
    static boolean[] array=new boolean[3200];


    public static void saveHistory(int id,String[] arrayFromValid){
        arrahHistory[id]=arrayFromValid;
        array[id]=true;
    }
    public static String[] getHistory(int id){//Возвращает чек
        boolean j =false;
        String[] arr = new String[3200];
        for(int i=0;i<3200;i++){
            if(arrahHistory[i][0] == String.valueOf(id))
            {
                j=true;
                arr = arrahHistory[i];
            }
        }
        if(j == false) return null;
        else return arr;
    }
}
