package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.EventVO;

public interface EventDAO {

	/**
	 * event ����
	 * 
	 * @return
	 */
	public void event_create(EventVO vo) throws Exception;
	
	/**
	 * event ����
	 * 
	 * @return
	 */
	public void event_update(EventVO vo) throws Exception;

	/**
	 * �ڵ����� seq �� ���ϱ�
	 * 
	 * @return int
	 */
	public int seq(EventVO vo) throws Exception;

	/**
	 * event ��
	 * 
	 * @return EventVO
	 */
	public List<EventVO> event_read(EventVO vo) throws Exception;

	/**
	 * event ����
	 * 
	 * @return List<EventVO>
	 */
	public List<EventVO> getEvent(EventVO vo) throws Exception;

	/**
	 * ����
	 * 
	 * @return
	 */
	public void remove(EventVO vo) throws Exception;

	/**
	 * ���Ͼ��ε� ��ȸ
	 * 
	 * @return
	 */
	public String getImage(EventVO vo) throws Exception;
	
	/**
	 * device serial ��ȸ
	 * 
	 * @return String
	 */
	public String getDevice(int devid) throws Exception;
	
	/**
	 * app packageName ��ȸ
	 * 
	 * @return String
	 */
	public String getApp(int appid) throws Exception;

}// interface
