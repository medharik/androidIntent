package com.harik.lenovo.intenttest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class ListeCustomAdatper extends AppCompatActivity {
ListView lv=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_custom_adatper);
            ArrayList<User> au=new ArrayList<User>();
au.add(new User("Harik","Mohamed"));
        au.add(new User("Harik","Amira"));
        au.add(new User("Harik","Lina"));
        final UsersAdapter adapter=new UsersAdapter(this,au);
lv=(ListView) findViewById(R.id.listusers);
lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Intent  données= new Intent("harik.mohamed.nomprenom");
                données.putExtra("nomprenom",adapter.getItem(i).getNom()+" "+adapter.getItem(i).getPrenom());
                sendBroadcast(données);
                finish();
            }
        });
    }




      class UsersAdapter extends ArrayAdapter<User> {

        public UsersAdapter(Context context, ArrayList<User> users) {


            super(context, 0, users);

        }



        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position

            User user = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view

            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligneitem, parent, false);

            }

            // Lookup view for data population

            TextView nom = (TextView) convertView.findViewById(R.id.nom);

            TextView prenom = (TextView) convertView.findViewById(R.id.prenom);

            // Populate the data into the template view using the data object

            nom.setText(user.getNom());

            prenom.setText(user.getPrenom());

            // Return the completed view to render on screen

            return convertView;

        }

    }
}
