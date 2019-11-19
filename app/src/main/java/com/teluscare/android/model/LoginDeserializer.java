package com.teluscare.android.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Created by SandeepY on 19112019
 **/


public class LoginDeserializer extends JsonDeserializer<LoginResponseBean> {
    @Override
    public LoginResponseBean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String status = node.get("status").asText();
        String errorMessage = node.get("error").asText();

        if (!"true".equalsIgnoreCase(status)) {
            LoginResponseBean responseBean = new LoginResponseBean();
            responseBean.setError(errorMessage);
            responseBean.setStatus(status);
            responseBean.setLoginResponseDataBean(null);
            return responseBean;
        }else{
            LoginResponseBean responseBeans = new LoginResponseBean();
            responseBeans.setStatus(status);
            responseBeans.setError("");
            responseBeans.setLoginResponseDataBean(responseBeans.getLoginResponseDataBean());
            return responseBeans;
        }
        //return null;
    }
}
