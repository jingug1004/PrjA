package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PhaseVO;

public interface PhaseService {

    /**
     * ������Ʈ ���
     * @return List<PhaseVO>
     */
	public List<PhaseVO> phase_list(ListCriteria vo) throws Exception;
	
	/**
	 * phase ��� ����
	 * @return int
	 */
	public int phase_listCount(ListCriteria vo) throws Exception;

	/**
	 * phase ����
	 * @return
	 */
	public void phase_create(PhaseVO vo) throws Exception;
	
	/**
	 * phase skip
	 * @return int 
	 */
	public void phase_skip(int projid) throws Exception;
	
	/**
	 * project namelist �˻�
	 * @return
	 */
	public List<PhaseVO> proj_namelist(ListCriteria vo) throws Exception;

	/**
	 * ���õ� project name �˻�
	 * @return
	 */
	public String proj_name(int projid) throws Exception;

	/**
	 * phase id ���ϱ�
	 * @return int
	 */
	public int phase_phid(ListCriteria vo) throws Exception;
	
	/**
	 * phase ��
	 * @return PhaseVO
	 */
	public PhaseVO phase_read(PhaseVO vo) throws Exception;
	
	/**
	 * phase ����
	 * @return
	 */
	public void phase_modify(PhaseVO vo) throws Exception;
	
	/**
	 * phase ����
	 * @return
	 */
	public void phase_remove(PhaseVO vo) throws Exception;
	
	/**
	 * ���Ͼ��ε� ��ȸ
	 * 
	 * @return String
	 */
	public String getAttach(PhaseVO vo) throws Exception;
	
	/**
	 * Phase ����
	 * @return List<PhaseVO>
	 */
	public List<PhaseVO> getPhase(PhaseVO vo) throws Exception;

}
