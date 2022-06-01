package com.ai.st.microservice.reports.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "RequestReportSNRSuppliesDto")
public final class RequestReportSNRSuppliesDto extends ReportRequestDto implements Serializable {

    @ApiModelProperty(required = true, notes = "Requests")
    private List<RequestReportSNRDto> requests;

    @ApiModelProperty(notes = "Created At")
    private String createdAt;

    public RequestReportSNRSuppliesDto() {
    }

    public List<RequestReportSNRDto> getRequests() {
        return requests;
    }

    public void setRequests(List<RequestReportSNRDto> requests) {
        this.requests = requests;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "RequestReportSNRSuppliesDto{" + "requests=" + requests + ", createdAt='" + createdAt + '\'' + '}';
    }
}
