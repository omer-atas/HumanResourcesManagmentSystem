package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.Date;
import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.OfficialPermission;

public interface OfficialPermissionService {
	
	Result add(OfficialPermission officialPermission);
	
	DataResult<List<OfficialPermission>> getAll(int pageNo, int pageSize);
	
	DataResult<OfficialPermission> getByPermissionTypeId(int permissionTypeId);
	
	DataResult<OfficialPermission> getByOfficialPermissionName(String officialPermissionName);
	
	Result updateByPermissionTypeName(int permissionTypeId,String permissionTypeNameUpdate);
	
	Result updateByOfficialPermissionName(int permissionTypeId,String officialPermissionNameUpdate);

	Result updateByStartingDate(int permissionTypeId,Date startingDateUpdate);
	
	Result updateByEndDate(int permissionTypeId,Date endDateUpdate);
	
	Result deleteByOfficialPermissionId(int permissionTypeId);
	
}
