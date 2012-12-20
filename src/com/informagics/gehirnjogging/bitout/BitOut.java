package com.informagics.gehirnjogging.bitout;

import com.informagics.gehirnjogging.InputOutput;
import com.informagics.gehirnjogging.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class BitOut extends Activity {

	/*To-Do:
	 * Fehler wenn Map im letzen Kästchen eine 0 hat
	 * Übersichtliches und ansprechendes Layout machen
	*/
	
	int moves, punktezahl, rätselanzahl=14, musikcheck = 0;;
	long zeit1, neuezeit=0, neuezeit2;
	boolean test = false;
	int mode=0; //0=noch nicht gelöst 1=gelöst
	int[][] mapwerte = new int[5][5];
	
	CountDownTimer countdowntimer;
	CountDownTimer countdowntimer2;
	
	MediaPlayer gameover, click, time, gewonnen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bit_out);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		click=MediaPlayer.create(this, R.raw.menuepunkt_auswahl);
        gameover=MediaPlayer.create(this, R.raw.ratsel_verloren);
        time=MediaPlayer.create(this, R.raw.zeitleft);
        gewonnen=MediaPlayer.create(this, R.raw.ratsel_gewonnen);
		
		Levelinit();
		
		countdowntimer = new CountDownTimer(240000, 1000) //setzt den Timer auf 240 Sekunden 
		 {
		     public void onTick(long zeit) 
		     {
		    	 zeit1 = zeit; //übermittelt die noch verbleibende zeit
		    	 ((TextView)findViewById(R.id.TimerBitOut)).setText("Zeit: "+zeit / 1000);
		    	if(zeit<3000 && musikcheck==0)
		    	{
		    		musikcheck=1;
		            time.start();
		    	}
		     }

		     public void onFinish() 
		     {
		    	 ((TextView)findViewById(R.id.TimerBitOut)).setText("Zeit ist abgelaufen!");
		    	 ((TextView)findViewById(R.id.PunktezahlBitOut)).setText("Punkte: 0");
		    	 Toast.makeText(getApplicationContext(), "Du warst zu langsam! Versuch es nochmal!", Toast.LENGTH_LONG).show();
		    	 mode =1; //setzt das Rätsel in den gelöst Zustand
		    	 
		     }
		 }.start();
	}
	
	@Override
	public void onPause() //Falls man das Spiel pausiert dann...
	{
		super.onPause();
		if (neuezeit == 0) //Überprüft ob die zeit nicht schon belegt ist. Kann sonst nur von Countdowntimer 2 belegt sein
		{
			neuezeit = zeit1; //setzt die neue zeit von countdowntimer2 = der restlichen zeit von countdowntimer1 
			test = true; //Der Zustand sagt aus, dass Countdowntimer 1 gestoppt ist
		}
		else 
		{
			countdowntimer2.cancel(); //Stoppt Countdowntimer2 damit er immer mit der neuen zeit belegt werden kann falls öfters das spiel pausiert wird
			neuezeit = neuezeit2;
		}
		countdowntimer.cancel();		
	}
	
	@Override
	public void onResume() //Wenn das Spiel wieder aufgenommen wird, dann...
	{
	    super.onResume();
	    if (test == true) //Countdowntimer2 funktioniert nur wenn countdowntimer1 gestoppt wurde
	    {
			countdowntimer2 = new CountDownTimer(neuezeit, 1000) 
			 {
			     public void onTick(long millis) 
			     {
			    	 ((TextView)findViewById(R.id.TimerBitOut)).setText("Zeit: "+millis / 1000);
			    	 if (neuezeit != neuezeit2)
			    	 {	
			    		neuezeit2 = millis;
			    	 }
			     }
	
			     public void onFinish() 
			     {
			    	 ((TextView)findViewById(R.id.TimerBitOut)).setText("Zeit ist abgelaufen!");
			    	 ((TextView)findViewById(R.id.PunktezahlBitOut)).setText("Punkte: 0");
			    	 Toast.makeText(getApplicationContext(), "Du warst zu langsam! Versuch es nochmal!", Toast.LENGTH_LONG).show();
			    	 mode =1; //setzt das Rätsel in den gelöst Zustand
			     }
			 }.start();
	    }
	}
	
	public void Levelinit(View view)
	{		
		Levelinit();
		
		click.start();
		
		countdowntimer.cancel();
		
		countdowntimer = new CountDownTimer(240000, 1000) //setzt den Timer auf 240 Sekunden 
		 {
		     public void onTick(long zeit) 
		     {
		    	 zeit1 = zeit; //übermittelt die noch verbleibende zeit
		    	 ((TextView)findViewById(R.id.TimerBitOut)).setText("Zeit: "+zeit / 1000);
		     }

		     public void onFinish() 
		     {
		    	 ((TextView)findViewById(R.id.TimerBitOut)).setText("Zeit ist abgelaufen!");
		    	 ((TextView)findViewById(R.id.PunktezahlBitOut)).setText("Punkte: 0");
		    	 Toast.makeText(getApplicationContext(), "Du warst zu langsam! Versuch es nochmal!", Toast.LENGTH_LONG).show();
		    	 mode =1; //setzt das Rätsel in den gelöst Zustand
		    	 gameover.start();
		     }
		 }.start();
	}
	
	public void Levelinit()
	{
		moves = 0;
		punktezahl = 100;
		mode=0;
		musikcheck = 0;
		
		((TextView) findViewById(R.id.PunktezahlBitOut)).setText("Punkte: "+punktezahl);
		((TextView) findViewById(R.id.Clicks)).setText("Clicks: "+moves);
		int Map = (int)(Math.random()*rätselanzahl*10)%rätselanzahl;
		String textdatei = null;
		textdatei=InputOutput.txt_int_auslesen("bitout.map","ISO-8859-1",this);
		
		String[] Zeilen=textdatei.split("\n");
		for(int y=0;y<5;y++){
			String[] Spalten=Zeilen[Map].split(" ");
			for(int x=0;x<5;x++){
				mapwerte[x][y] = Spalten[x+y*5].charAt(0) - 0x30;
				((Button) findViewById(R.id.b00+x+6*y)).setBackgroundResource(R.drawable.bits_button_zero - mapwerte[x][y]);
			}
		}
	}
	
	void domove(int y,int x){		
		int x1 = x - 1, x2 = x + 1, y1 = y - 1, y2 = y + 1;
        
        if (x > 0)
        	wechsel((Button)findViewById(R.id.b00+6*y+x1));
        if (x < 4)
        	wechsel((Button)findViewById(R.id.b00+6*y+x2));
        if (y > 0)
        	wechsel((Button)findViewById(R.id.b00+6*y1+x));
        if (y < 4)
        	wechsel((Button)findViewById(R.id.b00+6*y2+x));
        
        wechsel((Button)findViewById(R.id.b00+6*y+x));
	}
	
	void wechsel(Button view)
	{
		int zahl=view.getId()-R.id.b00;
		int x=zahl%6;
		int y=(zahl-x)/6;
		
		mapwerte[x][y] = (mapwerte[x][y] + 1) % 2;
		
		if(mapwerte[x][y] == 1)
			view.setBackgroundResource(R.drawable.bits_button_one);
		else
			view.setBackgroundResource(R.drawable.bits_button_zero);
	}
	
	public void clicked(View view)
	{
		if(mode == 1)
			return;
		
		click.start();
		
		int zahl=view.getId()-R.id.b00;
		int x=zahl%6;
		int y=(zahl-x)/6;
		
	    //clicked on cell (y,x)
    	moves++;
    	((TextView) findViewById(R.id.Clicks)).setText("Clicks: "+moves); //Übergibt die benötigten Klicks an den Clicks Textview
    	domove(y,x);
    		
    	if (punktezahl != 0) //Damit es keine negative Punktezahl gibt
    	{
    		punktezahl--;
    		((TextView) findViewById(R.id.PunktezahlBitOut)).setText("Punkte: "+punktezahl); //Punktezahl wird an Textview übergeben
    	}
    	else
    	{
    		((TextView) findViewById(R.id.PunktezahlBitOut)).setText("Punkte: 0");
    	}
    	
	   	solved();
	}
	
	public void solved()
	{
		for(int y=0;y<5;y++)
		{
			for(int x=0;x<5;x++)
			{
				if(mapwerte[x][y] == 1)
					return;
			}
		}
		countdowntimer.cancel();
		Toast.makeText(getApplicationContext(), "gelöst", Toast.LENGTH_SHORT).show();
		Toast.makeText(getApplicationContext(), "Deine Punktzahl: "+punktezahl, Toast.LENGTH_LONG).show();
		mode=1;
		gewonnen.start();
		InputOutput.HS_int_eintragen("bitout",String.valueOf(punktezahl),this);
	}
}
