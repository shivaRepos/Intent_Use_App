package com.giri.test1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
/*
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "123456879"));*/

        openDialog();
    }

    private void openDialog() {

        View view = LayoutInflater.from(CallActivity.this).inflate(R.layout.calldialog, (ViewGroup) findViewById(R.id.dialog_root));
        final Dialog dialog = new Dialog(CallActivity.this);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        final EditText numbox = view.findViewById(R.id.numbox);
        Button call = view.findViewById(R.id.call);
        Button cancel = view.findViewById(R.id.cancel);


        call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (!numbox.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numbox.getText()));
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                      //  requestPermission();
                        return;
                    }
                    startActivity(intent);
                    dialog.dismiss();

                } else {
                    numbox.setError("Enter the phone no. to call");

                }
            }
        });

        dialog.show();

    }
    }

