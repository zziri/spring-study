package com.zziri.eatgo.domain;

public class Restaurant {

    private final String name;
    private final String addres;

    public Restaurant(String name, String address) {
        this.name = name;
        this.addres = address;
    }

    public String getAddress() {
        return this.addres;
    }

    public String getName() {
        return this.name;
    }

    public String getInformation() {
        return this.name + " in " + this.addres;
    }
}
