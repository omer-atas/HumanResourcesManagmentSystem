package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.Date;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.InternService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.InternDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.StudiedDepartmentDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.StudiedSectionDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Intern;

@Service
public class InternManager implements InternService{
	
	private InternDao internDao;
	private StudiedDepartmentDao studiedDepartmentDao;
	private StudiedSectionDao studiedSectionDao;

	@Autowired
	public InternManager(InternDao internDao,StudiedDepartmentDao studiedDepartmentDao,StudiedSectionDao studiedSectionDao) {
		super();
		this.internDao = internDao;
		this.studiedDepartmentDao = studiedDepartmentDao;
		this.studiedSectionDao = studiedSectionDao;
	}

	@Override
	public DataResult<List<Intern>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Intern>>(this.internDao.findAll(pageable).getContent(), "Intern listed...");
	}
	
	public boolean checkInternId(int internId) {
		
		if(this.internDao.getByInternId(internId) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public Result add(Intern intern) {
		
		if(checkEmail(intern.getEmail()) == true) {
			return new ErrorDataResult<Intern>("Bu email adresine sahip stajyer bulunmaktadır..");
		}
		
		if(intern.getStudiedDepartmentIntern().getStudiedDepartmentId() == 0 ||
				this.studiedDepartmentDao.getByStudiedDepartmentId(intern.getStudiedDepartmentIntern().getStudiedDepartmentId()) == null ||
						intern.getStudiedSectionIntern().getStudiedSectionId() == 0 ||
				this.studiedSectionDao.getByStudiedSectionId(intern.getStudiedSectionIntern().getStudiedSectionId()) == null) {
			return new ErrorResult("İlişkiyi giriniz( İlişki girilmemiştir ya da girilen kimliğe karşılık gelen veri bulunmamaktadır.)....");
		}
		
		
		Pattern pattern = Pattern.compile("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
	    Matcher matcher = pattern.matcher(intern.getTelephoneNumber());
	    
	    if(matcher.matches() == false) {
	    	return new ErrorDataResult<Intern>("Telefon numarası formatında giriniz..");
	    }
		
		this.internDao.save(intern);
		return new SuccessResult("Intern of "+ intern.getEmail() +" added..");
	}

	@Override
	public Result updateByFirstName(int internId, String firstNameUpdate) {
		
		if(checkInternId(internId) == false) {
			return new ErrorDataResult<Intern>("No such intern was found.");
		}
		
		this.internDao.updateByFirstName(internId, firstNameUpdate);
		return new SuccessResult(internId + " updated.." + "( " + firstNameUpdate + " )");
	}

	@Override
	public Result updateByLastName(int internId, String lastNameUpdate) {
		
		if(checkInternId(internId) == false) {
			return new ErrorDataResult<Intern>("No such intern was found.");
		}
		
		this.internDao.updateByLastName(internId, lastNameUpdate);
		return new SuccessResult(internId + " updated.." + "( " + lastNameUpdate + " )");
	}

	@Override
	public Result updateByEmail(int internId, String emailUpdate) {
		
		if(checkInternId(internId) == false) {
			return new ErrorDataResult<Intern>("No such intern was found.");
		}
		
		this.internDao.updateByEmail(internId, emailUpdate);
		return new SuccessResult(internId + " updated.." + "( " + emailUpdate + " )");
	}

	@Override
	public Result updateByAddress(int internId, String addressUpdate) {
		
		if(checkInternId(internId) == false) {
			return new ErrorDataResult<Intern>("No such intern was found.");
		}
		
		this.internDao.updateByAddress(internId, addressUpdate);
		return new SuccessResult(internId + " updated.." + "( " + addressUpdate + " )");
	}

	@Override
	public Result updateByTelephoneNumber(int internId, String telephoneNumberUpdate) {
		
		if(checkInternId(internId) == false) {
			return new ErrorDataResult<Intern>("No such intern was found.");
		}
		
		this.internDao.updateByTelephoneNumber(internId, telephoneNumberUpdate);
		return new SuccessResult(internId + " updated.." + "( " + telephoneNumberUpdate + " )");
	}

	@Override
	public Result updateByInternshipStartedDate(int internId, Date internshipStartedDateUpdate) {
		
		if(checkInternId(internId) == false) {
			return new ErrorDataResult<Intern>("No such intern was found.");
		}
		
		this.internDao.updateByInternshipStartedDate(internId, internshipStartedDateUpdate);
		return new SuccessResult(internId + " updated.." + "( " + internshipStartedDateUpdate + " )");
	}

	@Override
	public Result updateByInternshipEndDate(int internId, Date internshipEndDateUpdate) {
		
		if(checkInternId(internId) == false) {
			return new ErrorDataResult<Intern>("No such intern was found.");
		}
		
		this.internDao.updateByInternshipEndDate(internId, internshipEndDateUpdate);
		return new SuccessResult(internId + " updated.." + "( " + internshipEndDateUpdate + " )");
	}

	@Override
	public Result deleteByInternId(int internId) {
		
		if(checkInternId(internId) == false) {
			return new ErrorDataResult<Intern>("No such intern was found.");
		}
		
		this.internDao.deleteByInternId(internId);
		return new SuccessResult(internId + " deleted.." + "( " + internId + " )");
	}

	public boolean checkEmail(String email) {
		
		if(this.internDao.getByEmail(email)== null) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public DataResult<Intern> getByEmail(String email) {
		return new SuccessDataResult<Intern>(this.internDao.getByEmail(email), "İntern found.. ("
				+ email + " )");
	}

	@Override
	public DataResult<Intern> getByEmailAndPassword(String email, String password) {
		return new SuccessDataResult<Intern>(this.internDao.getByEmailAndPassword(email,password), "İntern found.. ("
				+ email + " )");
	}
	
	

}
