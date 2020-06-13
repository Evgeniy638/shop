package com.game.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static com.game.shop.ListOnlineClientsActivity.countClients;

public class ListPurchaseHistory extends Activity {
    static LinearLayout viewListHistory;

    static Context listHistoryContext;

    static String[][] messages = new String[countClients][15];
    static int[] totalAmounts = new int[countClients];
    static int currentId = -1;
    static int currentIndexId = -1;
    static ArrayList<Integer> arrId = new ArrayList<>(0);

    private static Button onlineButton;

    private static boolean isDraw = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pribil);

        onlineButton = findViewById(R.id.online);

        onlineButton.setEnabled(false);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onlineButton.setEnabled(true);
                    }
                },
                400);

        listHistoryContext = this;

        viewListHistory = findViewById(R.id.list_history);

        onlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentId = -1;
                isDraw = false;
                finish();
            }
        });

        isDraw = true;
        drawList();
    }

    public static void sendMessage(int finalI){
        if(isAllNull( ListOnlineClientsActivity.messages[finalI])) return;

        ListPurchaseHistory.messages[finalI] = ListOnlineClientsActivity.messages[finalI];
        ListPurchaseHistory.totalAmounts[finalI] = ListOnlineClientsActivity.totalAmounts[finalI];

        arrId.add(finalI);

        if(isDraw) drawList();
    }

    private static void drawList(){
        viewListHistory.removeAllViews();

        for (int i = 0; i < arrId.size(); i++){
            if(isAllNull(messages[arrId.get(i)])) continue;

            TextView textView = addViewClients(viewListHistory.getContext());

            textView.setText("Клиент: " + (arrId.get(i) + 1));

            final int finalI = arrId.get(i);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(listHistoryContext, MoreAboutHistoryClientActivity.class);
                    currentId = finalI;
                    currentIndexId = arrId.indexOf(finalI);
                    listHistoryContext.startActivity(intent);
                }
            });
        }
    }

    private static TextView addViewClients(Context parentContext){
        Context newContext = new ContextThemeWrapper(parentContext, R.style.ForOnline);
        TextView textView = new TextView(newContext);

        //Добавлено начало
        LinearLayout.LayoutParams layoytParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoytParams.setMargins(20,10,20,10);
        //Добавлено конец

        viewListHistory.addView(textView,layoytParams);//Добавил layoutParams

        return textView;
    }

    public static boolean isAllNull(String[] strings){
        for (String string: strings){
            if(string != null){
                return false;
            }
        }

        return true;
    }
}
