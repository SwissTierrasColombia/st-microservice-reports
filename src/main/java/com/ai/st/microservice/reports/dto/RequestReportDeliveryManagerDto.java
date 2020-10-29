package com.ai.st.microservice.reports.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RequestReportDeliveryManagerDto")
public class RequestReportDeliveryManagerDto extends ReportRequestDto implements Serializable {

	private static final long serialVersionUID = 6128963726630383092L;

	@ApiModelProperty(required = true, notes = "Supplies")
	private List<DownloadedSupplyDto> supplies;

	@ApiModelProperty(required = true, notes = "Delivery Id")
	private String deliveryId;

	@ApiModelProperty(required = true, notes = "Date delivery")
	private String dateDelivery;

	@ApiModelProperty(required = true, notes = "Manager name")
	private String managerName;

	@ApiModelProperty(required = true, notes = "Operator name")
	private String operatorName;

	@ApiModelProperty(required = true, notes = "Municipality Code")
	private String municipalityCode;

	@ApiModelProperty(required = true, notes = "Municipality Name")
	private String municipalityName;

	@ApiModelProperty(required = true, notes = "Department Name")
	private String departmentName;

	@ApiModelProperty(required = true, notes = "Observations")
	private String observations;

	@ApiModelProperty(required = true, notes = "Date creation")
	private String dateCreation;

	public RequestReportDeliveryManagerDto() {
		this.supplies = new ArrayList<DownloadedSupplyDto>();
	}

	public List<DownloadedSupplyDto> getSupplies() {
		return supplies;
	}

	public void setSupplies(List<DownloadedSupplyDto> supplies) {
		this.supplies = supplies;
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(String dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

}