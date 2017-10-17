package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ProjectVO;
import kr.co.comes.projectA.dto.UseProjectVO;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	@Inject
	private SqlSession session;

	// 공통으로 들어가는 "kr.co.comes.projectA.projectMapper"를 namespace 변수에 저장해서 사용
	private static String namespace = "kr.co.comes.projectA.projectMapper";

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_list(ListCriteria vo)
	 * projectMapper에 있는 list 사용 프로젝트 목록
	 */
	@Override
	public List<ProjectVO> project_list(ListCriteria vo) throws Exception {
		return session.selectList(namespace + ".list", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_listCount(ListCriteria
	 * vo) projectMapper에 있는 listCount 사용 프로젝트 목록 갯수
	 */
	@Override
	public int project_listCount(ListCriteria vo) throws Exception {
		return session.selectOne(namespace + ".listCount", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_create(ProjectVO vo)
	 * projectMapper에 있는 project_create 사용 프로젝트 생성
	 */
	@Override
	public void project_create(ProjectVO vo) throws Exception {
		session.insert(namespace + ".project_create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_read(int projid)
	 * projectMapper에 있는 project_read 사용 프로젝트 상세
	 */
	@Override
	public ProjectVO project_read(int projid) throws Exception {
		return session.selectOne(namespace + ".project_read", projid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_modify(ProjectVO vo)
	 * projectMapper에 있는 project_modify 사용 프로젝트 수정
	 */
	@Override
	public void project_modify(ProjectVO vo) throws Exception {
		session.update(namespace + ".project_modify", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_remove(int projid)
	 * projectMapper에 있는 project_remove 사용 프로젝트 삭제
	 */
	@Override
	public void project_remove(int projid) throws Exception {
		session.delete(namespace + ".project_remove", projid);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#project_projid(ListCriteria vo)
	 * projectMapper에 있는 project_projid 사용 프로젝트 id 구하기
	 */
	@Override
	public int project_projid(String User) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".projid", User);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#getAttach(ProjectVO vo)
	 * projectMapper에 있는 getAttach 사용 파일업로드 조회
	 */
	@Override
	public String getAttach(int projid) throws Exception {

		return session.selectOne(namespace + ".getAttach", projid);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#useproj_create(UseProjectVO vo)
	 * projectMapper에 있는 useproj_create 사용
	 */
	@Override
	public void useproj_create(UseProjectVO vo) throws Exception {
		session.insert(namespace + ".useproj_create", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#paticipant(int projid)
	 * projectMapper에 있는 paticipant 사용
	 */
	@Override
	public List<String> paticipant(int projid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".paticipant", projid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#charge(int projid)
	 * projectMapper에 있는 charge 사용 
	 */
	@Override
	public String charge(int projid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".charge", projid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#charge_update(UseProjectVO vo)
	 * projectMapper에 있는 charge_update 사용
	 */
	@Override
	public void charge_update(UseProjectVO vo) throws Exception {
		session.update(namespace + ".charge_update", vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kr.co.comes.projectA.dao.ProjectDAO#useproj_delete(UseProjectVO vo)
	 * projectMapper에 있는 useproj_delete 사용 
	 */
	@Override
	public void useproj_delete(UseProjectVO vo) throws Exception {
		session.delete(namespace + ".useproj_delete", vo);
	}

}
