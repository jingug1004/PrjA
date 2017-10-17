package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.DeviceVO;
import kr.co.comes.projectA.dto.ListCriteria;

@Repository
public class DeviceDAOImpl implements DeviceDAO {
	
	@Inject
	SqlSession sqlSession;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DeviceVO> deviceList(ListCriteria vo) {
		return sqlSession.selectList("kr.co.comes.projectA.deviceMapper.deviceList",vo);
	}
	
	@Override
	public List<DeviceVO> devicecontrolList(DeviceVO vo) {
		return sqlSession.selectList("kr.co.comes.projectA.deviceMapper.devicecontrolList",vo);
	}

	@Override
	public void insertDevice(DeviceVO vo) {
		sqlSession.insert("kr.co.comes.projectA.deviceMapper.insertDevice", vo);
	}

	@Override
	public DeviceVO viewDevice(int devid) {
		return sqlSession.selectOne("kr.co.comes.projectA.deviceMapper.viewDevice", devid);
	}

	@Override
	public void deleteDevice(int devid) {
		sqlSession.delete("kr.co.comes.projectA.deviceMapper.deleteDevice", devid);
	}

	@Override
	public void updateDevice(DeviceVO vo) {
		sqlSession.update("kr.co.comes.projectA.deviceMapper.updateDevice", vo);
	}
	
	@Override
	public int sktDevice(DeviceVO vo){
		return sqlSession.selectOne("kr.co.comes.projectA.deviceMapper.sktDevice",vo);
	}
	@Override
	public int ktDevice(DeviceVO vo){
		return sqlSession.selectOne("kr.co.comes.projectA.deviceMapper.ktDevice",vo);
	}
	@Override
	public int lgDevice(DeviceVO vo){
		return sqlSession.selectOne("kr.co.comes.projectA.deviceMapper.lgDevice",vo);
	}
	@Override
	public int etcDevice(DeviceVO vo){
		return sqlSession.selectOne("kr.co.comes.projectA.deviceMapper.etcDevice",vo);
	}
	@Override
	public int countDevice(DeviceVO vo){
		return sqlSession.selectOne("kr.co.comes.projectA.deviceMapper.countDevice",vo);
	}
	@Override
	public int listCount(ListCriteria vo) throws Exception {
		return sqlSession.selectOne("kr.co.comes.projectA.deviceMapper.listCount", vo);
	}

}
