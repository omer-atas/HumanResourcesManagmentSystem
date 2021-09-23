package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.InCompanyPermissionService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.InCompanyPermissionDao;
import logdef.humanResourcesManagementSystem.entities.concretes.InCompanyPermission;
import logdef.humanResourcesManagementSystem.entities.concretes.Intern;

@Service
public class InCompanyPermissionManager implements InCompanyPermissionService{
	
	private InCompanyPermissionDao inCompanyPermissionDao;

	@Autowired
	public InCompanyPermissionManager(InCompanyPermissionDao inCompanyPermissionDao) {
		super();
		this.inCompanyPermissionDao = inCompanyPermissionDao;
	}

	@Override
	public Result add(InCompanyPermission inCompanyPermission) {
		
		inCompanyPermission.setVerified(false); // varsayılan olarak izin verilmedi olarak verildi.
		inCompanyPermission.setPermissionTypeName("Şirket İçi İzin");
		
		this.inCompanyPermissionDao.save(inCompanyPermission);
		return new SuccessResult("Company permission of "+ inCompanyPermission.getInCompanyPermissionName() +" added..");
	}

	@Override
	public DataResult<List<InCompanyPermission>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<InCompanyPermission>>(this.inCompanyPermissionDao.findAll(pageable).getContent(), "Company permission listed...");
	}
	
	public boolean checkInCompanyPermissionId(int permissionTypeId) {
		
		if(this.inCompanyPermissionDao.getByPermissionTypeId(permissionTypeId) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public DataResult<InCompanyPermission> getByPermissionTypeId(int permissionTypeId) {
		
		if(checkInCompanyPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<InCompanyPermission>(this.inCompanyPermissionDao.
					getByPermissionTypeId(permissionTypeId),"Company permission with this "+ permissionTypeId
					+" not found");
		}
		
		return new SuccessDataResult<InCompanyPermission>(this.inCompanyPermissionDao.
				getByPermissionTypeId(permissionTypeId), permissionTypeId +" found..");
	}
	
	public boolean checkInCompanyPermissionName(String inCompanyPermissionName) {
		
		if(this.inCompanyPermissionDao.getByInCompanyPermissionName(inCompanyPermissionName) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public DataResult<InCompanyPermission> getByInCompanyPermissionName(String inCompanyPermissionName) {
		
		if(checkInCompanyPermissionName(inCompanyPermissionName) == false) {
			return new ErrorDataResult<InCompanyPermission>(this.inCompanyPermissionDao.
					getByInCompanyPermissionName(inCompanyPermissionName),"Company permission with this "+ inCompanyPermissionName
					+" not found");
		}
		
		return new SuccessDataResult<InCompanyPermission>(this.inCompanyPermissionDao.
				getByInCompanyPermissionName(inCompanyPermissionName), inCompanyPermissionName +" found..");
	}

	@Override
	public Result updateByInCompanyPermissionName(int permissionTypeId, String inCompanyPermissionNameUpdate) {
		
		if(checkInCompanyPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such company permission was found.");
		}
		
		this.inCompanyPermissionDao.updateByInCompanyPermissionName(permissionTypeId, inCompanyPermissionNameUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + inCompanyPermissionNameUpdate + " )");
	}

	@Override
	public Result updateByStartingDate(int permissionTypeId, Date startingDateUpdate) {
		
		if(checkInCompanyPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such company permission was found.");
		}
		
		this.inCompanyPermissionDao.updateByStartingDate(permissionTypeId, startingDateUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + startingDateUpdate + " )");
	}

	@Override
	public Result updateByEndDate(int permissionTypeId, Date endDateUpdate) {
		
		if(checkInCompanyPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such company permission was found.");
		}
		
		this.inCompanyPermissionDao.updateByEndDate(permissionTypeId, endDateUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + endDateUpdate + " )");
	}

	@Override
	public Result deleteByInCompanyPermissionId(int permissionTypeId) {
		
		if(checkInCompanyPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such company permission was found.");
		}
		
		this.inCompanyPermissionDao.deleteByInCompanyPermissionId(permissionTypeId);
		return new SuccessResult(permissionTypeId + " deleted.." + "( " );
	}

	@Override
	public Result updateByPermissionTypeName(int permissionTypeId, String permissionTypeNameUpdate) {
		
		if(checkInCompanyPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such company permission was found.");
		}
		
		this.inCompanyPermissionDao.updateByPermissionTypeName(permissionTypeId, permissionTypeNameUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + permissionTypeNameUpdate + " )");
	
	}

	@Override
	public Result updateByIsVerified(int permissionTypeId, boolean isVerifiedUpdate) {
		if(checkInCompanyPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such company permission was found.");
		}
		
		this.inCompanyPermissionDao.updateByIsVerified(permissionTypeId, isVerifiedUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + isVerifiedUpdate + " )");
	}
	
	

}
