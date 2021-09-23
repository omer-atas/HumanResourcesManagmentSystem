package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.ProjectInternService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.InternDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.ProjectInternDao;
import logdef.humanResourcesManagementSystem.entities.concretes.ProjectIntern;

@Service
public class ProjectInternManager implements ProjectInternService{
	
	private ProjectInternDao projectInternDao;
	private InternDao internDao;

	@Autowired
	public ProjectInternManager(ProjectInternDao projectInternDao,InternDao internDao) {
		super();
		this.projectInternDao = projectInternDao;
		this.internDao = internDao;
	}

	@Override
	public Result add(ProjectIntern projectIntern) {
		
		if(this.internDao.getByInternId(projectIntern.getProjectIntern().getInternId()) == null 
				|| projectIntern.getProjectIntern().getInternId() == 0) {
			return new ErrorResult("Projenin hangi stajyere ait olduğunu giriniz..");
		}
		
		this.projectInternDao.save(projectIntern);
		return new SuccessResult(projectIntern.getProjectName() + " added..");
	}

	@Override
	public DataResult<List<ProjectIntern>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<ProjectIntern>>(this.projectInternDao.findAll(pageable).getContent(), " Projeler listelendi..");
	}

	@Override
	public DataResult<ProjectIntern> getByProjectInternId(int projectInternId) {
		return new SuccessDataResult<ProjectIntern>(this.projectInternDao.getByProjectInternId(projectInternId), projectInternId + " bulundu..");
	}

	@Override
	public DataResult<ProjectIntern> getByProjectName(String projectName) {
		return new SuccessDataResult<ProjectIntern>(this.projectInternDao.getByProjectName(projectName), projectName + " bulundu..");
	}

	@Override
	public DataResult<ProjectIntern> getBySourceCode(String sourceCode) {
		return new SuccessDataResult<ProjectIntern>(this.projectInternDao.getBySourceCode(sourceCode), sourceCode + " bulundu..");
	}

	@Override
	public Result updateByProjectName(int projectInternId, String projectNameUpdate) {
		
		if(this.projectInternDao.getByProjectInternId(projectInternId) == null) {
			
			return new ErrorResult("Böyle bir proje bulunmamaktadır.");
		}

		this.projectInternDao.updateByProjectName(projectInternId, projectNameUpdate);
		return new SuccessResult(projectInternId + " updated..( " + projectNameUpdate + " )");
	}

	@Override
	public Result updateBySourceCode(int projectInternId, String sourceCodeUpdate) {
		
		if(this.projectInternDao.getByProjectInternId(projectInternId) == null) {
			
			return new ErrorResult("Böyle bir proje bulunmamaktadır.");
		}

		this.projectInternDao.updateBySourceCode(projectInternId, sourceCodeUpdate);
		return new SuccessResult(projectInternId + " updated..( " + sourceCodeUpdate + " )");
	}

	@Override
	public Result deleteByProjectInternId(int projectInternId) {
		
		if(this.projectInternDao.getByProjectInternId(projectInternId) == null) {
			
			return new ErrorResult("Böyle bir proje bulunmamaktadır.");
		}
		
		this.projectInternDao.deleteByProjectInternId(projectInternId);
		return new SuccessResult(projectInternId + " deleted..");
	}
	
	

}
