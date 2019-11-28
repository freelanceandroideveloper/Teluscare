package com.teluscare.android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndividualListResponseBean extends BaseResponseBean {


    public List<IndividualListResponseDataBean> getData() {
        return Data;
    }

    public void setData(List<IndividualListResponseDataBean> data) {
        Data = data;
    }

    @JsonProperty("data")
    private List<IndividualListResponseDataBean> Data;



}
