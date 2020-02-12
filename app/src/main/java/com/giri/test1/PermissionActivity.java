package com.giri.test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import static com.giri.test1.SplashActivity.MY_PERMISSIONS_REQUEST_READ_CONTACTS;

public class PermissionActivity extends AppCompatActivity {

    Activity thisActivity = PermissionActivity.this;
    String[] a = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(thisActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(thisActivity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            for (int i = 0; i < 2; i++) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                        a[i])) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    ActivityCompat.requestPermissions(PermissionActivity.this, a, MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(thisActivity,
                            a, MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
        } else {
            // Permission has already been granted
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(PermissionActivity.this, ImageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                {
                // If request is cancelled, the result arrays are empty.
                int count = 0 ;

                if (grantResults.length > 0)
                {
                    for (int i = 0; i < grantResults.length; i++)
                    {

                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            // permission was granted, yay! Do the
                            // contacts-related task you need to do.
                            count++;
                        } else {
                            // permission denied, boo! Disable the
                            // functionality that depends on this permission.
                            Toast.makeText(PermissionActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

                        }
                    }
                    if (count == grantResults.length) {
                        Intent intent = new Intent(PermissionActivity.this, ImageActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

                }
                return;

                // other 'case' lines to check for other
                // permissions this app might request.
            }
        }
    }
}
