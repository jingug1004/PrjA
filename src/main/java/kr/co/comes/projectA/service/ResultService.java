package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ReplayVO;
import kr.co.comes.projectA.dto.ResourceVO;
import kr.co.comes.projectA.dto.ResultVO;

public interface ResultService {


	/**
	 * result list
	 * 
	 * @return List<ResultVO>
	 */
	public List<ResultVO> result_list(ListCriteria vo) throws Exception;

	/**
	 * result list count
	 * 
	 * @return int
	 */
	public int result_listCount(ListCriteria vo) throws Exception;

	/**
	 * result ��
	 * 
	 * @return ResultVO
	 */
	public ResultVO read(ResultVO vo) throws Exception;
	
    /**
     * result ����
     * @return
     */
	public void result_create(ResultVO vo) throws Exception;
	
	/**
	 * �׽�Ʈ ��� ����
	 * @return
	 */
	public void result_modify(ResultVO vo) throws Exception;
	
	/**
	 * �׽�Ʈ ��� ����
	 * @return
	 */
	public void result_remove(ResultVO vo) throws Exception;
	
    /**
     * �׽�Ʈ ��� nameSelect
     * @return ResultVO
     */
	public ResultVO nameSelect(ListCriteria vo)throws Exception;
	
    /**
     * �ش� �ó����� Filename �� ���ϱ�
     * @return String
     */
	public String getFilename(ListCriteria vo)throws Exception;
	
    /**
     * �ش� �ó������� �׽�Ʈ ��� ���ϱ�
     * @return List<ResultVO>
     */
	public List<ResultVO> getResult(ResultVO vo)throws Exception;
	
	/**
	 * ���Ͼ��ε� ��ȸ
	 * 
	 * @return String
	 */
	public String getAttach(ResultVO vo) throws Exception;
	
    /**
     * ������ resid ���ϱ�
     * @return int
     */
	public int getResid(ResultVO vo)throws Exception;
	
	 /**
    * ���÷��� ��� ����
    * @return 
    */
	public void replay_create(ReplayVO vo) throws Exception;
	
   /**
    * ���÷��� ��� ����
    * @return 
    */
	public void replay_remove(ResultVO vo) throws Exception;
	
	
    /**
     * �ش� �ó������� ���÷��� ��� ���ϱ�
     * @return ReplayVO
     */
	public List<String> getReplay(ReplayVO vo)throws Exception;
	
    /**
     * �ش� �׽�Ʈ ����� �ݺ�Ƚ�� �� ���ϱ� 
     * @return int
     */
	public int getRepeatNum(ResultVO vo)throws Exception;
	
    /**
     * ���ҽ� ����
     * @return 
     */
	public void resource_create(ResourceVO vo) throws Exception;
	
    /**
     * ���ҽ� ����
     * @return 
     */
	public void resource_remove(ResultVO vo) throws Exception;
	
    /**
     * �ش� �ó������� ����͸� ��� ���ϱ�
     * @return ResourceVO
     */
	public ResourceVO getReource(ResultVO vo)throws Exception;
	
}
