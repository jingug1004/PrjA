package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ProjectVO;
import kr.co.comes.projectA.dto.UseProjectVO;

public interface ProjectService {

    /**
     * ������Ʈ ���
     * @return List<ProjectVO>
     */
	public List<ProjectVO> project_list(ListCriteria vo) throws Exception;
	
	/**
	 * ������Ʈ ��� ����
	 * @return int
	 */
	public int project_listCount(ListCriteria vo) throws Exception;
	/**
	 * ������Ʈ ����
	 * @return
	 */
	public void project_create(ProjectVO vo) throws Exception;
	
	/**
	 * ������Ʈ ��
	 * @return ProjectVO
	 */
	public ProjectVO project_read(int projid) throws Exception;
	
	/**
	 * ������Ʈ id ���ϱ�
	 * @return int
	 */
	public int project_projid(String User) throws Exception;
	
	/**
	 * ������Ʈ ����
	 * @return
	 */
	public void project_modify(ProjectVO vo) throws Exception;
	
	/**
	 * ������Ʈ ����
	 * @return
	 */
	public void project_remove(int projid) throws Exception;
	
	/**
	 * ���Ͼ��ε� ��ȸ
	 * 
	 * @return String
	 */
	public String getAttach(int projid) throws Exception;
	
	
	/**
	 * useproj insert
	 * 
	 * @return
	 */
	public void useproj_create(UseProjectVO vo) throws Exception;
	
	/**
	 * ������ ���ϱ�
	 * 
	 * @return List<String>
	 */
	public List<String> paticipant(int projid) throws Exception;
	
	/**
	 * å���� ���ϱ�
	 * 
	 * @return String
	 */
	public String charge(int projid) throws Exception;
	
	/**
	 * å����  ����
	 * 
	 * @return
	 */
	public void charge_update(UseProjectVO vo) throws Exception;
	
	/**
	 * useproj delete
	 * 
	 * @return
	 */
	public void useproj_delete(UseProjectVO vo) throws Exception;

}
