package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import logdef.humanResourcesManagementSystem.entities.concretes.WorkingHour;

public interface WorkingHourDao extends JpaRepository<WorkingHour, Integer>{
	
	WorkingHour getByWorkingHourId(int workingHourId);
	
	WorkingHour getByWorkingHour(String workingHour);
	

	@Transactional
	@Modifying
	@Query("UPDATE WorkingHour w SET w.workingHour=:workingHourUpdate WHERE w.workingHourId=:workingHourId")
	void updateByWorkingHour(int workingHourId,String workingHourUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From WorkingHour w where w.workingHourId=:workingHourId")
	void deleteByWorkingHourId(int workingHourId);

}
