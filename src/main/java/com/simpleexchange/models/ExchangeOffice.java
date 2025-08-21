package com.simpleexchange.models;

public class ExchangeOffice {
    private Long id;
    private String name;
    private String address;
    private boolean isDefault;

    public ExchangeOffice() {}

    public ExchangeOffice(Long id, String name, String address, boolean isDefault) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.isDefault = isDefault;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
