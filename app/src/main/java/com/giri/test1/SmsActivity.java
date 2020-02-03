package com.giri.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SmsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, message;
    TextView send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        name = findViewById(R.id.name);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String n = name.getText().toString();
        String m = message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+n));
        intent.putExtra("sms_body",""+m);
        startActivity(Intent.createChooser(intent,"Send Message"));
    }
}
