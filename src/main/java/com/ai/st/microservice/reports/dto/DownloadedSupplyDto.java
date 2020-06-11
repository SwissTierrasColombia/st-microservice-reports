package com.ai.st.microservice.reports.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DownloadedSupplyDto")
public class DownloadedSupplyDto implements Serializable {

	private static final long serialVersionUID = -3681367307489938097L;

	@ApiModelProperty(required = true, notes = "Supply name")
	private String supplyName;

	@ApiModelProperty(required = true, notes = "Download date")
	private String downloadedAt;

	@ApiModelProperty(required = true, notes = "User who downloaded the supply")
	private String downloadedBy;

	@ApiModelProperty(required = true, notes = "Provider name")
	private String providerName;

	public DownloadedSupplyDto() {

	}

	public DownloadedSupplyDto(String supplyName, String downloadedAt, String downloadedBy, String providerName) {
		super();
		this.supplyName = supplyName;
		this.downloadedAt = downloadedAt;
		this.downloadedBy = downloadedBy;
		this.providerName = providerName;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getDownloadedAt() {
		return downloadedAt;
	}

	public void setDownloadedAt(String downloadedAt) {
		this.downloadedAt = downloadedAt;
	}

	public String getDownloadedBy() {
		return downloadedBy;
	}

	public void setDownloadedBy(String downloadedBy) {
		this.downloadedBy = downloadedBy;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

}
