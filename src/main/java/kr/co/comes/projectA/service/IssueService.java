package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.IssueVO;
import kr.co.comes.projectA.dto.ListCriteria;

public interface IssueService {

	/*
	 * issue 목록
	 * 
	 * @return List<IssueVO>
	 */
	public List<IssueVO> issue_list(ListCriteria vo) throws Exception;

	/*
	 * seq 구하기
	 * 
	 * @return int
	 */
	public int seq(int projid) throws Exception;

	/**
	 * 전체 issue 목록 갯수(페이징을 위한 sql문)
	 * 
	 * @return int
	 */
	public int issue_listCount(ListCriteria vo) throws Exception;

	/**
	 * issue insert
	 * 
	 * @return
	 */
	public void issue_create(IssueVO vo) throws Exception;

	/**
	 * issue 삭제
	 * 
	 * @return
	 */
	public void issue_remove(IssueVO vo) throws Exception;

	/**
	 * issue 상세
	 * 
	 * @return IssueVO
	 */
	public IssueVO issue_read(IssueVO vo) throws Exception;

	/**
	 * issue 수정
	 * 
	 * @return
	 */
	public void issue_modify(IssueVO vo) throws Exception;

	/**
	 * 파일업로드 조회
	 * 
	 * @return String
	 */
	public String getAttach(IssueVO vo) throws Exception;

	/**
	 * 파일업로드 삭제
	 * 
	 * @return
	 */
	public void deleteAttach(IssueVO vo) throws Exception;

	/**
	 * Issue 정보
	 * 
	 * @return List<IssueVO>
	 */
	public List<IssueVO> getIssue(IssueVO vo) throws Exception;
}
