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
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListOnlyClientsActivity extends AppCompatActivity {
    int countClients = 5;
    Client[] clients = new Client[countClients];
    Handler[] handler = new Handler[countClients];
    TextView[] textViews = new TextView[countClients];

    LinearLayout viewListOnlyClients;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_list);

        viewListOnlyClients = findViewById(R.id.list_only_clients);

        for (int i = 0; i < countClients; i++){
            textViews[i] = addViewClients(viewListOnlyClients.getContext());

            final int finalI = i;
            handler[i] = new Handler(){
                @Override
                public void handleMessage(@NonNull final Message msg) {
                    super.handleMessage(msg);

                    if(msg.what == 1){
                        textViews[finalI].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ListOnlyClientsActivity.this, MoreAboutClientActivity.class);
                                String data = (String) msg.obj;//данные о пользователе
                                i.putExtra("data", data);
                                startActivity(i);
                            }
                        });
                    }else {
                        removeViewClients(textViews[finalI]);
                    }
                }
            };

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

    private void removeViewClients(TextView textView){
        textView.getParent();
    }
}
