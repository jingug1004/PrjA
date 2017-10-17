package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.UserVO;

public interface UserDAO {
	
    /**
     * ȸ�� ���
     * @return
     */
    public List<UserVO> userList(ListCriteria vo);
    
    /**
     * ȸ�� ��ü ���
     * @return
     */
    public List<UserVO> list();
	/**
	 * ��ü ȸ�� ��
	 * 
	 * @return int
	 */
	public int listCount(ListCriteria vo) throws Exception;

	
	
    /**
     * ȸ�� ����
     * @return
     */
    public UserVO userOne(String id);
    /**
     * ȸ�� ���
     * @param vo
     */
    public void insertUser(UserVO vo);
    /**
     * ȸ�� ���� ����
     * @param id
     * @return
     */
    public UserVO viewUser(String id);
    /**
     * ȸ�� ����
     * @param id
     */
    public void deleteUser(String id);
    /**
     * ȸ�� ���� ����
     * @param vo
     */
    public void updateUser(UserVO vo);

}
