package kr.co.comes.projectA.service;

import java.util.List;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.UserVO;

public interface UserService {
    /**
     * 회원 목록
     * @return
     */
    public List<UserVO> userList(ListCriteria vo);
    /**
     * 
     * @return 회원 한줄 목록을 불러오기위해 메서드 생성
     */
    public UserVO userOne(String id);
    // 회원 입력
    public int listCount(ListCriteria vo) throws Exception;
    // 회원 입력
    public void insertUser(UserVO vo);
    // 회원 정보 상세보기
    public UserVO viewUser(String id);
    // 회원삭제
    public void deleteUser(String id);
    // 회원정보 수정
    public void updateUser(UserVO vo);
    
    public List<UserVO> list();
}
