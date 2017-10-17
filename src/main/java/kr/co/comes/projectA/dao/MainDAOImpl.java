package kr.co.comes.projectA.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ListCriteria;

@Repository
public class MainDAOImpl implements MainDAO {

	@Inject
	private SqlSession session;

	//�������� ���� "kr.co.comes.projectA.mainMapper"�� namespace ������ �����ؼ� ��� 
	private static String namespace = "kr.co.comes.projectA.mainMapper";

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_TotalCount(ListCriteria vo)
     * mainMapper�� �ִ� Total ���
     * ������Ʈ ��� ����
     */
	@Override
	public int project_TotalCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".p_Total", vo);
	}
	
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_OngoingCount(ListCriteria vo)
     * mainMapper�� �ִ� Ongoing ���
     * ������Ʈ ��� ����
     */
	@Override
	public int project_OngoingCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".p_Ongoing", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_HoldCount(ListCriteria vo)
     * mainMapper�� �ִ� Hold ���
     * ������Ʈ ��� ����
     */
	@Override
	public int project_HoldCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".p_Hold", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_EndCount(ListCriteria vo)
     * mainMapper�� �ִ� End ���
     * ������Ʈ ��� ����
     */
	@Override
	public int project_EndCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".p_End", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TotalCount(ListCriteria vo)
     * mainMapper�� �ִ� u_Total ���
     * ������Ʈ ��� ����
     */
	@Override
	public int user_TotalCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".u_Total", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TotalCount(ListCriteria vo)
     * mainMapper�� �ִ� u_Admin ���
     * ������Ʈ ��� ����
     */
	@Override
	public int user_AdminCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".u_Admin", vo);
	}

	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TestEngineerCount(ListCriteria vo)
     * mainMapper�� �ִ� u_TestEngineer ���
     * ������Ʈ ��� ����
     */
	@Override
	public int user_TestEngineerCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".u_TestEnginner", vo);
	}
	
	
	 /* (non-Javadoc)
    * @see kr.co.comes.projectA.dao.MainDAO#user_ReviewerCount(ListCriteria vo)
    * mainMapper�� �ִ� u_Reviewer ���
    * ������Ʈ ��� ����
    */
	@Override
	public int user_ReviewerCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".u_Reviewer", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#NoteCount(ListCriteria vo)
     * mainMapper�� �ִ� Note ���
     * note ����
     */
	@Override
	public int NoteCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".Note", vo);
	}

	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#IssueCount(ListCriteria vo)
     * mainMapper�� �ִ� Issue ���
     * issue ����
     */
	@Override
	public int IssueCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".Issue", vo);
	}
	
	
	 /* (non-Javadoc)
    * @see kr.co.comes.projectA.dao.MainDAO#DefectCount(ListCriteria vo)
    * mainMapper�� �ִ� Defect ���
    * defect ����
    */
	@Override
	public int DefectCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".Defect", vo);
	}

}
