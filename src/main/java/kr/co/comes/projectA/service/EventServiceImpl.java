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
	 * event ���� EventVO�� �ִ� event_create(EventVO vo) ���
	 */
	@Override
	public void event_create(EventVO vo) throws Exception {
		dao.event_create(vo);
	}
	
	/*
	 * event ���� EventVO�� �ִ� event_update(EventVO vo) ���
	 */
	@Override
	public void event_update(EventVO vo) throws Exception {
		dao.event_update(vo);;
	}

	/*
	 * �ڵ����� seq �� ���ϱ� EventDAO�� �ִ� seq(EventVO vo) ���
	 */
	@Override
	public int seq(EventVO vo) throws Exception {
		return dao.seq(vo);
	}

	/*
	 * event �� EventDAO�� �ִ� event_read(EventVO vo) ���
	 */
	@Override
	public List<EventVO> event_read(EventVO vo) throws Exception {
		return dao.event_read(vo);
	}

	/*
	 * Event �� EventDAO�� �ִ� getEvent(EventVO vo) ���
	 */
	@Override
	public List<EventVO> getEvent(EventVO vo) throws Exception {
		return dao.getEvent(vo);
	}
	
	/*
	 * �̺�Ʈ ���� EventDAO�� �ִ� remove(EventVO vo) ���
	 */
	@Override
	public void remove(EventVO vo) throws Exception{
		dao.remove(vo);
	}
	
	/*
	 * ���Ͼ��ε� ��ȸ EventDAO�� �ִ� getImage(EventVO vo) ���
	 */
	@Override
	public String getImage(EventVO vo) throws Exception{
		return dao.getImage(vo);
	}

	/*
	 * ���Ͼ��ε� ��ȸ EventDAO�� �ִ� getDevice(int devid) ���
	 */
	@Override
	public String getDevice(int devid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDevice(devid);
	}

	/*
	 * ���Ͼ��ε� ��ȸ EventDAO�� �ִ� getApp(int appid) ���
	 */
	@Override
	public String getApp(int appid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getApp(appid);
	}
	

}
