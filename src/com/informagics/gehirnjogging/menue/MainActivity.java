package com.informagics.gehirnjogging.menue;

import com.informagics.gehirnjogging.Highscore;
import com.informagics.gehirnjogging.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {
	
	MediaPlayer menuesound,click;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=MediaPlayer.create(this, R.raw.menuepunkt_auswahl);
        menuesound=MediaPlayer.create(this, R.raw.menue_sound);
        menuesound.start();
    }
    
    public void Spielauswahloeffnen(View a){
    	Intent intent = new Intent(this, Spielauswahl.class);
    	click.start();
    	startActivity(intent);
    }
    
    public void Optionenauswahloeffnen(View b){
    	Intent intent = new Intent(this, Optionenauswahl.class);
    	click.start();
    	startActivity(intent);
    }
    
    public void Highscoreoeffnen(View a){
    	Intent intent = new Intent(this, Highscore.class);
    	startActivity(intent);
    }
    
    public void Creditsoeffnen(View c){
    	Intent intent = new Intent(this, Credits.class);
    	click.start();
    	startActivity(intent);
    }
    
    public void Nachschlagewerkoeffnen(View c){
    	Intent intent = new Intent(this, Nachschlagewerk.class);
    	click.start();
    	startActivity(intent);
    }
    protected void onPause() 
    {
            super.onStop();
            menuesound.stop();
            
    }
}
