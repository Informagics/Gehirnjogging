package com.informagics.gehirnjogging;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import android.app.Activity;
import android.content.Context;

public class InputOutput
{	
	public static void HS_int_eintragen(String Spiel,String Punkte,Context con)
	{
        try {
            FileOutputStream fOut = con.openFileOutput ( Spiel + ".rec" , con.MODE_WORLD_READABLE ) ;
            OutputStreamWriter osw = new OutputStreamWriter ( fOut ) ;
            osw.write(HS_int_auslesen(Spiel,con));
            osw.write ( Punkte ) ;
            osw.flush ( ) ;
            osw.close ( ) ;
        } catch ( Exception e ) {
            e.printStackTrace ( ) ;
        }
	}
	
	public static String HS_int_auslesen(String Spiel,Context con)
	{
        String datax = "" ;
        try {
            FileInputStream fIn = con.openFileInput ( Spiel + ".rec" ) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                datax = datax + readString ;
                readString = buffreader.readLine ( ) ;
            }

            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        return datax ;
	}
	
	//Quelle : http://stackoverflow.com/questions/4789325/android-path-to-asset-txt-file
	public static String txt_int_auslesen(String Datei,String Format,Activity ac)
	{
    	InputStream is = null;
		try {
			is = ac.getResources().getAssets().open(Datei);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	finally
    	{
    		try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			} //Zugriff auf die Datei schließen
    	}
    	// In Var. text schreiben und in von Bit in String wandeln
    	return writer.toString(); //Text zurückgeben
	}
}
