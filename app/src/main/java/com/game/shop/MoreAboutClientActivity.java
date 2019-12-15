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

public class MoreAboutClientActivity extends Activity {
    private static String[] data;
    private static int totalAmount = 0;

    private static LinearLayout listProducts;
    private static TextView totalSumView;

    private static boolean isDraw = false;
    public static String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = getIntent().getStringExtra("id");

        ((TextView)findViewById(R.id.clients)).setText("Клиент: " + id);

        listProducts = findViewById(R.id.list_products);
        totalSumView = findViewById(R.id.sum);

        if(ListOnlyClientsActivity.currentIdUser == 0){
            findViewById(R.id.left_arrow).setVisibility(View.INVISIBLE);
        }

        if(ListOnlyClientsActivity.currentIdUser == ListOnlyClientsActivity.countClients - 1){
            findViewById(R.id.right_arrow).setVisibility(View.INVISIBLE);
        }

        findViewById(R.id.left_arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ListOnlyClientsActivity.currentIdUser - 1 >= 0){
                    listProducts.removeAllViews();
                    ListOnlyClientsActivity.currentIdUser--;
                    id = Integer.toString(ListOnlyClientsActivity.currentIdUser);
                    ((TextView)findViewById(R.id.clients)).setText("Клиент: " + id);
                    sendMessage();
                }

                if(ListOnlyClientsActivity.currentIdUser == 0){
                    findViewById(R.id.left_arrow).setVisibility(View.INVISIBLE);
                }else {
                    findViewById(R.id.right_arrow).setVisibility(View.VISIBLE);
                    findViewById(R.id.left_arrow).setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.right_arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ListOnlyClientsActivity.currentIdUser + 1 <= ListOnlyClientsActivity.countClients - 1){
                    listProducts.removeAllViews();
                    ListOnlyClientsActivity.currentIdUser++;
                    id = Integer.toString(ListOnlyClientsActivity.currentIdUser);
                    ((TextView)findViewById(R.id.clients)).setText("Клиент: " + id);
                    sendMessage();
                }

                if(ListOnlyClientsActivity.currentIdUser == ListOnlyClientsActivity.countClients - 1){
                    findViewById(R.id.right_arrow).setVisibility(View.INVISIBLE);
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
                ListOnlyClientsActivity.currentIdUser = -1;
                finish();
            }
        });

        isDraw = true;
        sendMessage();
    }

    public static void sendMessage(){
        data = ListOnlyClientsActivity.messages[ListOnlyClientsActivity.currentIdUser];
        MoreAboutClientActivity.totalAmount = ListOnlyClientsActivity.totalAmounts[ListOnlyClientsActivity.currentIdUser];

        if(isDraw) drawList();
    }

    @SuppressLint("SetTextI18n")
    private static void drawList(){
        totalSumView.setText(totalAmount + "руб.");

        if (data == null) return;

        listProducts.removeAllViews();

        for (int i = 1; i < data.length; i++){
            if (data[i] == null || data[i].equals("")) continue;

            Context context = new ContextThemeWrapper(listProducts.getContext(), R.style.ForGoods);
            TextView textView = new TextView(context);

            textView.setText(data[i]);

            listProducts.addView(textView);
        }
    }
}
