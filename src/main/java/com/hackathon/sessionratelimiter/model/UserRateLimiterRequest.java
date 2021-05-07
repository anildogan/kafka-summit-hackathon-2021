package com.hackathon.sessionratelimiter.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class UserRateLimiterRequest {

    private static String QUERY_FORMAT = "SELECT REQUEST_COUNT FROM USER_EVENTS_LIMIT_PER_MINUTE WHERE USERID = %s AND WINDOWEND > %s;";

    private String ksql;

    public String getKsql() {
        return ksql;
    }

    public void setKsql(String userId) {
        this.ksql = String.format(QUERY_FORMAT, userId, DateTime.now(DateTimeZone.UTC).getMillis());
    }
}
