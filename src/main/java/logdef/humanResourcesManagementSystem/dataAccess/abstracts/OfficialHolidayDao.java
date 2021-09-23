package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import logdef.humanResourcesManagementSystem.entities.concretes.OfficialHoliday;

public interface OfficialHolidayDao extends JpaRepository<OfficialHoliday, Integer>{
	
	OfficialHoliday	getByEventCalendarId(int eventCalendarId);
	
	OfficialHoliday	getByOfficialHolidayName(String officialHolidayName);
	
	@Transactional
	@Modifying
	@Query("UPDATE OfficialHoliday o SET o.eventCalendarName=:eventCalendarNameUpdate WHERE o.eventCalendarId=:eventCalendarId")
	void updateByEventCalendarName(int eventCalendarId,String eventCalendarNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE OfficialHoliday o SET o.officialHolidayName=:officialHolidayNameUpdate WHERE o.eventCalendarId=:eventCalendarId")
	void updateByOfficialHolidayName(int eventCalendarId,String officialHolidayNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE OfficialHoliday o SET o.startingDate=:startingDateUpdate WHERE o.eventCalendarId=:eventCalendarId")
	void updateByStartingDate(int eventCalendarId,Date startingDateUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE OfficialHoliday o SET o.endDate=:endDateUpdate WHERE o.eventCalendarId=:eventCalendarId")
	void updateByEndDate(int eventCalendarId,Date endDateUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From OfficialHoliday o where o.eventCalendarId=:eventCalendarId")
	void deleteByOfficialHolidayId(int eventCalendarId);

}
