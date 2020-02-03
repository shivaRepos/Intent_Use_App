package com.giri.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button image, call, sms, email, camera, map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIds();
    }

    private void getIds() {
        image = findViewById(R.id.image);
        image.setOnClickListener(this);

        call = findViewById(R.id.call);
        call.setOnClickListener(this);

        sms = findViewById(R.id.sms);
        sms.setOnClickListener(this);

        email = findViewById(R.id.email);
        email.setOnClickListener(this);

        camera = findViewById(R.id.camera);
        camera.setOnClickListener(this);

        map = findViewById(R.id.map);
        map.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == call) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + "8765375407"));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            startActivity(intent);
            }
        if(v == image){
            Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivity(intent);
        }
        if(v == sms){

            Intent intent = new Intent(MainActivity.this,SmsActivity.class);
            startActivity(intent);

        /*    Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:"+"8765375407"));
            startActivity(intent);
      */     }
        if(v == email){
        /*    Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"+"shiva03050@gmail.com"));
           // intent.setType("plain/text");

            intent.putExtra(Intent.EXTRA_EMAIL,"shiva03050");
            intent.putExtra(Intent.EXTRA_SUBJECT,"Regarding Job application");
            intent.putExtra(Intent.EXTRA_TEXT,"Hello" );
            startActivity(Intent.createChooser(intent,"SendEmail"));
            finish();
     */     Intent intent = new Intent(MainActivity.this,EmailActivity.class);
            startActivity(intent);
        }
        if(v == camera){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
            finish();
        }
        if(v == map){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData((Uri.parse("google.navigation:q=LKO")));
        }
    }

}
