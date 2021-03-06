package com.game.shop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import static com.game.shop.ListPurchaseHistory.arrId;

public class MoreAboutHistoryClientActivity extends Activity {
    private static String[] data;
    private static int totalAmount = 0;

    private static LinearLayout listProducts;
    private static TextView totalSumView;

    private static boolean isDraw = false;
    public static int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = ListPurchaseHistory.currentId;

        ((TextView)findViewById(R.id.clients)).setText("Клиент: " + (id + 1));

        listProducts = findViewById(R.id.list_products);
        totalSumView = findViewById(R.id.sum);

        if(ListPurchaseHistory.currentIndexId == 0){
            findViewById(R.id.left_arrow).setVisibility(View.INVISIBLE);
        }

        if(ListPurchaseHistory.currentIndexId == arrId.size() - 1){
            findViewById(R.id.right_arrow).setVisibility(View.INVISIBLE);
        }

        findViewById(R.id.left_arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newIndexId = ListPurchaseHistory.currentIndexId - 1;
                if (newIndexId >= 0 &&
                        !ListPurchaseHistory.isAllNull(ListPurchaseHistory.messages[arrId.get(newIndexId)])){
                    listProducts.removeAllViews();
                    ListPurchaseHistory.currentId = arrId.get(newIndexId);
                    id = arrId.get(newIndexId);
                    ((TextView)findViewById(R.id.clients)).setText("Клиент: " + (id + 1));
                    ListPurchaseHistory.currentIndexId = newIndexId;
                    sendMessage();
                }

                if(newIndexId == 0){
                    findViewById(R.id.left_arrow).setVisibility(View.INVISIBLE);
                    findViewById(R.id.right_arrow).setVisibility(View.VISIBLE);
                }else {
                    findViewById(R.id.right_arrow).setVisibility(View.VISIBLE);
                    findViewById(R.id.left_arrow).setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.right_arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newIndexId = ListPurchaseHistory.currentIndexId + 1;
                if (newIndexId <= arrId.size() - 1 &&
                        !ListPurchaseHistory.isAllNull(ListPurchaseHistory.messages[arrId.get(newIndexId)])){
                    listProducts.removeAllViews();
                    ListPurchaseHistory.currentId = arrId.get(newIndexId);
                    id = ListPurchaseHistory.currentId;
                    ((TextView)findViewById(R.id.clients)).setText("Клиент: " + (id + 1));
                    ListPurchaseHistory.currentIndexId = newIndexId;
                    sendMessage();
                }

                if(newIndexId == arrId.size() - 1){
                    findViewById(R.id.right_arrow).setVisibility(View.INVISIBLE);
                    findViewById(R.id.left_arrow).setVisibility(View.VISIBLE);
                }else {
                    findViewById(R.id.right_arrow).setVisibility(View.VISIBLE);
                    findViewById(R.id.left_arrow).setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDraw = false;
                ListPurchaseHistory.currentId = -1;
                finish();
            }
        });

        isDraw = true;
        sendMessage();
    }

    public static void sendMessage(){
        data = ListPurchaseHistory.messages[ListPurchaseHistory.currentId];
        totalAmount = ListPurchaseHistory.totalAmounts[ListPurchaseHistory.currentId];

        if(isDraw) drawList();
    }

    @SuppressLint("SetTextI18n")
    private static void drawList(){
        totalSumView.setText(totalAmount + "руб.");

        if (data == null) return;

        listProducts.removeAllViews();

        //Добавлено начало
        LinearLayout.LayoutParams layoytParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoytParams.setMargins(20,10,20,10);
        //Добавлено конец

        for (int i = 1; i < data.length; i++){
            if (data[i] == null || data[i].equals("")) continue;

            String[] strings = data[i].split(":");

            Context context = new ContextThemeWrapper(listProducts.getContext(), R.style.ForGoods);
            TextView textView = new TextView(context);

            textView.setText(strings[0] + "*" + strings[1]+" = "+ strings[2]);
            /*Изменил:
              textView.setText(strings[0] + " " + strings[1] + "*" + strings[2] + "руб.");
                  textView.setText(textView.getText() + "\t= " +
                  Integer.parseInt(strings[1]) * Integer.parseInt(strings[2]) + "руб.");*/

            listProducts.addView(textView,layoytParams);//Добавлено layoutParams
        }
    }
}
