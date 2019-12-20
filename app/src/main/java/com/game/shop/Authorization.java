package com.game.shop;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;

public class Authorization extends Activity {
    private String login = "DuHast";
    private String password = "IchWill";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.authorization);

        VideoView videoPlayer = (VideoView) findViewById(R.id.videoView);
        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.ricardo);
        videoPlayer.setVideoURI(myVideoUri);
        videoPlayer.start( );

        videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView logView = findViewById(R.id.login);
                TextView passView = findViewById(R.id.Pas);

                String log = logView.getText().toString();
                String pass = passView.getText().toString();


                if(log.equals(login) && pass.equals(password)){
                    Intent i = new Intent(Authorization.this, ListOnlineClientsActivity.class);
                    startActivity(i);
                }else {
                    logView.setText("");
                    passView.setText("");

                    logView.setHint("Неправильная кликуха");
                    passView.setHint("или кодовое слово :)");
                }
            }
        });
    }
}
