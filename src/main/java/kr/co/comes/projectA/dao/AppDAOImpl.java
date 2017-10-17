package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.AppVO;
import kr.co.comes.projectA.dto.ListCriteria;

@Repository
public class AppDAOImpl implements AppDAO {
	
	@Inject
	SqlSession sqlSession;

	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see kr.co.comes.projectA.dao.AppDAO#appList(kr.co.comes.projectA.dto.ListCriteria)
	 * 키워드를 기준으로 app목록 조회(키워드가 없는경우 전체)
	 */
	@Override
	public List<AppVO> appList(ListCriteria vo) {
		return sqlSession.selectList("kr.co.comes.projectA.appMapper.appList",vo);
	}

	/* (non-Javadoc)
	 * @see kr.co.comes.projectA.dao.AppDAO#insertApp(kr.co.comes.projectA.dto.AppVO)
	 * 앱 추가
	 */
	@Override
	public void insertApp(AppVO vo) {
		sqlSession.insert("kr.co.comes.projectA.appMapper.insertApp", vo);
	}

	/* (non-Javadoc)
	 * @see kr.co.comes.projectA.dao.AppDAO#viewApp(int)
	 * 한개의 app 데이터만 불러온다
	 */
	@Override
	public AppVO viewApp(int appid) {
		return sqlSession.selectOne("kr.co.comes.projectA.appMapper.viewApp", appid);
	}

	/* (non-Javadoc)
	 * @see kr.co.comes.projectA.dao.AppDAO#deleteApp(int)
	 * 앱 삭제
	 */
	@Override
	public void deleteApp(int appid) {
		sqlSession.delete("kr.co.comes.projectA.appMapper.deleteApp", appid);
	}

	/* (non-Javadoc)
	 * @see kr.co.comes.projectA.dao.AppDAO#updateApp(kr.co.comes.projectA.dto.AppVO)
	 * 앱 변경
	 */
	@Override
	public void updateApp(AppVO vo) {
		sqlSession.update("kr.co.comes.projectA.appMapper.updateApp", vo);
	}
	
	/* (non-Javadoc)
	 * @see kr.co.comes.projectA.dao.AppDAO#countApp(kr.co.comes.projectA.dto.AppVO)
	 * app 메인화면의 summery
	 */
	@Override
	public int countApp(AppVO vo) {
		return sqlSession.selectOne("kr.co.comes.projectA.appMapper.countApp",vo);
	}
	
	/* (non-Javadoc)
	 * @see kr.co.comes.projectA.dao.AppDAO#listCount(kr.co.comes.projectA.dto.ListCriteria)
	 * 페이징 처리
	 */
	@Override
	public int listCount(ListCriteria vo) throws Exception {
		return sqlSession.selectOne("kr.co.comes.projectA.appMapper.listCount", vo);
	}
}
