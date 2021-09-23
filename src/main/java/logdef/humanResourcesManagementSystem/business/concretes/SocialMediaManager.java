package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import logdef.humanResourcesManagementSystem.business.abstracts.SocialMediaService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EmployeesDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.SocialMediaDao;
import logdef.humanResourcesManagementSystem.entities.concretes.GeneralInformation;
import logdef.humanResourcesManagementSystem.entities.concretes.SocialMedia;

@Service
public class SocialMediaManager implements SocialMediaService {
	
	private SocialMediaDao socialMediaDao;
	private EmployeesDao employeesDao;

	@Autowired
	public SocialMediaManager(SocialMediaDao socialMediaDao,EmployeesDao employeesDao) {
		super();
		this.socialMediaDao = socialMediaDao;
		this.employeesDao = employeesDao;
	}

	@Override
	public Result add(SocialMedia socialMedia) {
		
		if((checkGithub(socialMedia.getGithub()) == true) ||
				(checkLinkedin(socialMedia.getLinkedin()) == true)){
			return new ErrorResult("There is someone with this github or linkedin ..");
		}
		
		if(this.socialMediaDao.getByEmployeeId(socialMedia.getEmployeesSocialMedia().getEmployeeId()) != null) {
			return new ErrorResult("İlişkili olmayan çalışan kimliği giriniz...");
		}
		
		if (socialMedia.getEmployeesSocialMedia().getEmployeeId() == 0 || this.employeesDao.getByEmployeeId(socialMedia.getEmployeesSocialMedia().getEmployeeId()) == null ){
			return new ErrorResult("İlişkiyi giriniz....");
		}
		
		this.socialMediaDao.save(socialMedia);
		return new SuccessResult("Social media added..");
	}

	public boolean checkSocialMediaId(int socialMediaId) {
		
		if(this.socialMediaDao.getBySocialMediaId(socialMediaId) == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean checkGithub(String github) {
		
		if(this.socialMediaDao.getByGithub(github) == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean checkLinkedin(String linkedin) {
		
		if(this.socialMediaDao.getByLinkedin(linkedin) == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public DataResult<List<SocialMedia>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<SocialMedia>>(this.socialMediaDao.findAll(pageable).getContent(),"Başarılı");
	}

	@Override
	public Result updateByGithub(int socialMediaId, String githubUpdate) {
		
		if(checkSocialMediaId(socialMediaId) == false) {
			return new ErrorDataResult<GeneralInformation>("No such social media was found.");
		}
		
		
		this.socialMediaDao.updateByGithub(socialMediaId, githubUpdate);
		return new SuccessResult(socialMediaId + " updated.." + "( " + githubUpdate + " )");
	}

	@Override
	public Result updateByLinkedin(int socialMediaId, String linkedinUpdate) {
		
		if(checkSocialMediaId(socialMediaId) == false) {
			return new ErrorDataResult<GeneralInformation>("No such social media was found.");
		}
		
		
		this.socialMediaDao.updateByLinkedin(socialMediaId, linkedinUpdate);
		return new SuccessResult(socialMediaId + " updated.." + "( " + linkedinUpdate + " )");
	}

	@Override
	public Result deleteBySocialMediaId(int socialMediaId) {
		
		if(checkSocialMediaId(socialMediaId) == false) {
			return new ErrorDataResult<GeneralInformation>("No such social media was found.");
		}
		
		
		this.socialMediaDao.deleteBySocialMediaId(socialMediaId);
		return new SuccessResult(socialMediaId + " deleted..");
	}

	
	

}
