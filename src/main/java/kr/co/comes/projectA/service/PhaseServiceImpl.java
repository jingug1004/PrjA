package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.PhaseDAO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PhaseVO;

@Service
public class PhaseServiceImpl implements PhaseService {

	@Inject
	private PhaseDAO dao;

	/*
	 * phase 목록 PhaseDAO에 있는 phase_list(ListCriteria vo) 사용
	 */
	@Override
	public List<PhaseVO> phase_list(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.phase_list(vo);
	}

	/*
	 * phase 목록 갯수 PhaseDAO에 있는 phase_listCount(ListCriteria vo) 사용
	 */
	@Override
	public int phase_listCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.phase_listCount(vo);
	}

	/*
	 * phase 생성 PhaseDAO에 있는 phase_create(PhaseVO vo) 사용
	 */
	@Override
	public void phase_create(PhaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.phase_create(vo);

	}

	/*
	 * phase 생성페이지에서 skip 버튼 클릭시 phid = -1로 생성 PhaseDAO에 있는 phase_skip(int
	 * projid) 사용
	 */
	@Override
	public void phase_skip(int projid) throws Exception {
		dao.phase_skip(projid);
	}

	/*
	 * phase 상세 PhaseDAO에 있는 phase_read(PhaseVO vo) 사용
	 */
	@Override
	public PhaseVO phase_read(PhaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.phase_read(vo);
	}

	/*
	 * phase 수정 PhaseDAO에 있는 phase_modify(PhaseVO vo) 사용
	 */
	@Override
	public void phase_modify(PhaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.phase_modify(vo);
	}

	/*
	 * phase 삭제 PhaseDAO에 있는 phase_remove(PhaseVO vo) 사용
	 */
	@Override
	public void phase_remove(PhaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.phase_remove(vo);

	}

	/*
	 * project namelist 불러오기 PhaseDAO에 있는 proj_namelist(ListCriteria vo) 사용
	 */
	@Override
	public List<PhaseVO> proj_namelist(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.proj_namelist(vo);
	}

	/*
	 * project name 불러오기 PhaseDAO에 있는 proj_name(int projid) 사용
	 */
	@Override
	public String proj_name(int projid) throws Exception {
		// TODO Auto-generated method stub
		return dao.proj_name(projid);
	}
	
	/*
	 * 프로젝트 id 구하기 PhaseDAO에 있는 phase_phid(ListCriteria vo) 사용
	 */
	@Override
	public int phase_phid(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.phase_phid(vo);
	}
	
	/*
	 * 파일업로드 조회 PhaseDAO에 있는 getAttach(PhaseVO vo) 사용
	 */
	@Override
	public String getAttach(PhaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAttach(vo);
	}

	/*
	 * Phase 상세 PhaseDAO에 있는 getPhase(PhaseVO vo) 사용
	 */
	@Override
	public List<PhaseVO> getPhase(PhaseVO vo) throws Exception {
		return dao.getPhase(vo);
	}
	

	
}
