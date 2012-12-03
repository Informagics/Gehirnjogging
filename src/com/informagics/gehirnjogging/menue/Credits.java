package com.informagics.gehirnjogging.menue;

import com.informagics.gehirnjogging.R;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import android.app.Activity;

public class Credits extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);
        
        /*MediaPlayer mp= MediaPlayer.create(this, R.raw.musikdatei);
        mp.start();
        
        VideoView Videoview = (VideoView)findViewById(R.id.videoview1);
        Uri videopfad = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videodatei);
        MediaController mc = new MediaController(this);
        Videoview.setMediaController(mc);
        Videoview.setVideoURI(videopfad);
        Videoview.start();*/
    }
}
