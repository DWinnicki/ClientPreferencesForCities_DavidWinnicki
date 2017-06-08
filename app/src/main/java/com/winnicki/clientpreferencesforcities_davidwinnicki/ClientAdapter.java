package com.winnicki.clientpreferencesforcities_davidwinnicki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.winnicki.clientpreferencesforcities_davidwinnicki.model.Client;

import java.util.ArrayList;

public class ClientAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Client> listOfClients;

    public ClientAdapter(Context context, ArrayList<Client> listOfClients) {
        this.context = context;
        this.listOfClients = listOfClients;
    }

    @Override
    public int getCount() {
        return listOfClients.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfClients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.client_item, null);
        }

        TextView textViewClientName = (TextView)convertView.findViewById(R.id.textViewClientName);
        ImageView imageViewClientPhoto = (ImageView)convertView.findViewById(R.id.imageViewClientPhoto);

        Client client = listOfClients.get(position);
        String clientName = client.getName();
        int clientPhoto = client.getPhoto();

        textViewClientName.setText(clientName);
        imageViewClientPhoto.setImageResource(clientPhoto);

        return convertView;
    }
}
