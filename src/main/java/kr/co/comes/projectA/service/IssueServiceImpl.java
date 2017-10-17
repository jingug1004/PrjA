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
	 * issue 목록 IssueDAO에 있는 issue_list(ListCriteria vo) 사용
	 */
	@Override
	public List<IssueVO> issue_list(ListCriteria vo) throws Exception {
		return dao.issue_list(vo);
	}

	/*
	 * issue 목록 IssueDAO에 있는 seq(ListCriteria vo) 사용
	 */
	@Override
	public int seq(int projid) throws Exception {
		return dao.seq(projid);
	}

	/*
	 * issue 목록 갯수 IssueDAO에 있는 issue_listCount(ListCriteria vo) 사용
	 */
	@Override
	public int issue_listCount(ListCriteria vo) throws Exception {
		return dao.issue_listCount(vo);
	}

	/*
	 * issue 생성 IssueDAO에 있는 issue_create(Issue VO vo) 사용
	 */
	@Override
	public void issue_create(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.issue_create(vo);

	}

	/*
	 * issue 삭제 IssueDAO에 있는 issue_remove(IssueVO vo) 사용
	 */
	@Override
	public void issue_remove(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.issue_remove(vo);
	}

	/*
	 * issue 상세 IssueDAO에 있는 issue_read(IssueVO vo) 사용
	 */
	@Override
	public IssueVO issue_read(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.issue_read(vo);
	}

	/*
	 * issue 수정 IssueDAO에 있는 issue_modify(IssueVO vo) 사용
	 */
	@Override
	public void issue_modify(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.issue_modify(vo);
	}

	/*
	 * 파일업로드 삭제 IssueDAO에 있는 deleteAttach(IssueVO vo) 사용
	 */
	@Override
	public void deleteAttach(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteAttach(vo);
	}

	/*
	 * 파일업로드 조회 IssueDAO에 있는 getAttach(IssueVO vo) 사용
	 */
	@Override
	public String getAttach(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAttach(vo);
	}

	/*
	 * Issue 상세 IssueDAO에 있는 getIssue(IssueVO vo) 사용
	 */
	@Override
	public List<IssueVO> getIssue(IssueVO vo) throws Exception {
		return dao.getIssue(vo);
	}
}
