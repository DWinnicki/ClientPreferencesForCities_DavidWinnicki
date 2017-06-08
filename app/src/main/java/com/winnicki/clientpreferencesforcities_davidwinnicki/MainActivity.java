package com.winnicki.clientpreferencesforcities_davidwinnicki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.winnicki.clientpreferencesforcities_davidwinnicki.model.Client;
import com.winnicki.clientpreferencesforcities_davidwinnicki.model.FileClientManagement;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    static final int PICK_CITIES_REQUEST = 100;

    ListView listViewClients;
    TextView textViewInfo;

    ArrayList<Client> listOfClients;
    ClientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        listViewClients = (ListView)findViewById(R.id.listViewClients);
        textViewInfo = (TextView)findViewById(R.id.textViewInfo);

        listOfClients = FileClientManagement.readClients(this, "clients.txt");
        adapter = new ClientAdapter(this, listOfClients);
        listViewClients.setAdapter(adapter);
        listViewClients.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Client client = listOfClients.get(position);

        Intent intent = new Intent(this, ClientPreferencesActivity.class);
        intent.putExtra("client", client);
        startActivityForResult(intent, PICK_CITIES_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CITIES_REQUEST) {
            Serializable serializable = data.getSerializableExtra("client");
            Client client = (Client)serializable;
            if (resultCode == RESULT_OK) {
                textViewInfo.setText("Info: " + client.toString() + "\nCities: " + client.getCityPreferences());
            } else if(resultCode == RESULT_CANCELED) {
                textViewInfo.setText("Info: " + client.toString() + "\nNo cities selected.");
            }
        }
    }
}
