package com.informagic;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void Spielauswahloeffnen(View a){
    	Intent intent = new Intent(this, Spielauswahl.class);
    	startActivity(intent);
    }
    public void Optionenauswahloeffnen(View b){
    	Intent intent = new Intent(this, Optionenauswahl.class);
    	startActivity(intent);
    	Toast.makeText(getApplicationContext(), "gggg", 3);
    }
    public void Creditsoeffnen(View c){
    	Intent intent = new Intent(this, Credits.class);
    	startActivity(intent);
    }
}
