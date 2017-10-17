package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.UserVO;

public interface UserDAO {
	
    /**
     * 회원 목록
     * @return
     */
    public List<UserVO> userList(ListCriteria vo);
    
    /**
     * 회원 전체 목록
     * @return
     */
    public List<UserVO> list();
	/**
	 * 전체 회원 수
	 * 
	 * @return int
	 */
	public int listCount(ListCriteria vo) throws Exception;

	
	
    /**
     * 회원 한줄
     * @return
     */
    public UserVO userOne(String id);
    /**
     * 회원 등록
     * @param vo
     */
    public void insertUser(UserVO vo);
    /**
     * 회원 정보 보기
     * @param id
     * @return
     */
    public UserVO viewUser(String id);
    /**
     * 회원 삭제
     * @param id
     */
    public void deleteUser(String id);
    /**
     * 회원 정보 수정
     * @param vo
     */
    public void updateUser(UserVO vo);

}
