package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.ReportVO;

public interface ReportService {

	public ReportVO reportList(int projid);

	public ReportVO viewProject(int projid);

	public List<ReportVO> caseList(int projid);

	public List<ReportVO> testList(int projid, int senaid);
}
