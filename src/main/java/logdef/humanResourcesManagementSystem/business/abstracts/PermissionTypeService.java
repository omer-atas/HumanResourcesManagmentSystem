package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.PermissionType;

public interface PermissionTypeService {
	
	Result add(PermissionType permissionType);
	
	DataResult<List<PermissionType>> getAll(int pageNo, int pageSize);
	
	DataResult<PermissionType> getByPermissionTypeId(int permissionTypeId);
	
	Result updateByPermissionTypeName(int permissionTypeId,String permissionTypeNameUpdate);
	
	Result deleteByPermissionTypeId(int permissionTypeId);

}
