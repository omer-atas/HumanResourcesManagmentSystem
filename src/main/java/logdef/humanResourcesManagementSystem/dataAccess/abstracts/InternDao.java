package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.Intern;

public interface InternDao extends JpaRepository<Intern, Integer>{
	
	Intern getByInternId(int internId);
	
	Intern getByEmail(String email);
	
	Intern getByEmailAndPassword(String email,String password);
	
	@Transactional
	@Modifying
	@Query("UPDATE Intern i SET i.firstName=:firstNameUpdate WHERE i.internId=:internId")
	void updateByFirstName(int internId,String firstNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Intern i SET i.lastName=:lastNameUpdate WHERE i.internId=:internId")
	void updateByLastName(int internId,String lastNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Intern i SET i.email=:emailUpdate WHERE i.internId=:internId")
	void updateByEmail(int internId,String emailUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Intern i SET i.address=:addressUpdate WHERE i.internId=:internId")
	void updateByAddress(int internId,String addressUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Intern i SET i.telephoneNumber=:telephoneNumberUpdate WHERE i.internId=:internId")
	void updateByTelephoneNumber(int internId,String telephoneNumberUpdate);

	@Transactional
	@Modifying
	@Query("UPDATE Intern i SET i.internshipStartedDate=:internshipStartedDateUpdate WHERE i.internId=:internId")
	void updateByInternshipStartedDate(int internId,Date internshipStartedDateUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Intern i SET i.internshipEndDate=:internshipEndDateUpdate WHERE i.internId=:internId")
	void updateByInternshipEndDate(int internId,Date internshipEndDateUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From Intern i where i.internId=:internId")
	void deleteByInternId(int internId);

}
