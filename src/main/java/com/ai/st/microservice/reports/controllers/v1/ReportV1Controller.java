package com.ai.st.microservice.reports.controllers.v1;

import com.ai.st.microservice.common.dto.general.BasicResponseDto;
import com.ai.st.microservice.reports.business.ReportSuppliesSNR;
import com.ai.st.microservice.reports.dto.*;
import com.ai.st.microservice.reports.services.tracing.SCMTracing;
import com.ai.st.microservice.reports.services.tracing.TracingKeyword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ai.st.microservice.reports.business.ReportDeliveryAUBusiness;
import com.ai.st.microservice.reports.business.ReportDeliveryManagerBusiness;
import com.ai.st.microservice.reports.business.ReportDownloadSupplyBusiness;
import com.ai.st.microservice.reports.exceptions.BusinessException;
import com.ai.st.microservice.reports.exceptions.InputValidationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Manage Reports", tags = { "Reports" })
@RestController
@RequestMapping("api/reports/v1/reports")
public class ReportV1Controller {

    private final Logger log = LoggerFactory.getLogger(ReportV1Controller.class);

    private final ReportDownloadSupplyBusiness reportDownloadSupplyTotalBusiness;
    private final ReportDeliveryAUBusiness reportDeliveryAUBusiness;
    private final ReportDeliveryManagerBusiness deliveryManagerBusiness;
    private final ReportSuppliesSNR suppliesSNR;

    public ReportV1Controller(ReportDownloadSupplyBusiness reportDownloadSupplyTotalBusiness,
            ReportDeliveryAUBusiness reportDeliveryAUBusiness, ReportDeliveryManagerBusiness deliveryManagerBusiness,
            ReportSuppliesSNR suppliesSNR) {
        this.reportDownloadSupplyTotalBusiness = reportDownloadSupplyTotalBusiness;
        this.reportDeliveryAUBusiness = reportDeliveryAUBusiness;
        this.deliveryManagerBusiness = deliveryManagerBusiness;
        this.suppliesSNR = suppliesSNR;
    }

    @PostMapping(value = "/download-supplies", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Report")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Create report", response = ReportInformationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> createReportDownloadSupplies(@RequestBody RequestReportDownloadSupplyDto reportDto) {

        HttpStatus httpStatus;
        Object responseDto;

        try {

            SCMTracing.setTransactionName("createReportDownloadSupplies");
            SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, reportDto.toString());

            if (reportDto.getNamespace() == null || reportDto.getNamespace().isEmpty()) {
                throw new InputValidationException("El directorio donde se guardará el reporte es obligatorio.");
            }

            if (reportDto.getDeliveryId() == null || reportDto.getDeliveryId().isEmpty()) {
                throw new InputValidationException("El ID de entrega es obligatory.");
            }

            if (reportDto.getDateDelivery() == null || reportDto.getDateDelivery().isEmpty()) {
                throw new InputValidationException("La fecha de entrega es obligatoria.");
            }

            if (reportDto.getManagerName() == null || reportDto.getManagerName().isEmpty()) {
                throw new InputValidationException("El nombre del gestor es obligatorio.");
            }

            if (reportDto.getOperatorName() == null || reportDto.getOperatorName().isEmpty()) {
                throw new InputValidationException("El nombre del operador es obligatorio.");
            }

            if (reportDto.getMunicipalityCode() == null || reportDto.getMunicipalityCode().isEmpty()) {
                throw new InputValidationException("El código del municipio es obligatorio.");
            }

            if (reportDto.getMunicipalityName() == null || reportDto.getMunicipalityName().isEmpty()) {
                throw new InputValidationException("El nombre del municipio es obligatorio.");
            }

            if (reportDto.getDepartmentName() == null || reportDto.getDepartmentName().isEmpty()) {
                throw new InputValidationException("El nombre del departamento es obligatorio.");
            }

            if (reportDto.getObservations() == null || reportDto.getObservations().isEmpty()) {
                throw new InputValidationException("Las observaciones son obligatorias.");
            }

            if (reportDto.getDateCreation() == null || reportDto.getDateCreation().isEmpty()) {
                throw new InputValidationException("La fecha de creación es obligatoria.");
            }

            if (reportDto.getSupplies() == null || reportDto.getSupplies().size() == 0) {
                throw new InputValidationException("Los insumos son requeridos.");
            }

            responseDto = reportDownloadSupplyTotalBusiness.generateReport(reportDto);
            httpStatus = HttpStatus.OK;

        } catch (InputValidationException e) {
            log.error("Error ReportV1Controller@createReportDownloadSupplies#Validation ---> " + e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        } catch (BusinessException e) {
            log.error("Error ReportV1Controller@createReportDownloadSupplies#Business ---> " + e.getMessage());
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        } catch (Exception e) {
            log.error("Error ReportV1Controller@createReportDownloadSupplies#General ---> " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }

    @PostMapping(value = "/delivery-au", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Report")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Create report", response = ReportInformationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> createReportDeliveryAU(@RequestBody RequestReportDeliveryACDto reportDto) {

        HttpStatus httpStatus;
        Object responseDto;

        try {

            SCMTracing.setTransactionName("createReportDeliveryAU");
            SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, reportDto.toString());

            if (reportDto.getNamespace() == null || reportDto.getNamespace().isEmpty()) {
                throw new InputValidationException("El directorio donde se guardará el reporte es obligatorio.");
            }

            if (reportDto.getManagerName() == null || reportDto.getManagerName().isEmpty()) {
                throw new InputValidationException("El nombre del gestor es obligatorio.");
            }

            if (reportDto.getMunicipalityCode() == null || reportDto.getMunicipalityCode().isEmpty()) {
                throw new InputValidationException("El código del municipio es obligatorio.");
            }

            if (reportDto.getMunicipalityName() == null || reportDto.getMunicipalityName().isEmpty()) {
                throw new InputValidationException("El nombre del municipio es obligatorio.");
            }

            if (reportDto.getDepartmentName() == null || reportDto.getDepartmentName().isEmpty()) {
                throw new InputValidationException("El nombre del departamento es obligatorio.");
            }

            if (reportDto.getSupplies() == null || reportDto.getSupplies().size() == 0) {
                throw new InputValidationException("Los insumos son requeridos.");
            }

            responseDto = reportDeliveryAUBusiness.generateReport(reportDto);
            httpStatus = HttpStatus.OK;

        } catch (InputValidationException e) {
            log.error("Error ReportV1Controller@createReportDeliveryAU#Validation ---> " + e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        } catch (BusinessException e) {
            log.error("Error ReportV1Controller@createReportDeliveryAU#Business ---> " + e.getMessage());
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        } catch (Exception e) {
            log.error("Error ReportV1Controller@createReportDeliveryAU#General ---> " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }

    @PostMapping(value = "/delivery-manager", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Report")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Create report", response = ReportInformationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> createReportDeliveryManager(@RequestBody RequestReportDeliveryManagerDto reportDto) {

        HttpStatus httpStatus;
        Object responseDto;

        try {

            SCMTracing.setTransactionName("createReportDeliveryManager");
            SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, reportDto.toString());

            if (reportDto.getNamespace() == null || reportDto.getNamespace().isEmpty()) {
                throw new InputValidationException("El directorio donde se guardará el reporte es obligatorio.");
            }

            if (reportDto.getDeliveryId() == null || reportDto.getDeliveryId().isEmpty()) {
                throw new InputValidationException("El ID de entrega es obligatorio.");
            }

            if (reportDto.getDateDelivery() == null || reportDto.getDateDelivery().isEmpty()) {
                throw new InputValidationException("La fecha de entrega es obligatoria.");
            }

            if (reportDto.getManagerName() == null || reportDto.getManagerName().isEmpty()) {
                throw new InputValidationException("El nombre del gestor es obligatorio.");
            }

            if (reportDto.getOperatorName() == null || reportDto.getOperatorName().isEmpty()) {
                throw new InputValidationException("El nombre del operador es obligatorio.");
            }

            if (reportDto.getMunicipalityCode() == null || reportDto.getMunicipalityCode().isEmpty()) {
                throw new InputValidationException("El código del municipio es obligatorio.");
            }

            if (reportDto.getMunicipalityName() == null || reportDto.getMunicipalityName().isEmpty()) {
                throw new InputValidationException("El nombre del municipio es obligatorio.");
            }

            if (reportDto.getDepartmentName() == null || reportDto.getDepartmentName().isEmpty()) {
                throw new InputValidationException("El nombre del departamento es obligatorio.");
            }

            if (reportDto.getObservations() == null || reportDto.getObservations().isEmpty()) {
                throw new InputValidationException("Las observaciones son obligatorias.");
            }

            if (reportDto.getDateCreation() == null || reportDto.getDateCreation().isEmpty()) {
                throw new InputValidationException("La fecha de creación es obligatoria.");
            }

            if (reportDto.getSupplies() == null || reportDto.getSupplies().size() == 0) {
                throw new InputValidationException("Los insumos son requeridos.");
            }

            responseDto = deliveryManagerBusiness.generateReport(reportDto);
            httpStatus = HttpStatus.OK;

        } catch (InputValidationException e) {
            log.error("Error ReportV1Controller@createReportDeliveryManager#Validation ---> " + e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        } catch (BusinessException e) {
            log.error("Error ReportV1Controller@createReportDeliveryManager#Business ---> " + e.getMessage());
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        } catch (Exception e) {
            log.error("Error ReportV1Controller@createReportDeliveryManager#General ---> " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }

    @PostMapping(value = "/supplies-snr", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Report")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Create report", response = ReportInformationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> createReportSuppliesSNR(@RequestBody RequestReportSNRSuppliesDto reportDto) {

        HttpStatus httpStatus;
        Object responseDto;

        try {

            SCMTracing.setTransactionName("createReportSuppliesSNR");
            SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, reportDto.toString());

            if (reportDto.getNamespace() == null || reportDto.getNamespace().isEmpty()) {
                throw new InputValidationException("El directorio donde se guardará el reporte es obligatorio.");
            }

            if (reportDto.getRequests() == null || reportDto.getRequests().size() == 0) {
                throw new InputValidationException("Las solicitudes son requeridas.");
            }

            responseDto = suppliesSNR.generateReport(reportDto);
            httpStatus = HttpStatus.OK;

        } catch (InputValidationException e) {
            log.error("Error ReportV1Controller@createReportSuppliesSNR#Validation ---> " + e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        } catch (BusinessException e) {
            log.error("Error ReportV1Controller@createReportSuppliesSNR#Business ---> " + e.getMessage());
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        } catch (Exception e) {
            log.error("Error ReportV1Controller@createReportSuppliesSNR#General ---> " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }

}
