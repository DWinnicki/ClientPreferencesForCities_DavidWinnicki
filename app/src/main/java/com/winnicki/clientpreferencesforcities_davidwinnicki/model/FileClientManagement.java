package com.winnicki.clientpreferencesforcities_davidwinnicki.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileClientManagement {

    public static ArrayList<City> readCities(Context context, String fileName) {
        ArrayList<City> listOfCities = new ArrayList<>();

        AssetManager assetManager = context.getResources().getAssets();

        try {
            InputStreamReader isr = new InputStreamReader(assetManager.open(fileName));
            BufferedReader br = new BufferedReader(isr);
            String oneLine;

            while ((oneLine = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(oneLine, ",");
                int countryCode = Integer.parseInt(st.nextToken());
                String countryName = st.nextToken();
                while (st.hasMoreTokens()) {
                    String cityName = st.nextToken();
                    City city = new City(countryName, countryCode, cityName);
                    listOfCities.add(city);
                }
            }
            br.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfCities;
    }

    public static ArrayList<Client> readClients(Context context, String fileName) {
        ArrayList<Client> listOfClients = new ArrayList<>();

        AssetManager assetManager = context.getResources().getAssets();

        try {
            InputStreamReader isr = new InputStreamReader(assetManager.open(fileName));
            BufferedReader br = new BufferedReader(isr);
            String oneLine;

            while ((oneLine = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(oneLine, ",");
                String picture = st.nextToken();
                int photo = context.getResources().getIdentifier(picture, "drawable", context.getPackageName());
                String name = st.nextToken();
                String address = st.nextToken();
                String phone = st.nextToken();
                Client client = new Client(photo, name, address, phone);
                listOfClients.add(client);
            }
            br.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfClients;
    }

}
