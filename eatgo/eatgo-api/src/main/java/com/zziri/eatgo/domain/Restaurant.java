package com.zziri.eatgo.domain;

public class Restaurant {

    private final String name;
    private final String address;
    private Long id;

    public Restaurant(Long id, String name, String address) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    public String getInformation() {
        return this.name + " in " + this.address;
    }
}
