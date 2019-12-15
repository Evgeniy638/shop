package com.game.shop;

public class Validator {
    Validator(String name){
        this.name=name+":";
        for(int i=0;i<100;i++)arryOfShoper[i]="";
        for(int i=0;i<100;i++)shoperList[i]=0;
    }
    //список товаров
    /*
        ...
    */
    private String name;
    private String[] arryOfShoper= new String[100];//хранилище операций
    private int[] shoperList = new int[100];//колличество товара(с запасом) в списке покупок,номер элемента соответствует id товара
    private int totalAmount=0;
    private int currentParsher=0;//номер текущей покупки

    //параметр будет товар
    //product="id товара:колличество товара"
    public void takeGoods(String product){
        String[] a = product.split(":");
        if(Shop.getQoanity(Integer.parseInt(a[1]))!=0){
            //получаем количество взятого в магазине товара
            a[1]=String.valueOf(Shop.setQuanityTake( Integer.parseInt(a[0]),Integer.parseInt(a[1])));
            //сохраняем в историю
            arryOfShoper[currentParsher] =name+a[0]+a[1];

            shoperList[Integer.parseInt(a[0])] +=Integer.parseInt(a[0]);

            //Общая стоимость повышается
            totalAmount+=Shop.getPrice(Integer.parseInt(a[0]))*Integer.parseInt(a[1]);
        }
        currentParsher++;
    }

    //параметр будет товар
    public void returnGoods(String product){
        String[] a = product.split(":");
        if(shoperList[Integer.parseInt(a[0])]!=0) {//если в корзине есть такой товар
            if(shoperList[Integer.parseInt(a[0])]-Integer.parseInt(a[1])>=0) {//Если в коризине >=колличество,которое хотят вернуть
                //Возвращаем товар
                Shop.setQuanityPut(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
                arryOfShoper[currentParsher] =name+ a[0] + ":-" + a[1];//минус указывает,что был возврат
                //Общая стоимость снижается
                totalAmount -= Shop.getPrice(Integer.parseInt(a[0])) * Integer.parseInt(a[1]);
                currentParsher++;
            }
            else {//возвращаем весь наш товар
                Shop.setQuanityPut(Integer.parseInt(a[0]),shoperList[Integer.parseInt(a[0])] );
                a[1]=String.valueOf(shoperList[Integer.parseInt(a[0])]);
                shoperList[Integer.parseInt(a[0])]=0;
                arryOfShoper[currentParsher] =name+ a[0] + ":-" + a[1];//минус указывает,что был возврат
                totalAmount -= Shop.getPrice(Integer.parseInt(a[0])) * Integer.parseInt(a[1]);
                currentParsher++;
            }
        }
    }

    public int[] getIdList(){
        int n=0;
        for(int i=0;i<100;i++){
            if(shoperList[i]!=0) n++;
        }
        int[] array=new int[n+1];
        array[0]=n;//0-левой элемент массива-это колличество элементов
        n=0;
        for(int i=0;i<100;i++){
            if(shoperList[i]!=0) array[n+1]=shoperList[i];
            n++;
        }
        return array;
    }
    public int getQuanity(int id){
        return shoperList[id];
    }

    public String[] getList(){
        return arryOfShoper;
    }//история операция
    public int getTotalAmount(){
        return totalAmount;
    }//текущая сумма покупки
}
