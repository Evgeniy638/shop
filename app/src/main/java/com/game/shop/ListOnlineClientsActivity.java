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
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListOnlineClientsActivity extends AppCompatActivity {
    static int countClients = 5;
    private int currentCountClients = countClients;
    Client[] clients = new Client[countClients];
    Handler[] handler = new Handler[countClients];
    TextView[] textViews = new TextView[countClients];

    static int currentIdUser = -1;

    static String[][] messages = new String[countClients][15];
    static int[] totalAmounts = new int[countClients];

    int schhetInString; //теккушее положение элемента в строке(1 или 2)
    LinearLayout linerNikLeft;//Добавил
    LinearLayout linerNikRight;//Добавил

    static Button list_history;

    LinearLayout viewListOnlyClients;

    int i=0;//Добавил


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_list);

        schhetInString=1;//Добавил
        linerNikLeft=findViewById(R.id.list_only_clients_left);//Добавил
        linerNikRight=findViewById(R.id.list_only_clients_right);//Добавил


        viewListOnlyClients = findViewById(R.id.list_only_clients);

        list_history = findViewById(R.id.list_history);

        list_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_history.setEnabled(false);
                Intent i = new Intent(ListOnlineClientsActivity.this, ListPurchaseHistory.class);
                startActivity(i);
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                list_history.setEnabled(true);
                            }
                        },
                        400);
            }
        });

        for (i = 0; i < countClients; i++){
            textViews[i] = addViewClients(viewListOnlyClients.getContext());

            textViews[i].setText("Клиент: " + (i+1));


            final int finalI = i;

            handler[i] = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);

                    if(msg.what == 0){
                        currentCountClients--;
                        //моё
                        deleteViewText(finalI);
                        //моё
                        //твоё: viewListOnlyClients.removeView(textViews[finalI]);
                        ListPurchaseHistory.sendMessage(finalI);
                    }else{
                        int id = Integer.parseInt(((String [])msg.obj)[0].split(":")[0]);
                        totalAmounts[id] = msg.arg1;
                        messages[id] = (String [])msg.obj;

                        if(id == currentIdUser){
                            MoreAboutOnlineClientActivity.sendMessage();
                        }
                        //моё начало
                        int b = Integer.parseInt(((String [])msg.obj)[0].substring(0, ((String [])msg.obj)[0].length()-1));
                        String a = "Клиент: " + (b+1)+" ";
                        for(int j=1;j<15;j++){
                            if( ((String[]) msg.obj)[j] !=""){
                                String[] c =((String[]) msg.obj)[j].split(":");
                                a=a.concat("\n"+ c[0]);
                            }
                        }
                        textViews[b].setText("");
                        textViews[b].setText(a);
                        //моё конец
                    }

                    if(currentCountClients == 0){
                        Context context = new ContextThemeWrapper(viewListOnlyClients.getContext(), R.style.center);
                        TextView textView = new TextView(context);
                        //моё
                        LinearLayout.LayoutParams layoytParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoytParams.gravity=Gravity.CENTER;
                        viewListOnlyClients.removeAllViews();
                        //моё
                        textView.setText("Никого нет онлайн");

                        viewListOnlyClients.addView(textView,layoytParams);
                    }
                }
            };

            textViews[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListOnlineClientsActivity.this, MoreAboutOnlineClientActivity.class);
                    currentIdUser = finalI;
                    intent.putExtra("id", Integer.toString(finalI));
                    startActivity(intent);
                }
            });

            clients[i] = new Client(Integer.toString(i), handler[i]);
            clients[i].start();
        }


    }


    private TextView addViewClients(Context parentContext){
        Context newContext = new ContextThemeWrapper(parentContext, R.style.ForOnline);
        TextView textView = new TextView(newContext);

        //Добавлено начало
        LinearLayout.LayoutParams layoytParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoytParams.setMargins(20,10,20,10);
        //Добавлено конец

        addViewText(textView,layoytParams);//добавил layoutParams

        return textView;
    }
//Моё
    public void addViewText(TextView text,LinearLayout.LayoutParams layoutParams){
        if (schhetInString == 1) {
            layoutParams.gravity=Gravity.LEFT;
            linerNikLeft.addView(text,layoutParams);
            schhetInString++;
        }
        else {
            layoutParams.gravity=Gravity.RIGHT;
            linerNikRight.addView(text,layoutParams);
            schhetInString--;
        }
    }
    public void deleteViewText(int finalId){
        if ( (finalId+1)%2 ==1) {
            linerNikLeft.removeView(textViews[finalId]);
            schhetInString++;
        }
        else {
            linerNikRight.removeView(textViews[finalId]);
            schhetInString--;
        }
    }
//Моё
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
