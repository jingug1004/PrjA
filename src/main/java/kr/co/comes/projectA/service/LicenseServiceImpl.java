package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.LicenseDAOImpl;
import kr.co.comes.projectA.dto.LicenseVO;

@Service
public class LicenseServiceImpl implements LicenseService {
	
	@Inject
	LicenseDAOImpl licenseDao;
	
	@Override
	public List<LicenseVO> licenseList() {
		return licenseDao.licenseList();
	}

	@Override
	public void insertLicense(LicenseVO vo) {
		licenseDao.insertLicense(vo);
	}

	@Override
	public LicenseVO viewLicense(int serverid) {
		return licenseDao.viewLicense(serverid);
	}

	@Override
	public void deleteLicense(int serverid) {
		licenseDao.deleteLicense(serverid);
	}

	@Override
	public void updateLicense(LicenseVO vo) {
		licenseDao.updateLicense(vo);
	}
}
