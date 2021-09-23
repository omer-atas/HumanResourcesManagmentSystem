package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.ProjectIntern;

public interface ProjectInternService {
	
	Result add(ProjectIntern projectIntern); 
	
	DataResult<List<ProjectIntern>> getAll(int pageNo, int pageSize);
	
	DataResult<ProjectIntern> getByProjectInternId(int projectInternId);
	
	DataResult<ProjectIntern> getByProjectName(String projectName);
	
	DataResult<ProjectIntern> getBySourceCode (String sourceCode);
	
	Result updateByProjectName(int projectInternId,String projectNameUpdate);
	
	Result updateBySourceCode(int projectInternId,String sourceCodeUpdate);
	
	Result  deleteByProjectInternId(int projectInternId);

}
