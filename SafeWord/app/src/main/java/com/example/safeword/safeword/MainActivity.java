package com.example.safeword.safeword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView callButton;
    private ImageView setupButton;
    private TextView setupText;
    private ImageView infoButton;
    private TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callButton = (ImageView) findViewById(R.id.callButton);
        setupButton = (ImageView) findViewById(R.id.setupButton);
        setupText = (TextView) findViewById(R.id.setupText);
        infoButton = (ImageView) findViewById(R.id.infoButton);
        infoText = (TextView) findViewById(R.id.infoText);

        setupButton.setOnClickListener(setup);
        setupText.setOnClickListener(setup);

        infoButton.setOnClickListener(info);
        infoText.setOnClickListener(info);


    }

    View.OnClickListener setup = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SetupActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener info = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
    };

}
