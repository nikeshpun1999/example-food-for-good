package com.example.foodforgood.Model;

public class Usermodel {
    private String firstname,middlename,lastname,uname,password,userdescription,profilepic,sex,nationality;
    private Number age;

    public Usermodel(String firstname, String middlename, String lastname, String uname, String password, String userdescription, String profilepic, String sex, String nationality, Number age) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.uname = uname;
        this.password = password;
        this.userdescription = userdescription;
        this.profilepic = profilepic;
        this.sex = sex;
        this.nationality = nationality;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserdescription() {
        return userdescription;
    }

    public void setUserdescription(String userdescription) {
        this.userdescription = userdescription;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
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

    public Number getAge() {
        return age;
    }

    public void setAge(Number age) {
        this.age = age;
    }
}


