package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PhaseVO;

public interface PhaseService {

    /**
     * 프로젝트 목록
     * @return List<PhaseVO>
     */
	public List<PhaseVO> phase_list(ListCriteria vo) throws Exception;
	
	/**
	 * phase 목록 갯수
	 * @return int
	 */
	public int phase_listCount(ListCriteria vo) throws Exception;

	/**
	 * phase 생성
	 * @return
	 */
	public void phase_create(PhaseVO vo) throws Exception;
	
	/**
	 * phase skip
	 * @return int 
	 */
	public void phase_skip(int projid) throws Exception;
	
	/**
	 * project namelist 검색
	 * @return
	 */
	public List<PhaseVO> proj_namelist(ListCriteria vo) throws Exception;

	/**
	 * 선택된 project name 검색
	 * @return
	 */
	public String proj_name(int projid) throws Exception;

	/**
	 * phase id 구하기
	 * @return int
	 */
	public int phase_phid(ListCriteria vo) throws Exception;
	
	/**
	 * phase 상세
	 * @return PhaseVO
	 */
	public PhaseVO phase_read(PhaseVO vo) throws Exception;
	
	/**
	 * phase 수정
	 * @return
	 */
	public void phase_modify(PhaseVO vo) throws Exception;
	
	/**
	 * phase 삭제
	 * @return
	 */
	public void phase_remove(PhaseVO vo) throws Exception;
	
	/**
	 * 파일업로드 조회
	 * 
	 * @return String
	 */
	public String getAttach(PhaseVO vo) throws Exception;
	
	/**
	 * Phase 정보
	 * @return List<PhaseVO>
	 */
	public List<PhaseVO> getPhase(PhaseVO vo) throws Exception;

}
