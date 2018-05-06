package com.example.safeword.safeword;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.telephony.gsm.SmsManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView callButton;
    private ImageView setupButton;
    private TextView setupText;
    private ImageView infoButton;
    private TextView infoText;
    private String txtString = "smsto:000911,";
    protected static final String FILENAME = "info.sav";
    protected ArrayList<String> phoneNumbers = new ArrayList<String>();

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

        callButton.setOnClickListener(message);
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

    View.OnClickListener message = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse(getTxtString()));
//            intent.putExtra("sms_body", "This is Elizabeth. I am in danger right now. My location is at: DEVICE LOCATION HERE");
//            startActivity(intent);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://2cc0eb50.ngrok.io/make_call_function"));
            startActivity(intent);
        }
    };

    public String getTxtString(){
        loadFromFile();
        for(int i=0; i<phoneNumbers.size();i++){
            txtString = txtString + phoneNumbers.get(i) + ",";
        }
        return txtString;
    }

    protected void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<String>>(){}.getType();

            phoneNumbers = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            phoneNumbers = new ArrayList<String>();
        }
    }


}
