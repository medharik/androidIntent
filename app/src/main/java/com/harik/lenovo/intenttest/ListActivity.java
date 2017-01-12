package com.harik.lenovo.intenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv=(ListView)findViewById(R.id.list);
try{
    ArrayList<String> items=new ArrayList<String>();
    for (int i=0;i<50;i++){
        items.add("Arab "+i);
        items.add("Français"+i);
    }

    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list_item_text,items);

    lv.setAdapter(adapter);
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(ListActivity.this,"click sur "+adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_LONG).show();
        }
    });
}catch (Exception e){
    Log.e("Erreur list",e.getMessage());
}

    }
}
