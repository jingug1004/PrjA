package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ProjectVO;
import kr.co.comes.projectA.dto.UseProjectVO;

public interface ProjectDAO {

    /**
     * 프로젝트 목록
     * @return List<ProjectVO>
     */
	public List<ProjectVO> project_list(ListCriteria vo) throws Exception;

	/**
	 * 전체 프로젝트 목록 갯수(페이징을 위한 sql문)
	 * @return int
	 */
	public int project_listCount(ListCriteria vo) throws Exception;
	/**
	 * 프로젝트 생성
	 * @return
	 */
	public void project_create(ProjectVO vo) throws Exception;

	/**
	 * 프로젝트 상세
	 * @return ProjectVO
	 */
	public ProjectVO project_read(int projid) throws Exception;
	
	/**
	 * 프로젝트 id 구하기
	 * @return int
	 */
	public int project_projid(String User) throws Exception;

	/**
	 * 프로젝트 수정
	 * @return
	 */
	public void project_modify(ProjectVO vo) throws Exception;
	
	/**
	 * 프로젝트 삭제
	 * @return
	 */
	public void project_remove(int projid) throws Exception;
	
	/**
	 * 파일업로드 조회
	 * 
	 * @return
	 */
	public String getAttach(int projid) throws Exception;
	
	/**
	 * useproj insert
	 * 
	 * @return
	 */
	public void useproj_create(UseProjectVO vo) throws Exception;
	
	/**
	 * 참여자 구하기
	 * 
	 * @return List<String>
	 */
	public List<String> paticipant(int projid) throws Exception;
	
	/**
	 * 책임자 구하기
	 * 
	 * @return String
	 */
	public String charge(int projid) throws Exception;
	
	/**
	 * 책임자 update
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


}// interface
