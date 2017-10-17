package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.ResultDAO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ReplayVO;
import kr.co.comes.projectA.dto.ResourceVO;
import kr.co.comes.projectA.dto.ResultVO;

@Service
public class ResultServiceImpl implements ResultService {

	@Inject
	private ResultDAO dao;

	/*
	 * �׽�Ʈ ��� ��� ResultDAO�� �ִ� result_list(ListCriteria vo) ���
	 */
	@Override
	public List<ResultVO> result_list(ListCriteria vo) throws Exception {
		return dao.result_list(vo);
	}

	/*
	 * �׽�Ʈ ��� ��� ���� ResultDAO�� �ִ� result_listCount(ResultCriteria vo) ���
	 */
	@Override
	public int result_listCount(ListCriteria vo) throws Exception {
		return dao.result_listCount(vo);
	}

	/*
	 * �׽�Ʈ �����̷� �� ResultDAO�� �ִ� read(ResultVO vo)���
	 */
	@Override
	public ResultVO read(ResultVO vo) throws Exception {
		return dao.read(vo);
	}

	/*
	 * �׽�Ʈ ��� ���� ResultDAO�� �ִ� result_create(ResultVO vo)���
	 */	
	@Override
	public void result_create(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.result_create(vo);
	}

	
	/*
	 * �׽�Ʈ �����̷� �� ResultDAO�� �ִ� nameSelect(ListCriteria vo)���
	 */
	@Override
	public ResultVO nameSelect(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.nameSelect(vo);
	}

	/*
	 * �ش� �ó����� Filename �� ���ϱ� ResultDAO�� �ִ� getFilename(ListCriteria vo)���
	 */
	@Override
	public String getFilename(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getFilename(vo);
	}

	/*
	 * �׽�Ʈ ��� ���� ResultDAO�� �ִ� result_modify(ResultVO vo)���
	 */
	@Override
	public void result_modify(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.result_modify(vo);

	}

	/*
	 * ���Ͼ��ε� ��ȸ ResultDAO�� �ִ� getAttach(ResultVO vo) ���
	 */
	@Override
	public String getAttach(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAttach(vo);
	}

	/*
	 * �׽�Ʈ ��� ���� ResultDAO�� �ִ� result_remove(ResultVO vo) ���
	 */
	@Override
	public void result_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.result_remove(vo);
	}

	/*
	 *�ش� �ó������� �׽�Ʈ ��� ���ϱ� ResultDAO�� �ִ� getResult(ResultVO vo) ���
	 */
	@Override
	public List<ResultVO> getResult(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getResult(vo);
	}

	
	/*
	 * ������ resid ���ϱ� ResultDAO�� �ִ� getResid(ResultVO vo) ���
	 */
	
	@Override
	public int getResid(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getResid(vo);
	}
	

	/*
	 * ResultDAO�� �ִ�  replay_create(ReplayVO vo) ���
	 * ���÷��� ��� ����
	 */
	@Override
	public void replay_create(ReplayVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.replay_create(vo);
	}

	/*
	 * ResultDAO�� �ִ� replay_remove(ResultVO vo)
	 * ���÷��� ��� ����
	 */
	@Override
	public void replay_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.replay_remove(vo);
	}

	/*
	 * ResultDAO�� �ִ� getReplay(ResultVO vo)
	 * �ش� �ó������� ���÷��� ��� ���ϱ�
	 */
	@Override
	public List<String> getReplay(ReplayVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getReplay(vo);
	}
	
	
    /**
     * �ش� �׽�Ʈ ����� �ݺ�Ƚ�� �� ���ϱ� 
     * @return ReplayVO
     */
	public int getRepeatNum(ResultVO vo)throws Exception{
		return dao.getRepeatNum(vo);
	}
	
	/*
	 * ResultDAO�� �ִ� resource_create(ResourceVO vo) 
	 * ���ҽ� ����
	 */
	@Override
	public void resource_create(ResourceVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.resource_create(vo);
	}

	/*
	 * ResultDAO�� �ִ� resource_remove(ResultVO vo)
	 * ���ҽ� ����
	 */
	@Override
	public void resource_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.resource_remove(vo);
	}

	/*
	 * ResultDAO�� �ִ� getReource(ResultVO vo)
	 * �ش� �ó������� ���ҽ� ���ϱ�
	 */
	@Override
	public ResourceVO getReource(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getReource(vo);
	}
}
