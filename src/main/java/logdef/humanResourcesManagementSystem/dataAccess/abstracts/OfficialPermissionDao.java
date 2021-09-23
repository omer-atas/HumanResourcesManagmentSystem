package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.OfficialPermission;

public interface OfficialPermissionDao extends JpaRepository<OfficialPermission, Integer> {
	
	OfficialPermission getByPermissionTypeId(int permissionTypeId);
	
	OfficialPermission getByOfficialPermissionName(String officialPermissionName);
	
	@Transactional
	@Modifying
	@Query("UPDATE OfficialPermission o SET o.permissionTypeName=:permissionTypeNameUpdate WHERE o.permissionTypeId=:permissionTypeId")
	void updateByPermissionTypeName(int permissionTypeId,String permissionTypeNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE OfficialPermission o SET o.officialPermissionName=:officialPermissionNameUpdate WHERE o.permissionTypeId=:permissionTypeId")
	void updateByOfficialPermissionName(int permissionTypeId,String officialPermissionNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE OfficialPermission o SET o.startingDate=:startingDateUpdate WHERE o.permissionTypeId=:permissionTypeId")
	void updateByStartingDate(int permissionTypeId,Date startingDateUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE OfficialPermission o SET o.endDate=:endDateUpdate WHERE o.permissionTypeId=:permissionTypeId")
	void updateByEndDate(int permissionTypeId,Date endDateUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From OfficialPermission o where o.permissionTypeId=:permissionTypeId")
	void deleteByOfficialPermissionId(int permissionTypeId);

}
