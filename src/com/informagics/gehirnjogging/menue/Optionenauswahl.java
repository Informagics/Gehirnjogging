package com.informagics.gehirnjogging.menue;

import com.informagics.gehirnjogging.R;

import android.os.Bundle;
import android.view.WindowManager;
import android.app.Activity;

public class Optionenauswahl extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optionenauswahl);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

}
