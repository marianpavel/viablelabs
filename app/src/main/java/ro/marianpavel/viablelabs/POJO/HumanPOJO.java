package ro.marianpavel.viablelabs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HumanPOJO {

    @Expose
    @SerializedName("gender")
    private String gender;

    @Expose
    @SerializedName("name")
    private NamePOJO name;

    @Expose
    @SerializedName("location")
    private LocationPOJO location;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("login")
    private LoginPOJO login;

    @Expose
    @SerializedName("dob")
    private String dob;

    @Expose
    @SerializedName("registered")
    private String registered;

    @Expose
    @SerializedName("phone")
    private String phone;

    @Expose
    @SerializedName("cell")
    private String cell;

    @Expose
    @SerializedName("picture")
    private PicturePOJO picture;

    @Expose
    @SerializedName("nat")
    private String nat;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public NamePOJO getName() {
        return name;
    }

    public void setName(NamePOJO name) {
        this.name = name;
    }

    public LocationPOJO getLocation() {
        return location;
    }

    public void setLocation(LocationPOJO location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginPOJO getLogin() {
        return login;
    }

    public void setLogin(LoginPOJO login) {
        this.login = login;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public PicturePOJO getPicture() {
        return picture;
    }

    public void setPicture(PicturePOJO picture) {
        this.picture = picture;
    }
}
