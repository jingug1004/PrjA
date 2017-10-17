package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.IssueVO;
import kr.co.comes.projectA.dto.ListCriteria;

public interface IssueService {

	/*
	 * issue ���
	 * 
	 * @return List<IssueVO>
	 */
	public List<IssueVO> issue_list(ListCriteria vo) throws Exception;

	/*
	 * seq ���ϱ�
	 * 
	 * @return int
	 */
	public int seq(int projid) throws Exception;

	/**
	 * ��ü issue ��� ����(����¡�� ���� sql��)
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
	 * issue ����
	 * 
	 * @return
	 */
	public void issue_remove(IssueVO vo) throws Exception;

	/**
	 * issue ��
	 * 
	 * @return IssueVO
	 */
	public IssueVO issue_read(IssueVO vo) throws Exception;

	/**
	 * issue ����
	 * 
	 * @return
	 */
	public void issue_modify(IssueVO vo) throws Exception;

	/**
	 * ���Ͼ��ε� ��ȸ
	 * 
	 * @return String
	 */
	public String getAttach(IssueVO vo) throws Exception;

	/**
	 * ���Ͼ��ε� ����
	 * 
	 * @return
	 */
	public void deleteAttach(IssueVO vo) throws Exception;

	/**
	 * Issue ����
	 * 
	 * @return List<IssueVO>
	 */
	public List<IssueVO> getIssue(IssueVO vo) throws Exception;
}
