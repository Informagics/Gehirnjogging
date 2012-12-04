package com.informagics.gehirnjogging.menue;

import com.informagics.gehirnjogging.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void Spielauswahloeffnen(View a){
    	Intent intent = new Intent(this, Spielauswahl.class);
    	startActivity(intent);
    }
    
    public void Optionenauswahloeffnen(View b){
    	Intent intent = new Intent(this, Optionenauswahl.class);
    	startActivity(intent);
    }
    
    public void Creditsoeffnen(View c){
    	Intent intent = new Intent(this, Credits.class);
    	startActivity(intent);
    }
    
    public void Nachschlagewerkoeffnen(View c){
    	Intent intent = new Intent(this, Nachschlagewerk.class);
    	startActivity(intent);
    }
}
