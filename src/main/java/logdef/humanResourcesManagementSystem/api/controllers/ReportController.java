package logdef.humanResourcesManagementSystem.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import logdef.humanResourcesManagementSystem.business.abstracts.ReportService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.Accountant;
import logdef.humanResourcesManagementSystem.entities.concretes.Report;

@RestController
@RequestMapping("/api/report")
@CrossOrigin
public class ReportController {
	
	private ReportService reportService;

	@Autowired
	public ReportController(ReportService reportService) {
		super();
		this.reportService = reportService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Report report) {
		return ResponseEntity.ok(this.reportService.add(report));
	}
	
	@GetMapping("/getall")
	public DataResult<List<Report>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.reportService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByReportNameAndReportField")
	public DataResult<Report> getByReportNameAndReportField(@RequestParam(value = "reportName") String reportName,@RequestParam(value = "reportField") String reportField) {
		return this.reportService.getByReportNameAndReportField(reportName, reportField);
	}
	
	@PutMapping("/updateByReportName/{reportId}")
	public Result updateByReportName(@RequestParam(value = "reportId") int reportId,@RequestParam(value = "reportNameUpdate")  String reportNameUpdate) {
		return this.reportService.updateByReportName(reportId, reportNameUpdate);
	}
	
	@PutMapping("/updateByReportField/{reportId}")
	public Result updateByReportField(@RequestParam(value = "reportId") int reportId,@RequestParam(value = "reportFieldUpdate") String reportFieldUpdate) {
		return this.reportService.updateByReportField(reportId, reportFieldUpdate);
	}
	
	@DeleteMapping("/deleteByReportId/{reportId}")
	public Result deleteByReportId(@RequestParam(value = "reportId") int reportId) {
		return this.reportService.deleteByReportId(reportId);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception){
		
		Map<String, String> validationErrors = new HashMap<String, String>();
		
		for(FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
		
		return errors;
		
	}

}
