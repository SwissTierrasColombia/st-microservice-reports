package com.ai.st.microservice.reports.services.tracing;

public enum TracingKeyword {
    BODY_REQUEST("bodyRequest");

    private final String value;

    TracingKeyword(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
