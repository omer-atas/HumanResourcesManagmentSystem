package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.Manager;

public interface ManagerDao extends JpaRepository<Manager, Integer>{

	Manager getByEmployeeId(int employeeId);
	
	@Transactional
	@Modifying
	@Query("DELETE From Manager m where m.employeeId=:employeeId")
	void deleteByAccountantId(int employeeId);
}
