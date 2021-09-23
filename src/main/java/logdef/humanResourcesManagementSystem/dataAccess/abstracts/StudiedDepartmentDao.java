package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.StudiedDepartment;

public interface StudiedDepartmentDao extends JpaRepository<StudiedDepartment, Integer>{

	StudiedDepartment getByStudiedDepartmentId(int studiedDepartmentId);
	
	StudiedDepartment getByStudiedDepartment(String studiedDepartment);
	

	@Transactional
	@Modifying
	@Query("UPDATE StudiedDepartment s SET s.studiedDepartment=:studiedDepartmentUpdate WHERE s.studiedDepartmentId=:studiedDepartmentId")
	void updateByStudiedDepartment(int studiedDepartmentId,String studiedDepartmentUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From StudiedDepartment s where s.studiedDepartmentId=:studiedDepartmentId")
	void deleteByStudiedDepartmentId(int studiedDepartmentId);
}
