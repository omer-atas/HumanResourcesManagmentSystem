package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.EmployeePermissionType;

public interface EmployeePermissionTypeService {
	
	Result add(EmployeePermissionType employeePermissionType);
	
	DataResult<List<EmployeePermissionType>> getAll(int pageNo, int pageSize);

}
