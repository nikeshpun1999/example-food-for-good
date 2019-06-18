package com.example.foodforgood.Model;

public class Usermodel {
private String Fname,Mname,Lname,Username,Password,UserDesc,ProfilePic,Sex,Nationality;
private Number Age;

    public Usermodel(String fname, String mname, String lname, String username, String password, String userDesc, String profilePic, String sex, String nationality, Number age) {

        Fname = fname;
        Mname = mname;
        Lname = lname;
        Username = username;
        Password = password;
        UserDesc = userDesc;
        ProfilePic = profilePic;
        Sex = sex;
        Nationality = nationality;
        Age = age;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserDesc() {
        return UserDesc;
    }

    public void setUserDesc(String userDesc) {
        UserDesc = userDesc;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public Number getAge() {
        return Age;
    }

    public void setAge(Number age) {
        Age = age;
    }
}
