package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.StudiedSectionService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.StudiedSectionDao;
import logdef.humanResourcesManagementSystem.entities.concretes.StudiedSection;

@Service
public class StudiedSectionManager implements StudiedSectionService{

	private StudiedSectionDao studiedSectionDao;

	@Autowired
	public StudiedSectionManager(StudiedSectionDao studiedSectionDao) {
		super();
		this.studiedSectionDao = studiedSectionDao;
	}

	@Override
	public Result add(StudiedSection studiedSection) {
		if(this.studiedSectionDao.getByStudiedSection(studiedSection.getStudiedSection()) != null) {
			return new ErrorResult("Böyle bir şube mevcuttur..");
		}
		this.studiedSectionDao.save(studiedSection);
		return new SuccessResult(studiedSection.getStudiedSection() + " added.");
	}

	@Override
	public DataResult<List<StudiedSection>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<StudiedSection>>(this.studiedSectionDao.findAll(pageable).getContent(), " Çalışılan şubebler listelendi..");
	}

	@Override
	public DataResult<StudiedSection> getByStudiedSection(String studiedSection) {
		return new SuccessDataResult<StudiedSection>(this.studiedSectionDao.getByStudiedSection(studiedSection), studiedSection + " bulundu..");
	}

	@Override
	public DataResult<StudiedSection> getByStudiedSectionId(int studiedSectionId) {
		
		return new SuccessDataResult<StudiedSection>(this.studiedSectionDao.getByStudiedSectionId(studiedSectionId), studiedSectionId + " bulundu..");
	}

	@Override
	public Result updateByStudiedSection(int studiedSectionId, String studiedSectionUpdate) {
		
		if(this.studiedSectionDao.getByStudiedSectionId(studiedSectionId) == null) {
			return new ErrorResult("Böyle bir çalışma şubesi yok..");
		}
		
		this.studiedSectionDao.updateByStudiedSection(studiedSectionId, studiedSectionUpdate);
		return new SuccessResult(studiedSectionId + " updated .. ( " + studiedSectionUpdate + " )");
	}

	@Override
	public Result deleteByStudiedSectionId(int studiedSectionId) {
		
		if(this.studiedSectionDao.getByStudiedSectionId(studiedSectionId) == null) {
			return new ErrorResult("Böyle bir çalışma şubesi yok..");
		}
		
		this.studiedSectionDao.deleteByStudiedSectionId(studiedSectionId);
		return new SuccessResult(studiedSectionId + " deleted ..");
	}
	
	
}
