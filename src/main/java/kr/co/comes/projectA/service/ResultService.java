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
	 * result 상세
	 * 
	 * @return ResultVO
	 */
	public ResultVO read(ResultVO vo) throws Exception;
	
    /**
     * result 생성
     * @return
     */
	public void result_create(ResultVO vo) throws Exception;
	
	/**
	 * 테스트 결과 수정
	 * @return
	 */
	public void result_modify(ResultVO vo) throws Exception;
	
	/**
	 * 테스트 결과 삭제
	 * @return
	 */
	public void result_remove(ResultVO vo) throws Exception;
	
    /**
     * 테스트 결과 nameSelect
     * @return ResultVO
     */
	public ResultVO nameSelect(ListCriteria vo)throws Exception;
	
    /**
     * 해당 시나리오 Filename 값 구하기
     * @return String
     */
	public String getFilename(ListCriteria vo)throws Exception;
	
    /**
     * 해당 시나리오의 테스트 결과 구하기
     * @return List<ResultVO>
     */
	public List<ResultVO> getResult(ResultVO vo)throws Exception;
	
	/**
	 * 파일업로드 조회
	 * 
	 * @return String
	 */
	public String getAttach(ResultVO vo) throws Exception;
	
    /**
     * 마지막 resid 구하기
     * @return int
     */
	public int getResid(ResultVO vo)throws Exception;
	
	 /**
    * 리플레이 결과 생성
    * @return 
    */
	public void replay_create(ReplayVO vo) throws Exception;
	
   /**
    * 리플레이 결과 제거
    * @return 
    */
	public void replay_remove(ResultVO vo) throws Exception;
	
	
    /**
     * 해당 시나리오의 리플레이 결과 구하기
     * @return ReplayVO
     */
	public List<String> getReplay(ReplayVO vo)throws Exception;
	
    /**
     * 해당 테스트 결과의 반복횟수 값 구하기 
     * @return int
     */
	public int getRepeatNum(ResultVO vo)throws Exception;
	
    /**
     * 리소스 생성
     * @return 
     */
	public void resource_create(ResourceVO vo) throws Exception;
	
    /**
     * 리소스 제거
     * @return 
     */
	public void resource_remove(ResultVO vo) throws Exception;
	
    /**
     * 해당 시나리오의 모니터링 결과 구하기
     * @return ResourceVO
     */
	public ResourceVO getReource(ResultVO vo)throws Exception;
	
}
