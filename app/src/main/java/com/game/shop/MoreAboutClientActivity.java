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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listProducts = findViewById(R.id.list_products);

        findViewById(R.id.back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        drawList();
    }

    public static void sendMessage(String[] strings){
        data = strings;
        drawList();
    }

    private static void drawList(){
        for (int i = 0; i < data.length; i++){
            Context context = new ContextThemeWrapper(listProducts.getContext(), R.style.ForGoods);
            TextView textView = new TextView(context);

            textView.setText(data[i]);

            listProducts.addView(textView);
        }
    }
}
