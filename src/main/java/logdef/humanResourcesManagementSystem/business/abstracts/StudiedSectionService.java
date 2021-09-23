package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.StudiedSection;

public interface StudiedSectionService {
	
	Result add(StudiedSection studiedSection); 
	
	DataResult<List<StudiedSection>> getAll(int pageNo, int pageSize);
	
	DataResult<StudiedSection>  getByStudiedSection(String studiedSection);
	
	DataResult<StudiedSection>  getByStudiedSectionId(int studiedSectionId);
	
	Result updateByStudiedSection(int studiedSectionId,String studiedSectionUpdate);
	
	Result deleteByStudiedSectionId(int studiedSectionId);

}
