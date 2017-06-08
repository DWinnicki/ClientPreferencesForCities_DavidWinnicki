package com.winnicki.clientpreferencesforcities_davidwinnicki.model;

import java.io.Serializable;

public class City implements Serializable {
    private String cityName;
    private int countryCode;
    private String countryName;

    public City(String cityName, int countryCode, String countryName) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName.toUpperCase();
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return getCountryName() + ", " + cityName;
    }
}
