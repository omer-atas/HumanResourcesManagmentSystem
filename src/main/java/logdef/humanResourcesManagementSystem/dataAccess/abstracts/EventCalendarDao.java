package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.EventCalendar;

public interface EventCalendarDao extends JpaRepository<EventCalendar, Integer>{
	
	EventCalendar getByEventCalendarId(int eventCalendarId);
	
	@Transactional
	@Modifying
	@Query("UPDATE EventCalendar e SET e.eventCalendarName=:eventCalendarNameUpdate WHERE e.eventCalendarId=:eventCalendarId")
	void updateByEventCalendarName(int eventCalendarId,String eventCalendarNameUpdate);

	@Transactional
	@Modifying
	@Query("DELETE From EventCalendar e where e.eventCalendarId=:eventCalendarId")
	void deleteByEventCalendarId (int eventCalendarId);

}
