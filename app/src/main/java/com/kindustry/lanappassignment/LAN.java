package com.kindustry.lanappassignment;

public class LAN {
    private int LanID;
    private String name;
    private String description;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String locationCode;
    private String locationPhone;
    private String locationManager;
    private String dateOfConfiguration;

    //default constructor
    //sets lanID to -1 if record is new
    LAN(){
        this.LanID = -1;
    };

    //defined constructor
    LAN(int lanID, String name, String description, String address, String city, String state, String zipCode, String locationCode, String locationPhone, String locationManager, String dateOfConfiguration){
        this.LanID = lanID;
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.locationCode = locationCode;
        this.locationPhone = locationPhone;
        this.locationManager = locationManager;
        this.dateOfConfiguration = dateOfConfiguration;
    }

    //setters and getters for each LAN attribute
    public void setLanID(int lanID){
        this.LanID = lanID;
    }
    public int getLanID(){
        return LanID;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }

    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }

    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }

    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
    public String getZipCode(){
        return this.zipCode;
    }
    public void setLocationCode(String locationCode){
        this.locationCode = locationCode;
    }
    public String getLocationCode(){
        return this.locationCode;
    }

    public void setLocationPhone(String locationPhone){
        this.locationPhone = locationPhone;
    }
    public String getLocationPhone(){
        return this.locationPhone;
    }

    public void setLocationManager(String locationManager){
        this.locationManager = locationManager;
    }
    public String getLocationManager(){
        return this.locationManager;
    }

    public void setDateOfConfiguration(String dateOfConfiguration){
        this.dateOfConfiguration = dateOfConfiguration;
    }
    public String getDateOfConfiguration(){
        return this.dateOfConfiguration;
    }
}

