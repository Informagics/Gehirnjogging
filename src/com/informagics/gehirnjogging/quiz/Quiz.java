package com.informagics.gehirnjogging.quiz;

import com.informagics.gehirnjogging.InputOutput;
import com.informagics.gehirnjogging.R;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Quiz extends Activity
{
	int grr=0;
	int check=0;
	int scorehigh=0;
	int bereitsgeklickt=0;
	int clicked=0,musikcheck=0;
	String Fragenliste[]={""};
	CountDownTimer CountDown2;
	int countdowntime=180000;
	int end=0;
	MediaPlayer gameover,click,time;
	
	//Quelle : http://stackoverflow.com/questions/1877417/how-to-set-a-timer-in-android
		
	final Handler _newRoundHandler = new Handler();
		
		final Runnable _newRound= new Runnable()
		{
			public void run()
			{
				if(end!=1)
				neueRunde(); // Aufruf der neuen Runde
			}
		};	
		 
	//CountDown
    final CountDownTimer CountDown1 =new CountDownTimer(180000,1000)
    {
    	public void onTick(long millisUntilFinished)
    	{
    		((TextView)findViewById(R.id.txtCountDown)).setText("Verbleibende Zeit : "+ millisUntilFinished/1000);
    		if(millisUntilFinished<3000 && musikcheck==0)
    		{
    		musikcheck=1;
            time.start();
    		}
    	}
    	public void onFinish()
    	{
    		stop(); // Funktionsaufruf Stop
    	}
    };
		 
	String txt;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{  
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
        click=MediaPlayer.create(this, R.raw.menuepunkt_auswahl);
        gameover=MediaPlayer.create(this, R.raw.ratsel_verloren);
        time=MediaPlayer.create(this, R.raw.zeitleft);
        
        CountDown1.start();
        
		txt = InputOutput.txt_int_auslesen("fragen.txt","UTF-8",this);
		check=getQuestion();
    
       
        /*Quellen : 
		http://developer.android.com/reference/android/view/View.OnTouchListener.html
		http://www.mybringback.com/tutorial-series/3279/android-the-basics-32-androids-ontouchlistener-and-motionevent/ 
		*/

	}
	
	protected void onPause() 
	{
        super.onStop();
        
        CountDown1.cancel();
        CountDown2.cancel();
	}

	protected void onResume() 
	{
    	super.onResume();
    	
    	CountDown2 =new CountDownTimer(countdowntime,1000)
        {
        	public void onTick(long millisUntilFinished)
        	{
        		((TextView)findViewById(R.id.txtCountDown)).setText("Verbleibende Zeit : "+ millisUntilFinished/1000);
        		
        		if(millisUntilFinished<3000 && musikcheck==0)
        		{
        		musikcheck=1;
                time.start();
        		}
        		
        		countdowntime-=1000;
        	}
        	public void onFinish()
        	{
        		stop(); // Funktionsaufruf Stop
        	}
        }.start();   	
	}

	public void Clicked(View view)
	{
		
		if( bereitsgeklickt==1)
			return;
		
		TextView highscore = (TextView) findViewById(R.id.txtHighscore);
		if(clicked==0)
		{
			clicked=1;
			click.start();
		}
    
		if(view.getId()==R.id.IMVKat1)
		{
			grr=1;
			((ImageView)findViewById(R.id.IMVPointer)).setVisibility(1);
			((ImageView)findViewById(R.id.IMVPointer)).setImageResource(R.drawable.quiz_pointer_links);
		}
		else if(view.getId()==R.id.IMVKat2)
		{
			grr=2;
			((ImageView)findViewById(R.id.IMVPointer)).setVisibility(1);
			((ImageView)findViewById(R.id.IMVPointer)).setImageResource(R.drawable.quiz_pointer_mitte);
		}
		else if(view.getId()==R.id.IMVKat3)
		{
			grr=3;
			((ImageView)findViewById(R.id.IMVPointer)).setVisibility(1);
			((ImageView)findViewById(R.id.IMVPointer)).setImageResource(R.drawable.quiz_pointer_rechts);
			} // Ende IF getView
		
		if(check==1)
		{
			((ImageView)findViewById(R.id.IMVKat1)).setImageResource(R.drawable.quiz_button_r);
			((ImageView)findViewById(R.id.IMVKat2)).setImageResource(R.drawable.quiz_button_f);
			((ImageView)findViewById(R.id.IMVKat3)).setImageResource(R.drawable.quiz_button_f);
			}
		else if(check==2)
		{
			((ImageView)findViewById(R.id.IMVKat1)).setImageResource(R.drawable.quiz_button_f);
			((ImageView)findViewById(R.id.IMVKat2)).setImageResource(R.drawable.quiz_button_r);
			((ImageView)findViewById(R.id.IMVKat3)).setImageResource(R.drawable.quiz_button_f);
		}
		else if(check==3)
		{
			((ImageView)findViewById(R.id.IMVKat1)).setImageResource(R.drawable.quiz_button_f);
			((ImageView)findViewById(R.id.IMVKat2)).setImageResource(R.drawable.quiz_button_f);
			((ImageView)findViewById(R.id.IMVKat3)).setImageResource(R.drawable.quiz_button_r);
		}// Ende IF CHeck
		
		if(grr==check && bereitsgeklickt==0)
		{
			scorehigh+=5;
			bereitsgeklickt=1;
			highscore.setText(" "+ scorehigh);
			_newRoundHandler.postDelayed(_newRound, 1000);
		}
		else
		{
			bereitsgeklickt=1;
			_newRoundHandler.postDelayed(_newRound, 1000);
		}
	}
	
	public int getQuestion()
	{
    	final TextView tkat1=(TextView) findViewById(R.id.txtKat1);
		final TextView tkat2=(TextView) findViewById(R.id.txtKat2);
		final TextView tkat3=(TextView) findViewById(R.id.txtKat3);
		final TextView tbegriff=(TextView) findViewById(R.id.txtBegriff);
		
		String begriff,kat1,kat2,kat3;
		String stringsplit = txt;
   	
		String[] arr1 = stringsplit.split("\n"); //Splitten nach jeder Newline
   	
		//Quelle : http://blog.root-of-all-evil.com/2010/03/math-random-zufallszahlen-in-java/
		int select =(int) (Math.random() * (arr1.length - 0) + 0); //Rand auswahl der Fragen
    
		//Quelle : http://www.codebeach.com/2007/09/split-string-into-array-in-java.html
		String[] arr =arr1[select].split(" "); //Split der zuvor gesplittenten linien in ihre Elemente
    
		int auswahl=0;
    
		//Quelle : http://openbook.galileocomputing.de/javainsel/javainsel_04_004.html#dodtp519a961b-a9e3-488c-be97-69cfd3802c45
		begriff=arr[0].replace("&", " "); //Replace & durch " "
		kat1=arr[1].replace("&", " ");
		kat2=arr[2].replace("&", " ");
		kat3=arr[3].replace("&", " ");
    
		int randswitch =(int) (Math.random() * (4 - 1) + 1); //Rand auswahl der Kategorien sortierung
    
		if(randswitch==1)
		{
			tkat1.setText(kat3);
			tkat2.setText(kat2);
			tkat3.setText(kat1);
			tbegriff.setText(begriff);
    	
			auswahl=1;
		}
		else if(randswitch==2)
		{
			tkat1.setText(kat1);
			tkat2.setText(kat3);
			tkat3.setText(kat2);
			tbegriff.setText(begriff);
    	
			auswahl= 2;
		}
		else if(randswitch==3)
		{
			tkat1.setText(kat2);
			tkat2.setText(kat1);
			tkat3.setText(kat3);
			tbegriff.setText(begriff);
    	
			auswahl= 3;
		}
		return auswahl;
	}
	
	//Quelle : 
    public void neueRunde()
    {
    	 //Neue Runde einleiten(Alle werte zurücksetzen)
    	 if(end!=1)
         ((ImageView)findViewById(R.id.IMVKat1)).setImageResource(R.drawable.quiz_button_1);
         ((ImageView)findViewById(R.id.IMVKat2)).setImageResource(R.drawable.quiz_button_1);
         ((ImageView)findViewById(R.id.IMVKat3)).setImageResource(R.drawable.quiz_button_1);
         
         bereitsgeklickt=0;
         clicked=0;
         /* Quellen :
          * http://developer.android.com/reference/android/widget/ImageView.html
			http://developer.android.com/reference/android/widget/ImageView.html#setVisibility%28int%29

          */
         
         ((ImageView)findViewById(R.id.IMVPointer)).setVisibility(4);
         check=getQuestion(); // Neue Frage auswï¿½hlen und in der Funktion setzen
         
    }
    
    public void stop()
    {
    	//Alles auf unsichtbar setzen & Text leeren
    	if(end==0)
    		InputOutput.HS_int_eintragen("quiz",String.valueOf(scorehigh),this);
        //Neu
    	end=1;
		((ImageView)findViewById(R.id.IMVPointer)).setVisibility(4);
		((ImageView)findViewById(R.id.IMVKat1)).setVisibility(4);
		((ImageView)findViewById(R.id.IMVKat2)).setVisibility(4);
		((ImageView)findViewById(R.id.IMVKat3)).setVisibility(4);
		((TextView)findViewById(R.id.txtKat1)).setText("");
		((TextView)findViewById(R.id.txtKat2)).setText("");
		((TextView)findViewById(R.id.txtKat3)).setText("");
		((TextView)findViewById(R.id.txtBegriff)).setText("");
		((TextView)findViewById(R.id.txtHighscore)).setText("");
		((TextView)findViewById(R.id.txtCountDown)).setText("");
		
		gameover.start();
		//GameOver Screen setzen
		((LinearLayout)findViewById(R.id.LinearLayoutQuiz)).setBackgroundResource(R.drawable.bluescreen_gameover_quiz);  
    }
}
