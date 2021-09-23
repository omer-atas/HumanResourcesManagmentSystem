package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import logdef.humanResourcesManagementSystem.entities.concretes.SuperAdmin;

public interface SuperAdminDao extends JpaRepository<SuperAdmin, Integer>{
	
	SuperAdmin getByEmail(String email);

}
