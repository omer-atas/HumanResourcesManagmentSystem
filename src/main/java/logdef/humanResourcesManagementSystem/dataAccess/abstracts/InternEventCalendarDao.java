package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import logdef.humanResourcesManagementSystem.entities.concretes.InternEventCalendar;

public interface InternEventCalendarDao extends JpaRepository<InternEventCalendar, Integer>{

	InternEventCalendar getByInternEventCalendarId(int internEventCalendarId);
}
