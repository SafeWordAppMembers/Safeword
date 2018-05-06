package com.example.safeword.safeword;

import android.widget.EditText;

public class SetupActivity extends SetupSActivity {

    @Override
    public void fillEditText(EditText... views) {
        loadFromFile();
        int i = 0;
        for(EditText view: views){
            view.setText(phoneNumbers.get(i));
            i++;
        }
        phoneNumbers.clear();
    }

}
