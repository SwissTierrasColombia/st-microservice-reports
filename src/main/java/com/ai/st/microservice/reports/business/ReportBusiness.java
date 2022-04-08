package com.ai.st.microservice.reports.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import com.ai.st.microservice.reports.services.tracing.SCMTracing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.util.StringUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public abstract class ReportBusiness {

    Logger log = LogManager.getLogger(ReportBusiness.class);

    @Value("${st.filesDirectory}")
    private String stFilesDirectory;

    private JasperReport loadTemplate(String template) throws JRException {

        final InputStream reportInputStream = getClass().getResourceAsStream(template);
        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

        return JasperCompileManager.compileReport(jasperDesign);
    }

    private File fileWithDirectoryAssurance(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists())
            dir.mkdirs();
        return new File(directory + File.separatorChar + filename);
    }

    protected String generateReportSimplePDF(String template, Map<String, Object> parameters, String namespace,
            String fileName) throws Exception {

        String pathFile = stFilesDirectory + File.separatorChar + StringUtils.cleanPath(namespace);

        // Create a PDF file
        File pdfFile = this.fileWithDirectoryAssurance(pathFile, StringUtils.cleanPath(fileName) + ".pdf");

        // Initiate a FileOutputStream
        try (FileOutputStream pos = new FileOutputStream(pdfFile)) {

            // Load the invoice jrxml template.
            final JasperReport report = this.loadTemplate(template);

            // Create an empty datasource.
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
                    Collections.singletonList("Invoice"));

            JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);

        } catch (final Exception e) {
            String messageError = String.format("Error creando reporte en pdf : %s", e.getMessage());
            SCMTracing.sendError(messageError);
            log.error(messageError);
            throw new Exception("Error creando el reporte con jaspersoft");
        }

        return pdfFile.getAbsolutePath();
    }

}
