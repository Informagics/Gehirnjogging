package com.informagics.gehirnjogging.menue;

import com.informagics.gehirnjogging.R;
import com.informagics.gehirnjogging.bitout.BitOut;
import com.informagics.gehirnjogging.kabelbinder.kabelbinder;
import com.informagics.gehirnjogging.mathe.mathe;
import com.informagics.gehirnjogging.quiz.Quiz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class Spielauswahl extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spielauswahl);
    }
    
    public void Mathe(View view)
    {
    	Intent intent = new Intent(this, mathe.class);
    	startActivity(intent);
    }
    
    public void Informatik(View view)
    {
    	Intent intent = new Intent(this, Quiz.class);
    	startActivity(intent);
    }
    
    public void Kabelbinder(View view)
    {
    	Intent intent = new Intent(this, kabelbinder.class);
    	startActivity(intent);
    }
    
    public void BitOut(View view)
    {
    	Intent intent = new Intent(this, BitOut.class);
    	startActivity(intent);
    }

}
