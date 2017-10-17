package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.CaseVO;
import kr.co.comes.projectA.dto.ListCriteria;

@Repository
public class CaseDAOImpl implements CaseDAO {

	@Inject
	private SqlSession session;

	//공통으로 들어가는 "kr.co.comes.projectA.caseMapper"를 namespace 변수에 저장해서 사용 
	private static String namespace = "kr.co.comes.projectA.caseMapper";

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_list(ListCriteria vo)
     * caseMapper에 있는 list 사용
     * 프로젝트 목록
     */
	@Override
	public List<CaseVO> case_list(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".list", vo);
	}
	
	@Override
	public List<CaseVO> getReport(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".getreport", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_listCount(ListCriteria vo)
     * caseMapper에 있는 listCount 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int case_listCount(ListCriteria vo) throws Exception {
		return session.selectOne(namespace + ".listCount", vo);
	}
	
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_proj_ph_name(ListCriteria vo)
     * caseMapper에 있는 proj_ph_name 사용
     * 해당 Case의 Project명,Phase명 검색
     */
	@Override
	public CaseVO case_proj_ph_name(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".proj_ph_name", vo);
	}
	
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#proj_abbr(int projid)
     * caseMapper에 있는 proj_abbr 사용
     * 해당 projid의 abbr 검색
     */
	@Override
	public String proj_abbr(int projid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".proj_abbr", projid);
	}
	
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#ph_abbr(ListCriteria vo)
     * caseMapper에 있는 ph_abbr 사용
     * 해당 phid의 abbr 검색
     */
	@Override
	public String ph_abbr(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".ph_abbr", vo);
	}
	
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#senaabbr(ListCriteria vo)
     * caseMapper에 있는 proj_abbr 사용
     * 해당 senid의 abbr 검색
     */
	@Override
	public String senaabbr(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".senaabbr", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_create(CaseVO vo)
     * caseMapper에 있는 create 사용
     * case 생성
     */
	@Override
	public void case_create(CaseVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#proj_namelist(ListCriteria vo)
     * caseMapper에 있는 ph_namelist 사용
     * 사용자의 phase 명 목록 구하기
     */
	@Override
	public List<CaseVO> ph_namelist(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".ph_namelist", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#proj_name(ListCriteria vo)
     * caseMapper에 있는 ph_name 사용
     * 선택된 phase 이름 select
     */
	@Override
	public String ph_name(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".ph_name", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#senid(ListCriteria vo)
     * caseMapper에 있는 senid 사용
     * 프로젝트 상세
     */
	 @Override
	public int senid(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		 return session.selectOne(namespace + ".senid", vo);
	}

	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_read(CaseVO vo)
     * caseMapper에 있는 read 사용
     * 프로젝트 상세
     */
	@Override
	public CaseVO case_read(CaseVO vo) throws Exception {
		return session.selectOne(namespace + ".read", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_modify(CaseVO vo)
     * caseMapper에 있는 modify 사용
     * 프로젝트 수정
     */
	@Override
	public void case_modify(CaseVO vo) throws Exception {
		session.update(namespace + ".modify", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#case_remove(CaseVO vo)
     * caseMapper에 있는 remove 사용
     * 프로젝트 삭제
     */
	@Override
	public void case_remove(CaseVO vo) throws Exception {
		session.delete(namespace + ".remove", vo);

	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getAttach(CaseVO vo)
     * caseMapper에 있는 getAttach 사용
     * 파일업로드 조회
     */
	@Override
	public String getAttach(CaseVO vo) throws Exception {
		
		return session.selectOne(namespace+".getAttach", vo);	
		
	}
	
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getCase(CaseVO vo)
     * CaseMapper에 있는 getCase 사용
     * case 정보 받아오기
     */
	@Override
	public List<CaseVO> getCase(CaseVO vo) throws Exception {
		return session.selectList(namespace + ".getCase", vo);
	}

	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getDevid(String user)
     * CaseMapper에 있는 getDevid 사용
     * user의 devid 값
     */
	@Override
	public int getDevid(String user) throws Exception {
		return session.selectOne(namespace+".getDevid", user);
	}

	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getAppid(String user)
     * CaseMapper에 있는 getAppid 사용
     * user의 appid 값
     */
	@Override
	public int getAppid(String user) throws Exception {
		return session.selectOne(namespace+".getAppid", user);
	}

	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getDevName(int devid)
     * CaseMapper에 있는 getDevName 사용
     * user의 devid 값의 name
     */
	@Override
	public String getDevName(int devid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".getDevName", devid);
	}

	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.CaseDAO#getAppName(int appid)
     * CaseMapper에 있는 getAppName 사용
     * user의 appid 값의 name
     */
	@Override
	public String getAppName(int appid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".getAppName", appid);
	}
	



}
