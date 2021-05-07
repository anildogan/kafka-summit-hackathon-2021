package com.hackathon.sessionratelimiter.model;

import java.util.List;

public class UserRateLimiterResponseRow {

    private List<Integer> columns;

    public List<Integer> getColumns() {
        return columns;
    }

    public void setColumns(List<Integer> columns) {
        this.columns = columns;
    }
}
