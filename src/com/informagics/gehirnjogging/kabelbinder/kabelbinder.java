package com.informagics.gehirnjogging.kabelbinder;

import com.informagics.gehirnjogging.InputOutput;
import com.informagics.gehirnjogging.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class kabelbinder extends Activity 
{
	
	CountDownTimer cd,cd2;
	int Punkte = 100 , mode=0;
	long zeit1, neuezeit=0, neuezeit2;
	boolean test = false;
	
	int[][] Bilder=
		{
			{R.drawable.cable_straight_1,R.drawable.cable_straight_2,R.drawable.cable_straight_1,R.drawable.cable_straight_2},
			{R.drawable.cable_curve_1,R.drawable.cable_curve_2,R.drawable.cable_curve_3,R.drawable.cable_curve_4},
			{R.drawable.cable_double_1,R.drawable.cable_double_2,R.drawable.cable_double_1,R.drawable.cable_double_2},
			{R.drawable.cable_plug_1,R.drawable.cable_plug_2,R.drawable.cable_plug_3,R.drawable.cable_plug_4}
		};
	
	String[][] mapwerte = new String[5][5];
	int Clickzähler = 0;
	int rätselanzahl = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kabelbinder);
		
		Levelinit((int)(Math.random()*rätselanzahl*10)%rätselanzahl);//rand für zufalls level
		
		cd = new CountDownTimer(240000, 1000) //setzt den Timer auf 240 Sekunden 
		{
		     public void onTick(long zeit) 
		     {
		    	 zeit1 = zeit; //übermittelt die noch verbleibende zeit
		    	((TextView)findViewById(R.id.TimerKabel)).setText("Zeit: "+zeit / 1000);
		     }

		     public void onFinish() 
		     {
		    	 mode = 1;
		    	 ((TextView)findViewById(R.id.TimerKabel)).setText("Zeit ist abgelaufen!");
		    	 ((TextView)findViewById(R.id.PunkteKabel)).setText("Punkte: 0");
		    	 Toast.makeText(getApplicationContext(), "Du warst zu langsam! Versuch es nochmal!", Toast.LENGTH_LONG).show();		    	 
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
			cd2.cancel(); //Stoppt Countdowntimer2 damit er immer mit der neuen zeit belegt werden kann falls öfters das spiel pausiert wird
			neuezeit = neuezeit2;
		}
		cd.cancel();		
	}
	@Override
	public void onResume() //Wenn das Spiel wieder aufgenommen wird, dann...
	{
	    super.onResume();
	    if (test == true) //Countdowntimer2 funktioniert nur wenn countdowntimer1 gestoppt wurde
	    {
			cd2 = new CountDownTimer(neuezeit, 1000) 
			 {
			     public void onTick(long millis) 
			     {
			    	 ((TextView)findViewById(R.id.TimerKabel)).setText("Zeit: "+millis / 1000);
			    	 if (neuezeit != neuezeit2)
			    	 {	
			    		neuezeit2 = millis;
			    	 }
			     }
	
			     public void onFinish() 
			     {
			    	 ((TextView)findViewById(R.id.TimerKabel)).setText("Zeit ist abgelaufen!");
			    	 ((TextView)findViewById(R.id.PunkteKabel)).setText("Punkte: 0");
			    	 Toast.makeText(getApplicationContext(), "Du warst zu langsam! Versuch es nochmal!", Toast.LENGTH_LONG).show();	
			     }
			 }.start();
	    }
	}
	
	public void Clicked(View view)
	{
		if(mode == 1)
			return;

		int zahl=view.getId()-R.id.i00;
		int x=zahl%6;
		int y=(zahl-x)/6;
		
		String str_element[]=mapwerte[x][y].split(":");
		int rotation=Integer.valueOf(str_element[1]);
		int element=Integer.valueOf(str_element[0]);
		
		rotation=(rotation+1)%4;
		mapwerte[x][y]=String.valueOf(element) + ":" + String.valueOf(rotation);
		
		((ImageView)findViewById(view.getId())).setImageResource(Bilder[element][rotation]);
		
		Clickzähler++;
		
		if (Punkte != 0) //Damit es keine negative Punktezahl gibt
		{
			Punkte--;
			((TextView) findViewById(R.id.PunkteKabel)).setText("Punkte: "+Punkte); //Punktezahl wird an Textview übergeben
		}
		else
		{
			((TextView) findViewById(R.id.PunkteKabel)).setText("Punkte: 0");
		}
		
		((TextView)findViewById(R.id.Clickskabel)).setText("Clicks: "+Clickzähler);
		
		Lösung();
	}
	
	public void Levelinit(int Map)
	{
		String textdatei = null;
		textdatei=InputOutput.txt_int_auslesen("kabelstrecken.map","ISO-8859-1",this);
		
		String[] Zeilen=textdatei.split("\n");
		for(int y=0;y<5;y++){
			String[] Spalten=Zeilen[Map].split(" ");
			for(int x=0;x<5;x++){
				mapwerte[x][y]=Spalten[x+5*y];
				String str_element[]=mapwerte[x][y].split(":");
				int rotation=Integer.parseInt(str_element[1]);
				int element=Integer.parseInt(str_element[0]);
				((ImageView)findViewById(R.id.i00+x+y*6)).setImageResource(Bilder[element][rotation]);
			}
		}
	}
	
	public void Lösung()
	{
		int x=0,y=0;
		int element=0;
		int rotation=0;
		int rotationneu=0;
		
		do//suche nach einem Stecker Baustein (Element 3)
		{
			String str_element[]=mapwerte[x][y].split(":");
			rotation=Integer.parseInt(str_element[1]);
			element=Integer.parseInt(str_element[0]);
			
			if(element!=3)
			{
				if(x==4){ y++; x=0; }
				else x++;
			}
			
		}while(element!=3);
		
		//Debug
		//((TextView)findViewById(R.id.Clickskabel)).setText("e: "+element+" x: "+x+" y: "+y+" Ra: "+rotation);
		
		do//Durchlauf der Map bis fehlerhafter Baustein oder zweiter Stecker Baustein erreicht wird
		{
			if(element == 0 || element == 3)
			{
				if(rotation%2==1)
					x+=rotation-2;//x manipulation
				else
					y+=rotation-1;//y manipulation
			}
			else if(element == 1)
			{
				if(rotation==rotationneu)
				{
					switch (rotationneu)
					{
					case 0:
						x-=1;
						break;
					case 1:
						y+=1;
						break;
					case 2:
						x+=1;
						break;
					case 3:
						y-=1;
						break;
					}
					rotation=(rotationneu+1)%4;
				}
				else if((rotation+1)%4==rotationneu)
				{
					switch (rotationneu)
					{
					case 0:
						y+=1;
						break;
					case 1:
						x+=1;
						break;
					case 2:
						y-=1;
						break;
					case 3:
						x-=1;
						break;
					}
					rotation=(rotationneu+2)%4;
				}
				else
					return;
			}
			else
			{
				if(rotationneu%2==0)
				{
					switch (rotation)
					{
					case 0:
						rotation=1;
						x-=1;
						break;
					case 1:
						rotation=0;
						y-=1;
						break;
					case 2:
						rotation=3;
						x+=1;
						break;
					case 3:
						rotation=2;
						y+=1;
						break;
					}
				}
				else
				{
					switch (rotation)
					{
					case 0:
						rotation=3;
						x+=1;
						break;
					case 1:
						rotation=2;
						y+=1;
						break;
					case 2:
						rotation=1;
						x-=1;
						break;
					case 3:
						rotation=0;
						y-=1;
						break;
					}
				}
			}
			
			if(x==-1||x==5||y==-1||y==5)
				return;
			
			String str_element[]=mapwerte[x][y].split(":");
			rotationneu=Integer.parseInt(str_element[1]);
			element=Integer.parseInt(str_element[0]);
			
			//Debug
			//((TextView)findViewById(R.id.Clickskabel)).setText(((TextView)findViewById(R.id.Clickskabel)).getText()+" | e: "+element+" x: "+x+" y: "+y+" Ra: "+rotation+" Rn: "+rotationneu);
			
			if(element == 3 && rotationneu%2==rotation%2 && rotationneu!=rotation)//ignorieren der Stecker
				break;
			else if(element == 3 && rotationneu%2==rotation%2 && rotationneu==rotation)
				return;
			
			if(element!=1 && element!=2 && rotation%2!=rotationneu%2)//falsches rotierung des nächsten Bauteils
				return;
			
		}while(element!=3);
		cd.cancel();
		mode = 1;
		InputOutput.HS_int_eintragen("cabel",String.valueOf(Punkte),this);
		((TextView)findViewById(R.id.Clickskabel)).setText(InputOutput.HS_int_auslesen("cabel",this));
	}
}
