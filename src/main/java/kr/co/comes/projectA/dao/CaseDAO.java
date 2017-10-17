package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.CaseVO;
import kr.co.comes.projectA.dto.ListCriteria;

public interface CaseDAO {

    /**
     * case 목록
     * @return List<CaseVO>
     */
	public List<CaseVO> case_list(ListCriteria vo) throws Exception;
	
	public List<CaseVO> getReport(ListCriteria vo) throws Exception;

	/**
	 * 전체 case 목록 갯수(페이징을 위한 sql문)
	 * @return int
	 */
	public int case_listCount(ListCriteria vo) throws Exception;
	
	/**
	 * 해당 Case의 Project명,Phase명 검색
	 * @return CaseVO
	 */
	public CaseVO case_proj_ph_name(ListCriteria vo) throws Exception;
	
	/**
	 * 해당 projid의 abbr 검색
	 * @return String
	 */
	public String proj_abbr(int projid) throws Exception;
	
	/**
	 * 해당 phid의 abbr 검색
	 * @return String
	 */
	public String ph_abbr(ListCriteria vo) throws Exception;
	
	/**
	 * 해당 senid의 abbr 검색
	 * @return String
	 */
	public String senaabbr(ListCriteria vo) throws Exception;
	
	/**
	 * case 생성
	 * @return
	 */
	public void case_create(CaseVO vo) throws Exception;

	/**
	 * phase namelist 검색
	 * @return List<CaseVO>
	 */
	public List<CaseVO> ph_namelist(ListCriteria vo) throws Exception;

	/**
	 * 선택된 phase name 검색
	 * @return String 
	 */
	public String ph_name(ListCriteria vo) throws Exception;
	
	/**
	 * 자동증가 senid 값 구하기
	 * @return int
	 */
	public int senid(ListCriteria vo) throws Exception;
	
	/**
	 * case 상세
	 * @return CaseVO
	 */
	public CaseVO case_read(CaseVO vo) throws Exception;

	/**
	 * case 수정
	 * @return
	 */
	public void case_modify(CaseVO vo) throws Exception;
	
	/**
	 * case 삭제
	 * @return
	 */
	public void case_remove(CaseVO vo) throws Exception;
	/**
	 * 파일업로드 조회
	 * 
	 * @return
	 */
	public String getAttach(CaseVO vo) throws Exception;
	
	/**
	 * case 정보
	 * @return List<CaseVO>
	 */
	public List<CaseVO> getCase(CaseVO vo) throws Exception;
	
	/**
	 * user의 devid 값
	 * 
	 * @return int
	 */
	public int getDevid(String user) throws Exception;
	
	/**
	 * user의 devid 값의 name 값
	 * 
	 * @return String
	 */
	public String getDevName(int devid) throws Exception;
	
	/**
	 * user의 appid 값
	 * 
	 * @return int
	 */
	public int getAppid(String user) throws Exception;
	
	/**
	 * user의  appid 값의 name 값
	 * 
	 * @return String
	 */
	public String getAppName(int appid) throws Exception;

	

}// interface
