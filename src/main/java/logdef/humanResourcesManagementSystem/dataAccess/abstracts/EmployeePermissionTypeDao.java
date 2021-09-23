package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import logdef.humanResourcesManagementSystem.entities.concretes.EmployeePermissionType;

public interface EmployeePermissionTypeDao extends JpaRepository<EmployeePermissionType, Integer>{
	
	
	EmployeePermissionType getByEmployeePermissionTypeId(int employeePermissionTypeId);
	


}
