package com.example.safeword.safeword;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private static final String FILENAME = "info.sav";
    private ArrayList<String> phoneNumbers = new ArrayList<String>();
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

        username.setHint("Your Name");
        phone1.setHint("Phone # of Contact 1");
        phone2.setHint("Phone # of Contact 2");
        phone3.setHint("Phone # of Contact 3");

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
                }


            }
        });
    }

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
}
