package com.example.foodforgood.Model;

public class Tokenauth {

    private String token;
    private String id;
    private String fname;
    private String mname;
    private String lname;
    private String username;
    private String password;
    private String userdesc;
    private String profilepic;
    private String age;
    private String sex;
    private String nationality;


    public Tokenauth(String token, String id, String fname, String mname, String lname, String username, String password, String userdesc, String profilepic, String age, String sex, String nationality) {
        this.token = token;
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.userdesc = userdesc;
        this.profilepic = profilepic;
        this.age = age;
        this.sex = sex;
        this.nationality = nationality;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserdesc() {
        return userdesc;
    }

    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
