package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.Employees;

public interface EmployeesDao extends JpaRepository<Employees, Integer>{
	
	Employees getByEmployeeId(int employeeId);
	
	Employees getByIdentityNumber(String identityNumber);
	
	Employees getByFirstNameAndLastName(String firstName,String lastName);
	
	@Query(value = "SELECT e FROM Employees e WHERE e.firstName=:firstName AND e.lastName=:lastName")
	List<Employees> getByFirstNameLastName(String firstName,String lastName);
	
	List<Employees> getByFirstNameContains(String firstName);
	
	List<Employees> getByFirstNameStartsWith(String firstName);
	
	@Query(value = "SELECT e FROM Employees e WHERE e.firstName LIKE :firstName% ")
	List<Employees> findByFirstNameStartsWith(String firstName);
	
	//  veritabanı operasyonlarının yönetimi parametreler aracılığıyla spring’e bırakmış oluyoruz
	@Transactional
	@Modifying
	@Query("UPDATE Employees e SET e.firstName=:firstNameUpdate WHERE e.identityNumber=:identityNumber")
	void updateByFirstName(String identityNumber,String firstNameUpdate);
	//  UPDATE public.employees SET first_name='deneme2' WHERE first_name='deneme';
	
	@Transactional
	@Modifying
	@Query("UPDATE Employees e SET e.lastName=:lastNameUpdate WHERE e.identityNumber=:identityNumber")
	void updateByLastName(String identityNumber,String lastNameUpdate);	
	
	@Transactional
	@Modifying
	@Query("UPDATE Employees e SET e.maritalStatus=:maritalStatusUpdate WHERE e.identityNumber=:identityNumber")
	void updateByMaritalStatus(String identityNumber,String maritalStatusUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Employees e SET e.militaryStatus=:militaryStatusUpdate WHERE e.identityNumber=:identityNumber")
	void updateByMilitaryStatus(String identityNumber,String militaryStatusUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Employees e SET e.childrenCount=:childrenCountUpdate WHERE e.identityNumber=:identityNumber")
	void updateByChildrenCount(String identityNumber,String childrenCountUpdate);
		
	@Transactional
	@Modifying
	@Query("UPDATE Employees e SET e.bloodGroup=:bloodGroupUpdate WHERE e.identityNumber=:identityNumber")
	void updateByBloodGroup(String identityNumber,String bloodGroupUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Employees e SET e.nationality=:nationalityUpdate WHERE e.identityNumber=:identityNumber")
	void updateByNationality(String identityNumber,String nationalityUpdate);

	@Transactional
	@Modifying
	@Query("UPDATE Employees e SET e.gender=:genderUpdate WHERE e.identityNumber=:identityNumber")
	void updateByGender(String identityNumber,String genderUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Employees e SET e.birthday=:birthdayUpdate WHERE e.identityNumber=:identityNumber")
	void updateByBirthday(String identityNumber,Date birthdayUpdate);

	@Transactional
	@Modifying
	@Query("DELETE From Employees e where e.employeeId=:employeeId")
	void deleteByEmployeeId(int employeeId);
	//	DELETE FROM public.employees WHERE employee_id=1;
	
	
}
