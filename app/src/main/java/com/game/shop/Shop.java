package com.game.shop;

public  class Shop {
    static String[] arrayShop;
    static {
        arrayShop = new String[15];
        //Название:цена:колличество
        arrayShop[0]="Виски:1500:100";
        arrayShop[1]="Пиво:150:100";
        arrayShop[2]="Бренди:800:100";
        arrayShop[3]="Водка:500:100";
        arrayShop[4]="Вино:750:100";
        arrayShop[5]="Ром:800:100";
        arrayShop[6]="Абсент:650:100";
        arrayShop[7]="Сидр:450:100";
        arrayShop[8]="Джин:500:100";
        arrayShop[9]="Тоник:750:100";
        arrayShop[10]="Текила:950:100";
        arrayShop[11]="Самбука:600:100";
        arrayShop[12]="Ликёр:1500:100";
        arrayShop[13]="Портвейн:700:100";
        arrayShop[14]="Мадера:950:100";
    }


    public static int setQuanityTake (int id,int quanity){//Взять товар
        String[] a = arrayShop[id].split(":");
        if(Integer.parseInt(a[2]) < 0) return -1;//товар закончился
        else if((Integer.parseInt(a[2]) - quanity)>=0 ) {
            a[2] = String.valueOf(Integer.parseInt(a[2]) - quanity);
            arrayShop[id] = a[0]+":"+a[1]+":"+a[2];
            return quanity; //возвращает колличество взятого товара
        }
        else {
            //Товара меньше,чем хотел человек.Поэтому он забирает всё
            quanity = Integer.parseInt(a[2]);
            arrayShop[id] = a[0]+":"+a[1]+":"+"0";
            return quanity;
        }
    }

    public static  int setQuanityPut(int id,int quanity){ //вернуть товар
        String[] a = arrayShop[id].split(":");
            a[2] = String.valueOf(Integer.parseInt(a[2]) + quanity);
            arrayShop[id] = a[0]+":"+a[1]+":"+a[2];
            return quanity; //возвращает колличество возвращённого товара
    }

    public static String getName(int id){ //получить название товара
        String[] a = arrayShop[id].split(":");
        return a[0];
    }

    public static int getPrice(int id){//получите цену товара
        String[] a = arrayShop[id].split(":");
        return Integer.parseInt(a[1]);
    }
    public static int getQoanity(int id){ //получить количество товара
        String[] a = arrayShop[id].split(":");
        return Integer.parseInt(a[2]);
    }
}
