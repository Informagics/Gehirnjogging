package com.informagics.gehirnjogging.menue;

import com.informagics.gehirnjogging.R;
import com.informagics.gehirnjogging.bitout.BitOut;
import com.informagics.gehirnjogging.kabelbinder.kabelbinder;
import com.informagics.gehirnjogging.mathe.mathe;
import com.informagics.gehirnjogging.quiz.Quiz;
import com.informagics.gehirnjogging.Memory.Memory;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class Spielauswahl extends Activity
{
	MediaPlayer click;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spielauswahl);
        click=MediaPlayer.create(this, R.raw.menuepunkt_auswahl);
    }
    
    public void Spieloeffnen(View view)
    {
    	Intent intent = null;
    	switch (view.getId())
    	{
    	case R.id.btn_Matheauswahl:
    		intent = new Intent(this, mathe.class);
        	break;
    	case R.id.btn_Quizauswahl:
    		intent = new Intent(this, Quiz.class);
        	break;
    	case R.id.btn_BitOutauswahl:
        	intent = new Intent(this, BitOut.class);
        	break;
    	case R.id.btn_Cableauswahl:
    		intent = new Intent(this, kabelbinder.class);
    		break;
    	case R.id.btn_Memoryauswahl:
        	intent = new Intent(this, Memory.class);
        	break;
    	}
    	click.start();
    	startActivity(intent);
    }
}
