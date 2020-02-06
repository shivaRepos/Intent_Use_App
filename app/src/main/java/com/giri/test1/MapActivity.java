package com.giri.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {
        EditText mapQuery;
        Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapQuery = findViewById(R.id.mapQuery);
        search = findViewById(R.id.search);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String a = mapQuery.getText().toString();
    /*    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+a));
        startActivity(Intent.createChooser(intent,"search location"));
    */
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+a);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
