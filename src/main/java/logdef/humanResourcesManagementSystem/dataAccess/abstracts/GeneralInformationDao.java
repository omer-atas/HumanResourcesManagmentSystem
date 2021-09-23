package logdef.humanResourcesManagementSystem.dataAccess.abstracts;


import java.util.Date;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import logdef.humanResourcesManagementSystem.entities.concretes.GeneralInformation;

public interface GeneralInformationDao extends JpaRepository<GeneralInformation, Integer>{
	
	GeneralInformation getByGeneralInformationId(int generalInformationId);
	
	@Query("FROM GeneralInformation WHERE employeesGeneralInformation.employeeId=:employeeId")
	GeneralInformation getByEmployeeId(int employeeId);
	
	@Transactional
	@Modifying
	@Query("UPDATE GeneralInformation g SET g.superscription=:superscriptionUpdate WHERE g.generalInformationId=:generalInformationId")
	void updateBySuperscription(int generalInformationId,String superscriptionUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE GeneralInformation g SET g.telephoneNumber=:telephoneNumberUpdate WHERE g.generalInformationId=:generalInformationId")
	void updateByTelephoneNumber(int generalInformationId,String telephoneNumberUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE GeneralInformation g SET g.studiedDepartment.studiedDepartmentId=:studiedDepartmentIdUpdate WHERE g.generalInformationId=:generalInformationId")
	void updateByStudiedDepartment(int generalInformationId,int studiedDepartmentIdUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE GeneralInformation g SET g.studiedSection.studiedSectionId=:studiedSectionIdUpdate WHERE g.generalInformationId=:generalInformationId")
	void updateByStudiedSection(int generalInformationId,int studiedSectionIdUpdate );
	
	@Transactional
	@Modifying
	@Query("UPDATE GeneralInformation g SET g.email=:emailUpdate WHERE g.generalInformationId=:generalInformationId")
	void updateByEmail(int generalInformationId,String emailUpdate);

	@Transactional
	@Modifying
	@Query("UPDATE GeneralInformation g SET g.workingHour.workingHourId=:workingHourIdUpdate WHERE g.generalInformationId=:generalInformationId")
	void updateByWorkingHour(int generalInformationId,int workingHourIdUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE GeneralInformation g SET g.startedDate=:startedDateUpdate WHERE g.generalInformationId=:generalInformationId")
	void updateByStartedDate(int generalInformationId,Date startedDateUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From GeneralInformation g where g.generalInformationId=:generalInformationId")
	void deleteByGeneralInformationId(int generalInformationId);
	

	
	

}
