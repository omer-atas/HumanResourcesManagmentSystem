package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.ReportService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.InternDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.ReportDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Report;
@Service
public class ReportManager implements ReportService{
	
	private ReportDao reportDao;
	private InternDao internDao;

	@Autowired
	public ReportManager(ReportDao reportDao,InternDao internDao) {
		super();
		this.reportDao = reportDao;
		this.internDao = internDao;
	}

	@Override
	public Result add(Report report) {
		
		if(report.getProjectIntern().getProjectInternId() == 0 || this.internDao.getByInternId(report.getProjectIntern().getProjectInternId()) == null) {
			return new ErrorResult("Stajyer bulunamadı..");
		}
		
		this.reportDao.save(report);
		return new SuccessResult(report.getReportName() + " added.." );
	}

	@Override
	public DataResult<List<Report>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Report>>(this.reportDao.findAll(pageable).getContent(), " Raporlar listelendi..") ;
	}

	@Override
	public DataResult<Report> getByReportNameAndReportField(String reportName, String reportField) {
		return new SuccessDataResult<Report>(this.reportDao.getByReportNameAndReportField(reportName, reportField), "bulundu..");
	}

	@Override
	public Result updateByReportName(int reportId, String reportNameUpdate) {
		if(this.reportDao.getByReportId(reportId) == null) {
			return new ErrorResult("Böyle bir rapor bulunmamaktadır..");
		}
		
		this.reportDao.updateByReportName(reportId, reportNameUpdate);
		return new SuccessResult(reportId + " updated..( " + reportNameUpdate + " )");
	}

	@Override
	public Result updateByReportField(int reportId, String reportFieldUpdate) {
		
		if(this.reportDao.getByReportId(reportId) == null) {
			return new ErrorResult("Böyle bir rapor bulunmamaktadır..");
		}
		
		this.reportDao.updateByLinkedin(reportId, reportFieldUpdate);
		return new SuccessResult(reportId + " updated..( " + reportFieldUpdate + " )");
	}

	@Override
	public Result deleteByReportId(int reportId) {
		
		if(this.reportDao.getByReportId(reportId) == null) {
			return new ErrorResult("Böyle bir rapor bulunmamaktadır..");
		}
		
		this.reportDao.deleteByReportId(reportId);
		return new SuccessResult(reportId + " deleted..");
	}
	
	
	

}
