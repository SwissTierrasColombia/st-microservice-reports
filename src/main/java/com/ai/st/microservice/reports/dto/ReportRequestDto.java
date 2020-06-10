package com.ai.st.microservice.reports.dto;

import io.swagger.annotations.ApiModelProperty;

public class ReportRequestDto {

	@ApiModelProperty(required = true, notes = "Namespace")
	private String namespace;

	@ApiModelProperty(required = false, notes = "Filename")
	private String filename;

	public ReportRequestDto() {

	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
