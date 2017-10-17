package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.CaseDAO;
import kr.co.comes.projectA.dto.CaseVO;
import kr.co.comes.projectA.dto.ListCriteria;

@Service
public class CaseServiceImpl implements CaseService {

	@Inject
	private CaseDAO dao;

	/*
	 * case 목록 CaseDAO에 있는 case_list(ListCriteria vo) 사용
	 */
	@Override
	public List<CaseVO> case_list(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.case_list(vo);
	}
	
	@Override
	public List<CaseVO> getReport(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getReport(vo);
	}

	/*
	 * case 목록 갯수 CaseDAO에 있는 case_listCount(ListCriteria vo) 사용
	 */
	@Override
	public int case_listCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.case_listCount(vo);
	}

	/*
	 * 해당 Case의 Project명,Phase명 검색 CaseDAO에 있는 case_proj_ph_name(ListCriteria
	 * vo) 사용
	 */
	@Override
	public CaseVO case_proj_ph_name(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.case_proj_ph_name(vo);
	}

	/*
	 * 해당 projid의 abbr 검색 CaseDAO에 있는 proj_abbr(int projid) 사용
	 */
	@Override
	public String proj_abbr(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.proj_abbr(projid);
	}

	/*
	 * 해당 phid의 abbr 검색 CaseDAO에 있는 ph_abbr(ListCriteria vo) 사용
	 */
	@Override
	public String ph_abbr(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.ph_abbr(vo);
	}

	/*
	 * 해당 senid의 abbr 검색 CaseDAO에 있는 senaabbr(int senid) 사용
	 */
	@Override
	public String senaabbr(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.senaabbr(vo);
	}

	/*
	 * case 생성 CaseDAO에 있는 case_create(CaseVO vo) 사용
	 */
	@Override
	public void case_create(CaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.case_create(vo);

	}

	/*
	 * phase namelist 불러오기 CaseDAO에 있는 ph_namelist(ListCriteria vo) 사용
	 */
	@Override
	public List<CaseVO> ph_namelist(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.ph_namelist(vo);
	}

	/*
	 * phase name 불러오기 CaseDAO에 있는 ph_name(int projid) 사용
	 */
	@Override
	public String ph_name(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.ph_name(vo);
	}

	/*
	 * 자동증가 senid 값 구하기 CaseDAO에 있는 senid(int ListCriteria vo) 사용
	 */
	@Override
	public int senid(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.senid(vo);
	}

	/*
	 * case 상세 CaseDAO에 있는 case_read(CaseVO vo) 사용
	 */
	@Override
	public CaseVO case_read(CaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.case_read(vo);
	}

	/*
	 * case 수정 CaseDAO에 있는 case_modify(CaseVO vo) 사용
	 */
	@Override
	public void case_modify(CaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.case_modify(vo);
	}

	/*
	 * case 삭제 CaseDAO에 있는 case_remove(CaseVO vo) 사용
	 */
	@Override
	public void case_remove(CaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.case_remove(vo);

	}

	/*
	 * 파일업로드 조회 CaseDAO에 있는 getAttach(CaseVO vo) 사용
	 */
	@Override
	public String getAttach(CaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAttach(vo);
	}

	/*
	 * Case 상세 CaseDAO에 있는 getCase(CaseVO vo) 사용
	 */
	@Override
	public List<CaseVO> getCase(CaseVO vo) throws Exception {
		return dao.getCase(vo);
	}

	/*
	 * Case 상세 CaseDAO에 있는 getDevid(String user) 사용
	 */
	@Override
	public int getDevid(String user) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDevid(user);
	}

	/*
	 * Case 상세 CaseDAO에 있는 getAppid(String user) 사용
	 */
	@Override
	public int getAppid(String user) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAppid(user);
	}

	/*
	 * Case 상세 CaseDAO에 있는 getDevName(int devid) 사용
	 */
	@Override
	public String getDevName(int devid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDevName(devid);
	}

	/*
	 * Case 상세 CaseDAO에 있는 getAppName(int appid) 사용
	 */
	@Override
	public String getAppName(int appid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAppName(appid);
	}

}
