package com.jonathan.proyectofinal.data;

public class HealthcareProfessional {

    private String firstName;
    private String lastName;
    private String identificationType;
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
    private String address;
    private String employment_place;

    public HealthcareProfessional() {
    }

    public HealthcareProfessional(String firstName, String lastName, String identificationType,
                                  Long identification, String gender, String birthday, Long phoneNumber,
                                  Integer age, String userName, String password, String email,
                                  String nativeCity, String actualCity, String address, String employment_place) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identificationType = identificationType;
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
        this.address = address;
        this.employment_place = employment_place;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmployment_place() {
        return employment_place;
    }

    public void setEmployment_place(String employment_place) {
        this.employment_place = employment_place;
    }

    @Override
    public String toString() {
        return "HealthcareProfessional{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identificationType='" + identificationType + '\'' +
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
                ", address='" + address + '\'' +
                ", employment_place='" + employment_place + '\'' +
                '}';
    }
}
