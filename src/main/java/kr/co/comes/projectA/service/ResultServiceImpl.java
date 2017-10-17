package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.ResultDAO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ReplayVO;
import kr.co.comes.projectA.dto.ResourceVO;
import kr.co.comes.projectA.dto.ResultVO;

@Service
public class ResultServiceImpl implements ResultService {

	@Inject
	private ResultDAO dao;

	/*
	 * 테스트 결과 목록 ResultDAO에 있는 result_list(ListCriteria vo) 사용
	 */
	@Override
	public List<ResultVO> result_list(ListCriteria vo) throws Exception {
		return dao.result_list(vo);
	}

	/*
	 * 테스트 결과 목록 갯수 ResultDAO에 있는 result_listCount(ResultCriteria vo) 사용
	 */
	@Override
	public int result_listCount(ListCriteria vo) throws Exception {
		return dao.result_listCount(vo);
	}

	/*
	 * 테스트 수행이력 상세 ResultDAO에 있는 read(ResultVO vo)사용
	 */
	@Override
	public ResultVO read(ResultVO vo) throws Exception {
		return dao.read(vo);
	}

	/*
	 * 테스트 결과 생성 ResultDAO에 있는 result_create(ResultVO vo)사용
	 */	
	@Override
	public void result_create(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.result_create(vo);
	}

	
	/*
	 * 테스트 수행이력 상세 ResultDAO에 있는 nameSelect(ListCriteria vo)사용
	 */
	@Override
	public ResultVO nameSelect(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.nameSelect(vo);
	}

	/*
	 * 해당 시나리오 Filename 값 구하기 ResultDAO에 있는 getFilename(ListCriteria vo)사용
	 */
	@Override
	public String getFilename(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getFilename(vo);
	}

	/*
	 * 테스트 결과 수정 ResultDAO에 있는 result_modify(ResultVO vo)사용
	 */
	@Override
	public void result_modify(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.result_modify(vo);

	}

	/*
	 * 파일업로드 조회 ResultDAO에 있는 getAttach(ResultVO vo) 사용
	 */
	@Override
	public String getAttach(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAttach(vo);
	}

	/*
	 * 테스트 결과 삭제 ResultDAO에 있는 result_remove(ResultVO vo) 사용
	 */
	@Override
	public void result_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.result_remove(vo);
	}

	/*
	 *해당 시나리오의 테스트 결과 구하기 ResultDAO에 있는 getResult(ResultVO vo) 사용
	 */
	@Override
	public List<ResultVO> getResult(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getResult(vo);
	}

	
	/*
	 * 마지막 resid 구하기 ResultDAO에 있는 getResid(ResultVO vo) 사용
	 */
	
	@Override
	public int getResid(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getResid(vo);
	}
	

	/*
	 * ResultDAO에 있는  replay_create(ReplayVO vo) 사용
	 * 리플레이 결과 생성
	 */
	@Override
	public void replay_create(ReplayVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.replay_create(vo);
	}

	/*
	 * ResultDAO에 있는 replay_remove(ResultVO vo)
	 * 리플레이 결과 제거
	 */
	@Override
	public void replay_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.replay_remove(vo);
	}

	/*
	 * ResultDAO에 있는 getReplay(ResultVO vo)
	 * 해당 시나리오의 리플레이 결과 구하기
	 */
	@Override
	public List<String> getReplay(ReplayVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getReplay(vo);
	}
	
	
    /**
     * 해당 테스트 결과의 반복횟수 값 구하기 
     * @return ReplayVO
     */
	public int getRepeatNum(ResultVO vo)throws Exception{
		return dao.getRepeatNum(vo);
	}
	
	/*
	 * ResultDAO에 있는 resource_create(ResourceVO vo) 
	 * 리소스 생성
	 */
	@Override
	public void resource_create(ResourceVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.resource_create(vo);
	}

	/*
	 * ResultDAO에 있는 resource_remove(ResultVO vo)
	 * 리소스 제거
	 */
	@Override
	public void resource_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.resource_remove(vo);
	}

	/*
	 * ResultDAO에 있는 getReource(ResultVO vo)
	 * 해당 시나리오의 리소스 구하기
	 */
	@Override
	public ResourceVO getReource(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getReource(vo);
	}
}
