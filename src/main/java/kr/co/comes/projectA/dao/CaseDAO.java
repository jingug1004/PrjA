package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.CaseVO;
import kr.co.comes.projectA.dto.ListCriteria;

public interface CaseDAO {

    /**
     * case ���
     * @return List<CaseVO>
     */
	public List<CaseVO> case_list(ListCriteria vo) throws Exception;
	
	public List<CaseVO> getReport(ListCriteria vo) throws Exception;

	/**
	 * ��ü case ��� ����(����¡�� ���� sql��)
	 * @return int
	 */
	public int case_listCount(ListCriteria vo) throws Exception;
	
	/**
	 * �ش� Case�� Project��,Phase�� �˻�
	 * @return CaseVO
	 */
	public CaseVO case_proj_ph_name(ListCriteria vo) throws Exception;
	
	/**
	 * �ش� projid�� abbr �˻�
	 * @return String
	 */
	public String proj_abbr(int projid) throws Exception;
	
	/**
	 * �ش� phid�� abbr �˻�
	 * @return String
	 */
	public String ph_abbr(ListCriteria vo) throws Exception;
	
	/**
	 * �ش� senid�� abbr �˻�
	 * @return String
	 */
	public String senaabbr(ListCriteria vo) throws Exception;
	
	/**
	 * case ����
	 * @return
	 */
	public void case_create(CaseVO vo) throws Exception;

	/**
	 * phase namelist �˻�
	 * @return List<CaseVO>
	 */
	public List<CaseVO> ph_namelist(ListCriteria vo) throws Exception;

	/**
	 * ���õ� phase name �˻�
	 * @return String 
	 */
	public String ph_name(ListCriteria vo) throws Exception;
	
	/**
	 * �ڵ����� senid �� ���ϱ�
	 * @return int
	 */
	public int senid(ListCriteria vo) throws Exception;
	
	/**
	 * case ��
	 * @return CaseVO
	 */
	public CaseVO case_read(CaseVO vo) throws Exception;

	/**
	 * case ����
	 * @return
	 */
	public void case_modify(CaseVO vo) throws Exception;
	
	/**
	 * case ����
	 * @return
	 */
	public void case_remove(CaseVO vo) throws Exception;
	/**
	 * ���Ͼ��ε� ��ȸ
	 * 
	 * @return
	 */
	public String getAttach(CaseVO vo) throws Exception;
	
	/**
	 * case ����
	 * @return List<CaseVO>
	 */
	public List<CaseVO> getCase(CaseVO vo) throws Exception;
	
	/**
	 * user�� devid ��
	 * 
	 * @return int
	 */
	public int getDevid(String user) throws Exception;
	
	/**
	 * user�� devid ���� name ��
	 * 
	 * @return String
	 */
	public String getDevName(int devid) throws Exception;
	
	/**
	 * user�� appid ��
	 * 
	 * @return int
	 */
	public int getAppid(String user) throws Exception;
	
	/**
	 * user��  appid ���� name ��
	 * 
	 * @return String
	 */
	public String getAppName(int appid) throws Exception;

	

}// interface
