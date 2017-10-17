package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PhaseVO;

@Repository
public class PhaseDAOImpl implements PhaseDAO {

	@Inject
	private SqlSession session;

	//�������� ���� "kr.co.comes.projectA.phaseMapper"�� namespace ������ �����ؼ� ��� 
	private static String namespace = "kr.co.comes.projectA.phaseMapper";

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_list(ListCriteria vo)
     * phaseMapper�� �ִ� list ���
     * phase ���
     */
	@Override
	public List<PhaseVO> phase_list(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".list", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_listCount(ListCriteria vo)
     * phaseMapper�� �ִ� listCount ���
     * phase ��� ����
     */
	@Override
	public int phase_listCount(ListCriteria vo) throws Exception {
		return session.selectOne(namespace + ".listCount", vo);
	}
	

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_create(PhaseVO vo)
     * phaseMapper�� �ִ� create ���
     * phase ����
     */
	@Override
	public void phase_create(PhaseVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_skip(PhaseVO vo)
     * phaseMapper�� �ִ� skip ���
     * phase �������������� skip ��ư Ŭ���� phid = -1�� ����
     */
	@Override
	public void phase_skip(int projid) throws Exception {
		session.insert(namespace + ".skip", projid);
	}	

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_read(int phid)
     * phaseMapper�� �ִ� read ���
     * phase ��
     */
	@Override
	public PhaseVO phase_read(PhaseVO vo) throws Exception {
		return session.selectOne(namespace + ".read", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_modify(PhaseVO vo)
     * phaseMapper�� �ִ� modify ���
     * phase ����
     */
	@Override
	public void phase_modify(PhaseVO vo) throws Exception {
		session.update(namespace + ".modify", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_remove(int phid)
     * phaseMapper�� �ִ� remove ���
     * phase ����
     */
	@Override
	public void phase_remove(PhaseVO vo) throws Exception {
		session.delete(namespace + ".remove", vo);

	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#proj_namelist(ListCriteria vo)
     * phaseMapper�� �ִ� proj_namelist ���
     * project name list
     */
	@Override
	public List<PhaseVO> proj_namelist(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".proj_namelist", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#proj_name(int projid)
     * phaseMapper�� �ִ� proj_name ���
     * project name
     */
	@Override
	public String proj_name(int projid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".proj_name", projid);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.ProjectDAO#phase_phid(ListCriteria vo)
     * projectMapper�� �ִ� phid ���
     * phase id ���ϱ�
     */
	@Override
	public int phase_phid(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".phid", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#getAttach(PhaseVO vo)
     * phaseMapper�� �ִ� getAttach ���
     * ���Ͼ��ε� ��ȸ
     */
	@Override
	public String getAttach(PhaseVO vo) throws Exception {
		
		return session.selectOne(namespace+".getAttach", vo);	
		
	}
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#getPhase(PhaseVO vo)
     * PhaseMapper�� �ִ� getPhase ���
     * Phase ���� �޾ƿ���
     */
	@Override
	public List<PhaseVO> getPhase(PhaseVO vo) throws Exception {
		return session.selectList(namespace + ".getPhase", vo);
	}

}
