package com.example.safeword.safeword;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


public abstract class SetupSActivity extends AppCompatActivity {
    protected static final String FILENAME = "info.sav";
    protected ArrayList<String> phoneNumbers = new ArrayList<String>();
    private EditText username;
    private EditText phone1;
    private EditText phone2;
    private EditText phone3;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Setup Info");

        username= findViewById(R.id.username);
        phone1 = findViewById(R.id.phone1);
        phone2 = findViewById(R.id.phone2);
        phone3 = findViewById(R.id.phone3);
        Button saveButton = findViewById(R.id.saveButton);

        fillEditText(phone1, phone2, phone3);
        username.setText("Elizabeth");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                String nameOfUser = username.getText().toString();
                String contact1 = phone1.getText().toString();
                String contact2 = phone2.getText().toString();
                String contact3 = phone3.getText().toString();

                phoneNumbers.add(contact1);
                phoneNumbers.add(contact2);
                phoneNumbers.add(contact3);

                /* Check empty string */
                boolean allFilled = true;
                boolean contacts = checkIfContactExists(contact1, contact2, contact3);
                if(nameOfUser.equals("")){
                    username.setHintTextColor(Color.RED);
                    username.setHint("**");
                    allFilled = false;
                }
                if (!contacts){
                    phone1.setHintTextColor(Color.RED);
                    phone1.setHint("** Need at least 1 contact");
                    allFilled = false;
                }

                /* Save In File */
                if (allFilled){
                    saveInFile();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }


            }
        });
    }

    public abstract void fillEditText(EditText... views);

    private boolean checkIfContactExists(String... contacts){
        for (String contact : contacts) {
            if (!contact.equals("")){
                return true;
            }
        }
        return false;
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(phoneNumbers, out);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
