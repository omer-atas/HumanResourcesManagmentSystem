package logdef.humanResourcesManagementSystem.api.controllers;

import java.util.HashMap;
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

import logdef.humanResourcesManagementSystem.business.abstracts.StudiedSectionService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.StudiedSection;

@RestController
@RequestMapping("/api/studiedSection")
@CrossOrigin
public class StudiedSectionController {
	
	private StudiedSectionService studiedSectionService;

	@Autowired
	public StudiedSectionController(StudiedSectionService studiedSectionService) {
		super();
		this.studiedSectionService = studiedSectionService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody StudiedSection studiedSection) {
		return ResponseEntity.ok(this.studiedSectionService.add(studiedSection));
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return ResponseEntity.ok(this.studiedSectionService.getAll(pageNo, pageSize));
	}
	
	@GetMapping("/getByStudiedSection/{studiedSection}")
	public DataResult<StudiedSection> getByStudiedSection(@RequestParam(value = "studiedSection") String studiedSection) {
		return this.studiedSectionService.getByStudiedSection(studiedSection);
	}
	
	@GetMapping("/getByStudiedSectionId/{studiedSectionId}")
	public DataResult<StudiedSection> getByStudiedSectionId(@RequestParam(value = "studiedSectionId") int studiedSectionId) {
		return this.studiedSectionService.getByStudiedSectionId(studiedSectionId);
	}
	
	@PutMapping("/updateByStudiedSection/{studiedSectionId}")
	public Result updateByStudiedSection(@RequestParam(value = "studiedSectionId") int studiedSectionId,@RequestParam(value = "studiedSectionUpdate") String studiedSectionUpdate) {
		return this.studiedSectionService.updateByStudiedSection(studiedSectionId, studiedSectionUpdate);
	}
	
	@DeleteMapping("/deleteByWorkingHourId/{studiedSectionId}")
	public Result deleteByStudiedSectionId(@RequestParam(value = "studiedSectionId") int studiedSectionId) {
		return this.studiedSectionService.deleteByStudiedSectionId(studiedSectionId);
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
