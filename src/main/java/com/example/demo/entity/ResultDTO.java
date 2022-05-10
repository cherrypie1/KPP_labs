package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultDTO {
    @JsonProperty
    private List<ResultValue> list;
    @JsonProperty
    private Double averageResult;
    @JsonProperty
    private Integer maxResult;
    @JsonProperty
    private Integer minResult;

    public void setList(List<ResultValue> list) {
        this.list = list;
    }

    public void setAverageResult(Double averageResult) {
        this.averageResult = averageResult;
    }

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    public void setMinResult(Integer minResult) {
        this.minResult = minResult;
    }
}
