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
    * 프로젝트 목록
    * ProjectDAO에 있는 project_list(ListCriteria vo) 사용
    */
	@Override
	public List<ProjectVO> project_list(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_list(vo);
	}

	/*
	    * 프로젝트 목록 갯수
	    * ProjectDAO에 있는 project_listCount(ListCriteria vo) 사용
	    */
	@Override
	public int project_listCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_listCount(vo);
	}
	
	/*
	    * 프로젝트 생성
	    * ProjectDAO에 있는 project_create(ProjectVO vo) 사용
	    */
	@Override
	public void project_create(ProjectVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.project_create(vo);

	}

	/*
	    * 프로젝트 상세
	    * ProjectDAO에 있는 project_read(int projid) 사용
	    */
	@Override
	public ProjectVO project_read(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_read(projid);
	}

	/*
	    * 프로젝트 수정
	    * ProjectDAO에 있는 project_modify(ProjectVO vo) 사용
	    */
	@Override
	public void project_modify(ProjectVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.project_modify(vo);
	}

	/*
	    * 프로젝트 삭제
	    * ProjectDAO에 있는 project_remove(int projid) 사용
	    */
	@Override
	public void project_remove(int projid) throws Exception {
		// TODO Auto-generated method stub
		dao.project_remove(projid);

	}
	
	/*
	    * 프로젝트 id 구하기
	    * ProjectDAO에 있는 project_projid(ListCriteria vo) 사용
	    */
	@Override
	public int project_projid(String User) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_projid(User);
	}
	
	/*
	 * 파일업로드 조회 ProjectDAO에 있는 getAttach(ProjectVO vo) 사용
	 */
	@Override
	public String getAttach(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAttach(projid);
	}

	/*
	 * useproj create ProjectDAO에 있는 useproj_create(UseProjectVO vo) 사용
	 */
	@Override
	public void useproj_create(UseProjectVO vo) throws Exception{
		dao.useproj_create(vo);
	}

	/*
	 * 참여자 구하기
	 *  ProjectDAO에 있는 paticipant(int projid) 사용
	 */
	@Override
	public List<String> paticipant(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.paticipant(projid);
	}

	/*
	 * 책임자 구하기
	 *  ProjectDAO에 있는 charge(int projid) 사용
	 */
	@Override
	public String charge(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.charge(projid);
	}
	
	/*
	 * 책임자 update
	 *  ProjectDAO에 있는 charge_update(UseProjectVO vo) 사용
	 */
	@Override
	public void charge_update(UseProjectVO vo) throws Exception{
		dao.charge_update(vo);
	}
	
	/*
	 * useproj delete 
	 * ProjectDAO에 있는 useproj_delete(UseProjectVO vo) 사용
	 */
	@Override
	public void useproj_delete(UseProjectVO vo) throws Exception{
		dao.useproj_delete(vo);
	}


}
