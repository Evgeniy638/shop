package com.game.shop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public  class Shop {
    static String[] arrayShopName;
    static int[] arrayShopQuanity;
    static int[] arrayShopPeice;
    public static void main(String[] args){
        arrayShopName = new String[15];
        //Название:цена:колличество
        arrayShopName[0]="Виски:1500:100";
        arrayShopName[1]="Пиво:150:100";
        arrayShopName[2]="Бренди:800:100";
        arrayShopName[3]="Водка:500:100";
        arrayShopName[4]="Вино:750:100";
        arrayShopName[5]="Ром:800:100";
        arrayShopName[6]="Абсент:650:100";
        arrayShopName[7]="Сидр:450:100";
        arrayShopName[8]="Джин:500:100";
        arrayShopName[9]="Тоник:750:100";
        arrayShopName[10]="Текила950:100";
        arrayShopName[11]="Самбука600:100";
        arrayShopName[12]="Ликёр:1500:100";
        arrayShopName[13]="Портвейн:700:100";
        arrayShopName[14]="Мадера:950:100";
        arrayShopName[15]="Вода:100:100";

    }

    public static int getQoanity(int id){ //получить количество товара
        String[] a = arrayShopName[id].split(":");
        return Integer.parseInt(a[2]);
    }
    public static  int setQuanity(int id,int quanity){ //добавить-взять товар
        String[] a = arrayShopName[id].split(":");
        if((Integer.parseInt(a[2]) + quanity)>=0 ) {
            a[2] = String.valueOf(Integer.parseInt(a[2]) + quanity);
                arrayShopName[id] = a[0]+a[1]+a[2];
                return Integer.parseInt(a[2]) + quanity;
                }
        else return -1; //товар закончился
    }
    public static String getName(int id){ //получить название товара
        String[] a = arrayShopName[id].split(":");
        return a[0];
    }

    public static int getPrice(int id){//получите цену товара
        String[] a = arrayShopName[id].split(":");
        return Integer.parseInt(a[1]);
    }

}
