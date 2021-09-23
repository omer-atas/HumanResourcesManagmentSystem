package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.InternPermissionTypeService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.InternDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.InternPermissionTypeDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.ManagerDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.PermissionTypeDao;
import logdef.humanResourcesManagementSystem.entities.concretes.InternPermissionType;

@Service
public class InternPermissionTypeManager implements InternPermissionTypeService{
	
	private InternPermissionTypeDao internPermissionTypeDao;
	private ManagerDao managerDao;
	private InternDao internDao;
	private PermissionTypeDao permissionTypeDao;

	@Autowired
	public InternPermissionTypeManager(InternPermissionTypeDao internPermissionTypeDao, ManagerDao managerDao,
			InternDao internDao, PermissionTypeDao permissionTypeDao) {
		super();
		this.internPermissionTypeDao = internPermissionTypeDao;
		this.managerDao = managerDao;
		this.internDao = internDao;
		this.permissionTypeDao = permissionTypeDao;
	}

	@Override
	public Result add(InternPermissionType internPermissionType) {
		
		if(internPermissionType.getInternPermissionTypeEmployeesManager().getEmployeeId() == 0 ||
				internPermissionType.getInternPermissionTypePermissionType().getPermissionTypeId() == 0 ||
						internPermissionType.getIntern().getInternId() == 0) {
			return new ErrorResult("Null bırakılamaz..");
		}
		
		if((this.internDao.getByInternId(internPermissionType.getIntern().getInternId()) == null)
				|| (this.managerDao.getByEmployeeId(internPermissionType.getInternPermissionTypeEmployeesManager().getEmployeeId()) == null)
				|| (this.permissionTypeDao.getByPermissionTypeId(internPermissionType.getInternPermissionTypePermissionType().getPermissionTypeId()) == null)) {
			return new ErrorResult("Boş değer girmeyiniz..");
		}
		
		if(this.managerDao.getByEmployeeId(internPermissionType.getInternPermissionTypeEmployeesManager().getEmployeeId()) == null) {
			return new ErrorResult("Yönetici giriniz....");
		}
		
		this.internPermissionTypeDao.save(internPermissionType);
		return new SuccessResult("Intern event calendar "+ internPermissionType.getInternPermissionTypeId() +" added..");
	}

	@Override
	public DataResult<List<InternPermissionType>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<InternPermissionType>>(this.internPermissionTypeDao.findAll(pageable).getContent(), "Intern permission types listed...");
	}
	
	

}
