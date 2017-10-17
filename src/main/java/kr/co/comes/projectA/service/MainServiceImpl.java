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
     * mainMapper�� �ִ� p_Total ���
     * ������Ʈ ��� ����
     */
	@Override
	public int project_TotalCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_TotalCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_OngoingCount(ListCriteria vo)
     * mainMapper�� �ִ� p_Ongoing ���
     * ������Ʈ ��� ����
     */
	@Override
	public int project_OngoingCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_OngoingCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_HoldCount(ListCriteria vo)
     * mainMapper�� �ִ� p_Hold ���
     * ������Ʈ ��� ����
     */
	@Override
	public int project_HoldCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_HoldCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#project_EndCount(ListCriteria vo)
     * mainMapper�� �ִ� p_End ���
     * ������Ʈ ��� ����
     */
	@Override
	public int project_EndCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.project_EndCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TotalCount(ListCriteria vo)
     * mainMapper�� �ִ� u_Total ���
     * ������Ʈ ��� ����
     */
	@Override
	public int user_TotalCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.user_TotalCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TestEngineerCount(ListCriteria vo)
     * mainMapper�� �ִ� u_Admin ���
     * ������Ʈ ��� ����
     */
	@Override
	public int user_AdminCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.user_AdminCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_TestEngineerCount(ListCriteria vo)
     * mainMapper�� �ִ� u_TestEngineer ���
     * ������Ʈ ��� ����
     */
	@Override
	public int user_TestEnginnerCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.user_TestEngineerCount(vo);
	}
	
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.MainDAO#user_ReviewerCount(ListCriteria vo)
     * mainMapper�� �ִ� u_Reviewer ���
     * ������Ʈ ��� ����
     */
	@Override
	public int user_ReviewerCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.user_ReviewerCount(vo);
	}
	
	 /* (non-Javadoc)
    * @see kr.co.comes.projectA.dao.MainDAO#NoteCount(ListCriteria vo)
    * mainMapper�� �ִ� Note ���
    * note ����
    */
	@Override
	public int NoteCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.NoteCount(vo);
	}

	
	 /* (non-Javadoc)
    * @see kr.co.comes.projectA.dao.MainDAO#IssueCount(ListCriteria vo)
    * mainMapper�� �ִ� Issue ���
    * issue ����
    */
	@Override
	public int IssueCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.IssueCount(vo);
	}
	
	
	 /* (non-Javadoc)
   * @see kr.co.comes.projectA.dao.MainDAO#DefectCount(ListCriteria vo)
   * mainMapper�� �ִ� Defect ���
   * defect ����
   */
	@Override
	public int DefectCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.DefectCount(vo);
	}
}
