package com.jonathan.proyectofinal.data;

public class Patient {
    private long id;
    private long phoneNumber;
    private int age;
    private String nativeCity;
    private String cityOfResidence;
    private String name;
    private String address;
    private String gender ;

    public Patient() {
    }

    public Patient(long id, long phoneNumber, int age, String nativeCity, String cityOfResidence, String name, String address, String gender) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.nativeCity = nativeCity;
        this.cityOfResidence = cityOfResidence;
        this.name = name;
        this.address = address;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNativeCity() {
        return nativeCity;
    }

    public void setNativeCity(String nativeCity) {
        this.nativeCity = nativeCity;
    }

    public String getCityOfResidence() {
        return cityOfResidence;
    }

    public void setCityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
