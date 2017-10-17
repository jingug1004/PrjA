package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.EventVO;

public interface EventDAO {

	/**
	 * event 생성
	 * 
	 * @return
	 */
	public void event_create(EventVO vo) throws Exception;
	
	/**
	 * event 수정
	 * 
	 * @return
	 */
	public void event_update(EventVO vo) throws Exception;

	/**
	 * 자동증가 seq 값 구하기
	 * 
	 * @return int
	 */
	public int seq(EventVO vo) throws Exception;

	/**
	 * event 상세
	 * 
	 * @return EventVO
	 */
	public List<EventVO> event_read(EventVO vo) throws Exception;

	/**
	 * event 정보
	 * 
	 * @return List<EventVO>
	 */
	public List<EventVO> getEvent(EventVO vo) throws Exception;

	/**
	 * 삭제
	 * 
	 * @return
	 */
	public void remove(EventVO vo) throws Exception;

	/**
	 * 파일업로드 조회
	 * 
	 * @return
	 */
	public String getImage(EventVO vo) throws Exception;
	
	/**
	 * device serial 조회
	 * 
	 * @return String
	 */
	public String getDevice(int devid) throws Exception;
	
	/**
	 * app packageName 조회
	 * 
	 * @return String
	 */
	public String getApp(int appid) throws Exception;

}// interface
