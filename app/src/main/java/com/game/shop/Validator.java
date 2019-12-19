package com.game.shop;

public class Validator {
    Validator(String id){
        this.id = id +":";
        for(int i=0;i<100;i++)arryOfShoper[i]="";
        for(int i=0;i<100;i++)shoperList[i]=0;
        answer[0]=this.id;
        for(int i=1;i<100;i++) answer[i]="";
    }
    //список товаров
    /*
        ...
    */
    private String id;
    private String[] arryOfShoper= new String[1000];//хранилище операций
    private String[] answer=new String[100];
    private int[] shoperList = new int[100];//колличество вариантов товара(с запасом) в списке покупок,номер элемента соответствует id товара
    private int totalAmount=0;



    //product="id товара:колличество товара"
    //Покупаем товар
    public String[] takeGoods(String product){
        String[] a = product.split(":");
        if(Shop.getQoanity(Integer.parseInt(a[0]))!=0){
            if(Shop.getQoanity(Integer.parseInt(a[0])) > Integer.parseInt(a[1])){//Если а магазине товара больше,чем мы хотим
                shoperList[Integer.parseInt(a[0])] +=Integer.parseInt(a[1]);//записываем в список
                totalAmount += Shop.getPrice(Integer.parseInt(a[0])) * Integer.parseInt(a[1]);
                Shop.setQuanityTake(Integer.parseInt(a[0]),Integer.parseInt(a[1]));//забираем из магазина нужное колличество
                makeAnswer(a[0],String.valueOf(shoperList[Integer.parseInt(a[0])]));
            }
            else {//Если а магазине товара меньше, чем мы хотим или равно
                shoperList[Integer.parseInt(a[0])] +=Shop.getQoanity(Integer.parseInt(a[0]));//записываем в список
                totalAmount -= Shop.getPrice(Integer.parseInt(a[0])) * Shop.getQoanity(Integer.parseInt(a[0]));
                Shop.setQuanityTake(Integer.parseInt(a[0]),Shop.getQoanity(Integer.parseInt(a[0])));//забираем из магазина всё,что осталось
                makeAnswer(a[0],String.valueOf(shoperList[Integer.parseInt(a[0])]));
            }
        }
        return answer;
    }
    //Возвращаем товар
    public String[] returnGoods(String product){
                 String[] a = product.split(":");
                //Общая стоимость снижается
                shoperList[Integer.parseInt(a[0])] -=Integer.parseInt(a[1]);//записываем в shoperList
                totalAmount -= Shop.getPrice(Integer.parseInt(a[0])) * Integer.parseInt(a[1]);
                Shop.setQuanityPut(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
                return makeAnswer(a[0],String.valueOf(shoperList[Integer.parseInt(a[0])]));
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
            if(shoperList[i]!=0) {
                array[n+1]=i;
                n++;
            }
        }
        return array;
    }//возвращает массив id товаров,0левой элемент-колличество товаров
    public int getQuanity(int id){
        return shoperList[id];
    }//колличество определённого товара

    public int getTotalAmount(){
        return totalAmount;
    }//текущая сумма покупки

    public void saveHistory(){//Делаем чек вида [id][колличество покупок][строка][строка]
        String[] arr= new String[this.getIdList()[0]+2];
        arr[0]=id.substring(0,id.length()-1);//записываем id
        arr[1]=String.valueOf(this.getIdList()[0]);//колличество строк с покупками в чеке
        for(int i=2;i<this.getIdList()[0]+2;i++){
            arr[i]=String.valueOf(this.getIdList()[i-1]) +":"+String.valueOf(this.getQuanity(this.getIdList()[i-1]));
        }
        ShopHistory.saveHistory(Integer.parseInt(id.substring(0,id.length()-1)),arr);//сохраняем чек
    }

    public String[] makeAnswer(String idProd,String amountProd){
        if(!amountProd.equals("0")){
            //Хотим положить,но изначально пусто
            answer[Integer.parseInt(idProd)+1] = Shop.getName(Integer.parseInt(idProd))+":"+amountProd+":"+Integer.parseInt(amountProd) * Shop.getPrice(Integer.parseInt(idProd)) + " \u20BD";
        }
        else answer[Integer.parseInt(idProd)+1] = "";
        return answer;
    }
}
