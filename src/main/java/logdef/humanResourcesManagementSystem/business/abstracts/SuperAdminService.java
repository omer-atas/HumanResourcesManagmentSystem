package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.SuperAdmin;

public interface SuperAdminService {
	
	Result add(SuperAdmin superAdmin);
	
	DataResult<List<SuperAdmin>> getAll(int pageNo, int pageSize);

}
