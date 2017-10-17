package kr.co.comes.projectA.dao;

import kr.co.comes.projectA.dto.ListCriteria;

public interface MainDAO {
	/**
	 * 전체 프로젝트 목록 갯수(project Summary를 위한 sql문)
	 * @return int
	 */
	public int project_TotalCount(ListCriteria vo) throws Exception;
	
	/**
	 * 진행 프로젝트 목록 갯수
	 * @return int
	 */
	public int project_OngoingCount(ListCriteria vo) throws Exception;
	
	/**
	 * 미진행 프로젝트 목록 갯수
	 * @return int
	 */
	public int project_HoldCount(ListCriteria vo) throws Exception;
	/**
	 * 종료 프로젝트 목록 갯수
	 * @return int
	 */
	public int project_EndCount(ListCriteria vo) throws Exception;
	
	/**
	 * 전체 프로젝트 목록 갯수(user Summary를 위한 sql문)
	 * @return int
	 */
	public int user_TotalCount(ListCriteria vo) throws Exception;
	
	/**
	 * 전체 프로젝트 목록 갯수(user Summary를 위한 sql문)
	 * @return int
	 */
	public int user_AdminCount(ListCriteria vo) throws Exception;
	
	/**
	 * 전체 프로젝트 목록 갯수(user Summary를 위한 sql문)
	 * @return int
	 */
	public int user_TestEngineerCount(ListCriteria vo) throws Exception;
	
	/**
	 * 전체 프로젝트 목록 갯수(user Summary를 위한 sql문)
	 * @return int
	 */
	public int user_ReviewerCount(ListCriteria vo) throws Exception;
	
	/**
	 * note 갯수(note Summary를 위한 sql문)
	 * @return int
	 */
	public int NoteCount(ListCriteria vo) throws Exception;
	
	/**
	 * issue 갯수(note Summary를 위한 sql문)
	 * @return int
	 */
	public int IssueCount(ListCriteria vo) throws Exception;
	
	/**
	 * defect 갯수(note Summary를 위한 sql문)
	 * @return int
	 */
	public int DefectCount(ListCriteria vo) throws Exception;

}// interface
