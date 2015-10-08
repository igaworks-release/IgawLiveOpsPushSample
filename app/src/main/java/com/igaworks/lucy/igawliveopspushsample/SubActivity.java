package com.igaworks.lucy.igawliveopspushsample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.igaworks.IgawCommon;

/**
 * Created by HMin on 2015-06-18.
 */
public class SubActivity extends ActionBarActivity {
    // Igaworks Common
    public String tag = "Igaw";


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

            /*
             * Your Code
             */
        Intent i  = getIntent();
        Uri data = i.getData();
        Log.d(tag, "Custom Scheme Type Deep Link Data :::  " + data);
    }


     /*
      * Your Code
      */


    protected void onResume(){
        super.onResume();
        IgawCommon.startSession(getApplicationContext());
        Log.d(tag, "startSession ::: SubActivity");
    }

    protected void onPause(){
        super.onPause();
        IgawCommon.endSession();
        Log.d(tag, "endSession ::: SubActivity");
    }

}
