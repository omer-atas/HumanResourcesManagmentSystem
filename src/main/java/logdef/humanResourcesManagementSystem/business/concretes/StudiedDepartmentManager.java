package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.StudiedDepartmentService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.StudiedDepartmentDao;
import logdef.humanResourcesManagementSystem.entities.concretes.StudiedDepartment;

@Service
public class StudiedDepartmentManager implements StudiedDepartmentService{

	private StudiedDepartmentDao studiedDepartmentDao;

	@Autowired
	public StudiedDepartmentManager(StudiedDepartmentDao studiedDepartmentDao) {
		super();
		this.studiedDepartmentDao = studiedDepartmentDao;
	}

	@Override
	public Result add(StudiedDepartment studiedDepartment) {
	
		if(this.studiedDepartmentDao.getByStudiedDepartment(studiedDepartment.getStudiedDepartment()) != null) {
			return new ErrorResult("Bu departman mevcuttur..");
		}
		
		this.studiedDepartmentDao.save(studiedDepartment);
		return new SuccessResult(studiedDepartment.getStudiedDepartment() + " added..");
	}

	@Override
	public DataResult<List<StudiedDepartment>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<StudiedDepartment>>(this.studiedDepartmentDao.findAll(pageable).getContent(), "Departmanlar listelendi..");
	}

	@Override
	public DataResult<StudiedDepartment> getByStudiedDepartmentId(int studiedDepartmentId) {
		return new SuccessDataResult<StudiedDepartment>(this.studiedDepartmentDao.getByStudiedDepartmentId(studiedDepartmentId), studiedDepartmentId + " bulundu..");
	}

	@Override
	public DataResult<StudiedDepartment> getByStudiedDepartment(String studiedDepartment) {
		return new SuccessDataResult<StudiedDepartment>(this.studiedDepartmentDao.getByStudiedDepartment(studiedDepartment), studiedDepartment + " bulundu..");
	}

	@Override
	public Result updateByStudiedDepartment(int studiedDepartmentId, String studiedDepartmentUpdate) {
		
		if(this.studiedDepartmentDao.getByStudiedDepartmentId(studiedDepartmentId) == null) {
			return new ErrorResult("Böyle bir departman bulunmamaktadır..");
		}
		
		this.studiedDepartmentDao.updateByStudiedDepartment(studiedDepartmentId, studiedDepartmentUpdate);
		return new SuccessResult(studiedDepartmentId + " updated .. ( " + studiedDepartmentUpdate + " )");
	}

	@Override
	public Result deleteByStudiedDepartmentId(int studiedDepartmentId) {
		
		if(this.studiedDepartmentDao.getByStudiedDepartmentId(studiedDepartmentId) == null) {
			return new ErrorResult("Böyle bir departman bulunmamaktadır..");
		}
		
		this.studiedDepartmentDao.deleteByStudiedDepartmentId(studiedDepartmentId);
		return new SuccessResult(studiedDepartmentId + " deleted..");
	}
	
	
	
}
