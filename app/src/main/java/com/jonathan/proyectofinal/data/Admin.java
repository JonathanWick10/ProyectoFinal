package com.jonathan.proyectofinal.data;

public class Admin {
    //region Variables
    private String adminUId;
    private String firstName;
    private String lastName;
    private String identificationType;
    private String identification;
    private String profession;
    private String gender;
    private String birthday;
    private long phoneNumber;
    private String userName;
    private String password;
    private String email;
    private String nativeCity;
    private String actualCity;
    private String address;
    private String employmentPlace;
    private String role;
    private String uriImage;
    private String playerId;
    //endregion

    //region Builders
    public Admin() {
    }

    public Admin(String adminUId, String firstName, String lastName, String identificationType,
                 String  identification, String profession, String gender, String birthday,
                 long phoneNumber, String userName, String password, String email,
                 String nativeCity, String actualCity, String address, String employmentPlace,
                 String role, String uriImage) {
        this.adminUId = adminUId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identificationType = identificationType;
        this.identification = identification;
        this.profession = profession;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nativeCity = nativeCity;
        this.actualCity = actualCity;
        this.address = address;
        this.employmentPlace = employmentPlace;
        this.role = role;
        this.uriImage = uriImage;
    }
    //endregion

    //region Getters and Setters
    public String getAdminUId() {
        return adminUId;
    }

    public void setAdminUId(String adminUId) {
        this.adminUId = adminUId;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

    public String getEmploymentPlace() {
        return employmentPlace;
    }

    public void setEmploymentPlace(String employmentPlace) {
        this.employmentPlace = employmentPlace;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }

    //endregion


    /*
    @Override
    public String toString() {
        return  "adminUId: " + adminUId + '\n' +
                ", firstName: " + firstName + '\n' +
                ", lastName: " + lastName + '\n' +
                ", identificationType: " + identificationType + '\n' +
                ", identification: " + identification + '\n' +
                ", profession: " + profession + '\n' +
                ", gender='" + gender + '\n' +
                ", birthday: " + birthday + '\n' +
                ", phoneNumber: " + phoneNumber +
                ", userName: " + userName + '\n' +
                ", password: " + password + '\n' +
                ", email: " + email + '\n' +
                ", nativeCity='" + nativeCity + '\n' +
                ", actualCity: " + actualCity + '\n' +
                ", address: " + address + '\n' +
                ", employmentPlace: " + employmentPlace + '\n' +
                ", role: " + role + '\n';
    }
    */
}
