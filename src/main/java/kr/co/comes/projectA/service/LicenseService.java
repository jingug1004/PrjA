package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.LicenseVO;

public interface LicenseService {

	public List<LicenseVO> licenseList();

	public void insertLicense(LicenseVO vo);

	public LicenseVO viewLicense(int serverid);

	public void deleteLicense(int serverid);

	public void updateLicense(LicenseVO vo);
}
