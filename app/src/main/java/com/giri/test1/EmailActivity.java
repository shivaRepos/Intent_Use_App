package com.giri.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends AppCompatActivity implements View.OnClickListener {
        EditText name,subject,message;
        Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        getIds();

    }

    private void getIds() {
        name = findViewById(R.id.name);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);
        send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String a = name.getText().toString();
        String b = subject.getText().toString();
        String c = message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{ a});
        intent.putExtra(Intent.EXTRA_SUBJECT,""+b);
        intent.putExtra(Intent.EXTRA_TEXT,""+c);
        intent.setType("plain/text");
        startActivity(Intent.createChooser(intent,"SendEmail"));


    }
}
