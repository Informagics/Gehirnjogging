package com.informagics.gehirnjogging.mathe;

import com.informagics.gehirnjogging.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class mathe extends Activity 
{
	
	private static int Ergebnis = 0;
	private static int Zahl1 = 0;
	private static int Zahl2 = 0;
	private static int Rechenzeichen;
	private static int Punkte = 0;
	CountDownTimer CountDown2;
	int countdowntime=120000;
	
	//CountDown
    CountDownTimer CountDown1 =new CountDownTimer(120000,1000)
    {
    	public void onTick(long millisUntilFinished)
    	{
    		((TextView)findViewById(R.id.txtTimeLeftMathe)).setText("Verbleibende Zeit : "+ millisUntilFinished/1000);
    	}
    	public void onFinish()
    	{
    		endGame(); // Funktionsaufruf Stop
    	}
    };
	
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_mathe);
        CountDown1.start();
        Rätsel(this); 
	}
	
	public static int myRandom(int low, int high) // Rand. Funk.
	{ 
		return (int) (Math.random() * (high - low) + low);
    }
	
	public void onPause() 
	{
	        super.onStop();
	        
	        CountDown1.cancel();
	        CountDown2.cancel();
	}

	public void onResume() 
	{
	    	super.onResume();
	    	
	    	CountDown2 =new CountDownTimer(countdowntime,1000)
	        {
	        	public void onTick(long millisUntilFinished)
	        	{
	        		((TextView)findViewById(R.id.txtTimeLeftMathe)).setText("Verbleibende Zeit : "+ millisUntilFinished/1000);
	        		countdowntime-=1000;
	        	}
	        	public void onFinish()
	        	{
	        		endGame();
	        	}
	        }.start();
	    	
	}
	
	public static void Rätsel(Activity activity)
	{
	    Zahl1 = myRandom(1,20); // Rnd generation von 1-20
	    Zahl2 = myRandom(1,20);
	    Rechenzeichen = myRandom(0,1000)%4; // Auswahl des Rechenzeichens per Rand.
	    
	    if(Rechenzeichen==2) // If Div.
	    	while(Zahl1%Zahl2!=0) // Damit keine ,5 Zahlen entstehen können
	    		Zahl2 = myRandom(1,20);
	    
	    Ergebnis=ergebnis(Zahl1,Zahl2,Rechenzeichen); // Übergabe an Funk. ergebnis
	    
	    //TextView init. , schriftgröße setzen und aufgabe mit .setText
	    TextView textView = (TextView) activity.findViewById(R.id.Aufgabe);
	    textView.setTextSize(20);
	    textView.setText(String.valueOf(Zahl1) + "  "+ String.valueOf(Zahl2) + "=" + String.valueOf(Ergebnis)); // Setzen der Aufgabe
	}
	
	public static int ergebnis(int zahl1,int zahl2,int zeichen)
	{
		int Ergebnis = 88;
		
		if(zeichen==0)// je nachdem welches Zeichen zuvor gewählt wurde wird das erg berechnet
	    	Ergebnis=zahl1*zahl2;
		else if(zeichen==1)
	    	Ergebnis=zahl1+zahl2;
		else if(zeichen==2)
	    	Ergebnis=zahl1/zahl2;
		else if(zeichen==3)
	    	Ergebnis=zahl1-zahl2;
	    
		return Ergebnis;
	}
    
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) 
    {
    	
    	//Dek. und Zuordnung der Views zu Vars.
        if(ergebnis(Zahl1,Zahl2,view.getId()-R.id.m00)==Ergebnis)
        {
        	((TextView)findViewById(R.id.punkte)).setText("Punktezahl : "+(++Punkte));
        }
        else
        {
        	if(Punkte!=0)
        	((TextView)findViewById(R.id.punkte)).setText("Punktezahl : "+(--Punkte));
        }
        
        neueRunde(); // Aufruf zur neuen Runde
    }
    
    public void neueRunde()
    {
    	
    	 //Spielfeld aufräumen
    	 ((TextView)findViewById(R.id.Aufgabe)).setText("");
    	 Rätsel(this);
    	
    }
    
    public void endGame()
    {
    	
    	 //Alles auf dem Spielfeld auf unsichtbar stellen und inx txtAusgabe den Endpunktestand ausgeben
    	 ((Button)findViewById(R.id.m00)).setVisibility(4);
    	 ((Button)findViewById(R.id.m01)).setVisibility(4);
    	 ((Button)findViewById(R.id.m02)).setVisibility(4);
    	 ((Button)findViewById(R.id.m03)).setVisibility(4);
    	 ((TextView)findViewById(R.id.punkte)).setTextSize(12);
    	 ((TextView)findViewById(R.id.Aufgabe)).setVisibility(4);
    	 
    	 if(Punkte==1)
    	 ((TextView)findViewById(R.id.punkte)).setText("Du hast "+String.valueOf(Punkte)+" Punkt erreicht. GOTT IST DAS SCHLECHT! *NAK NAK*");
    	 else
    	 ((TextView)findViewById(R.id.punkte)).setText("Du hast "+String.valueOf(Punkte)+" Punkte erreicht. GOTT IST DAS SCHLECHT! *NAK NAK*");
    	 
    	 ((TextView)findViewById(R.id.txtTimeLeftMathe)).setVisibility(4);
    	 
    }
}
