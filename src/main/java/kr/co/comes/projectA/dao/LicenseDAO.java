package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.LicenseVO;

public interface LicenseDAO {
	
	public List<LicenseVO> licenseList();

	public void insertLicense(LicenseVO vo);

	public LicenseVO viewLicense(int serverid);

	public void deleteLicense(int serverid);

	public void updateLicense(LicenseVO vo);
}
