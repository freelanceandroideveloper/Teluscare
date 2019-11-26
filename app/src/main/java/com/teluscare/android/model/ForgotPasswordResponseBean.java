package com.teluscare.android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SandeepY on 25112019
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForgotPasswordResponseBean extends BaseResponseBean{

    @JsonProperty("data")
    private String Data;

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
