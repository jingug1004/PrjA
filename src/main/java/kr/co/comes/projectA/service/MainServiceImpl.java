package kr.co.comes.projectA.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.MainDAO;
import kr.co.comes.projectA.dto.ListCriteria;

@Service
public class MainServiceImpl implements MainService {

	@Inject
	private MainDAO dao;
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_OngoingCount(ListCriteria vo)
     * mainMapper에 있는 p_Total 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int project_TotalCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_TotalCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_OngoingCount(ListCriteria vo)
     * mainMapper에 있는 p_Ongoing 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int project_OngoingCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_OngoingCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_HoldCount(ListCriteria vo)
     * mainMapper에 있는 p_Hold 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int project_HoldCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_HoldCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_EndCount(ListCriteria vo)
     * mainMapper에 있는 p_End 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int project_EndCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_EndCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TotalCount(ListCriteria vo)
     * mainMapper에 있는 u_Total 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int user_TotalCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.user_TotalCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TestEngineerCount(ListCriteria vo)
     * mainMapper에 있는 u_Admin 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int user_AdminCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.user_AdminCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TestEngineerCount(ListCriteria vo)
     * mainMapper에 있는 u_TestEngineer 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int user_TestEnginnerCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.user_TestEngineerCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_ReviewerCount(ListCriteria vo)
     * mainMapper에 있는 u_Reviewer 사용
     * 프로젝트 목록 갯수
     */
	@Override
	public int user_ReviewerCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.user_ReviewerCount(vo);
	}
	
	 /* (non-Javadoc)
    * @see kr.co.comes.projectA.dao.MainDAO#NoteCount(ListCriteria vo)
    * mainMapper에 있는 Note 사용
    * note 갯수
    */
	@Override
	public int NoteCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.NoteCount(vo);
	}

	
	 /* (non-Javadoc)
    * @see kr.co.comes.projectA.dao.MainDAO#IssueCount(ListCriteria vo)
    * mainMapper에 있는 Issue 사용
    * issue 갯수
    */
	@Override
	public int IssueCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.IssueCount(vo);
	}
	
	
	 /* (non-Javadoc)
   * @see kr.co.comes.projectA.dao.MainDAO#DefectCount(ListCriteria vo)
   * mainMapper에 있는 Defect 사용
   * defect 갯수
   */
	@Override
	public int DefectCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.DefectCount(vo);
	}
}
