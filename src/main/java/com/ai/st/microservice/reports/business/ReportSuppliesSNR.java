package com.ai.st.microservice.reports.business;

import com.ai.st.microservice.reports.dto.ReportInformationDto;
import com.ai.st.microservice.reports.dto.RequestReportSNRSuppliesDto;
import com.ai.st.microservice.reports.exceptions.BusinessException;
import com.ai.st.microservice.reports.services.tracing.SCMTracing;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public final class ReportSuppliesSNR extends ReportBusiness {

    Logger log = LogManager.getLogger(ReportSuppliesSNR.class);

    public ReportInformationDto generateReport(RequestReportSNRSuppliesDto requestDto) throws BusinessException {

        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("request", requestDto);
        parameters.put("logo_main", getClass().getResourceAsStream("/jasper/images/st-logo-main.png"));
        parameters.put("logo_second", getClass().getResourceAsStream("/jasper/images/st-logo-second.png"));
        parameters.put("logo_agencia", getClass().getResourceAsStream("/jasper/images/st-logo-agencia.png"));

        String filename = (requestDto.getFilename() != null && !requestDto.getFilename().isEmpty())
                ? requestDto.getFilename() : RandomStringUtils.random(10, true, false);

        try {
            String pathFile = this.generateReportSimplePDF("/jasper/template_report_requests_attended_snr.jrxml",
                    parameters, requestDto.getNamespace(), filename);
            return new ReportInformationDto(pathFile, true);
        } catch (Exception e) {
            String messageError = String
                    .format("Error creando reporte en pdf de las solicitudes atentidas por SNR : %s", e.getMessage());
            SCMTracing.sendError(messageError);
            log.error(messageError);
            throw new BusinessException("Error creando el reporte");
        }

    }

}
