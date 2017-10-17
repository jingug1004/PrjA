package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.AppVO;
import kr.co.comes.projectA.dto.ListCriteria;

public interface AppService {

	public List<AppVO> appList(ListCriteria vo) throws Exception;
	
	public int listCount(ListCriteria vo) throws Exception;

	public void insertApp(AppVO vo);

	public AppVO viewApp(int appid);

	public void deleteApp(int appid);

	public void updateApp(AppVO vo);
	
	public int countApp(AppVO vo);
	
}
