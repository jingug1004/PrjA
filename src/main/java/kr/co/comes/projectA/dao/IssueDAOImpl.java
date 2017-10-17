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

	// �������� ���� "kr.co.comes.projectA.issueMapper"�� namespace ������ �����ؼ� ���
	private static String namespace = "kr.co.comes.projectA.issueMapper";

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_list(ListCriteria vo)
	 * issueMapper�� �ִ� list ��� issue ���
	 */
	@Override
	public List<IssueVO> issue_list(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".list", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#seq(ListCriteria vo) 
	 * issueMapper�� �ִ� seq ��� issue ���
	 */
	@Override
	public int seq(int projid) throws Exception {
		return session.selectOne(namespace + ".seq", projid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_listCount(ListCriteria vo)
	 * issueMapper�� �ִ� listCount ��� issue ��� ����
	 */
	@Override
	public int issue_listCount(ListCriteria vo) throws Exception {
		return session.selectOne(namespace + ".listCount", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_create(IssueVO vo)
	 * issueMapper�� �ִ� create ��� issue ����
	 */
	@Override
	public void issue_create(IssueVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_remove(IssueVO vo)
	 * issueMapper�� �ִ� remove ��� issue ����
	 */
	@Override
	public void issue_remove(IssueVO vo) throws Exception {
		session.delete(namespace + ".remove", vo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_read(IssueVO vo)
	 * issueMapper�� �ִ� read ��� issue ��
	 */
	@Override
	public IssueVO issue_read(IssueVO vo) throws Exception {
		return session.selectOne(namespace + ".read", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#issue_modify(IssueVO vo)
	 * issueMapper�� �ִ� modify ��� issue ����
	 */
	@Override
	public void issue_modify(IssueVO vo) throws Exception {
		session.update(namespace + ".modify", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#deleteAttach(IssueVO vo)
	 * issueMapper�� �ִ� deleteAttach ��� ���Ͼ��ε� ����
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
	 * issueMapper�� �ִ� getAttach ��� ���Ͼ��ε� ��ȸ
	 */
	@Override
	public String getAttach(IssueVO vo) throws Exception {

		return session.selectOne(namespace + ".getAttach", vo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.IssueDAO#getIssue(IssueVO vo) 
	 * IssueMapper�� �ִ� getIssue ��� Issue ���� �޾ƿ���
	 */
	@Override
	public List<IssueVO> getIssue(IssueVO vo) throws Exception {
		return session.selectList(namespace + ".getIssue", vo);
	}

}
