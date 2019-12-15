package com.game.shop;

public class Client extends Thread {
    Validator validator;
    String name="";

    Client(String name){
        this.name=name;
    }
    @Override
    public synchronized void start() {
        super.start();
        validator = new Validator("a");
    }

    @Override
    public  void run() {
        super.run();

        int countGoods = (int)(Math.random() * 20);

        for (int i = 0; i < countGoods; i++){
            try {
                sleep((long)(Math.random() * 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(Math.random() < 0.3){
                int[] IdList=validator.getIdList();//id всех товаров в списке
                if(IdList[0]!=0) {//Если список не пуст
                    int randGoodId =1+ (int) (Math.random() * IdList[0]);
                    randGoodId=IdList[randGoodId];//выбираем рандомный товар из нашего списка

                    int randGoodQuanity= 1 + (int)(Math.random() * validator.getQuanity(randGoodId));//Колличество возвр. товара
                    String a= String.valueOf(randGoodId)+ String.valueOf(randGoodQuanity);
                    validator.returnGoods(a);
                }
                //логика возвращения рандомного товара
            }else {
                int randGoodId=0+(int)(Math.random()*14);//Выбираем рандомный товар
                int randGoodQuanity = 1+(int)(Math.random()*100);//колличество рандомного товара
                String a= String.valueOf(randGoodId)+ String.valueOf(randGoodQuanity);
                validator.takeGoods(a);
            }
        }

        Сashbox.calculate(validator);
    }
}
