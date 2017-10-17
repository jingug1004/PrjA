package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.ProjectDAO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ProjectVO;
import kr.co.comes.projectA.dto.UseProjectVO;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Inject
	private ProjectDAO dao;
	
	/*
    * ������Ʈ ���
    * ProjectDAO�� �ִ� project_list(ListCriteria vo) ���
    */
	@Override
	public List<ProjectVO> project_list(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_list(vo);
	}

	/*
	    * ������Ʈ ��� ����
	    * ProjectDAO�� �ִ� project_listCount(ListCriteria vo) ���
	    */
	@Override
	public int project_listCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_listCount(vo);
	}
	
	/*
	    * ������Ʈ ����
	    * ProjectDAO�� �ִ� project_create(ProjectVO vo) ���
	    */
	@Override
	public void project_create(ProjectVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.project_create(vo);

	}

	/*
	    * ������Ʈ ��
	    * ProjectDAO�� �ִ� project_read(int projid) ���
	    */
	@Override
	public ProjectVO project_read(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_read(projid);
	}

	/*
	    * ������Ʈ ����
	    * ProjectDAO�� �ִ� project_modify(ProjectVO vo) ���
	    */
	@Override
	public void project_modify(ProjectVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.project_modify(vo);
	}

	/*
	    * ������Ʈ ����
	    * ProjectDAO�� �ִ� project_remove(int projid) ���
	    */
	@Override
	public void project_remove(int projid) throws Exception {
		// TODO Auto-generated method stub
		dao.project_remove(projid);

	}
	
	/*
	    * ������Ʈ id ���ϱ�
	    * ProjectDAO�� �ִ� project_projid(ListCriteria vo) ���
	    */
	@Override
	public int project_projid(String User) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_projid(User);
	}
	
	/*
	 * ���Ͼ��ε� ��ȸ ProjectDAO�� �ִ� getAttach(ProjectVO vo) ���
	 */
	@Override
	public String getAttach(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAttach(projid);
	}

	/*
	 * useproj create ProjectDAO�� �ִ� useproj_create(UseProjectVO vo) ���
	 */
	@Override
	public void useproj_create(UseProjectVO vo) throws Exception{
		dao.useproj_create(vo);
	}

	/*
	 * ������ ���ϱ�
	 *  ProjectDAO�� �ִ� paticipant(int projid) ���
	 */
	@Override
	public List<String> paticipant(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.paticipant(projid);
	}

	/*
	 * å���� ���ϱ�
	 *  ProjectDAO�� �ִ� charge(int projid) ���
	 */
	@Override
	public String charge(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.charge(projid);
	}
	
	/*
	 * å���� update
	 *  ProjectDAO�� �ִ� charge_update(UseProjectVO vo) ���
	 */
	@Override
	public void charge_update(UseProjectVO vo) throws Exception{
		dao.charge_update(vo);
	}
	
	/*
	 * useproj delete 
	 * ProjectDAO�� �ִ� useproj_delete(UseProjectVO vo) ���
	 */
	@Override
	public void useproj_delete(UseProjectVO vo) throws Exception{
		dao.useproj_delete(vo);
	}


}
