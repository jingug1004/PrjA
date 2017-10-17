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
	 * case ��� CaseDAO�� �ִ� case_list(ListCriteria vo) ���
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
	 * case ��� ���� CaseDAO�� �ִ� case_listCount(ListCriteria vo) ���
	 */
	@Override
	public int case_listCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.case_listCount(vo);
	}

	/*
	 * �ش� Case�� Project��,Phase�� �˻� CaseDAO�� �ִ� case_proj_ph_name(ListCriteria
	 * vo) ���
	 */
	@Override
	public CaseVO case_proj_ph_name(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.case_proj_ph_name(vo);
	}

	/*
	 * �ش� projid�� abbr �˻� CaseDAO�� �ִ� proj_abbr(int projid) ���
	 */
	@Override
	public String proj_abbr(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.proj_abbr(projid);
	}

	/*
	 * �ش� phid�� abbr �˻� CaseDAO�� �ִ� ph_abbr(ListCriteria vo) ���
	 */
	@Override
	public String ph_abbr(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.ph_abbr(vo);
	}

	/*
	 * �ش� senid�� abbr �˻� CaseDAO�� �ִ� senaabbr(int senid) ���
	 */
	@Override
	public String senaabbr(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.senaabbr(vo);
	}

	/*
	 * case ���� CaseDAO�� �ִ� case_create(CaseVO vo) ���
	 */
	@Override
	public void case_create(CaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.case_create(vo);

	}

	/*
	 * phase namelist �ҷ����� CaseDAO�� �ִ� ph_namelist(ListCriteria vo) ���
	 */
	@Override
	public List<CaseVO> ph_namelist(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.ph_namelist(vo);
	}

	/*
	 * phase name �ҷ����� CaseDAO�� �ִ� ph_name(int projid) ���
	 */
	@Override
	public String ph_name(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.ph_name(vo);
	}

	/*
	 * �ڵ����� senid �� ���ϱ� CaseDAO�� �ִ� senid(int ListCriteria vo) ���
	 */
	@Override
	public int senid(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.senid(vo);
	}

	/*
	 * case �� CaseDAO�� �ִ� case_read(CaseVO vo) ���
	 */
	@Override
	public CaseVO case_read(CaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.case_read(vo);
	}

	/*
	 * case ���� CaseDAO�� �ִ� case_modify(CaseVO vo) ���
	 */
	@Override
	public void case_modify(CaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.case_modify(vo);
	}

	/*
	 * case ���� CaseDAO�� �ִ� case_remove(CaseVO vo) ���
	 */
	@Override
	public void case_remove(CaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.case_remove(vo);

	}

	/*
	 * ���Ͼ��ε� ��ȸ CaseDAO�� �ִ� getAttach(CaseVO vo) ���
	 */
	@Override
	public String getAttach(CaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAttach(vo);
	}

	/*
	 * Case �� CaseDAO�� �ִ� getCase(CaseVO vo) ���
	 */
	@Override
	public List<CaseVO> getCase(CaseVO vo) throws Exception {
		return dao.getCase(vo);
	}

	/*
	 * Case �� CaseDAO�� �ִ� getDevid(String user) ���
	 */
	@Override
	public int getDevid(String user) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDevid(user);
	}

	/*
	 * Case �� CaseDAO�� �ִ� getAppid(String user) ���
	 */
	@Override
	public int getAppid(String user) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAppid(user);
	}

	/*
	 * Case �� CaseDAO�� �ִ� getDevName(int devid) ���
	 */
	@Override
	public String getDevName(int devid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDevName(devid);
	}

	/*
	 * Case �� CaseDAO�� �ִ� getAppName(int appid) ���
	 */
	@Override
	public String getAppName(int appid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAppName(appid);
	}

}
