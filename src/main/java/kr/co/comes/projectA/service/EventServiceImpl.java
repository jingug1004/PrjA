package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.EventDAO;
import kr.co.comes.projectA.dto.EventVO;

@Service
public class EventServiceImpl implements EventService {

	@Inject
	private EventDAO dao;

	/*
	 * event 생성 EventVO에 있는 event_create(EventVO vo) 사용
	 */
	@Override
	public void event_create(EventVO vo) throws Exception {
		dao.event_create(vo);
	}
	
	/*
	 * event 수정 EventVO에 있는 event_update(EventVO vo) 사용
	 */
	@Override
	public void event_update(EventVO vo) throws Exception {
		dao.event_update(vo);;
	}

	/*
	 * 자동증가 seq 값 구하기 EventDAO에 있는 seq(EventVO vo) 사용
	 */
	@Override
	public int seq(EventVO vo) throws Exception {
		return dao.seq(vo);
	}

	/*
	 * event 상세 EventDAO에 있는 event_read(EventVO vo) 사용
	 */
	@Override
	public List<EventVO> event_read(EventVO vo) throws Exception {
		return dao.event_read(vo);
	}

	/*
	 * Event 상세 EventDAO에 있는 getEvent(EventVO vo) 사용
	 */
	@Override
	public List<EventVO> getEvent(EventVO vo) throws Exception {
		return dao.getEvent(vo);
	}
	
	/*
	 * 이벤트 삭제 EventDAO에 있는 remove(EventVO vo) 사용
	 */
	@Override
	public void remove(EventVO vo) throws Exception{
		dao.remove(vo);
	}
	
	/*
	 * 파일업로드 조회 EventDAO에 있는 getImage(EventVO vo) 사용
	 */
	@Override
	public String getImage(EventVO vo) throws Exception{
		return dao.getImage(vo);
	}

	/*
	 * 파일업로드 조회 EventDAO에 있는 getDevice(int devid) 사용
	 */
	@Override
	public String getDevice(int devid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDevice(devid);
	}

	/*
	 * 파일업로드 조회 EventDAO에 있는 getApp(int appid) 사용
	 */
	@Override
	public String getApp(int appid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getApp(appid);
	}
	

}
