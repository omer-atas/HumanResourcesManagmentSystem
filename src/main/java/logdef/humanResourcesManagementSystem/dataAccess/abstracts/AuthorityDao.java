package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {
	
	Authority getByAuthorityId(int authorityId);
	
	Authority getByAuthorityIdAndInCompanyPermissionControl(int authorityId,boolean inCompanyPermissionControl);
	
	@Transactional
	@Modifying
	@Query("UPDATE Authority a SET a.inCompanyPermissionControl=:inCompanyPermissionControl WHERE a.authorityId=:authorityId")
	void updateByInCompanyPermissionControl(int authorityId,boolean inCompanyPermissionControl);

}
