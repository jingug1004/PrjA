package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.CaseVO;
import kr.co.comes.projectA.dto.ListCriteria;

@Repository
public class CaseDAOImpl implements CaseDAO {

	@Inject
	private SqlSession session;

	//�������� ���� "kr.co.comes.projectA.caseMapper"�� namespace ������ �����ؼ� ��� 
	private static String namespace = "kr.co.comes.projectA.caseMapper";

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_list(ListCriteria vo)
     * caseMapper�� �ִ� list ���
     * ������Ʈ ���
     */
	@Override
	public List<CaseVO> case_list(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".list", vo);
	}
	
	@Override
	public List<CaseVO> getReport(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".getreport", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_listCount(ListCriteria vo)
     * caseMapper�� �ִ� listCount ���
     * ������Ʈ ��� ����
     */
	@Override
	public int case_listCount(ListCriteria vo) throws Exception {
		return session.selectOne(namespace + ".listCount", vo);
	}
	
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_proj_ph_name(ListCriteria vo)
     * caseMapper�� �ִ� proj_ph_name ���
     * �ش� Case�� Project��,Phase�� �˻�
     */
	@Override
	public CaseVO case_proj_ph_name(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".proj_ph_name", vo);
	}
	
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#proj_abbr(int projid)
     * caseMapper�� �ִ� proj_abbr ���
     * �ش� projid�� abbr �˻�
     */
	@Override
	public String proj_abbr(int projid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".proj_abbr", projid);
	}
	
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#ph_abbr(ListCriteria vo)
     * caseMapper�� �ִ� ph_abbr ���
     * �ش� phid�� abbr �˻�
     */
	@Override
	public String ph_abbr(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".ph_abbr", vo);
	}
	
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#senaabbr(ListCriteria vo)
     * caseMapper�� �ִ� proj_abbr ���
     * �ش� senid�� abbr �˻�
     */
	@Override
	public String senaabbr(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".senaabbr", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_create(CaseVO vo)
     * caseMapper�� �ִ� create ���
     * case ����
     */
	@Override
	public void case_create(CaseVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#proj_namelist(ListCriteria vo)
     * caseMapper�� �ִ� ph_namelist ���
     * ������� phase �� ��� ���ϱ�
     */
	@Override
	public List<CaseVO> ph_namelist(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".ph_namelist", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#proj_name(ListCriteria vo)
     * caseMapper�� �ִ� ph_name ���
     * ���õ� phase �̸� select
     */
	@Override
	public String ph_name(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".ph_name", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#senid(ListCriteria vo)
     * caseMapper�� �ִ� senid ���
     * ������Ʈ ��
     */
	 @Override
	public int senid(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		 return session.selectOne(namespace + ".senid", vo);
	}

	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_read(CaseVO vo)
     * caseMapper�� �ִ� read ���
     * ������Ʈ ��
     */
	@Override
	public CaseVO case_read(CaseVO vo) throws Exception {
		return session.selectOne(namespace + ".read", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_modify(CaseVO vo)
     * caseMapper�� �ִ� modify ���
     * ������Ʈ ����
     */
	@Override
	public void case_modify(CaseVO vo) throws Exception {
		session.update(namespace + ".modify", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_remove(CaseVO vo)
     * caseMapper�� �ִ� remove ���
     * ������Ʈ ����
     */
	@Override
	public void case_remove(CaseVO vo) throws Exception {
		session.delete(namespace + ".remove", vo);

	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getAttach(CaseVO vo)
     * caseMapper�� �ִ� getAttach ���
     * ���Ͼ��ε� ��ȸ
     */
	@Override
	public String getAttach(CaseVO vo) throws Exception {
		
		return session.selectOne(namespace+".getAttach", vo);	
		
	}
	
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getCase(CaseVO vo)
     * CaseMapper�� �ִ� getCase ���
     * case ���� �޾ƿ���
     */
	@Override
	public List<CaseVO> getCase(CaseVO vo) throws Exception {
		return session.selectList(namespace + ".getCase", vo);
	}

	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getDevid(String user)
     * CaseMapper�� �ִ� getDevid ���
     * user�� devid ��
     */
	@Override
	public int getDevid(String user) throws Exception {
		return session.selectOne(namespace+".getDevid", user);
	}

	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getAppid(String user)
     * CaseMapper�� �ִ� getAppid ���
     * user�� appid ��
     */
	@Override
	public int getAppid(String user) throws Exception {
		return session.selectOne(namespace+".getAppid", user);
	}

	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getDevName(int devid)
     * CaseMapper�� �ִ� getDevName ���
     * user�� devid ���� name
     */
	@Override
	public String getDevName(int devid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".getDevName", devid);
	}

	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getAppName(int appid)
     * CaseMapper�� �ִ� getAppName ���
     * user�� appid ���� name
     */
	@Override
	public String getAppName(int appid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".getAppName", appid);
	}
	



}
