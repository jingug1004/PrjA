package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ProjectVO;
import kr.co.comes.projectA.dto.UseProjectVO;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	@Inject
	private SqlSession session;

	// �������� ���� "kr.co.comes.projectA.projectMapper"�� namespace ������ �����ؼ� ���
	private static String namespace = "kr.co.comes.projectA.projectMapper";

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_list(ListCriteria vo)
	 * projectMapper�� �ִ� list ��� ������Ʈ ���
	 */
	@Override
	public List<ProjectVO> project_list(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".list", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_listCount(ListCriteria
	 * vo) projectMapper�� �ִ� listCount ��� ������Ʈ ��� ����
	 */
	@Override
	public int project_listCount(ListCriteria vo) throws Exception {
		return session.selectOne(namespace + ".listCount", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_create(ProjectVO vo)
	 * projectMapper�� �ִ� project_create ��� ������Ʈ ����
	 */
	@Override
	public void project_create(ProjectVO vo) throws Exception {
		session.insert(namespace + ".project_create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_read(int projid)
	 * projectMapper�� �ִ� project_read ��� ������Ʈ ��
	 */
	@Override
	public ProjectVO project_read(int projid) throws Exception {
		return session.selectOne(namespace + ".project_read", projid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_modify(ProjectVO vo)
	 * projectMapper�� �ִ� project_modify ��� ������Ʈ ����
	 */
	@Override
	public void project_modify(ProjectVO vo) throws Exception {
		session.update(namespace + ".project_modify", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_remove(int projid)
	 * projectMapper�� �ִ� project_remove ��� ������Ʈ ����
	 */
	@Override
	public void project_remove(int projid) throws Exception {
		session.delete(namespace + ".project_remove", projid);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_projid(ListCriteria vo)
	 * projectMapper�� �ִ� project_projid ��� ������Ʈ id ���ϱ�
	 */
	@Override
	public int project_projid(String User) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".projid", User);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#getAttach(ProjectVO vo)
	 * projectMapper�� �ִ� getAttach ��� ���Ͼ��ε� ��ȸ
	 */
	@Override
	public String getAttach(int projid) throws Exception {

		return session.selectOne(namespace + ".getAttach", projid);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#useproj_create(UseProjectVO vo)
	 * projectMapper�� �ִ� useproj_create ���
	 */
	@Override
	public void useproj_create(UseProjectVO vo) throws Exception {
		session.insert(namespace + ".useproj_create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#paticipant(int projid)
	 * projectMapper�� �ִ� paticipant ���
	 */
	@Override
	public List<String> paticipant(int projid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".paticipant", projid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#charge(int projid)
	 * projectMapper�� �ִ� charge ��� 
	 */
	@Override
	public String charge(int projid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".charge", projid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#charge_update(UseProjectVO vo)
	 * projectMapper�� �ִ� charge_update ���
	 */
	@Override
	public void charge_update(UseProjectVO vo) throws Exception {
		session.update(namespace + ".charge_update", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#useproj_delete(UseProjectVO vo)
	 * projectMapper�� �ִ� useproj_delete ��� 
	 */
	@Override
	public void useproj_delete(UseProjectVO vo) throws Exception {
		session.delete(namespace + ".useproj_delete", vo);
	}

}
