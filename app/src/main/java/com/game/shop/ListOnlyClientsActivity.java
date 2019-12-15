package com.game.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListOnlyClientsActivity extends AppCompatActivity {
    int countClients = 5;
    Client[] clients = new Client[countClients];
    Handler[] handler = new Handler[countClients];
    TextView[] textViews = new TextView[countClients];

    static int currentIdUser = -1;

    LinearLayout viewListOnlyClients;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_list);

        viewListOnlyClients = findViewById(R.id.list_only_clients);

        (findViewById(R.id.list_history)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListOnlyClientsActivity.this, ListPurchaseHistory.class);
                startActivity(i);
            }
        });

        for (int i = 0; i < countClients; i++){
            textViews[i] = addViewClients(viewListOnlyClients.getContext());

            final int finalI = i;

            handler[i] = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);

                    if(msg.what == 0){
                        viewListOnlyClients.removeView(textViews[finalI]);
                    }else if(((String [])msg.obj)[0].equals(currentIdUser + ":")){
                        MoreAboutClientActivity.sendMessage((String[]) msg.obj);
                    }
                }
            };

            textViews[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListOnlyClientsActivity.this, MoreAboutClientActivity.class);
                    currentIdUser = finalI;
                    intent.putExtra("id", Integer.toString(finalI));
                    startActivity(intent);
                }
            });

            clients[i] = new Client(Integer.toString(i), handler[i]);
            clients[i].start(/* handler[i] */);
        }
    }

    private TextView addViewClients(Context parentContext){
        Context newContext = new ContextThemeWrapper(parentContext, R.style.ForOnline);
        TextView textView = new TextView(newContext);

        viewListOnlyClients.addView(textView);

        return textView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
