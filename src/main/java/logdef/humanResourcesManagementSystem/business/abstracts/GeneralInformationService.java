package logdef.humanResourcesManagementSystem.business.abstracts;


import java.util.Date;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.GeneralInformation;

public interface GeneralInformationService {
	
	DataResult<List<GeneralInformation>> getAll(int pageNo, int pageSize);
	
	Result add(GeneralInformation generalInformation);
		
	DataResult<GeneralInformation> getByGeneralInformationId(int generalInformationId);
	
	Result deleteByGeneralInformationId(int generalInformationId);
	
	Result updateBySuperscription(int generalInformationId,String superscriptionUpdate);
	
	Result updateByTelephoneNumber(int generalInformationId,String telephoneNumberUpdate);
	
	Result updateByEmail(int generalInformationId,String emailUpdate);
	
	Result updateByWorkingHour(int generalInformationId,int workingHourIdUpdate);
	
	Result updateByStartedDate(int generalInformationId,Date startedDateUpdate);
}
