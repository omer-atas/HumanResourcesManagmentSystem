package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import logdef.humanResourcesManagementSystem.entities.concretes.ReligiousHoliday;

public interface ReligiousHolidaysDao extends JpaRepository<ReligiousHoliday, Integer>{
	
	ReligiousHoliday 	getByEventCalendarId(int eventCalendarId);
	
	ReligiousHoliday	getByReligiouslHolidayName(String religiouslHolidayName);
	
	@Transactional
	@Modifying
	@Query("UPDATE ReligiousHoliday r SET r.eventCalendarName=:eventCalendarNameUpdate WHERE r.eventCalendarId=:eventCalendarId")
	void updateByEventCalendarName(int eventCalendarId,String eventCalendarNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE ReligiousHoliday r SET r.religiouslHolidayName=:religiouslHolidayNameUpdate WHERE r.eventCalendarId=:eventCalendarId")
	void updateByReligiouslHolidayName(int eventCalendarId,String religiouslHolidayNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE ReligiousHoliday r SET r.startingDate=:startingDateUpdate WHERE r.eventCalendarId=:eventCalendarId")
	void updateByStartingDate(int eventCalendarId,Date startingDateUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE ReligiousHoliday r SET r.endDate=:endDateUpdate WHERE r.eventCalendarId=:eventCalendarId")
	void updateByEndDate(int eventCalendarId,Date endDateUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From ReligiousHoliday r where r.eventCalendarId=:eventCalendarId")
	void deleteByReligiousHolidayId(int eventCalendarId);

}
