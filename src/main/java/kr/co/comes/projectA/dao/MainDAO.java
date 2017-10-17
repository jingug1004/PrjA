package kr.co.comes.projectA.dao;

import kr.co.comes.projectA.dto.ListCriteria;

public interface MainDAO {
	/**
	 * ��ü ������Ʈ ��� ����(project Summary�� ���� sql��)
	 * @return int
	 */
	public int project_TotalCount(ListCriteria vo) throws Exception;
	
	/**
	 * ���� ������Ʈ ��� ����
	 * @return int
	 */
	public int project_OngoingCount(ListCriteria vo) throws Exception;
	
	/**
	 * ������ ������Ʈ ��� ����
	 * @return int
	 */
	public int project_HoldCount(ListCriteria vo) throws Exception;
	/**
	 * ���� ������Ʈ ��� ����
	 * @return int
	 */
	public int project_EndCount(ListCriteria vo) throws Exception;
	
	/**
	 * ��ü ������Ʈ ��� ����(user Summary�� ���� sql��)
	 * @return int
	 */
	public int user_TotalCount(ListCriteria vo) throws Exception;
	
	/**
	 * ��ü ������Ʈ ��� ����(user Summary�� ���� sql��)
	 * @return int
	 */
	public int user_AdminCount(ListCriteria vo) throws Exception;
	
	/**
	 * ��ü ������Ʈ ��� ����(user Summary�� ���� sql��)
	 * @return int
	 */
	public int user_TestEngineerCount(ListCriteria vo) throws Exception;
	
	/**
	 * ��ü ������Ʈ ��� ����(user Summary�� ���� sql��)
	 * @return int
	 */
	public int user_ReviewerCount(ListCriteria vo) throws Exception;
	
	/**
	 * note ����(note Summary�� ���� sql��)
	 * @return int
	 */
	public int NoteCount(ListCriteria vo) throws Exception;
	
	/**
	 * issue ����(note Summary�� ���� sql��)
	 * @return int
	 */
	public int IssueCount(ListCriteria vo) throws Exception;
	
	/**
	 * defect ����(note Summary�� ���� sql��)
	 * @return int
	 */
	public int DefectCount(ListCriteria vo) throws Exception;

}// interface
