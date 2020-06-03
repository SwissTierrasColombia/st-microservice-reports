package com.ai.st.microservice.reports.dto;

import java.io.Serializable;

public class DeliveryDto implements Serializable {

	private static final long serialVersionUID = -112754967726891284L;

	private String title;

	public DeliveryDto() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}