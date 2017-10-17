package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PhaseVO;

@Repository
public class PhaseDAOImpl implements PhaseDAO {

	@Inject
	private SqlSession session;

	//공통으로 들어가는 "kr.co.comes.projectA.phaseMapper"를 namespace 변수에 저장해서 사용 
	private static String namespace = "kr.co.comes.projectA.phaseMapper";

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_list(ListCriteria vo)
     * phaseMapper에 있는 list 사용
     * phase 목록
     */
	@Override
	public List<PhaseVO> phase_list(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".list", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_listCount(ListCriteria vo)
     * phaseMapper에 있는 listCount 사용
     * phase 목록 갯수
     */
	@Override
	public int phase_listCount(ListCriteria vo) throws Exception {
		return session.selectOne(namespace + ".listCount", vo);
	}
	

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_create(PhaseVO vo)
     * phaseMapper에 있는 create 사용
     * phase 생성
     */
	@Override
	public void phase_create(PhaseVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_skip(PhaseVO vo)
     * phaseMapper에 있는 skip 사용
     * phase 생성페이지에서 skip 버튼 클릭시 phid = -1로 생성
     */
	@Override
	public void phase_skip(int projid) throws Exception {
		session.insert(namespace + ".skip", projid);
	}	

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_read(int phid)
     * phaseMapper에 있는 read 사용
     * phase 상세
     */
	@Override
	public PhaseVO phase_read(PhaseVO vo) throws Exception {
		return session.selectOne(namespace + ".read", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_modify(PhaseVO vo)
     * phaseMapper에 있는 modify 사용
     * phase 수정
     */
	@Override
	public void phase_modify(PhaseVO vo) throws Exception {
		session.update(namespace + ".modify", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_remove(int phid)
     * phaseMapper에 있는 remove 사용
     * phase 삭제
     */
	@Override
	public void phase_remove(PhaseVO vo) throws Exception {
		session.delete(namespace + ".remove", vo);

	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#proj_namelist(ListCriteria vo)
     * phaseMapper에 있는 proj_namelist 사용
     * project name list
     */
	@Override
	public List<PhaseVO> proj_namelist(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".proj_namelist", vo);
	}

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#proj_name(int projid)
     * phaseMapper에 있는 proj_name 사용
     * project name
     */
	@Override
	public String proj_name(int projid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".proj_name", projid);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.ProjectDAO#phase_phid(ListCriteria vo)
     * projectMapper에 있는 phid 사용
     * phase id 구하기
     */
	@Override
	public int phase_phid(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".phid", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#getAttach(PhaseVO vo)
     * phaseMapper에 있는 getAttach 사용
     * 파일업로드 조회
     */
	@Override
	public String getAttach(PhaseVO vo) throws Exception {
		
		return session.selectOne(namespace+".getAttach", vo);	
		
	}
	/* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#getPhase(PhaseVO vo)
     * PhaseMapper에 있는 getPhase 사용
     * Phase 정보 받아오기
     */
	@Override
	public List<PhaseVO> getPhase(PhaseVO vo) throws Exception {
		return session.selectList(namespace + ".getPhase", vo);
	}

}
