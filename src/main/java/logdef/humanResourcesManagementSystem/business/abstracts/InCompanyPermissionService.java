package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.Date;
import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.InCompanyPermission;

public interface InCompanyPermissionService {
	
	Result add(InCompanyPermission inCompanyPermission);
	
	DataResult<List<InCompanyPermission>> getAll(int pageNo, int pageSize);
	
	DataResult<InCompanyPermission> getByPermissionTypeId(int permissionTypeId);
	
	DataResult<InCompanyPermission> getByInCompanyPermissionName(String inCompanyPermissionName);
	
	Result updateByInCompanyPermissionName(int permissionTypeId,String inCompanyPermissionNameUpdate);
	
	Result updateByIsVerified(int permissionTypeId,boolean isVerifiedUpdate);
	
	Result updateByStartingDate(int permissionTypeId,Date startingDateUpdate);
	
	Result updateByEndDate(int permissionTypeId,Date endDateUpdate);
	
	Result updateByPermissionTypeName(int permissionTypeId,String permissionTypeNameUpdate);
	
	Result deleteByInCompanyPermissionId(int permissionTypeId);
	

}
