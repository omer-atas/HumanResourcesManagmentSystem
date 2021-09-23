package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.PermissionTypeService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.PermissionTypeDao;
import logdef.humanResourcesManagementSystem.entities.concretes.PermissionType;

@Service
public class PermissionTypeManager implements PermissionTypeService{
	
	private PermissionTypeDao permissionTypeDao;
	
	@Autowired
	public PermissionTypeManager(PermissionTypeDao permissionTypeDao) {
		super();
		this.permissionTypeDao = permissionTypeDao;
	}

	@Override
	public Result add(PermissionType permissionType) {
		this.permissionTypeDao.save(permissionType);
		return new SuccessResult("Permission type of "+ permissionType.getPermissionTypeName() +" added..");
	}

	@Override
	public DataResult<List<PermissionType>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<PermissionType>>(this.permissionTypeDao.findAll(pageable).getContent(), "Permission type listed...");
	}
	
	public boolean checkPermissionTypeId(int permissionTypeId) {
		
		if(this.permissionTypeDao.getByPermissionTypeId(permissionTypeId) == null) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public DataResult<PermissionType> getByPermissionTypeId(int permissionTypeId) {
		return new SuccessDataResult<PermissionType>
		(this.permissionTypeDao.getByPermissionTypeId(permissionTypeId),"Permission type id "+
				permissionTypeId +" found..");
	}
	
	public DataResult<PermissionType> message (int permissionTypeId){
		
		if(checkPermissionTypeId(permissionTypeId) == false) {
			return new ErrorDataResult<PermissionType>("No such permission type was found.");
		}else {
			return new SuccessDataResult<PermissionType>("Success");
		}	
	}

	@Override
	public Result updateByPermissionTypeName(int permissionTypeId, String permissionTypeNameUpdate) {

		if(this.permissionTypeDao.getByPermissionTypeId(permissionTypeId) == null) {
			return new SuccessResult("No such permission type was found.");
		}
		
		this.permissionTypeDao.updateByPermissionTypeName(permissionTypeId, permissionTypeNameUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + permissionTypeNameUpdate + " )");
	}

	@Override
	public Result deleteByPermissionTypeId(int permissionTypeId) {
		
		if(this.permissionTypeDao.getByPermissionTypeId(permissionTypeId) == null) {
			return new SuccessResult("No such permission type was found.");
		}
		
		this.permissionTypeDao.deleteByPermissionTypeId(permissionTypeId);
		return new SuccessResult(permissionTypeId + " deleted.." + "( " + permissionTypeId + " )");
	}

}
