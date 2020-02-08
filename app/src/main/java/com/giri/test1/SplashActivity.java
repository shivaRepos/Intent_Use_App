package com.giri.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 123;
     String TAG = "SplashActivity";

         ProgressBar progressBar;
        String[] s = {Manifest.permission.CALL_PHONE,Manifest.permission.SEND_SMS,
              Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,
              Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_CONTACTS };

      //  Activity thisActivity = SplashActivity.this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
     //   permissioncheck();

          progressBar = findViewById(R.id.progressBar);

        //Internet,SMS,Camera,Call,Location
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(SplashActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SplashActivity.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)

        {
            // Permission is not granted
                Log.e(TAG,"Permission not granted");

            for(int i=0;i<6;i++) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,
                        s[i])) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    Log.e(TAG,"ShouldShowRequestPermissionRaionale() running");

                    ActivityCompat.requestPermissions(SplashActivity.this,s,MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                } else {
                    // No explanation needed; request the permission
                    Log.e(TAG,"No explanations Needed. Request the permissions");

                    ActivityCompat.requestPermissions(SplashActivity.this,s, MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
        } else {
            // Permission has already been granted
            Log.e(TAG,"Permissions have been already granted");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this,AuthActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },500);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            Log.e(TAG,"grant Results"+grantResults.length);
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS :
            {
                //If request is denied the result array is empty.
                int count = 0;
                if(grantResults.length > 0)
                {
                    for (int i = 0; i < grantResults.length; i++)
                    {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            //Permission granted! Perform the task related to the granted permission.
                            count++;
                        } else {
                            //permission denied! disable the functionality that depends on this permission.
                            Toast.makeText(SplashActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

                        }
                    }

                    if (count == grantResults.length)
                    {
                        Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else{

                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }

                return;
            }


        }

    }
}
