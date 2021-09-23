package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.InCompanyActivity;

public interface InCompanyActivityDao extends JpaRepository<InCompanyActivity, Integer>{
	
	InCompanyActivity getByEventCalendarId(int eventCalendarId);
	
	InCompanyActivity getByInCompanyActivityName(String inCompanyActivityName);
	
	@Transactional
	@Modifying
	@Query("UPDATE InCompanyActivity i SET i.eventCalendarName=:eventCalendarNameUpdate WHERE i.eventCalendarId=:eventCalendarId")
	void updateByEventCalendarName(int eventCalendarId,String eventCalendarNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE InCompanyActivity i SET i.inCompanyActivityName=:inCompanyActivityNameUpdate WHERE i.eventCalendarId=:eventCalendarId")
	void updateByInCompanyPermissionName(int eventCalendarId,String inCompanyActivityNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE InCompanyActivity i SET i.startingDate=:startingDateUpdate WHERE i.eventCalendarId=:eventCalendarId")
	void updateByStartingDate(int eventCalendarId,Date startingDateUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE InCompanyActivity i SET i.endDate=:endDateUpdate WHERE i.eventCalendarId=:eventCalendarId")
	void updateByEndDate(int eventCalendarId,Date endDateUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From InCompanyActivity i where i.eventCalendarId=:eventCalendarId")
	void deleteByInCompanyActivityId(int eventCalendarId);

}
