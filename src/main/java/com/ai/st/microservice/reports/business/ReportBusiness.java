package com.ai.st.microservice.reports.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public abstract class ReportBusiness {
	
	Logger log = LogManager.getLogger(ReportBusiness.class);

	private JasperReport loadTemplate(String template) throws JRException {

		final InputStream reportInputStream = getClass().getResourceAsStream(template);
		final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

		return JasperCompileManager.compileReport(jasperDesign);
	}

	protected void generateReportSimplePDF(String template, Map<String, Object> parameters, String fileName)
			throws IOException {

		// Create a temporary PDF file
		File pdfFile = File.createTempFile(fileName, ".pdf");

		// Initiate a FileOutputStream
		try (FileOutputStream pos = new FileOutputStream(pdfFile)) {

			// Load the invoice jrxml template.
			final JasperReport report = this.loadTemplate(template);

			// Create an empty datasource.
			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
					Collections.singletonList("Invoice"));

			JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);

		} catch (final Exception e) {
			log.error(String.format("An error occured during PDF creation: %s", e));
		}

	}

}
