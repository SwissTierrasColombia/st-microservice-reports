package com.ai.st.microservice.reports.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SupplyACDto")
public class SupplyACDto implements Serializable {

	private static final long serialVersionUID = 1768635734498088439L;

	@ApiModelProperty(required = true, notes = "Supply name")
	private String supplyName;

	@ApiModelProperty(required = true, notes = "Download date")
	private String loadedAt;

	@ApiModelProperty(required = true, notes = "User who downloaded the supply")
	private String observations;

	@ApiModelProperty(required = true, notes = "Provider name")
	private String providerName;

	public SupplyACDto() {

	}

	public SupplyACDto(String supplyName, String loadedAt, String observations, String providerName) {
		super();
		this.supplyName = supplyName;
		this.loadedAt = loadedAt;
		this.observations = observations;
		this.providerName = providerName;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getLoadedAt() {
		return loadedAt;
	}

	public void setLoadedAt(String loadedAt) {
		this.loadedAt = loadedAt;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

}
