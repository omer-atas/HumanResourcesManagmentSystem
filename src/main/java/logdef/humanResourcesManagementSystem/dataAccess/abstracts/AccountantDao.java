package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.Accountant;

public interface AccountantDao extends JpaRepository<Accountant, Integer>{
	
	
	Accountant getByEmployeeId(int employeeId);
	
	@Transactional
	@Modifying
	@Query("DELETE From Accountant a where a.employeeId=:employeeId")
	void deleteByAccountantId(int employeeId);

}
