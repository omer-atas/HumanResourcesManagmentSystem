package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.InternPermissionType;

public interface InternPermissionTypeService {
	
	Result add(InternPermissionType internPermissionType);

	DataResult<List<InternPermissionType>> getAll(int pageNo, int pageSize);

}
