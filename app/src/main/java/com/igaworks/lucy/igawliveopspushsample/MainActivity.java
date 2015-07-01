package com.igaworks.lucy.igawliveopspushsample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.igaworks.IgawCommon;
import com.igaworks.liveops.IgawLiveOps;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    // Igaworks Common
    String tag = "Igaw";

    // Igaworks LiveOps Push
    String usn;
    String encryptUsn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Your Code
        TelephonyManager manager =  (TelephonyManager)getSystemService(MainActivity.this.TELEPHONY_SERVICE);
        usn = manager.getDeviceId().toString();
        usn = usn+"a";
        encryptUsn = Base64.encodeToString(usn.getBytes(), 0);


        // Igaworks Common
        IgawCommon.startApplication(MainActivity.this);
        Log.d(tag, "startApplication ::: MainActivity");

        // Igaworks LiveOps Push
        IgawCommon.setUserId(encryptUsn);
        Log.d(tag, "setUserId ::: " + encryptUsn);

        // Igaworks LiveOps Push
        IgawLiveOps.initialize(MainActivity.this);
        Log.d(tag, "IgawLiveOps ::: initialize");

        // Igaworks LiveOps Push Optional
        onNewIntent(getIntent());

        // Igaworks LiveOps Push Optional
        // IgawLiveOps.enableService(MainActivity.this, true);

        /*
         * Your Code
         */

    }


     /*
      * Your Code
      */

    // Your Code
    public void moveSubactivity(View view){
        Log.d(tag, "moveSubactivity ::: Button Click");

        Intent intent = new Intent(this, SubActivity.class);
        startActivity(intent);

        String usergroupKey = "SubStage";

        // Igaworks LiveOps Push Optional
        IgawLiveOps.setTargetingData(MainActivity.this, usergroupKey, "String" );
        Log.d(tag, "setTargetingData ::: " + usergroupKey);
    }

    // Your Code
    public void  sendNormalClientPush(View view){
        Log.d(tag, "sendNormalClientPush ::: Button Click");

        // Igaworks LiveOps Push Client
        IgawLiveOps.setNormalClientPushEvent(
                this,
                1,
                "Normal Client Push",
                1,
                true
        );
        Log.d(tag, "setNormalClientPushEvent ::: Send Success");

    }

    // Your Code
    public void sendBigTextClientPush(View view){
        Log.d(tag, "sendBigTextClientPush ::: Button Click");

        // Igaworks LiveOps Push Client
        IgawLiveOps.setBigTextClientPushEvent(
                this,
                1,
                "Big Text ClientPush",
                "Main Event",
                "Let's play now! WoooooooW!!",
                "Play now",
                1,
                true
        );
        Log.d(tag, "setBigTextClientPushEvent ::: Send Success");

    }

    // Igaworks LiveOps Push Optional
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);

        /*
         * Your code
         */

        // Igaworks LiveOps Push Optional (URL Type Deeplink)
        IgawLiveOps.onNewIntent(MainActivity.this, intent);
        Log.d(tag, "onNewIntent ::: URL Type Deeplink");


        // Igaworks LiveOps Push Optional (Json Type Deeplink)
        String deepLinkStr = intent.getStringExtra(IgawLiveOps.LIVEOPS_DEEPLINK_JSON_KEY);
        if (deepLinkStr != null && !deepLinkStr.equalsIgnoreCase("")){
            JSONObject json;
            try {
                json = new JSONObject(deepLinkStr);
                Log.d(tag, "deepLinkStr ::: " + deepLinkStr);

                // Your Code
                if (json != null) {

                    String Param = json.getString("WebURL");
                    Log.d(tag, "Param ::: " + Param);

                    Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(Param));
                    startActivity(intent1);
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        // Igaworks Common
        IgawCommon.startSession(MainActivity.this);
        Log.d(tag, "startSession ::: MainActivity" );

        // Igaworks LiveOps Push
        IgawLiveOps.resume(MainActivity.this);
        Log.d(tag, "IgawLiveOps ::: resume");

        /*
         * Your Code
         */
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Igaworks Common
        IgawCommon.endSession();
        Log.d(tag, "endSession ::: MainActivity");

        /*
         * Your Code
         */
    }

}
