package com.teluscare.android.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SandeepY on 19112019
 **/


public class LoginResponseDataBean {

    @JsonProperty("user_id")
    private String User_id;

    @JsonProperty("user_type")
    private String User_type;

    @JsonProperty("consumer_type")
    private String Consumer_type;

    @JsonProperty("first_name")
    private String First_name;

    @JsonProperty("last_name")
    private String Last_name;

    @JsonProperty("user_name")
    private String User_name;

    @JsonProperty("mobile_number")
    private String Mobile_number;

    @JsonProperty("last_login_date")
    private String Last_login_date;

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public String getUser_type() {
        return User_type;
    }

    public void setUser_type(String user_type) {
        User_type = user_type;
    }

    public String getConsumer_type() {
        return Consumer_type;
    }

    public void setConsumer_type(String consumer_type) {
        Consumer_type = consumer_type;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getMobile_number() {
        return Mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        Mobile_number = mobile_number;
    }

    public String getLast_login_date() {
        return Last_login_date;
    }

    public void setLast_login_date(String last_login_date) {
        Last_login_date = last_login_date;
    }
}
