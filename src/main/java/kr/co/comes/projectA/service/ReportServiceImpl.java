package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.ReportDAOImpl;
import kr.co.comes.projectA.dto.ReportVO;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Inject
	ReportDAOImpl reportDao;
	
	@Override
	public ReportVO reportList(int projid) {
		return reportDao.reportList(projid);
	}
	@Override
	public List<ReportVO> caseList(int projid) {
		return reportDao.caseList(projid);
	}
	@Override
	public List<ReportVO> testList(int projid, int senaid) {
		return reportDao.testList(projid, senaid);
	}

	@Override
	public ReportVO viewProject(int projid) {
		return reportDao.viewProject(projid);
	}
	
}
