package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import logdef.humanResourcesManagementSystem.entities.concretes.EmployessAuthorities;

public interface EmployessAuthoritiesDao extends JpaRepository<EmployessAuthorities, Integer>{
	
	EmployessAuthorities getByEmployeeIdAndAuthorityId(int employeeId,int authorityId);
	
	EmployessAuthorities getByEmployeeIdAndInCompanyPermissionControl(int employeeId ,boolean inCompanyPermissionControl);
	
}
