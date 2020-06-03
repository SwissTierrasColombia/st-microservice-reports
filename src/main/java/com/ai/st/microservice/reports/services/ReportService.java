package com.ai.st.microservice.reports.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.st.microservice.reports.dto.DeliveryDto;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

	private final String invoice_template_path = "/jasper/template.jrxml";

	Logger log = LogManager.getLogger(ReportService.class);

	public void generateReport(DeliveryDto deliveryDto) throws IOException {

		// Create a temporary PDF file
		File pdfFile = File.createTempFile("report-delivery", ".pdf");

		// Initiate a FileOutputStream
		try (FileOutputStream pos = new FileOutputStream(pdfFile)) {

			// Load the invoice jrxml template.
			final JasperReport report = loadTemplate();

			// Create parameters map.
			final Map<String, Object> parameters = parameters(deliveryDto);

			// Create an empty datasource.
			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
					Collections.singletonList("Invoice"));
			
			JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);

		} catch (final Exception e) {
			log.error(String.format("An error occured during PDF creation: %s", e));
		}

	}

	// Fill template order parametres
	private Map<String, Object> parameters(DeliveryDto deliveryDto) {

		final Map<String, Object> parameters = new HashMap<>();

		parameters.put("delivery", deliveryDto);

		return parameters;
	}

	// Load invoice jrxml template
	private JasperReport loadTemplate() throws JRException {

		log.info(String.format("Invoice template path : %s", invoice_template_path));

		final InputStream reportInputStream = getClass().getResourceAsStream(invoice_template_path);
		final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

		return JasperCompileManager.compileReport(jasperDesign);
	}

}