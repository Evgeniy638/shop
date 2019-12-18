package com.game.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import static com.game.shop.ListOnlineClientsActivity.countClients;

public class ListPurchaseHistory extends Activity {
    static LinearLayout viewListHistory;

    static Context listHistoryContext;

    static String[][] messages = new String[countClients][15];
    static int[] totalAmounts = new int[countClients];
    static int currentId = -1;

    private static boolean isDraw = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pribil);

        listHistoryContext = this;

        viewListHistory = findViewById(R.id.list_history);

        findViewById(R.id.online).setOnClickListener(new View.OnClickListener() {
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
        ListPurchaseHistory.messages[finalI] = ListOnlineClientsActivity.messages[finalI];
        ListPurchaseHistory.totalAmounts[finalI] = ListOnlineClientsActivity.totalAmounts[finalI];

        if(isDraw) drawList();
    }

    private static void drawList(){
        viewListHistory.removeAllViews();

        for (int i = 0; i < messages.length; i++){
            if(isAllNull(messages[i])) continue;

            TextView textView = addViewClients(viewListHistory.getContext());

            textView.setText("Клиент: " + i);

            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(listHistoryContext, MoreAboutHistoryClientActivity.class);
                    currentId = finalI;
                    listHistoryContext.startActivity(intent);
                }
            });
        }
    }

    private static TextView addViewClients(Context parentContext){
        Context newContext = new ContextThemeWrapper(parentContext, R.style.ForOnline);
        TextView textView = new TextView(newContext);

        viewListHistory.addView(textView);

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
