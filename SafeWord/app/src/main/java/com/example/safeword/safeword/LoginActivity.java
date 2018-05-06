package com.example.safeword.safeword;

import android.widget.EditText;

public class LoginActivity extends SetupSActivity {

    public void setHints(EditText... views){
        int i = 0;
        for(EditText view: views){
            view.setHint("Phone # of Contact" + Integer.toString(i++));
        }
    }

}
