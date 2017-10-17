package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.ReportVO;

public interface ReportDAO {
	
	public ReportVO reportList(int projid);

	public ReportVO viewProject(int projid);

	public List<ReportVO> caseList(int projid);

	public List<ReportVO> testList(int projid, int senaid);

}
