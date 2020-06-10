package com.ai.st.microservice.reports.controllers.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ai.st.microservice.reports.business.ReportDownloadSupplyIndividualBusiness;
import com.ai.st.microservice.reports.business.ReportDownloadSupplyTotalBusiness;
import com.ai.st.microservice.reports.dto.RequestReportDownloadSupplyIndividualDto;
import com.ai.st.microservice.reports.dto.RequestReportDownloadSupplyTotalDto;
import com.ai.st.microservice.reports.dto.MessageDto;
import com.ai.st.microservice.reports.dto.ReportInformationDto;
import com.ai.st.microservice.reports.exceptions.BusinessException;
import com.ai.st.microservice.reports.exceptions.InputValidationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Manage Reports", description = "Manage Reports", tags = { "Reports" })
@RestController
@RequestMapping("api/reports/v1/reports")
public class ReportV1Controller {

	private final Logger log = LoggerFactory.getLogger(ReportV1Controller.class);

	@Autowired
	private ReportDownloadSupplyIndividualBusiness reportDownloadSupplyIndividualBusiness;

	@Autowired
	private ReportDownloadSupplyTotalBusiness reportDownloadSupplyTotalBusiness;

	@RequestMapping(value = "/download-supplies-individual", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Report")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Create report", response = ReportInformationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> createReportDownloadSuppliesIndividual(
			@RequestBody RequestReportDownloadSupplyIndividualDto reportDto) {

		HttpStatus httpStatus = null;
		Object responseDto = null;

		try {

			if (reportDto.getNamespace() == null || reportDto.getNamespace().isEmpty()) {
				throw new InputValidationException("El directorio donde se guardará el reporte es obligatorio");
			}

			if (reportDto.getTitle() == null || reportDto.getTitle().isEmpty()) {
				throw new InputValidationException("El titulo es obligatorio");
			}

			responseDto = reportDownloadSupplyIndividualBusiness.generateReport(reportDto);
			httpStatus = HttpStatus.OK;

		} catch (InputValidationException e) {
			log.error("Error ReportV1Controller@createReportDownloadSuppliesIndividual#Validation ---> "
					+ e.getMessage());
			httpStatus = HttpStatus.BAD_REQUEST;
			responseDto = new MessageDto(e.getMessage(), 1);
		} catch (BusinessException e) {
			log.error(
					"Error ReportV1Controller@createReportDownloadSuppliesIndividual#Business ---> " + e.getMessage());
			httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
			responseDto = new MessageDto(e.getMessage(), 2);
		} catch (Exception e) {
			log.error("Error ReportV1Controller@createReportDownloadSuppliesIndividual#General ---> " + e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseDto = new MessageDto(e.getMessage(), 3);
		}

		return new ResponseEntity<>(responseDto, httpStatus);
	}

	@RequestMapping(value = "/download-supplies-total", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Report")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Create report", response = ReportInformationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> createReportDownloadSuppliesTotal(
			@RequestBody RequestReportDownloadSupplyTotalDto reportDto) {

		HttpStatus httpStatus = null;
		Object responseDto = null;

		try {

			if (reportDto.getNamespace() == null || reportDto.getNamespace().isEmpty()) {
				throw new InputValidationException("El directorio donde se guardará el reporte es obligatorio");
			}

			if (reportDto.getTitle() == null || reportDto.getTitle().isEmpty()) {
				throw new InputValidationException("El titulo es obligatorio");
			}

			responseDto = reportDownloadSupplyTotalBusiness.generateReport(reportDto);
			httpStatus = HttpStatus.OK;

		} catch (InputValidationException e) {
			log.error("Error ReportV1Controller@createReportDownloadSuppliesTotal#Validation ---> " + e.getMessage());
			httpStatus = HttpStatus.BAD_REQUEST;
			responseDto = new MessageDto(e.getMessage(), 1);
		} catch (BusinessException e) {
			log.error("Error ReportV1Controller@createReportDownloadSuppliesTotal#Business ---> " + e.getMessage());
			httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
			responseDto = new MessageDto(e.getMessage(), 2);
		} catch (Exception e) {
			log.error("Error ReportV1Controller@createReportDownloadSuppliesTotal#General ---> " + e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseDto = new MessageDto(e.getMessage(), 3);
		}

		return new ResponseEntity<>(responseDto, httpStatus);
	}

}
