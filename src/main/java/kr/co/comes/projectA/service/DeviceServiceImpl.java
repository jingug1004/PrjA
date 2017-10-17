package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.DeviceDAOImpl;
import kr.co.comes.projectA.dto.DeviceVO;
import kr.co.comes.projectA.dto.ListCriteria;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Inject
	DeviceDAOImpl deviceDao;

	@Override
	public List<DeviceVO> deviceList(ListCriteria vo) {
		return deviceDao.deviceList(vo);
	}
	
	@Override
	public List<DeviceVO> devicecontrolList(DeviceVO vo) {
		return deviceDao.devicecontrolList(vo);
	}

	@Override
	public int listCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return deviceDao.listCount(vo);
	}
	
	@Override
	public void insertDevice(DeviceVO vo) {
		deviceDao.insertDevice(vo);
	}

	@Override
	public DeviceVO viewDevice(int devid) {
		return deviceDao.viewDevice(devid);
	}

	@Override
	public void deleteDevice(int devid) {
		deviceDao.deleteDevice(devid);
	}

	@Override
	public void updateDevice(DeviceVO vo) {
		deviceDao.updateDevice(vo);
	}

	@Override
	public int countDevice(DeviceVO vo) {
		return deviceDao.countDevice(vo);
	}
	@Override
	public int ktDevice(DeviceVO vo) {
		return deviceDao.ktDevice(vo);
	}
	@Override
	public int sktDevice(DeviceVO vo) {
		return deviceDao.sktDevice(vo);
	}
	@Override
	public int lgDevice(DeviceVO vo) {
		return deviceDao.lgDevice(vo);
	}
	@Override
	public int etcDevice(DeviceVO vo) {
		return deviceDao.etcDevice(vo);
	}
}
