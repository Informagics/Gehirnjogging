package com.informagics.gehirnjogging.menue;

import com.informagics.gehirnjogging.R;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

public class Credits extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);
        /*
        MediaPlayer mp= MediaPlayer.create(this, R.raw.musikdatei); //nur f�r Musikwiedergabe
        mp.start();
        */
        VideoView Videoview = (VideoView)findViewById(R.id.videoview1);
        Uri videopfad = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.credits); //Quelle ist eigenes Projekt; Name des Projekts; Dateipfad (ohne dateiendung)
        MediaController mc = new MediaController(this); // Erstellt ein Videoplayer mit Pause-Button usw. DRINGEND NOTWENDIG!!! ohne gehts nicht
        mc.setVisibility(4);
        Videoview.setOnCompletionListener(new OnCompletionListener()
        {	
        	public void onCompletion(MediaPlayer arg0)
        	{
        		arg0.seekTo(0);
        		arg0.start();
        	}
        });
        Videoview.setMediaController(mc);
        Videoview.setVideoURI(videopfad);
        Videoview.start();
    }
}
