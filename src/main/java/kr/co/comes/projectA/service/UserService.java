package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.UserVO;

public interface UserService {
    /**
     * ȸ�� ���
     * @return
     */
    public List<UserVO> userList(ListCriteria vo);
    /**
     * 
     * @return ȸ�� ���� ����� �ҷ��������� �޼��� ����
     */
    public UserVO userOne(String id);
    // ȸ�� �Է�
    public int listCount(ListCriteria vo) throws Exception;
    // ȸ�� �Է�
    public void insertUser(UserVO vo);
    // ȸ�� ���� �󼼺���
    public UserVO viewUser(String id);
    // ȸ������
    public void deleteUser(String id);
    // ȸ������ ����
    public void updateUser(UserVO vo);
    
    public List<UserVO> list();
}
