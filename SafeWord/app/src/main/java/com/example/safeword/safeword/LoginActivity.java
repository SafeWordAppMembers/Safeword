package com.example.safeword.safeword;

import android.content.Intent;
import android.widget.EditText;

public class LoginActivity extends SetupSActivity {

    public void fillEditText(EditText... views){
        loadFromFile();
        if (phoneNumbers.size()>0){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            return;
        }
        int i = 0;
        for(EditText view: views){
            i++;
            view.setHint("Phone # of Contact" + Integer.toString(i));
        }
    }

}
