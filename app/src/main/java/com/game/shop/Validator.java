package com.game.shop;

public class Validator {
    //список товаров
    /*
        ...
    */
    private String[] arryOfShoper= new String[100];//хранилище операций
    private int totalAmount=0;
    private int currentParsher=0;//номер текущей покупки

    //параметр будет товар
    //product="id товара+ колличество товара"
    public void takeGoods(String product){
        String[] a = product.split(":");
        if(Shop.getQoanity(Integer.parseInt(a[1]))!=0){
            //получаем количество взятого в магазине товара
            a[1]=String.valueOf(Shop.setQuanityTake( Integer.parseInt(a[0]),Integer.parseInt(a[0])));
            //сохраняем в история
            arryOfShoper[currentParsher] =a[0]+a[1];
            //Общая стоимость повышается
            totalAmount+=Shop.getPrice(Integer.parseInt(a[0]))*Integer.parseInt(a[1]);
        }
    }

    //параметр будет товар
    public void returnGoods(String product){
        String[] a = product.split(":");
        //Возвращаем товар
        Shop.setQuanityPut( Integer.parseInt(a[0]),Integer.parseInt(a[0]));
        arryOfShoper[currentParsher] = a[0]+":-"+a[1];//минус указывает,что был возврат
        //Общая стоимость снижается
        totalAmount-=Shop.getPrice(Integer.parseInt(a[0]))*Integer.parseInt(a[1]);
    }
}
