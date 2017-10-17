package kr.co.comes.projectA.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ListCriteria;

@Repository
public class MainDAOImpl implements MainDAO {

	@Inject
	private SqlSession session;

	//공통으로 들어가는 "kr.co.comes.projectA.mainMapper"를 namespace 변수에 저장해서 사용 
	private static String namespace = "kr.co.comes.projectA.mainMapper";

	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_TotalCount(ListCriteria vo)
     * mainMapper에 있는 Total 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int project_TotalCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".p_Total", vo);
	}
	
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_OngoingCount(ListCriteria vo)
     * mainMapper에 있는 Ongoing 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int project_OngoingCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".p_Ongoing", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_HoldCount(ListCriteria vo)
     * mainMapper에 있는 Hold 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int project_HoldCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".p_Hold", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_EndCount(ListCriteria vo)
     * mainMapper에 있는 End 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int project_EndCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".p_End", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TotalCount(ListCriteria vo)
     * mainMapper에 있는 u_Total 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int user_TotalCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".u_Total", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TotalCount(ListCriteria vo)
     * mainMapper에 있는 u_Admin 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int user_AdminCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".u_Admin", vo);
	}

	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TestEngineerCount(ListCriteria vo)
     * mainMapper에 있는 u_TestEngineer 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int user_TestEngineerCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".u_TestEnginner", vo);
	}
	
	
	 /* (non-Javadoc)
    * @see kr.co.comes.projectA.dao.MainDAO#user_ReviewerCount(ListCriteria vo)
    * mainMapper에 있는 u_Reviewer 사용
    * 프로젝트 목록 갯수
    */
	@Override
	public int user_ReviewerCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".u_Reviewer", vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#NoteCount(ListCriteria vo)
     * mainMapper에 있는 Note 사용
     * note 갯수
     */
	@Override
	public int NoteCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".Note", vo);
	}

	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#IssueCount(ListCriteria vo)
     * mainMapper에 있는 Issue 사용
     * issue 갯수
     */
	@Override
	public int IssueCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".Issue", vo);
	}
	
	
	 /* (non-Javadoc)
    * @see kr.co.comes.projectA.dao.MainDAO#DefectCount(ListCriteria vo)
    * mainMapper에 있는 Defect 사용
    * defect 갯수
    */
	@Override
	public int DefectCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".Defect", vo);
	}

}
