package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.EmployeePermissionTypeService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EmployeePermissionTypeDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EmployeesDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.InCompanyPermissionDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.ManagerDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.PermissionTypeDao;
import logdef.humanResourcesManagementSystem.entities.concretes.EmployeePermissionType;
@Service
public class EmployeePermissionTypeManager implements EmployeePermissionTypeService{
	
	private EmployeePermissionTypeDao employeePermissionTypeDao;
	private ManagerDao managerDao;
	private InCompanyPermissionDao InCompanyPermissionDao;
	private EmployeesDao employeesDao;
	private PermissionTypeDao permissionTypeDao;

	@Autowired
	public EmployeePermissionTypeManager(EmployeePermissionTypeDao employeePermissionTypeDao, ManagerDao managerDao,
			logdef.humanResourcesManagementSystem.dataAccess.abstracts.InCompanyPermissionDao inCompanyPermissionDao,
			EmployeesDao employeesDao, PermissionTypeDao permissionTypeDao) {
		super();
		this.employeePermissionTypeDao = employeePermissionTypeDao;
		this.managerDao = managerDao;
		InCompanyPermissionDao = inCompanyPermissionDao;
		this.employeesDao = employeesDao;
		this.permissionTypeDao = permissionTypeDao;
	}
	

	@Override
	public Result add(EmployeePermissionType employeePermissionType) {
		
		if(employeePermissionType.getEmployees().getEmployeeId() == 0 ||
				employeePermissionType.getEmployeesManager().getEmployeeId() == 0 ||
				employeePermissionType.getPermissionType().getPermissionTypeId() == 0) {
			return new ErrorResult("Null bırakılamaz");
		}
		
		if((this.employeesDao.getByEmployeeId(employeePermissionType.getEmployees().getEmployeeId()) == null)
				|| (this.managerDao.getByEmployeeId(employeePermissionType.getEmployeesManager().getEmployeeId()) == null)
				|| (this.permissionTypeDao.getByPermissionTypeId(employeePermissionType.getPermissionType().getPermissionTypeId()) == null)) {
			return new ErrorResult("Boş değer girmeyiniz..");
		}
		
		if(this.managerDao.getByEmployeeId(employeePermissionType.getEmployeesManager().getEmployeeId()) == null) {
			return new ErrorResult("Yönetici giriniz....");
		}
		
		if(this.InCompanyPermissionDao.
				getByPermissionTypeId(employeePermissionType.getPermissionType().getPermissionTypeId()).
					isVerified() != true) {
			
			return new ErrorResult("Şirket içi izin onay verilmesi gereklidir...");
		}
		
		this.employeePermissionTypeDao.save(employeePermissionType);
		return new SuccessResult("Employee permission type "+ employeePermissionType.getEmployeePermissionTypeId () +" added..");
	}

	@Override
	public DataResult<List<EmployeePermissionType>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<EmployeePermissionType>>(this.employeePermissionTypeDao.findAll(pageable).getContent(), "Employee permission type listed...");
	}

	
	
	
	

}
