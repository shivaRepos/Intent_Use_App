package com.giri.test1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button image, call, sms, email, camera, map,video;


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

        video = findViewById(R.id.video);
        video.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == call) {
            Intent intent = new Intent(MainActivity.this,CallActivity.class);
        /*    Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + "123456879"));
            *//*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
            }*/
            startActivity(intent);
            }
        if(v == image){/*
            Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivity(intent);*/
            Intent intent = new Intent(MainActivity.this,ImageActivity.class);
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
            /*
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,1);
            finish();*/
            Intent intent = new Intent(MainActivity.this,ImageViewActivity.class);
            startActivity(intent);

        }

        if(v == video){
/*
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,2);*/
        Intent intent = new Intent(MainActivity.this,VideoActivity.class);
        startActivity(intent);

        }

        if(v == map){
            Intent intent = new Intent(MainActivity.this,MapActivity.class);
            startActivity(intent);
        }
    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();

    }*/
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==1)
            {
                if(data!=null)
                {

                    }

                }
            }

    }*/
}
