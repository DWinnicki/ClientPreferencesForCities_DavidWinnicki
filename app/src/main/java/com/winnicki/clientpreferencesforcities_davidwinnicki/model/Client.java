package com.winnicki.clientpreferencesforcities_davidwinnicki.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable {
    private int photo;
    private String name;
    private String address;
    private String phone;
    private ArrayList<City> cityPreferences;

    public Client(int photo, String name, String address, String phone) {
        this.photo = photo;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<City> getCityPreferences() {
        return cityPreferences;
    }

    public void setCityPreferences(ArrayList<City> cityPreferences) {
        this.cityPreferences = cityPreferences;
    }

    @Override
    public String toString() {
        return getName() + '\n' + phone;
    }
}
