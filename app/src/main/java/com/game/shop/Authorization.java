package com.game.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Authorization extends Activity {
    private String login = "DuHast";
    private String password = "IchWill";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.authorization);

        findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView logView = findViewById(R.id.login);
                TextView passView = findViewById(R.id.Pas);

                String log = logView.getText().toString();
                String pass = passView.getText().toString();

                if((log.equals(login) && pass.equals(password)) || true){
                    Intent i = new Intent(Authorization.this, ListOnlineClientsActivity.class);
                    startActivity(i);
                }else {
                    logView.setText("");
                    passView.setText("");

                    logView.setHint("Неправильный логин");
                    passView.setHint("или пароль :)");
                }
            }
        });
    }
}
