package com.ai.st.microservice.reports.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ai.st.microservice.reports.dto.RequestReportDownloadSupplyTotalDto;
import com.ai.st.microservice.reports.dto.ReportInformationDto;
import com.ai.st.microservice.reports.exceptions.BusinessException;

@Service
public class ReportDownloadSupplyTotalBusiness extends ReportBusiness {

	Logger log = LogManager.getLogger(ReportDownloadSupplyTotalBusiness.class);

	public ReportInformationDto generateReport(RequestReportDownloadSupplyTotalDto deliveryDto)
			throws BusinessException {

		final Map<String, Object> parameters = new HashMap<>();

		parameters.put("delivery", deliveryDto);

		String filename = (deliveryDto.getFilename() != null && !deliveryDto.getFilename().isEmpty())
				? deliveryDto.getFilename()
				: RandomStringUtils.random(10, true, false);

		try {
			String pathFile = this.generateReportSimplePDF("/jasper/template_report_download_supply_total.jrxml",
					parameters, deliveryDto.getNamespace(), filename);
			return new ReportInformationDto(pathFile, true);
		} catch (Exception e) {
			log.error("Error creando reporte pdf: " + e.getMessage());
			throw new BusinessException("Error creando el reporte");
		}

	}

}
