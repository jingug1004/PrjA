package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.PhaseDAO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PhaseVO;

@Service
public class PhaseServiceImpl implements PhaseService {

	@Inject
	private PhaseDAO dao;

	/*
	 * phase ��� PhaseDAO�� �ִ� phase_list(ListCriteria vo) ���
	 */
	@Override
	public List<PhaseVO> phase_list(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.phase_list(vo);
	}

	/*
	 * phase ��� ���� PhaseDAO�� �ִ� phase_listCount(ListCriteria vo) ���
	 */
	@Override
	public int phase_listCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.phase_listCount(vo);
	}

	/*
	 * phase ���� PhaseDAO�� �ִ� phase_create(PhaseVO vo) ���
	 */
	@Override
	public void phase_create(PhaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.phase_create(vo);

	}

	/*
	 * phase �������������� skip ��ư Ŭ���� phid = -1�� ���� PhaseDAO�� �ִ� phase_skip(int
	 * projid) ���
	 */
	@Override
	public void phase_skip(int projid) throws Exception {
		dao.phase_skip(projid);
	}

	/*
	 * phase �� PhaseDAO�� �ִ� phase_read(PhaseVO vo) ���
	 */
	@Override
	public PhaseVO phase_read(PhaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.phase_read(vo);
	}

	/*
	 * phase ���� PhaseDAO�� �ִ� phase_modify(PhaseVO vo) ���
	 */
	@Override
	public void phase_modify(PhaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.phase_modify(vo);
	}

	/*
	 * phase ���� PhaseDAO�� �ִ� phase_remove(PhaseVO vo) ���
	 */
	@Override
	public void phase_remove(PhaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.phase_remove(vo);

	}

	/*
	 * project namelist �ҷ����� PhaseDAO�� �ִ� proj_namelist(ListCriteria vo) ���
	 */
	@Override
	public List<PhaseVO> proj_namelist(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.proj_namelist(vo);
	}

	/*
	 * project name �ҷ����� PhaseDAO�� �ִ� proj_name(int projid) ���
	 */
	@Override
	public String proj_name(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.proj_name(projid);
	}
	
	/*
	 * ������Ʈ id ���ϱ� PhaseDAO�� �ִ� phase_phid(ListCriteria vo) ���
	 */
	@Override
	public int phase_phid(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.phase_phid(vo);
	}
	
	/*
	 * ���Ͼ��ε� ��ȸ PhaseDAO�� �ִ� getAttach(PhaseVO vo) ���
	 */
	@Override
	public String getAttach(PhaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAttach(vo);
	}

	/*
	 * Phase �� PhaseDAO�� �ִ� getPhase(PhaseVO vo) ���
	 */
	@Override
	public List<PhaseVO> getPhase(PhaseVO vo) throws Exception {
		return dao.getPhase(vo);
	}
	

	
}
