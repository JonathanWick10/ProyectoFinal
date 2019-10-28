package com.jonathan.proyectofinal.data;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Patient {
    //region Variables
    private String patientUID;
    private String firstName;
    private String lastName;
    private String identificationType;
    private String identification;
    private String gender;
    private String birthday;
    private long phoneNumber;
    private String department;
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;
    private String nativeCity;
    private String actualCity;
    private long familyPhoneNumber;
    private String address;
    private String dateDiagnostic;
    private String diagnostic;
    private String observations;
    private String emergencyNumber;
    private String role;
    List<String> assigns;
    private Uri uriImg;
    //endregion

    //region Builders
    public Patient() {
    }

    public Patient(String patientUID, String firstName, String lastName, String identificationType, String identification,
                   String gender, String birthday, long phoneNumber, String department, String userName,
                   String password, String confirmPassword, String email, String nativeCity, String actualCity,
                   long familyPhoneNumber, String address, String dateDiagnostic, String diagnostic,
                   String observations, String emergencyNumber, String role, List<String> assigns, Uri uriImg) {
        this.patientUID = patientUID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identificationType = identificationType;
        this.identification = identification;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.nativeCity = nativeCity;
        this.actualCity = actualCity;
        this.familyPhoneNumber = familyPhoneNumber;
        this.address = address;
        this.dateDiagnostic = dateDiagnostic;
        this.diagnostic = diagnostic;
        this.observations = observations;
        this.emergencyNumber = emergencyNumber;
        this.role = role;
        this.assigns = assigns;
        this.uriImg = uriImg;
    }
    //endregion

    //region Getters and Setters
    public String getPatientUID() {
        return patientUID;
    }

    public void setPatientUID(String patientUID) {
        this.patientUID = patientUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) { this.identificationType = identificationType;}

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNativeCity() {
        return nativeCity;
    }

    public void setNativeCity(String nativeCity) {
        this.nativeCity = nativeCity;
    }

    public String getActualCity() {
        return actualCity;
    }

    public void setActualCity(String actualCity) {
        this.actualCity = actualCity;
    }

    public long getFamilyPhoneNumber() {
        return familyPhoneNumber;
    }

    public void setFamilyPhoneNumber(long familyPhoneNumber) { this.familyPhoneNumber = familyPhoneNumber;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateDiagnostic() {
        return dateDiagnostic;
    }

    public void setDateDiagnostic(String dateDiagnostic) {
        this.dateDiagnostic = dateDiagnostic;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) { this.emergencyNumber = emergencyNumber; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role;}

    public List<String> getAssigns() {
        return assigns;
    }

    public void setAssigns(List<String> assigns) {
        this.assigns = assigns;
    }

    public Uri getUriImg() {
        return uriImg;
    }

    public void setUriImg(Uri uriImg) {
        this.uriImg = uriImg;
    }

    //endregion

    //region toString
    @Override
    public String toString() {
        return "Patient{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identificationType='" + identificationType + '\'' +
                ", identification=" + identification +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nativeCity='" + nativeCity + '\'' +
                ", actualCity='" + actualCity + '\'' +
                ", familyPhoneNumber=" + familyPhoneNumber +
                ", address='" + address + '\'' +
                ", dateDiagnostic='" + dateDiagnostic + '\'' +
                ", diagnostic='" + diagnostic + '\'' +
                ", observations='" + observations + '\'' +
                ", emergencyNumber='" + emergencyNumber + '\'' +
                '}';
    }
    //endregion
}