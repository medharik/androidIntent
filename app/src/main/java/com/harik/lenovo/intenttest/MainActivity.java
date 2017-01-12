package com.harik.lenovo.intenttest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    Button calculerprix=null,changerdevise=null,télephoner=null,lister=null,listeruser=null,ok=null;
    TextView resultat=null,tdevise=null;
    EditText prix=null,tel=null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String devise=data.getStringExtra("devise");
        switch (requestCode){
            case 10 :
                Toast.makeText(this,"retour "+devise,Toast.LENGTH_LONG).show();

               tdevise.setText(devise);
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculerprix= (Button) findViewById(R.id.btncalculer);

        télephoner= (Button) findViewById(R.id.telephoner);
        listeruser= (Button) findViewById(R.id.btnlisterUsers);
listeruser.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,ListeCustomAdatper.class));
    }
});
        tel= (EditText) findViewById(R.id.editTexttel);
        télephoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=tel.getText().toString();
                Uri tt=Uri.parse("tel:"+num);
                Intent call=new Intent(Intent.ACTION_DIAL,tt);
                startActivity(call);

            }
        });
        changerdevise= (Button) findViewById(R.id.btnchanger);
        changerdevise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Main2Activity.class);
               startActivityForResult(i,10);
               // startActivity(i);
            }
        });
        prix=(EditText) findViewById(R.id.edittextprix);
        resultat=(TextView)findViewById(R.id.textViewresultat);
        tdevise=(TextView)findViewById(R.id.textviewdevise);
calculerprix.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        double prixd=Double.parseDouble(prix.getText().toString());
        resultat.setText("Prix taxe comprise est "+prixd*1.2+" ");
        tdevise.setText("DHs");
        Toast.makeText(MainActivity.this,"tst"+prixd*1.2,Toast.LENGTH_LONG).show();

    }

});

lister=(Button)findViewById(R.id.changerlangue);
        lister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    startActivity(new Intent (MainActivity.this,ListActivity.class) );



            }
        });
ok= (Button) findViewById(R.id.httpok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,HttpOk.class);
                startActivity(i);

            }
        });
    }

}
