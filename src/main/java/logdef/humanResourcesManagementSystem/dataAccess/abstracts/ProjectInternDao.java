package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.ProjectIntern;

public interface ProjectInternDao extends JpaRepository<ProjectIntern, Integer>{
	
	ProjectIntern getByProjectInternId(int projectInternId);
	
	ProjectIntern getByProjectName(String projectName);
	
	ProjectIntern getBySourceCode (String sourceCode);

	@Transactional
	@Modifying
	@Query("UPDATE ProjectIntern p SET p.projectName=:projectNameUpdate WHERE p.projectInternId=:projectInternId")
	void updateByProjectName(int projectInternId,String projectNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE ProjectIntern p SET p.sourceCode=:sourceCodeUpdate WHERE p.projectInternId=:projectInternId")
	void updateBySourceCode(int projectInternId,String sourceCodeUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From ProjectIntern p where p.projectInternId=:projectInternId")
	void deleteByProjectInternId(int projectInternId);

}
