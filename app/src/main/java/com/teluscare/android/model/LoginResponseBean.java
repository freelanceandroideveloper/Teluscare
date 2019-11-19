package com.teluscare.android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by SandeepY on 19112019
 **/

@JsonDeserialize(using = LoginDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseBean extends BaseResponseBean{

    @JsonProperty("data")
    private LoginResponseDataBean loginResponseDataBean;

    public LoginResponseDataBean getLoginResponseDataBean() {
        return loginResponseDataBean;
    }

    public void setLoginResponseDataBean(LoginResponseDataBean loginResponseDataBean) {
        this.loginResponseDataBean = loginResponseDataBean;
    }
}
