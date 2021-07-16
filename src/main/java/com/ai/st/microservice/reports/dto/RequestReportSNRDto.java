package com.ai.st.microservice.reports.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "RequestReportSNRDto")
public final class RequestReportSNRDto implements Serializable {

    @ApiModelProperty(required = true, notes = "Department")
    private String department;

    @ApiModelProperty(required = true, notes = "Municipality")
    private String municipality;

    @ApiModelProperty(required = true, notes = "Manager")
    private String manager;

    @ApiModelProperty(required = true, notes = "Delivery Date")
    private String deliveryDate;

    @ApiModelProperty(required = true, notes = "Order number")
    private String orderNumber;

    @ApiModelProperty(required = true, notes = "Request number")
    private String requestNumber;

    public RequestReportSNRDto() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }
}
