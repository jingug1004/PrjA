package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.IssueVO;
import kr.co.comes.projectA.dto.ListCriteria;

@Repository
public class IssueDAOImpl implements IssueDAO {

	@Inject
	private SqlSession session;

	// 공통으로 들어가는 "kr.co.comes.projectA.issueMapper"를 namespace 변수에 저장해서 사용
	private static String namespace = "kr.co.comes.projectA.issueMapper";

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_list(ListCriteria vo)
	 * issueMapper에 있는 list 사용 issue 목록
	 */
	@Override
	public List<IssueVO> issue_list(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".list", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#seq(ListCriteria vo) 
	 * issueMapper에 있는 seq 사용 issue 목록
	 */
	@Override
	public int seq(int projid) throws Exception {
		return session.selectOne(namespace + ".seq", projid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_listCount(ListCriteria vo)
	 * issueMapper에 있는 listCount 사용 issue 목록 갯수
	 */
	@Override
	public int issue_listCount(ListCriteria vo) throws Exception {
		return session.selectOne(namespace + ".listCount", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_create(IssueVO vo)
	 * issueMapper에 있는 create 사용 issue 생성
	 */
	@Override
	public void issue_create(IssueVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_remove(IssueVO vo)
	 * issueMapper에 있는 remove 사용 issue 삭제
	 */
	@Override
	public void issue_remove(IssueVO vo) throws Exception {
		session.delete(namespace + ".remove", vo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_read(IssueVO vo)
	 * issueMapper에 있는 read 사용 issue 상세
	 */
	@Override
	public IssueVO issue_read(IssueVO vo) throws Exception {
		return session.selectOne(namespace + ".read", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_modify(IssueVO vo)
	 * issueMapper에 있는 modify 사용 issue 수정
	 */
	@Override
	public void issue_modify(IssueVO vo) throws Exception {
		session.update(namespace + ".modify", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#deleteAttach(IssueVO vo)
	 * issueMapper에 있는 deleteAttach 사용 파일업로드 삭제
	 */
	@Override
	public void deleteAttach(IssueVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace + ".deleteAttach", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#getAttach(IssueVO vo) 
	 * issueMapper에 있는 getAttach 사용 파일업로드 조회
	 */
	@Override
	public String getAttach(IssueVO vo) throws Exception {

		return session.selectOne(namespace + ".getAttach", vo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#getIssue(IssueVO vo) 
	 * IssueMapper에 있는 getIssue 사용 Issue 정보 받아오기
	 */
	@Override
	public List<IssueVO> getIssue(IssueVO vo) throws Exception {
		return session.selectList(namespace + ".getIssue", vo);
	}

}
