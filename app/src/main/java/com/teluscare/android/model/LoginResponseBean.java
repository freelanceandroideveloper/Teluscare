package com.teluscare.android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by SandeepY on 19112019
 **/

//@JsonDeserialize(using = LoginDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseBean extends BaseResponseBean{

    @JsonProperty("data")
    private LoginResponseDataBean Data;

    public LoginResponseDataBean getData() {
        return Data;
    }

    public void setData(LoginResponseDataBean data) {
        Data = data;
    }
}
