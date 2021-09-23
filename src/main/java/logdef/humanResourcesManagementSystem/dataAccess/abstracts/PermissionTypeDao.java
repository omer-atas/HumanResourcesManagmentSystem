package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.PermissionType;

public interface PermissionTypeDao extends JpaRepository<PermissionType, Integer> {
	
	PermissionType getByPermissionTypeId(int permissionTypeId);
	
	@Transactional
	@Modifying
	@Query("UPDATE PermissionType p SET p.permissionTypeName=:permissionTypeNameUpdate WHERE p.permissionTypeId=:permissionTypeId")
	void updateByPermissionTypeName(int permissionTypeId,String permissionTypeNameUpdate);

	@Transactional
	@Modifying
	@Query("DELETE From PermissionType p where p.permissionTypeId=:permissionTypeId")
	void deleteByPermissionTypeId(int permissionTypeId);
	
}
