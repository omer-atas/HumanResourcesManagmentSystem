package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import logdef.humanResourcesManagementSystem.entities.concretes.StudiedSection;

public interface StudiedSectionDao extends JpaRepository<StudiedSection, Integer> {
	
	StudiedSection getByStudiedSectionId(int studiedSectionId);
	
	StudiedSection getByStudiedSection(String studiedSection);
	

	@Transactional
	@Modifying
	@Query("UPDATE StudiedSection s SET s.studiedSection=:studiedSectionUpdate WHERE s.studiedSectionId=:studiedSectionId")
	void updateByStudiedSection(int studiedSectionId,String studiedSectionUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From StudiedSection s where s.studiedSectionId=:studiedSectionId")
	void deleteByStudiedSectionId(int studiedSectionId);

}
