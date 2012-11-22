package com.informagics.gehirnjogging.mathe;

import com.informagics.gehirnjogging.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class mathe extends Activity {
	
	private static int Ergebnis = 0;
	private static int Zahl1 = 0;
	private static int Zahl2 = 0;
	private static String Rechenzeichen;
	private static int Punkte = 0;
	
	
	 
	  final Handler _TimeisUp = new Handler();
		
		 final Runnable _TimeUp= new Runnable()
		  {
		  public void run()
		 {
		   endGame(); // Aufruf der neuen Runde
		  }
		  };
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_mathe);
        
        _TimeisUp.postDelayed(_TimeUp, 120000); // Timer starten (2 Minuten )
        
        Rätsel(this); 
	}
	
	public static int myRandom(int low, int high) { // Rand. Funk.
		return (int) (Math.random() * (high - low) + low);
    }
	
	public static void Rätsel(Activity activity)
	{
	    Zahl1 = myRandom(1,20); // Rnd generation von 1-20
	    Zahl2 = myRandom(1,20);
	    String zeichen[]={"+","-","*","/"}; //Dek. und Ini. von zeichen mit den Mathematischen Operatoren
	    Rechenzeichen = zeichen[myRandom(0,3)]; // Auswahl des Rechenzeichens per Rand.
	    
	    if(Rechenzeichen=="/") // If Div.
	    	while(Zahl1%Zahl2==0) // Damit keine ,5 Zahlen entstehen können
	    		Zahl2 = myRandom(1,20);
	    
	    Ergebnis=ergebnis(Zahl1,Zahl2,Rechenzeichen); // Übergabe an Funk. ergebnis
	    
	    // Create the text view
	    TextView textView = (TextView) activity.findViewById(R.id.textView1);
	    textView.setTextSize(20);
	    textView.setText(String.valueOf(Zahl1) + "  "+ String.valueOf(Zahl2) + "=" + String.valueOf(Ergebnis)); // Setzen der Aufgabe
	}
	
	public static int ergebnis(int zahl1,int zahl2,String zeichen)
	{
		int Ergebnis = 88;
		
		if(zeichen == "+")// je nachdem welches Zeichen zuvor gewählt wurde wird das erg berechnet
	    	Ergebnis=zahl1+zahl2;
		else if(zeichen == "-")
	    	Ergebnis=zahl1-zahl2;
		else if(zeichen == "*")
	    	Ergebnis=zahl1*zahl2;
		else if(zeichen == "/")
	    	Ergebnis=zahl1/zahl2;
	    
		return Ergebnis;
	}
    
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
    	
    	//Dek. und Zuordnung der Views zu Vars.
        Button button = (Button) findViewById(view.getId());
        char tests = button.getText().toString().charAt(0);
        TextView testrichtigkeit = (TextView) findViewById(R.id.textView2);
        TextView punkte = (TextView) findViewById(R.id.punkte);
        
        if(tests==Rechenzeichen.charAt(0)){ 
        	
        	testrichtigkeit.setText("Richtig"); // Ausgabe das die Antwort richtig war
        	Punkte++; // Punkte erhöhen
        	punkte.setText("Punktzahl : "+Punkte); // Neuer Punktestand
        	
        }
        else{
        	if(Punkte!=0){
        		
        		Punkte--; // Punkte verringern
        		punkte.setText("Punktzahl : "+Punkte); // Neuer Punktestand
        		
        	}
        	
        	testrichtigkeit.setText("Falsch"); // Ausgabe das die Antwort falsch ist
        }
        
        neueRunde(); // Aufruf zur neuen Runde
    }
    public void neueRunde(){
    	
    	 //Spielfeld aufräumen
    	 TextView testrichtigkeit = (TextView) findViewById(R.id.textView2);
    	 testrichtigkeit.setText("");
    	 TextView textView = (TextView)findViewById(R.id.textView1);
    	 textView.setText("");
    	 Rätsel(this);
    	
    }
    public void endGame(){
    	
    	 //Alles auf dem Spielfeld auf unsichtbar stellen und die Sprechblase mit Text anzeigen lassen
    	 Button button1 = (Button) findViewById(R.id.button1);
    	 Button button2 = (Button) findViewById(R.id.button2);
    	 Button button3 = (Button) findViewById(R.id.button3);
    	 Button button4 = (Button) findViewById(R.id.button4);
    	 TextView testrichtigkeit = (TextView) findViewById(R.id.textView2);
    	 TextView textView = (TextView)findViewById(R.id.textView1);
    	 TextView punkte = (TextView) findViewById(R.id.punkte);
    	 TextView nur = (TextView)findViewById(R.id.txtNur);
    	 TextView schlecht = (TextView) findViewById(R.id.txtSchlecht);
    	 ImageView sprechblase = (ImageView) findViewById(R.id.IMVSprechblase);
    	 button1.setVisibility(4);
    	 button2.setVisibility(4);
    	 button3.setVisibility(4);
    	 button4.setVisibility(4);
    	 testrichtigkeit.setVisibility(4);
    	 textView.setVisibility(4);
    	 punkte.setVisibility(4);
    	 sprechblase.setVisibility(1);
    	 nur.setText("Du hast nur "+Punkte+" Punkte");
    	 nur.setVisibility(1);
    	 schlecht.setVisibility(1);
    	 
    }
}
