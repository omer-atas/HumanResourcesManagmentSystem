package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.Date;
import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.PersonalPermission;

public interface PersonalPermissionService {
	
	Result add(PersonalPermission personalPermission);
	
	DataResult<List<PersonalPermission>> getAll(int pageNo, int pageSize);
	
	DataResult<PersonalPermission> getByPermissionTypeId(int permissionTypeId);

	DataResult<PersonalPermission> getByPersonalPermissionName(String personalPermissionName);
	
	Result updateByPersonalPermissionName(int permissionTypeId,String personalPermissionNameUpdate);
	
	Result updateByStartingDate(int permissionTypeId,Date startingDateUpdate);
	
	Result updateByEndDate(int permissionTypeId,Date endDateUpdate);
	
	Result deleteByPersonalPermissionId(int permissionTypeId);
	
	Result updateByPermissionTypeName(int permissionTypeId,String permissionTypeNameUpdate);
}
