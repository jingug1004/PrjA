package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.UserDAOImpl;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.UserVO;

@Service
public class UserServiceImpl implements UserService {

	// MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
	@Inject
	UserDAOImpl userDao;

	@Override
	public List<UserVO> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

	// 회원 리스트 목록 조회
	@Override
	public List<UserVO> userList(ListCriteria vo) {
		return userDao.userList(vo);
	}

	// 회원 한줄 목록 조회
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crud.member.service.MemberService#memberOne() MemberService에
	 * memberOne 호출
	 */
	@Override
	public UserVO userOne(String id) {
		return userDao.userOne(id);
	}

	// 회원 등록
	@Override
	public void insertUser(UserVO vo) {
		userDao.insertUser(vo);
	}

	// 회원정보 상세조회
	@Override
	public UserVO viewUser(String id) {
		return userDao.viewUser(id);
	}

	// 회원정보 삭제
	@Override
	public void deleteUser(String id) {
		userDao.deleteUser(id);
	}

	// 회원정보 수정
	@Override
	public void updateUser(UserVO vo) {
		userDao.updateUser(vo);
	}

	@Override
	public int listCount(ListCriteria vo) throws Exception {
		// TODO Auto-generated method stub
		return userDao.listCount(vo);
	}

}
