package com.harik.lenovo.intenttest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HttpOk extends AppCompatActivity {
Button ajouter=null;
    EditText nom=null,loc=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_ok);
ajouter= (Button) findViewById(R.id.ajouter);
        nom=(EditText) findViewById(R.id.editTextnom);
        loc=(EditText) findViewById(R.id.editTextloc);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client =new OkHttpClient();
                String n=nom.getText().toString();
                String l=loc.getText().toString();
                GPSTracker g=new GPSTracker(HttpOk.this);
loc.setText(g.getLatitude()+","+g.getLongitude());
             //   Request r=new Request.Builder().url("http://10.0.2.2/android/add.php").build();

           /*     HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost/android/add.php").newBuilder();
                urlBuilder.addQueryParameter("nom", n);
                urlBuilder.addQueryParameter("loc", l);
                String url = urlBuilder.build().toString();

                Request r = new Request.Builder()
                        .url("http://192.168.1.38/android/add.php?nom="+n+"&loc="+l+"")
                        .build();
                client.newCall(r).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                       Log.e("onfailure",e.getMessage());
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.i("onReponse",response.message());
                        Toast.makeText(getApplicationContext(),"Résponse : "+response.message(),Toast.LENGTH_LONG).show();
                    }
                });
            */


                try {
                   doGetRequest("http://10.0.2.2//android/add.php?nom="+n+"&loc="+l);
                    Log.i("OnREP","ok");
                } catch (IOException e) {
                   Log.e("onErreur : ",e.getMessage());
                }

                try {
                    GPSTracker gps=new GPSTracker(HttpOk.this);
                    l=gps.getLatitude()+","+gps.getLongitude();

               AsyncTask<String, Void, String> sss = new BackGroud();
                    sss.execute("http://10.0.2.2//android/add.php", "{'nom':'" + n + "','loc':'" + l + "'}");


                } catch (Exception e) {
                  Log.e("Err post",e.getMessage());
                }

            }
        });
    }

    public OkHttpClient client=new OkHttpClient();


    void doGetRequest(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .build();


        //Response response = client.newCall(request).execute();
         client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
Log.e("OnFail ",e.getMessage()+" request : "+request.toString());

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.i("OnOk ",response.toString());



            }

        });
//       return rep[0].body().string();
    }
    String doPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

     class BackGroud extends AsyncTask<String, Void, String> {

AlertDialog.Builder a;
ProgressDialog prd;
         String s;
         @Override
         protected void onPreExecute() {
          prd=new ProgressDialog(HttpOk.this);
             prd.setTitle("Etat d'envoie");
             prd.setMessage("Envoie en cours...");
             prd.show();
             super.onPreExecute();
         }

         @Override
         protected void onPostExecute(String s) {
             if(prd!=null && prd.isShowing()){
                 prd.dismiss();
             }
             super.onPostExecute("");
         }

         @Override
         protected String doInBackground(String... strings) {

             try {
                 System.out.println("string"+strings[0]+" string 2 "+strings[1]);
                 s = doPostRequest(strings[0],strings[1]);
                 System.out.print("reponse php "+s);
             } catch (IOException e) {
                Log.e("Post erreur ",e.getMessage());
                 e.printStackTrace();
             }


             return s;
         }
     }

}

