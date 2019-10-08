package com.jonathan.proyectofinal.data;

public class HealthcareProfessional {

    //region Variables
    private String hpUID;
    private String firstName;
    private String lastName;
    private String identificationType;
    private String identification;
    private String gender;
    private String birthday;
    private String nativeCity;
    private Long phoneNumber;
    private String address;
    private String actualCity;
    private String email;
    private String userName;
    private String password;
    private String profession;
    private String employment_place;
    private String rol;
    //endregion

    //region Builders
    public HealthcareProfessional() {
    }

    public HealthcareProfessional(String hpUID, String firstName, String lastName, String identificationType,
                                  String identification, String gender, String birthday,
                                  String nativeCity, Long phoneNumber, String address,
                                  String actualCity, String email, String userName, String password,
                                  String profession, String employment_place, String rol) {
        this.hpUID = hpUID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identificationType = identificationType;
        this.identification = identification;
        this.gender = gender;
        this.birthday = birthday;
        this.nativeCity = nativeCity;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.actualCity = actualCity;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.profession = profession;
        this.employment_place = employment_place;
        this.rol = rol;
    }
    //endregion

    //region Getters and Setters
    public String getHpUID() {
        return hpUID;
    }

    public void setHpUID(String hpUID) {
        this.hpUID = hpUID;
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

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

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

    public String getNativeCity() {
        return nativeCity;
    }

    public void setNativeCity(String nativeCity) {
        this.nativeCity = nativeCity;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActualCity() {
        return actualCity;
    }

    public void setActualCity(String actualCity) {
        this.actualCity = actualCity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmployment_place() {
        return employment_place;
    }

    public void setEmployment_place(String employment_place) {
        this.employment_place = employment_place;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    //endregion

    //region toStingn
    @Override
    public String toString() {
        return "HealthcareProfessional{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identificationType='" + identificationType + '\'' +
                ", identification='" + identification + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", nativeCity='" + nativeCity + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", actualCity='" + actualCity + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", profession='" + profession + '\'' +
                ", employment_place='" + employment_place + '\'' +
                '}';
    }
    //endregion
}
