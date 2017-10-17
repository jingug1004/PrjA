package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.LicenseVO;

@Repository
public class LicenseDAOImpl implements LicenseDAO {
	
	@Inject
	SqlSession sqlSession;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<LicenseVO> licenseList() {
		return sqlSession.selectList("kr.co.comes.projectA.licenseMapper.licenseList");
	}

	@Override
	public void insertLicense(LicenseVO vo) {
		sqlSession.insert("kr.co.comes.projectA.licenseMapper.insertLicense", vo);
	}

	@Override
	public LicenseVO viewLicense(int serverid) {
		return sqlSession.selectOne("kr.co.comes.projectA.licenseMapper.viewLicense", serverid);
	}

	@Override
	public void deleteLicense(int serverid) {
		sqlSession.delete("kr.co.comes.projectA.licenseMapper.deleteLicense", serverid);
	}

	@Override
	public void updateLicense(LicenseVO vo) {
		sqlSession.update("kr.co.comes.projectA.licenseMapper.updateLicense", vo);
	}
}
