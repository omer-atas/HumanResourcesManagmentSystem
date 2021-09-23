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

import logdef.humanResourcesManagementSystem.business.abstracts.SocialMediaService;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.SocialMedia;

@RestController
@RequestMapping("/api/socialmedia")
@CrossOrigin
public class SocialMediaController {
	
	private SocialMediaService socialMediaService;

	@Autowired
	public SocialMediaController(SocialMediaService socialMediaService) {
		super();
		this.socialMediaService = socialMediaService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody SocialMedia socialMedia) {
		return ResponseEntity.ok(this.socialMediaService.add(socialMedia));
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return ResponseEntity.ok(this.socialMediaService.getAll(pageNo, pageSize));
	}
	
	@PutMapping("/updateByGithub/{socialMediaId}")
	public Result updateByGithub(@RequestParam(value = "socialMediaId") int socialMediaId,@RequestParam(value = "githubUpdate") String githubUpdate) {
		return this.socialMediaService.updateByGithub(socialMediaId, githubUpdate);
	}
	
	@PutMapping("/updateByLinkedin/{socialMediaId}")
	public Result updateByLinkedin(@RequestParam(value = "socialMediaId") int socialMediaId,@RequestParam(value = "linkedinUpdate") String linkedinUpdate) {
		return this.socialMediaService.updateByLinkedin(socialMediaId, linkedinUpdate);
	}
	
	@DeleteMapping("/deleteBySocialMediaId/{socialMediaId}")
	public Result deleteBySocialMediaId(@RequestParam(value = "socialMediaId") int socialMediaId) {
		return this.socialMediaService.deleteBySocialMediaId(socialMediaId);
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
