package com.example.foodforgood.Model;

public class Tokenauth {

    private String Success,message,token,username;

    public Tokenauth(String success, String message, String token, String username) {
        Success = success;
        this.message = message;
        this.token = token;
        this.username = username;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
