package com.informagics.gehirnjogging;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import android.app.Activity;
import android.widget.NumberPicker;

public class InputOutput
{	
	public static void HS_int_eintragen(String Spiel,String Punkte,Activity ac) throws IOException
	{
		/*try
		{
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("/sdcard/"+Spiel));
			ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(scoreObj);//objekt
            oos.close();    
        }
		catch (FileNotFoundException e)
		{
            e.printStackTrace();
        }
		catch (IOException e)
		{
            e.printStackTrace();
        }*/
		
		FileOutputStream fos;

	    try {
	        fos = new FileOutputStream("/sdcard/"+Spiel+".rec");
	        fos.write((HS_int_auslesen(Spiel,ac)+"\n"+Punkte).getBytes());
	        fos.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();

	    } catch (IOException e) {
	        e.printStackTrace();

	    }
	}
	
	public static String HS_int_auslesen(String Spiel,Activity ac) throws IOException
	{
		/*try {
			BufferedInputStream bs = new BufferedInputStream(new FileInputStream("/sdcard/score"));
			ObjectInputStream objIn = new ObjectInputStream(bs);
			highScore = (Score) objIn.readObject( );
			objIn.close(); 
			bs.close();    
        }
		catch (FileNotFoundException e)
		{
            e.printStackTrace();
        }
		catch (StreamCorruptedException e)
		{
            e.printStackTrace();
        }
		catch (IOException e)
		{
            e.printStackTrace();
        }
		catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }*/
		
		FileInputStream fis;
		fis = new FileInputStream("/sdcard/score");
		StringBuffer fileContent = new StringBuffer("");

		byte[] buffer = new byte[1024];
		int n;
		while ((n = fis.read(buffer)) != -1) {
		    fileContent.append(new String(buffer));
		}
		
		return fileContent.toString();
	}
	
	//Quelle : http://stackoverflow.com/questions/4789325/android-path-to-asset-txt-file
	public static String txt_int_auslesen(String Datei,String Format,Activity ac) throws IOException
	{
    	InputStream is = ac.getResources().getAssets().open(Datei);
    	Writer writer = new StringWriter();
    	char[] buffer = new char[2048];
    	try
    	{
    		Reader reader = new BufferedReader(new InputStreamReader(is, Format));
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
    	// In Var. text schreiben und in von Bit in String wandeln
    	return writer.toString(); //Text zurückgeben
	}
}
