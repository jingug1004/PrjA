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

	// 공통으로 들어가는 "kr.co.comes.projectA.eventMapper"를 namespace 변수에 저장해서 사용
	private static String namespace = "kr.co.comes.projectA.eventMapper";

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#event_create(EventVO vo)
	 * eventMapper에 있는 create 사용 case 생성
	 */
	@Override
	public void event_create(EventVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#event_update(EventVO vo)
	 * eventMapper에 있는 update 사용 case 수정
	 */
	@Override
	public void event_update(EventVO vo) throws Exception {
		session.insert(namespace + ".update", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#seq(EventVO vo) 
	 * eventMapper에 있는 seq 사용 프로젝트 상세
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
	 * eventMapper에 있는 read 사용 시나리오 상세
	 */
	@Override
	public List<EventVO> event_read(EventVO vo) throws Exception {
		return session.selectList(namespace + ".read", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#getEvent(EventVO vo) 
	 * eventMapper에 있는 read 사용 이벤트 정보 받아오기
	 */
	@Override
	public List<EventVO> getEvent(EventVO vo) throws Exception {
		return session.selectList(namespace + ".getEvent", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#remove(EventVO vo) 
	 * eventMapper에 있는 remove 사용 이미지 정보 받아오기
	 */
	@Override
	public void remove(EventVO vo) throws Exception {
		session.delete(namespace + ".remove", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#getImage(EventVO vo) 
	 * eventMapper에 있는 getImage 사용 이미지 정보 받아오기
	 */
	@Override
	public String getImage(EventVO vo) throws Exception {
		return session.selectOne(namespace + ".getImage", vo);
	}
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.EventDAO#getDevice(int devid) 
	 * eventMapper에 있는 getDevice 사용 device serial 조회
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
	 * eventMapper에 있는 getApp 사용 app packageName 조회
	 */
	@Override
	public String getApp(int appid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".getApp",appid);
	}

}
