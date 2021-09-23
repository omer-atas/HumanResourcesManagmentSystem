package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.StudiedDepartment;

public interface StudiedDepartmentService {
	
	Result add(StudiedDepartment studiedDepartment); 
	
	DataResult<List<StudiedDepartment>> getAll(int pageNo, int pageSize);
	
	DataResult<StudiedDepartment>  getByStudiedDepartmentId(int studiedDepartmentId);
	
	DataResult<StudiedDepartment>  getByStudiedDepartment(String studiedDepartment);
	
	Result updateByStudiedDepartment(int studiedDepartmentId,String studiedDepartmentUpdate);
	
	Result deleteByStudiedDepartmentId(int studiedDepartmentId);

}
