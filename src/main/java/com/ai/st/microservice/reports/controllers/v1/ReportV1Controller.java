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

import com.ai.st.microservice.reports.business.ReportTestBusiness;
import com.ai.st.microservice.reports.dto.DeliveryDto;
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
	private ReportTestBusiness reportTestBusiness;

	@RequestMapping(value = "/test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Report")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create report", response = ReportInformationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> createReportTest(@RequestBody DeliveryDto deliveryDto) {

		HttpStatus httpStatus = null;
		Object responseDto = null;

		try {

			if (deliveryDto.getTitle() == null || deliveryDto.getTitle().isEmpty()) {
				throw new InputValidationException("El titulo es obligatorio");
			}

			responseDto = reportTestBusiness.generateReportTest(deliveryDto);
			httpStatus = HttpStatus.OK;

		} catch (InputValidationException e) {
			log.error("Error ReportV1Controller@createReportTest#Validation ---> " + e.getMessage());
			httpStatus = HttpStatus.BAD_REQUEST;
			responseDto = new MessageDto(e.getMessage(), 1);
		} catch (BusinessException e) {
			log.error("Error ReportV1Controller@createReportTest#Business ---> " + e.getMessage());
			httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
			responseDto = new MessageDto(e.getMessage(), 2);
		} catch (Exception e) {
			log.error("Error ReportV1Controller@createReportTest#General ---> " + e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseDto = new MessageDto(e.getMessage(), 3);
		}

		return new ResponseEntity<>(responseDto, httpStatus);
	}

}
