package com.game.shop;

public class Validator {
    Validator(String id){
        this.id = id +":";
        for(int i=0;i<100;i++)arryOfShoper[i]="";
        for(int i=0;i<100;i++)shoperList[i]=0;
        answer[0]=this.id;
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
    private int currentParsher=0;//номер текущей покупки


    //параметр будет товар
    //product="id товара:колличество товара"
    public String[] takeGoods(String product){
        String[] a = product.split(":");
        if(Shop.getQoanity(Integer.parseInt(a[0]))!=0){
            //получаем количество взятого в магазине товара
            a[1]=String.valueOf(Shop.setQuanityTake( Integer.parseInt(a[0]),Integer.parseInt(a[1])));
            //сохраняем в историю
            arryOfShoper[currentParsher] = id +a[0]+":"+a[1];

            shoperList[Integer.parseInt(a[0])] +=Integer.parseInt(a[1]);

            //Общая стоимость повышается
            totalAmount+=Shop.getPrice(Integer.parseInt(a[0]))*Integer.parseInt(a[1]);
            currentParsher++;
            return makeAnswer(a[0],a[1]);
        }
        else return null;//товар закончился
    }
    
    public String[] returnGoods(String product){
        String[] a = product.split(":");
        if(shoperList[Integer.parseInt(a[0])]!=0) {//если в корзине есть такой товар
            if(shoperList[Integer.parseInt(a[0])]-Integer.parseInt(a[1])>=0) {//Если в коризине >=колличество,которое хотят вернуть
                //Возвращаем товар
                Shop.setQuanityPut(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
                arryOfShoper[currentParsher] = id + a[0] + ":-" + a[1];//минус указывает,что был возврат
                //Общая стоимость снижается
                totalAmount -= Shop.getPrice(Integer.parseInt(a[0])) * Integer.parseInt(a[1]);
                currentParsher++;
            }
            else {//возвращаем весь наш товар
                Shop.setQuanityPut(Integer.parseInt(a[0]),shoperList[Integer.parseInt(a[0])] );
                a[1]=String.valueOf(shoperList[Integer.parseInt(a[0])]);
                shoperList[Integer.parseInt(a[0])]=0;
                arryOfShoper[currentParsher] = id + a[0] + ":-" + a[1];//минус указывает,что был возврат
                totalAmount -= Shop.getPrice(Integer.parseInt(a[0])) * Integer.parseInt(a[1]);
                currentParsher++;
            }
            return makeAnswer(a[0],":-"+a[1]) ;
        }
        else return null;//корзина пуста
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

    public String[] getList(){
        return arryOfShoper;
    }//история операция
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
        if(answer[Integer.parseInt(idProd)+1].length()>2){
            String[] a = answer[Integer.parseInt(idProd)+1].split(":");
            a[1]=String.valueOf(Integer.parseInt(a[1]) + amountProd);
            answer[Integer.parseInt(idProd)+1] = Shop.getName(Integer.parseInt(idProd))+":"+a[1]+":"+Shop.getPrice(Integer.parseInt(idProd));
        }
        else answer[Integer.parseInt(idProd)+1] = Shop.getName(Integer.parseInt(idProd))+":"+amountProd+":"+Shop.getPrice(Integer.parseInt(idProd));
        return answer;
    }
}
