package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import logdef.humanResourcesManagementSystem.entities.concretes.InternPermissionType;

public interface InternPermissionTypeDao extends JpaRepository<InternPermissionType, Integer>{
	
	InternPermissionType getByInternPermissionTypeId(int internPermissionTypeId);

}
