package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.EventVO;

@Repository
public class EventDAOImpl implements EventDAO {

	@Inject
	private SqlSession session;

	// �������� ���� "kr.co.comes.projectA.eventMapper"�� namespace ������ �����ؼ� ���
	private static String namespace = "kr.co.comes.projectA.eventMapper";

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#event_create(EventVO vo)
	 * eventMapper�� �ִ� create ��� case ����
	 */
	@Override
	public void event_create(EventVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#event_update(EventVO vo)
	 * eventMapper�� �ִ� update ��� case ����
	 */
	@Override
	public void event_update(EventVO vo) throws Exception {
		session.insert(namespace + ".update", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#seq(EventVO vo) 
	 * eventMapper�� �ִ� seq ��� ������Ʈ ��
	 */
	@Override
	public int seq(EventVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".senid", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#event_read(EventVO vo) 
	 * eventMapper�� �ִ� read ��� �ó����� ��
	 */
	@Override
	public List<EventVO> event_read(EventVO vo) throws Exception {
		return session.selectList(namespace + ".read", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#getEvent(EventVO vo) 
	 * eventMapper�� �ִ� read ��� �̺�Ʈ ���� �޾ƿ���
	 */
	@Override
	public List<EventVO> getEvent(EventVO vo) throws Exception {
		return session.selectList(namespace + ".getEvent", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#remove(EventVO vo) 
	 * eventMapper�� �ִ� remove ��� �̹��� ���� �޾ƿ���
	 */
	@Override
	public void remove(EventVO vo) throws Exception {
		session.delete(namespace + ".remove", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#getImage(EventVO vo) 
	 * eventMapper�� �ִ� getImage ��� �̹��� ���� �޾ƿ���
	 */
	@Override
	public String getImage(EventVO vo) throws Exception {
		return session.selectOne(namespace + ".getImage", vo);
	}
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#getDevice(int devid) 
	 * eventMapper�� �ִ� getDevice ��� device serial ��ȸ
	 */
	@Override
	public String getDevice(int devid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".getDevice",devid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#getApp(int appid) 
	 * eventMapper�� �ִ� getApp ��� app packageName ��ȸ
	 */
	@Override
	public String getApp(int appid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".getApp",appid);
	}

}
