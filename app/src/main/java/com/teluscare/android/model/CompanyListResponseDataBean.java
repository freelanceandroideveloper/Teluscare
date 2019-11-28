package com.teluscare.android.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyListResponseDataBean {

    @JsonProperty("id")
    private String User_id;

    @JsonProperty("job_type")
    private String username;

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
