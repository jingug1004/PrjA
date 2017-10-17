package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.AppVO;
import kr.co.comes.projectA.dto.ListCriteria;

public interface AppDAO {
	
	/**
	 * @param vo
	 * @return AppVO 목록 불러오기
	 * @throws Exception
	 */
	public List<AppVO> appList(ListCriteria vo) throws Exception;

	/**
	 * @param vo
	 * 앱 추가
	 */
	public void insertApp(AppVO vo);

	/**
	 * @param appid
	 * id값을 기준으로 app 정보 1줄
	 * @return
	 */
	public AppVO viewApp(int appid);

	/**
	 * @param appid
	 * 앱 삭제
	 */
	public void deleteApp(int appid);

	/**
	 * @param vo
	 * 앱 수정
	 */
	public void updateApp(AppVO vo);
	
	/**
	 * @param vo
	 * app 메인 summary 카운트
	 * @return
	 */
	public int countApp(AppVO vo);
	
	/**
	 * @param vo
	 * @return 페이징 처리
	 * @throws Exception
	 */
	public int listCount(ListCriteria vo) throws Exception;
}
