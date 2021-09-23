package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.SocialMedia;

public interface SocialMediaService {
	
	Result add(SocialMedia socialMedia); 
	
	DataResult<List<SocialMedia>> getAll(int pageNo, int pageSize);
	
	Result updateByGithub(int socialMediaId,String githubUpdate);
	
	Result updateByLinkedin(int socialMediaId,String linkedinUpdate);
	
	Result deleteBySocialMediaId(int socialMediaId);
	
}
