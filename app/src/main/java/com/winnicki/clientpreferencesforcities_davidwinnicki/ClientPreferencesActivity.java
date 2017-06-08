package com.winnicki.clientpreferencesforcities_davidwinnicki;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.winnicki.clientpreferencesforcities_davidwinnicki.model.City;
import com.winnicki.clientpreferencesforcities_davidwinnicki.model.Client;
import com.winnicki.clientpreferencesforcities_davidwinnicki.model.FileClientManagement;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientPreferencesActivity extends AppCompatActivity implements View.OnClickListener, ListView.OnItemClickListener {

    TextView textViewName, textViewAddress, textViewPhone;
    ImageView imageViewPhoto;
    ListView listViewCities;
    Button buttonReturn, buttonValidate;

    ArrayList<City> listOfCities;
    ArrayAdapter<City> adapter;

    ArrayList<City> listOfPreferredCities;

    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_preferences);
        initialize();
    }

    private void initialize() {
        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewAddress = (TextView)findViewById(R.id.textViewAddress);
        textViewPhone = (TextView)findViewById(R.id.textViewPhone);
        imageViewPhoto = (ImageView)findViewById(R.id.imageViewPhoto);
        listViewCities = (ListView)findViewById(R.id.listViewCities);
        buttonReturn = (Button)findViewById(R.id.buttonReturn);
        buttonValidate = (Button)findViewById(R.id.buttonValidate);

        Serializable serializable = getIntent().getSerializableExtra("client");
        client = (Client)serializable;

        textViewName.setText(client.getName());
        textViewAddress.setText(client.getAddress());
        textViewPhone.setText(client.getPhone());
        imageViewPhoto.setImageResource(client.getPhoto());

        listOfCities = FileClientManagement.readCities(this, "cities.txt");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, listOfCities);
        listViewCities.setAdapter(adapter);
        listViewCities.setOnItemClickListener(this);

        buttonReturn.setOnClickListener(this);
        buttonValidate.setOnClickListener(this);

        listOfPreferredCities = new ArrayList<>();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CheckedTextView check = (CheckedTextView)view;
        if(check.isChecked()) {
            listOfPreferredCities.add(listOfCities.get(position));
        } else {
            listOfPreferredCities.remove(listOfCities.get(position));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonReturn:
                processReturn();
                break;
            case R.id.buttonValidate:
                processValidate();
                break;
        }
    }

    private void processReturn() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("client", client);
        if(listOfPreferredCities.size() > 0) {
            setResult(Activity.RESULT_OK, intent);
        } else {
            setResult(Activity.RESULT_CANCELED, intent);
        }
        finish();
    }

    private void processValidate() {
        if(listOfPreferredCities.size() > 0) {
            client.setCityPreferences(listOfPreferredCities);
            Toast.makeText(this, "Cities: " + listOfPreferredCities, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No cities selected.", Toast.LENGTH_SHORT).show();
        }
    }
}
