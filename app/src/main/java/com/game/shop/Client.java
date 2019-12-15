package com.game.shop;

public class Client extends Thread {
    Validator validator;

    @Override
    public synchronized void start() {
        super.start();
        validator = new Validator();
    }

    @Override
    public void run() {
        super.run();

        int countGoods = (int)(Math.random() * 20);

        for (int i = 0; i < countGoods; i++){
            try {
                sleep((long)(Math.random() * 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(Math.random() < 0.3){
                //логика возвращения рандомного товара
                validator.returnGoods(/*товар из его списка*/);
            }else {
                validator.takeGoods(/*рандомный товар из списка возможных*/);
            }
        }

        Сashbox.calculate(validator);
    }
}
