package ca.acsea.funstop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class eventPagev2 extends AppCompatActivity {

    ImageView imgTopLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pagev2);
        imgTopLeft=(ImageView)findViewById(R.id.topLeft);
        imgTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(eventPagev2.this, eventSub1.class);
                startActivity(intent);
            }
        });
    }
}
