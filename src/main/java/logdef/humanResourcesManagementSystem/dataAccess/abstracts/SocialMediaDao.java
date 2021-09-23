package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import logdef.humanResourcesManagementSystem.entities.concretes.SocialMedia;

public interface SocialMediaDao extends JpaRepository<SocialMedia, Integer>{
	
	SocialMedia getBySocialMediaId(int socialMediaId);
	
	SocialMedia getByGithub(String github);
	
	SocialMedia getByLinkedin(String linkedin);
	
	@Query("FROM SocialMedia WHERE employeesSocialMedia.employeeId=:employeeId")
	SocialMedia getByEmployeeId(int employeeId);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE SocialMedia s SET s.github=:githubUpdate WHERE s.socialMediaId=:socialMediaId")
	void updateByGithub(int socialMediaId,String githubUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE SocialMedia s SET s.linkedin=:linkedinUpdate WHERE s.socialMediaId=:socialMediaId")
	void updateByLinkedin(int socialMediaId,String linkedinUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From SocialMedia s where s.socialMediaId=:socialMediaId")
	void deleteBySocialMediaId(int socialMediaId);

}
