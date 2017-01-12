package com.harik.lenovo.intenttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
Button dh,euro,dolar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
     dh=(Button)   findViewById(R.id.dh);
        euro=(Button)   findViewById(R.id.euro);
        dolar=(Button)   findViewById(R.id.dolar);
        dh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.putExtra("devise","Dhs");
                setResult(1,i);
                finish();
            }
        });
        euro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent();
                i.putExtra("devise","€");
                setResult(2,i);
                finish();
            }
        });

        dolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.putExtra("devise","$");
                setResult(3,i);
                finish();
            }
        });
    }
}
