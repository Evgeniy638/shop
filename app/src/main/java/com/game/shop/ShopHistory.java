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
    public static int[] getOffline(){
        int n=0;
        for(int i=0;i<320;i++) if(array[i] == true)n++;
        int[] arrInt = new int[n+1];
        arrInt[0]=n;
        for(int i=1;i<=n;i++) if(array[i]==true) {
            arrInt[n + 1] = i;
            n++;
        }
        return arrInt;
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
