package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.DeviceVO;
import kr.co.comes.projectA.dto.ListCriteria;

public interface DeviceDAO {

	public List<DeviceVO> deviceList(ListCriteria vo) throws Exception;

	public List<DeviceVO> devicecontrolList(DeviceVO vo);

	public void insertDevice(DeviceVO vo);

	public DeviceVO viewDevice(int devid);

	public void deleteDevice(int devid);

	public void updateDevice(DeviceVO vo);

	public int countDevice(DeviceVO vo);

	public int ktDevice(DeviceVO vo);

	public int sktDevice(DeviceVO vo);

	public int lgDevice(DeviceVO vo);

	public int etcDevice(DeviceVO vo);
	
	public int listCount(ListCriteria vo) throws Exception;
}
