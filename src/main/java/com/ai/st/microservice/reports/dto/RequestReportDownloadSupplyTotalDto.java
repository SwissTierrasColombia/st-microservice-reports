package com.ai.st.microservice.reports.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RequestReportDownloadSupplyTotalDto")
public class RequestReportDownloadSupplyTotalDto extends ReportRequestDto implements Serializable {

	private static final long serialVersionUID = -112754967726891284L;

	@ApiModelProperty(required = true, notes = "Title")
	private String title;

	public RequestReportDownloadSupplyTotalDto() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}