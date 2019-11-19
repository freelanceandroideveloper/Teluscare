package com.teluscare.android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SandeepY on 18112019
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponseBean {

    @JsonProperty("status")
    private String Status;

    @JsonProperty("error")
    private String Error;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
