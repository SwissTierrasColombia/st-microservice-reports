package com.ai.st.microservice.reports.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RequestReportDownloadACDto")
public class RequestReportDeliveryACDto extends ReportRequestDto implements Serializable {

    private static final long serialVersionUID = -112754967726891284L;

    @ApiModelProperty(required = true, notes = "Supplies")
    private List<SupplyACDto> supplies;

    @ApiModelProperty(required = true, notes = "Manager name")
    private String managerName;

    @ApiModelProperty(required = true, notes = "Municipality Code")
    private String municipalityCode;

    @ApiModelProperty(required = true, notes = "Municipality Name")
    private String municipalityName;

    @ApiModelProperty(required = true, notes = "Department Name")
    private String departmentName;

    @ApiModelProperty(notes = "Created At")
    private String createdAt;

    public RequestReportDeliveryACDto() {
        this.supplies = new ArrayList<SupplyACDto>();
    }

    public List<SupplyACDto> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<SupplyACDto> supplies) {
        this.supplies = supplies;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "RequestReportDeliveryACDto{" + "supplies=" + supplies + ", managerName='" + managerName + '\''
                + ", municipalityCode='" + municipalityCode + '\'' + ", municipalityName='" + municipalityName + '\''
                + ", departmentName='" + departmentName + '\'' + ", createdAt='" + createdAt + '\'' + '}';
    }
}