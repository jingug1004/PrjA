package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.IssueDAO;
import kr.co.comes.projectA.dto.IssueVO;
import kr.co.comes.projectA.dto.ListCriteria;

@Service
public class IssueServiceImpl implements IssueService {

	@Inject
	private IssueDAO dao;

	/*
	 * issue ��� IssueDAO�� �ִ� issue_list(ListCriteria vo) ���
	 */
	@Override
	public List<IssueVO> issue_list(ListCriteria vo) throws Exception {
		return dao.issue_list(vo);
	}

	/*
	 * issue ��� IssueDAO�� �ִ� seq(ListCriteria vo) ���
	 */
	@Override
	public int seq(int projid) throws Exception {
		return dao.seq(projid);
	}

	/*
	 * issue ��� ���� IssueDAO�� �ִ� issue_listCount(ListCriteria vo) ���
	 */
	@Override
	public int issue_listCount(ListCriteria vo) throws Exception {
		return dao.issue_listCount(vo);
	}

	/*
	 * issue ���� IssueDAO�� �ִ� issue_create(Issue VO vo) ���
	 */
	@Override
	public void issue_create(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.issue_create(vo);

	}

	/*
	 * issue ���� IssueDAO�� �ִ� issue_remove(IssueVO vo) ���
	 */
	@Override
	public void issue_remove(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.issue_remove(vo);
	}

	/*
	 * issue �� IssueDAO�� �ִ� issue_read(IssueVO vo) ���
	 */
	@Override
	public IssueVO issue_read(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.issue_read(vo);
	}

	/*
	 * issue ���� IssueDAO�� �ִ� issue_modify(IssueVO vo) ���
	 */
	@Override
	public void issue_modify(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.issue_modify(vo);
	}

	/*
	 * ���Ͼ��ε� ���� IssueDAO�� �ִ� deleteAttach(IssueVO vo) ���
	 */
	@Override
	public void deleteAttach(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteAttach(vo);
	}

	/*
	 * ���Ͼ��ε� ��ȸ IssueDAO�� �ִ� getAttach(IssueVO vo) ���
	 */
	@Override
	public String getAttach(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAttach(vo);
	}

	/*
	 * Issue �� IssueDAO�� �ִ� getIssue(IssueVO vo) ���
	 */
	@Override
	public List<IssueVO> getIssue(IssueVO vo) throws Exception {
		return dao.getIssue(vo);
	}
}
