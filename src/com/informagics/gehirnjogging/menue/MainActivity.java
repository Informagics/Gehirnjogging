package com.informagics.gehirnjogging.menue;

import com.informagics.gehirnjogging.Highscore;
import com.informagics.gehirnjogging.R;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity
{
	MediaPlayer menuesound,click;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=MediaPlayer.create(this, R.raw.menuepunkt_auswahl);
        menuesound=MediaPlayer.create(this, R.raw.menue_sound);
        menuesound.start();
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    
    public void Auswahloeffnen(View view)
    {
    	Intent intent = null;
    	switch (view.getId())
    	{
    	case R.id.btn_Start:
    		intent = new Intent(this, Spielauswahl.class);
    		break;
    	case R.id.btn_Nachschlagewerk:
    		intent = new Intent(this, Nachschlagewerk.class);
    		break;
    	case R.id.btn_Highscore:
    		intent = new Intent(this, Highscore.class);
    		break;
    	case R.id.btn_Credits:
    		intent = new Intent(this, Credits.class);
    		break;
    	case R.id.btn_Optionen:
    		intent = new Intent(this, Optionenauswahl.class);
    		break;
    	}
    	click.start();
    	startActivity(intent);
    }
    
    protected void onPause() 
    {
            super.onStop();
            menuesound.stop();        
    }
}
