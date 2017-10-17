package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.AppDAOImpl;
import kr.co.comes.projectA.dto.AppVO;
import kr.co.comes.projectA.dto.ListCriteria;

@Service
public class AppServiceImpl implements AppService {
	
	@Inject
	AppDAOImpl appDao;
	
	@Override
	public List<AppVO> appList(ListCriteria vo) {
		return appDao.appList(vo);
	}

	@Override
	public void insertApp(AppVO vo) {
		appDao.insertApp(vo);
	}

	@Override
	public AppVO viewApp(int appid) {
		return appDao.viewApp(appid);
	}

	@Override
	public void deleteApp(int appid) {
		appDao.deleteApp(appid);
	}

	@Override
	public void updateApp(AppVO vo) {
		appDao.updateApp(vo);
	}
	
	@Override
	public int countApp(AppVO vo) {
		return appDao.countApp(vo);
	}
	
	@Override
	public int listCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return appDao.listCount(vo);
	}
	
}
