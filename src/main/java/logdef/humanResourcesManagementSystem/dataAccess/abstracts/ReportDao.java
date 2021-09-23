package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import logdef.humanResourcesManagementSystem.entities.concretes.Report;

public interface ReportDao extends JpaRepository<Report, Integer> {
	
	
	Report getByReportId(int reportId);
	
	Report getByReportName(String reportName);
	
	Report getByReportField(String reportField);
	
	Report getByReportNameAndReportField(String reportName,String reportField);
	
	@Transactional
	@Modifying
	@Query("UPDATE Report r SET r.reportName=:reportNameUpdate WHERE r.reportId=:reportId")
	void updateByReportName(int reportId,String reportNameUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Report r SET r.reportField=:reportFieldUpdate WHERE r.reportId=:reportId")
	void updateByLinkedin(int reportId,String reportFieldUpdate);
	
	@Transactional
	@Modifying
	@Query("DELETE From Report r where r.reportId=:reportId")
	void deleteByReportId(int reportId);
	
	

}
