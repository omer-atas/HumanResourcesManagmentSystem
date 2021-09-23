package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.Report;

public interface ReportService {
	
	Result add(Report report);
	
	DataResult<List<Report>> getAll(int pageNo, int pageSize);
	
	DataResult<Report> getByReportNameAndReportField(String reportName,String reportField);
	
	Result  updateByReportName(int reportId,String reportNameUpdate);
	
	Result	updateByReportField(int reportId,String reportFieldUpdate);
	
	Result  deleteByReportId(int reportId);

}
