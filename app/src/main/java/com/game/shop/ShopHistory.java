package com.game.shop;

public class ShopHistory {
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
