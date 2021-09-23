package logdef.humanResourcesManagementSystem.business.concretes;


import java.util.Date;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.GeneralInformationService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EmployeesDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.GeneralInformationDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.StudiedDepartmentDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.StudiedSectionDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.WorkingHourDao;
import logdef.humanResourcesManagementSystem.entities.concretes.GeneralInformation;
import logdef.humanResourcesManagementSystem.entities.concretes.Intern;

@Service
public class GeneralInformationManager implements GeneralInformationService{
	
	private GeneralInformationDao generalInformationDao;
	private EmployeesDao employeesDao;
	private StudiedDepartmentDao studiedDepartmentDao;
	private StudiedSectionDao studiedSectionDao;
	private WorkingHourDao workingHourDao;
	
	@Autowired
	public GeneralInformationManager(GeneralInformationDao generalInformationDao, EmployeesDao employeesDao,
			StudiedDepartmentDao studiedDepartmentDao, StudiedSectionDao studiedSectionDao,WorkingHourDao workingHourDao) {
		super();
		this.generalInformationDao = generalInformationDao;
		this.employeesDao = employeesDao;
		this.studiedDepartmentDao = studiedDepartmentDao;
		this.studiedSectionDao = studiedSectionDao;
		this.workingHourDao = workingHourDao;
	}

	@Override
	public DataResult<List<GeneralInformation>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<GeneralInformation>>(this.generalInformationDao.findAll(pageable).getContent(), "General informations listed...");
		
	}

	@Override
	public Result add(GeneralInformation generalInformation) {	
		
		if (generalInformation.getEmployeesGeneralInformation().getEmployeeId() == 0 ||
				this.employeesDao.getByEmployeeId(generalInformation.getEmployeesGeneralInformation().getEmployeeId()) == null ||
				generalInformation.getStudiedDepartment().getStudiedDepartmentId() == 0 ||
				this.studiedDepartmentDao.getByStudiedDepartmentId(generalInformation.getStudiedDepartment().getStudiedDepartmentId())== null ||
				generalInformation.getStudiedSection().getStudiedSectionId() == 0 ||
				this.studiedSectionDao.getByStudiedSectionId(generalInformation.getStudiedSection().getStudiedSectionId()) == null ||
				generalInformation.getWorkingHour().getWorkingHourId() == 0 ||
				this.workingHourDao.getByWorkingHourId(generalInformation.getWorkingHour().getWorkingHourId()) == null	){
			return new ErrorResult("İlişkiyi giriniz( İlişki girilmemiştir ya da girilen kimliğe karşılık gelen veri bulunmamaktadır.)....");
		}
		
		
		if(this.generalInformationDao.getByEmployeeId(generalInformation.getEmployeesGeneralInformation().getEmployeeId()) != null) {
			return new ErrorResult("Bu çalışanın genel bilgileri zaten mevcuttur..");
		}
		
		Pattern pattern = Pattern.compile("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
	    Matcher matcher = pattern.matcher(generalInformation.getTelephoneNumber());
	    
	    if(matcher.matches() == false) {
	    	return new ErrorDataResult<Intern>("Telefon numarası formatında giriniz..");
	    }
		
		this.generalInformationDao.save(generalInformation);
		return new SuccessResult("General information of "+ generalInformation.getEmail() +" added..");
	}

	@Override
	public DataResult<GeneralInformation> getByGeneralInformationId(int generalInformationId) {
		
		if(checkGeneralInformationId(generalInformationId) == false) {
			return new ErrorDataResult<GeneralInformation>("No such general information was found.");
		}
		
		return new SuccessDataResult<GeneralInformation>(this.generalInformationDao
				.getByGeneralInformationId(generalInformationId), generalInformationId +" found..");
	}
	
	public boolean checkGeneralInformationId(int generalInformationId) {
		
		if(this.generalInformationDao.getByGeneralInformationId(generalInformationId) == null) {
			return false;
		}
		
		return true;
	}
	

	@Override
	public Result deleteByGeneralInformationId(int generalInformationId) {
		
		if(checkGeneralInformationId(generalInformationId) == false) {
			return new ErrorDataResult<GeneralInformation>("No such general information was found.");
		}
		
		this.generalInformationDao.deleteByGeneralInformationId(generalInformationId);
		return new SuccessResult(generalInformationId + " deleted.." );
		
	}

	@Override
	public Result updateBySuperscription(int generalInformationId, String superscriptionUpdate) {
		
		if(checkGeneralInformationId(generalInformationId) == false) {
			return new ErrorDataResult<GeneralInformation>("No such general information was found.");
		}
		
		this.generalInformationDao.updateBySuperscription(generalInformationId, superscriptionUpdate);
		return new SuccessResult(generalInformationId + " updated.." + "( " + superscriptionUpdate + " )");
	}

	@Override
	public Result updateByTelephoneNumber(int generalInformationId, String telephoneNumberUpdate) {
		
		if(checkGeneralInformationId(generalInformationId) == false) {
			return new ErrorDataResult<GeneralInformation>("No such general information was found.");
		}
		
		this.generalInformationDao.updateByTelephoneNumber(generalInformationId, telephoneNumberUpdate);
		return new SuccessResult(generalInformationId + " updated.." + "( " + telephoneNumberUpdate + " )");
	}

	@Override
	public Result updateByEmail(int generalInformationId, String emailUpdate) {
		
		if(checkGeneralInformationId(generalInformationId) == false) {
			return new ErrorDataResult<GeneralInformation>("No such general information was found.");
		}
		
		this.generalInformationDao.updateByEmail(generalInformationId, emailUpdate);
		return new SuccessResult(generalInformationId + " updated.." + "( " + emailUpdate + " )");
	}

	@Override
	public Result updateByWorkingHour(int generalInformationId, int workingHourIdUpdate) {
		
		if(checkGeneralInformationId(generalInformationId) == false) {
			return new ErrorDataResult<GeneralInformation>("No such general information was found.");
		}
		
		if(this.workingHourDao.getByWorkingHourId(workingHourIdUpdate) == null) {
			return new ErrorDataResult<GeneralInformation>("No such working hour was found.");
		}
		
		this.generalInformationDao.updateByWorkingHour(generalInformationId, workingHourIdUpdate);
		return new SuccessResult(generalInformationId + " updated.." + "( " + workingHourIdUpdate + " )");
	}

	@Override
	public Result updateByStartedDate(int generalInformationId, Date startedDateUpdate) {
		
		if(checkGeneralInformationId(generalInformationId) == false) {
			return new ErrorDataResult<GeneralInformation>("No such general information was found.");
		}
		
		this.generalInformationDao.updateByStartedDate(generalInformationId, startedDateUpdate);
		return new SuccessResult(generalInformationId + " updated.." + "( " + startedDateUpdate + " )");
	}

}
