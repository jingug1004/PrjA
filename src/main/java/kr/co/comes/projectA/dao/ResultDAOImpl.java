package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ReplayVO;
import kr.co.comes.projectA.dto.ResourceVO;
import kr.co.comes.projectA.dto.ResultVO;

@Repository
public class ResultDAOImpl implements ResultDAO {

	@Inject
	private SqlSession session;

	// 공통으로 들어가는 "kr.co.comes.projectA.resultMapper"를 namespace 변수에 저장해서 사용
	private static String namespace = "kr.co.comes.projectA.resultMapper";

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#result_list(ListCriteria vo)
	 * resultMapper에 있는 list 사용 테스트 결과 목록
	 */
	@Override
	public List<ResultVO> result_list(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".list", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#result_listCount(ListCriteria vo)
	 * resultMapper에 있는 count 사용 테스트 결과 목록 갯수
	 */
	@Override
	public int result_listCount(ListCriteria vo) throws Exception {
		return session.selectOne(namespace + ".count", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#result_create(ResultVO vo)
	 * resultMapper에 있는 create 사용 테스트 결과 생성
	 */
	@Override
	public void result_create(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#read(ResultVO vo) resultMapper에
	 * 있는 read 사용 테스트 수행이력 상세
	 */
	@Override
	public ResultVO read(ResultVO vo) throws Exception {
		return session.selectOne(namespace + ".read", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#nameSelect(ResultVO vo)
	 * resultMapper에 있는 nameSelect 사용 테스트 수행이력 상세
	 */
	@Override
	public ResultVO nameSelect(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".nameSelect", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getParam(ListCriteria vo)
	 * resultMapper에 있는 getFilename 사용 해당 시나리오 Filename 값 구하기
	 */
	@Override
	public String getFilename(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".getFilename", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#result_modify(ResultVO vo)
	 * resultMapper에 있는 modify 사용 테스트 결과 수정
	 */
	@Override
	public void result_modify(ResultVO vo) throws Exception {
		session.update(namespace + ".modify", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getAttach(ResultVO vo)
	 * resultMapper에 있는 getAttach 사용 파일업로드 조회
	 */
	@Override
	public String getAttach(ResultVO vo) throws Exception {

		return session.selectOne(namespace + ".getAttach", vo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#result_remove(ResultVO vo)
	 * resultMapper에 있는 result_remove 사용 결과 삭제
	 */
	@Override
	public void result_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace + ".remove", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getResult(ResultVO vo)
	 * resultMapper에 있는 getResult 사용 해당 시나리오의 테스트 결과 구하기
	 */
	@Override
	public List<ResultVO> getResult(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".getResult", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getResid(ResultVO vo)
	 * resultMapper에 있는getResid 사용 마지막 resid 구하기
	 */
	@Override
	public int getResid(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".getResid", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#resource_create(ReplayVO vo)
	 * resultMapper에 있는 resource_create 사용 / 리플레이 결과 생성
	 */
	@Override
	public void replay_create(ReplayVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".replay_create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#replay_remove(ResultVO vo)
	 * resultMapper에 있는 replay_remove 사용/ 리플레이 결과 제거
	 */
	@Override
	public void replay_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace + ".replay_remove", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getReplay(ReplayVO vo)
	 * resultMapper에 있는 getReplay 사용 해당 시나리오의 리플레이 결과 구하기
	 */
	@Override
	public List<String> getReplay(ReplayVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".getReplay", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getRepeatNum(ResultVO vo)
	 * resultMapper에 있는 getRepeatNum 사용 
	 * 해당 테스트 결과의 반복횟수 값 구하기
	 */
	@Override
	public int getRepeatNum(ResultVO vo)throws Exception {
		return session.selectOne(namespace + ".getRepeatNum", vo);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#resource_create(ResourceVO vo)
	 * resultMapper에 있는 resource_create 사용 리소스 생성
	 */
	@Override
	public void resource_create(ResourceVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".resource_create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#resource_remove(ResultVO vo)
	 * resultMapper에 있는 resource_remove 사용 리소스 제
	 */
	@Override
	public void resource_remove(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace + ".resource_remove", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ResultDAO#getResource(ResultVO vo)
	 * resultMapper에 있는 getResource 사용 해당 시나리오의 모니터링 결과 구하기
	 */
	@Override
	public ResourceVO getReource(ResultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".getResource", vo);
	}

}
