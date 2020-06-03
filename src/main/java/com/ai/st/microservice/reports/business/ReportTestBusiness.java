package com.ai.st.microservice.reports.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ai.st.microservice.reports.dto.DeliveryDto;
import com.ai.st.microservice.reports.dto.ReportInformationDto;
import com.ai.st.microservice.reports.exceptions.BusinessException;

@Service
public class ReportTestBusiness extends ReportBusiness {

	Logger log = LogManager.getLogger(ReportTestBusiness.class);

	public ReportInformationDto generateReportTest(DeliveryDto deliveryDto) throws BusinessException {

		final Map<String, Object> parameters = new HashMap<>();

		parameters.put("delivery", deliveryDto);

		try {
			this.generateReportSimplePDF("/jasper/template.jrxml", parameters, "report-delivery");
		} catch (Exception e) {
			log.error("Error creando reporte pdf: " + e.getMessage());
			throw new BusinessException("Error creando el reporte");
		}

		return new ReportInformationDto();
	}

}
