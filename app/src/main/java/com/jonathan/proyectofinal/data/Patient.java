package com.jonathan.proyectofinal.data;

public class Patient {
    private String patientId;
    private String firstName;
    private String lastName;
    private String typeIdentification;
    private Long identification;
    private String gender;
    private String birthday;
    private Long phoneNumber;
    private Integer age;
    private String userName;
    private String password;
    private String email;
    private String nativeCity;
    private String actualCity;
    private Long familyPhoneNumber;
    private String address;
    private String dateDiagnostic;
    private String diagnostic;
    private String observations;
    private String emergencyNumber;


    public Patient() {
    }

    public Patient(String patientId, String firstName, String lastName, String typeIdentification, Long identification, String gender, String birthday, Long phoneNumber, Integer age, String userName, String password, String email, String nativeCity, String actualCity, Long familyPhoneNumber, String address, String dateDiagnostic, String diagnostic, String observations, String emergencyNumber) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeIdentification = typeIdentification;
        this.identification = identification;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nativeCity = nativeCity;
        this.actualCity = actualCity;
        this.familyPhoneNumber = familyPhoneNumber;
        this.address = address;
        this.dateDiagnostic = dateDiagnostic;
        this.diagnostic = diagnostic;
        this.observations = observations;
        this.emergencyNumber = emergencyNumber;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public String getTypeIdentification() {
        return typeIdentification;
    }

    public void setTypeIdentification(String typeIdentification) {
        this.typeIdentification = typeIdentification;
    }

    public Long getIdentification() {
        return identification;
    }

    public void setIdentification(Long identification) {
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Long getFamilyPhoneNumber() {
        return familyPhoneNumber;
    }

    public void setFamilyPhoneNumber(Long familyPhoneNumber) {
        this.familyPhoneNumber = familyPhoneNumber;
    }

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

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", typeIdentification='" + typeIdentification + '\'' +
                ", identification=" + identification +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", age=" + age +
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
}