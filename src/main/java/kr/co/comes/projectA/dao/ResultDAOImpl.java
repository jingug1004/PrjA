package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ReplayVO;
import kr.co.comes.projectA.dto.ResourceVO;
import kr.co.comes.projectA.dto.ResultVO;

@Repository
public class ResultDAOImpl implements ResultDAO {

	@Inject
	private SqlSession session;

	// �������� ���� "kr.co.comes.projectA.resultMapper"�� namespace ������ �����ؼ� ���
	private static String namespace = "kr.co.comes.projectA.resultMapper";

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#result_list(ListCriteria vo)
	 * resultMapper�� �ִ� list ��� �׽�Ʈ ��� ���
	 */
	@Override
	public List<ResultVO> result_list(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".list", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#result_listCount(ListCriteria vo)
	 * resultMapper�� �ִ� count ��� �׽�Ʈ ��� ��� ����
	 */
	@Override
	public int result_listCount(ListCriteria vo) throws Exception {
		return session.selectOne(namespace + ".count", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#result_create(ResultVO vo)
	 * resultMapper�� �ִ� create ��� �׽�Ʈ ��� ����
	 */
	@Override
	public void result_create(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#read(ResultVO vo) resultMapper��
	 * �ִ� read ��� �׽�Ʈ �����̷� ��
	 */
	@Override
	public ResultVO read(ResultVO vo) throws Exception {
		return session.selectOne(namespace + ".read", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#nameSelect(ResultVO vo)
	 * resultMapper�� �ִ� nameSelect ��� �׽�Ʈ �����̷� ��
	 */
	@Override
	public ResultVO nameSelect(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".nameSelect", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getParam(ListCriteria vo)
	 * resultMapper�� �ִ� getFilename ��� �ش� �ó����� Filename �� ���ϱ�
	 */
	@Override
	public String getFilename(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".getFilename", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#result_modify(ResultVO vo)
	 * resultMapper�� �ִ� modify ��� �׽�Ʈ ��� ����
	 */
	@Override
	public void result_modify(ResultVO vo) throws Exception {
		session.update(namespace + ".modify", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getAttach(ResultVO vo)
	 * resultMapper�� �ִ� getAttach ��� ���Ͼ��ε� ��ȸ
	 */
	@Override
	public String getAttach(ResultVO vo) throws Exception {

		return session.selectOne(namespace + ".getAttach", vo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#result_remove(ResultVO vo)
	 * resultMapper�� �ִ� result_remove ��� ��� ����
	 */
	@Override
	public void result_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace + ".remove", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getResult(ResultVO vo)
	 * resultMapper�� �ִ� getResult ��� �ش� �ó������� �׽�Ʈ ��� ���ϱ�
	 */
	@Override
	public List<ResultVO> getResult(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".getResult", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getResid(ResultVO vo)
	 * resultMapper�� �ִ�getResid ��� ������ resid ���ϱ�
	 */
	@Override
	public int getResid(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".getResid", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#resource_create(ReplayVO vo)
	 * resultMapper�� �ִ� resource_create ��� / ���÷��� ��� ����
	 */
	@Override
	public void replay_create(ReplayVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".replay_create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#replay_remove(ResultVO vo)
	 * resultMapper�� �ִ� replay_remove ���/ ���÷��� ��� ����
	 */
	@Override
	public void replay_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace + ".replay_remove", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getReplay(ReplayVO vo)
	 * resultMapper�� �ִ� getReplay ��� �ش� �ó������� ���÷��� ��� ���ϱ�
	 */
	@Override
	public List<String> getReplay(ReplayVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".getReplay", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getRepeatNum(ResultVO vo)
	 * resultMapper�� �ִ� getRepeatNum ��� 
	 * �ش� �׽�Ʈ ����� �ݺ�Ƚ�� �� ���ϱ�
	 */
	@Override
	public int getRepeatNum(ResultVO vo)throws Exception {
		return session.selectOne(namespace + ".getRepeatNum", vo);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#resource_create(ResourceVO vo)
	 * resultMapper�� �ִ� resource_create ��� ���ҽ� ����
	 */
	@Override
	public void resource_create(ResourceVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".resource_create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#resource_remove(ResultVO vo)
	 * resultMapper�� �ִ� resource_remove ��� ���ҽ� ��
	 */
	@Override
	public void resource_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace + ".resource_remove", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getResource(ResultVO vo)
	 * resultMapper�� �ִ� getResource ��� �ش� �ó������� ����͸� ��� ���ϱ�
	 */
	@Override
	public ResourceVO getReource(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".getResource", vo);
	}

}
