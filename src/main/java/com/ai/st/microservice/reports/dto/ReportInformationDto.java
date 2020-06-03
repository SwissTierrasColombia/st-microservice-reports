package com.ai.st.microservice.reports.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ReportInformationDto", description = "Report Information Dto")
public class ReportInformationDto implements Serializable {

	private static final long serialVersionUID = -2036372078525852112L;

	@ApiModelProperty(required = true, notes = "URL Report")
	private String urlReport;

	public ReportInformationDto() {

	}

	public String getUrlReport() {
		return urlReport;
	}

	public void setUrlReport(String urlReport) {
		this.urlReport = urlReport;
	}

}
