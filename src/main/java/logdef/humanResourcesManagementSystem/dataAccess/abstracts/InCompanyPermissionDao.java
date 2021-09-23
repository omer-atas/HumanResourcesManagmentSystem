package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.InCompanyPermission;

public interface InCompanyPermissionDao extends JpaRepository<InCompanyPermission, Integer> {


	InCompanyPermission getByPermissionTypeId(int permissionTypeId);
	
	InCompanyPermission getByInCompanyPermissionName(String inCompanyPermissionName);
	
	@Transactional
	@Modifying
	@Query("UPDATE InCompanyPermission i SET i.permissionTypeName=:permissionTypeNameUpdate WHERE i.permissionTypeId=:permissionTypeId")
	void updateByPermissionTypeName(int permissionTypeId,String permissionTypeNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE InCompanyPermission i SET i.inCompanyPermissionName=:inCompanyPermissionNameUpdate WHERE i.permissionTypeId=:permissionTypeId")
	void updateByInCompanyPermissionName(int permissionTypeId,String inCompanyPermissionNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE InCompanyPermission i SET i.isVerified=:isVerifiedUpdate WHERE i.permissionTypeId=:permissionTypeId")
	void updateByIsVerified(int permissionTypeId,boolean isVerifiedUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE InCompanyPermission i SET i.startingDate=:startingDateUpdate WHERE i.permissionTypeId=:permissionTypeId")
	void updateByStartingDate(int permissionTypeId,Date startingDateUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE InCompanyPermission i SET i.endDate=:endDateUpdate WHERE i.permissionTypeId=:permissionTypeId")
	void updateByEndDate(int permissionTypeId,Date endDateUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From InCompanyPermission i where i.permissionTypeId=:permissionTypeId")
	void deleteByInCompanyPermissionId(int permissionTypeId);

}
