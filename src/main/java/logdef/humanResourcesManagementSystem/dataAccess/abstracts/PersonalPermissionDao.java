package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.PersonalPermission;

public interface PersonalPermissionDao extends JpaRepository<PersonalPermission, Integer>{
	
	PersonalPermission getByPermissionTypeId(int permissionTypeId);
	
	PersonalPermission getByPersonalPermissionName(String personalPermissionName);
	
	@Transactional
	@Modifying
	@Query("UPDATE PersonalPermission p SET p.permissionTypeName=:permissionTypeNameUpdate WHERE p.permissionTypeId=:permissionTypeId")
	void updateByPermissionTypeName(int permissionTypeId,String permissionTypeNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE PersonalPermission p SET p.personalPermissionName=:personalPermissionNameUpdate WHERE p.permissionTypeId=:permissionTypeId")
	void updateByPersonalPermissionName(int permissionTypeId,String personalPermissionNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE PersonalPermission p SET p.startingDate=:startingDateUpdate WHERE p.permissionTypeId=:permissionTypeId")
	void updateByStartingDate(int permissionTypeId,Date startingDateUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE PersonalPermission p SET p.endDate=:endDateUpdate WHERE p.permissionTypeId=:permissionTypeId")
	void updateByEndDate(int permissionTypeId,Date endDateUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From PersonalPermission p where p.permissionTypeId=:permissionTypeId")
	void deleteByPersonalPermissionId(int permissionTypeId);

}
