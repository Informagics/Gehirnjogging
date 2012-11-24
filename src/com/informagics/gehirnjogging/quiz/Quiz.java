package com.informagics.gehirnjogging.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import com.informagics.gehirnjogging.R;



import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Quiz extends Activity {
	
	int grr=0;
	int check=0;
	int scorehigh=0;
	int bereitsgeklickt=0;
	String Fragenliste[]={""};
	
	
	
	String txt;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	 //Quelle : http://stackoverflow.com/questions/1877417/how-to-set-a-timer-in-android
		
		 final Handler _newRoundHandler = new Handler();
		
		 final Runnable _newRound= new Runnable()
		  {
		  public void run()
		 {
		   neueRunde(); // Aufruf der neuen Runde
		  }
		  };
		  final Handler _GameOver = new Handler();
			
			 final Runnable _Over= new Runnable()
			  {
			  public void run()
			 {
			   stop(); // Aufruf zum stoppen des Spiels
			  }
			  };
		  
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
        _GameOver.postDelayed(_Over, 180000); // 3Minuten
        
        final ImageView opfeil = (ImageView) findViewById(R.id.IMVOben); //Mittleres IMGVIew
        final ImageView rpfeil = (ImageView) findViewById(R.id.IMVRechts); // Rechtes ImageView
        final ImageView lpfeil = (ImageView) findViewById(R.id.IMVLinks); // Linkes Imageview
        
        final TextView response = (TextView) findViewById(R.id.txtResponse);
        final ImageView oben = (ImageView) findViewById(R.id.IMVKat2); // Var. oben das ImageView IMVKAT2 zuweisen
        final TextView highscore = (TextView) findViewById(R.id.txtHighscore);
     
        InputStream is;
		try {
			 txt = convertStreamToString("fragen.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		check=getQuestion();
    
       
        /*Quellen : 
		http://developer.android.com/reference/android/view/View.OnTouchListener.html
		http://www.mybringback.com/tutorial-series/3279/android-the-basics-32-androids-ontouchlistener-and-motionevent/ 
		*/

        oben.setOnTouchListener(new OnTouchListener(){ //Ontouchlistener f�r die mittlere Kategorie
        
           
			public boolean onTouch(View oben, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN: //Wenn ImageView gedr�ckt wurde
                    {
                    	if( bereitsgeklickt==1)
                    		return true; // Verlassen der onTOuch falls die Kategorie bereits geklickt wurde.
                    	
                     opfeil.setVisibility(1); // ImageView des mittleren Pfeils auf visible setzen
                     grr=2; // CHeck der onTouch
                     
                     if(grr==check && bereitsgeklickt==0){ //Wenn check==grr RIchtig dann wurde die Frage richtig beantwortet
                    	 
                 		response.setText("Richtig"); 
                 		scorehigh+=5;
                 		bereitsgeklickt=1;
                 		highscore.setText(" "+ scorehigh);
                 	   _newRoundHandler.postDelayed(_newRound, 3000); //Aufrufen der neuen Runde mit einem Delay von 3000 
                 	   
                     }else{
                    	 
                 		response.setText("Falsch");
                 		bereitsgeklickt=1;
                 	   _newRoundHandler.postDelayed(_newRound, 3000);
                 	   
                 	 }
                  
                     return true; // Verlasse switch
                    }
                   
                }
                return true; // Verlasse onTouch
            }

        });
        final ImageView links = (ImageView) findViewById(R.id.IMVKat1);  // Var. links das ImageView IMVKAT1 zuweisen
        links.setOnTouchListener(new OnTouchListener(){

           
			public boolean onTouch(View links, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN: //Wenn ImageView gedr�ckt wurde
                    {
                    	
                    	  if( bereitsgeklickt==1)
                    	  return true;
                    	  
                    	  lpfeil.setVisibility(1); // ImageView des rechten Pfeils auf visible setze
                    	  grr=1;
                    	  if(grr==check && bereitsgeklickt==0){
                    		  
                      		response.setText("Richtig"); 
                      		scorehigh+=5;
                      		bereitsgeklickt=1;
                     		highscore.setText(" "+ scorehigh);
                     	   _newRoundHandler.postDelayed(_newRound, 3000);
                     	   
                    	  }else{
                    		  
                      		response.setText("Falsch");
                      		bereitsgeklickt=1;
                      	   _newRoundHandler.postDelayed(_newRound, 3000);
                      	   
                      	 }
                      
                    	  return true; // Verlasse switch
                    }
                    
                }
                return true; // Verlasse onTouch
            }

        });
        final ImageView rechts = (ImageView) findViewById(R.id.IMVKat3);  // Var. rechts das ImageView IMVKAT3 zuweisen
        rechts.setOnTouchListener(new OnTouchListener(){

           
			public boolean onTouch(View rechts, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN: //Wenn ImageView gedr�ckt wurde
                    {
                    	if( bereitsgeklickt==1)
                    		return true;
                    	 rpfeil.setVisibility(1); // ImageView des linken Pfeils auf visible setze
                    	 grr=3;
                    	
                    	 if(grr==check && bereitsgeklickt==0){
                    		 
                     		response.setText("Richtig"); 
                     		scorehigh+=5;
                     		 bereitsgeklickt=1;
                     		highscore.setText(" "+ scorehigh);
                     	   _newRoundHandler.postDelayed(_newRound, 3000);
                     	   
                     	 }else{
                     		 
                     		response.setText("Falsch");
                     		 bereitsgeklickt=1;
                     	   _newRoundHandler.postDelayed(_newRound, 3000);
                     	   
                     	 }
                       
                    	 return true; // Verlasse switch
                    }
                   
                }
               return true; // Verlasse onTouch
            }

        });
    }
  
public int getQuestion(){
    	
	  
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
    	
    	
    	if(randswitch==1){
    		
    		tkat1.setText(kat3);
        	tkat2.setText(kat2);
        	tkat3.setText(kat1);
        	tbegriff.setText(begriff);
    		
        	auswahl=1;
        	
    	}else if(randswitch==2){
    		
    		tkat1.setText(kat1);
        	tkat2.setText(kat3);
        	tkat3.setText(kat2);
        	tbegriff.setText(begriff);
    	
        	auswahl= 2;
        	
    	}else if(randswitch==3){
    		
    		tkat1.setText(kat2);
        	tkat2.setText(kat1);
        	tkat3.setText(kat3);
        	tbegriff.setText(begriff);
    		
        	auswahl= 3;
    	}
    	
    	return auswahl;
    	
    }
	//Quelle : 
    public void neueRunde(){
    	
    	 //Neue Runde einleiten(Alle werte zur�cksetzen)
    	 ImageView opfeil = (ImageView) findViewById(R.id.IMVOben); //Mittleres IMGVIew
         ImageView rpfeil = (ImageView) findViewById(R.id.IMVRechts); // Rechtes ImageView
         ImageView lpfeil = (ImageView) findViewById(R.id.IMVLinks); // Linkes Imageview
         TextView response = (TextView) findViewById(R.id.txtResponse); // Response Textview
         bereitsgeklickt=0;
         
         /* Quellen :
          * http://developer.android.com/reference/android/widget/ImageView.html
			http://developer.android.com/reference/android/widget/ImageView.html#setVisibility%28int%29

          */
         
         opfeil.setVisibility(4); // Visibility wieder auf invisible setzen
         lpfeil.setVisibility(4);
         rpfeil.setVisibility(4);
         
         response.setText("");
         check=getQuestion(); // Neue Frage ausw�hlen und in der Funktion setzen
         
    }
    
    //Quelle : http://stackoverflow.com/questions/4789325/android-path-to-asset-txt-file
    
    public String convertStreamToString(String filename) throws IOException
    {
    	InputStream is = getResources().getAssets().open(filename);
    	Writer writer = new StringWriter();
    	char[] buffer = new char[2048];
    	try
    	{
    		Reader reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
    		int n;
    		while ((n = reader.read(buffer)) != -1) //Solange das Ende der Datei noch erreicht wurd
    		{
    			writer.write(buffer, 0, n); //Schreiben buffer von 0 bis n
    		}
    	}
    	finally
    	{
    		is.close(); //Zugriff auf die Datei schließen
    	}
    	String text = writer.toString(); // In Var. text schreiben und in von Bit in String wandeln
    	return text; //Text zurückgeben
    }
    
    public void stop(){
    	
    	//Alles auf unsichtbar setzen & Text leeren
   	 	ImageView opfeil = (ImageView) findViewById(R.id.IMVOben); //Mittleres IMGVIew
        ImageView rpfeil = (ImageView) findViewById(R.id.IMVRechts); // Rechtes ImageView
        ImageView lpfeil = (ImageView) findViewById(R.id.IMVLinks); // Linkes Imageview
        TextView response = (TextView) findViewById(R.id.txtResponse); // Response Textview
        ImageView oben = (ImageView) findViewById(R.id.IMVKat2);
        ImageView links = (ImageView) findViewById(R.id.IMVKat1);
        ImageView rechts = (ImageView) findViewById(R.id.IMVKat3);
        TextView tkat1=(TextView) findViewById(R.id.txtKat1);
		TextView tkat2=(TextView) findViewById(R.id.txtKat2);
		TextView tkat3=(TextView) findViewById(R.id.txtKat3);
		TextView tbegriff=(TextView) findViewById(R.id.txtBegriff);
		
        opfeil.setVisibility(4); // Visibility wieder auf invisible setzen
        lpfeil.setVisibility(4);
        rpfeil.setVisibility(4);
        rechts.setVisibility(4);
        links.setVisibility(4);
        oben.setVisibility(4);
        
        tbegriff.setText(""); // Textfelder leeren und GameOver ausgeben
        tkat1.setText("");
        tkat2.setText("GameOver");
        tkat3.setText("");
        response.setText("");
    }
}
