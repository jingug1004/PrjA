package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ReportVO;

@Repository
public class ReportDAOImpl implements ReportDAO {
	
	@Inject
	SqlSession sqlSession;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ReportVO reportList(int projid) {
		return sqlSession.selectOne("kr.co.comes.projectA.reportMapper.reportList");
	}
	
	@Override
	public List<ReportVO> caseList(int projid) {
		return sqlSession.selectList("kr.co.comes.projectA.reportMapper.caseList");
	}

	@Override
	public List<ReportVO> testList(int projid, int senaid) {
		return sqlSession.selectList("kr.co.comes.projectA.reportMapper.List");
	}
	
	@Override
	public ReportVO viewProject(int projid){
		return sqlSession.selectOne("kr.co.comes.projectA.reportMapper.List");
	}
	
}
