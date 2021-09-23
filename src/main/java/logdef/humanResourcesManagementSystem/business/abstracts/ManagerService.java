package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.Manager;

public interface ManagerService {
	
	Result add(Manager manager);
	
	DataResult<List<Manager>> getAll(int pageNo, int pageSize);
	
	DataResult<Manager> getByEmployeeId(int employeeId);
	
	Result deleteByAccountantId(int employeeId);
}
