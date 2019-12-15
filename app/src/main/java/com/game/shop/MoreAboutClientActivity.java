package com.game.shop;

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

    private static LinearLayout listProducts;

    private static boolean isDraw = false;
    private static String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = getIntent().getStringExtra("id");

        listProducts = findViewById(R.id.list_products);

        findViewById(R.id.back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDraw = false;
                finish();
            }
        });

        drawList();
        isDraw = true;
    }

    public static synchronized void sendMessage(String[] strings){
        if (!strings[0].equals(id + ":")) return;

        data = strings;

        if(isDraw) drawList();
    }

    private static void drawList(){
        if (data == null) return;

        listProducts.removeAllViews();

        for (int i = 1; i < data.length; i++){
            if (data[i] == null) continue;

            Context context = new ContextThemeWrapper(listProducts.getContext(), R.style.ForGoods);
            TextView textView = new TextView(context);

            textView.setText(data[i]);

            listProducts.addView(textView);
        }
    }
}
