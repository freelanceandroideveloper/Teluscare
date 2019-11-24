package com.teluscare.android.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyListResponseBean extends BaseResponseBean {

    public List<CompanyListResponseDataBean> getData() {
        return data;
    }

    public void setData(List<CompanyListResponseDataBean> data) {
        this.data = data;
    }
    @JsonProperty("data")
    private List<CompanyListResponseDataBean> data;
}
