package com.ai.st.microservice.reports.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ai.st.microservice.reports.dto.ReportInformationDto;
import com.ai.st.microservice.reports.dto.RequestReportDeliveryACDto;
import com.ai.st.microservice.reports.exceptions.BusinessException;

@Service
public class ReportDeliveryAUBusiness extends ReportBusiness {

	Logger log = LogManager.getLogger(ReportDeliveryAUBusiness.class);

	public ReportInformationDto generateReport(RequestReportDeliveryACDto deliveryDto)
			throws BusinessException {

		final Map<String, Object> parameters = new HashMap<>();

		parameters.put("delivery", deliveryDto);
		parameters.put("logo_main", getClass().getResourceAsStream("/jasper/images/st-logo-main.png"));
		parameters.put("logo_second", getClass().getResourceAsStream("/jasper/images/st-logo-second.png"));
		parameters.put("logo_agencia", getClass().getResourceAsStream("/jasper/images/st-logo-agencia.png"));

		String filename = (deliveryDto.getFilename() != null && !deliveryDto.getFilename().isEmpty())
				? deliveryDto.getFilename()
				: RandomStringUtils.random(10, true, false);

		try {
			String pathFile = this.generateReportSimplePDF("/jasper/template_report_download_supply_ac.jrxml",
					parameters, deliveryDto.getNamespace(), filename);
			return new ReportInformationDto(pathFile, true);
		} catch (Exception e) {
			log.error("Error creando reporte pdf autoridad catastral: " + e.getMessage());
			throw new BusinessException("Error creando el reporte");
		}

	}

}
