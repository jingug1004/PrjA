package kr.co.comes.projectA.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.comes.projectA.dao.UserDAOImpl;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.UserVO;

@Service
public class UserServiceImpl implements UserService {

	// MemberDAOImpl ��ü�� ���������� �����Ͽ� ���Խ�Ŵ
	@Inject
	UserDAOImpl userDao;

	@Override
	public List<UserVO> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

	// ȸ�� ����Ʈ ��� ��ȸ
	@Override
	public List<UserVO> userList(ListCriteria vo) {
		return userDao.userList(vo);
	}

	// ȸ�� ���� ��� ��ȸ
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crud.member.service.MemberService#memberOne() MemberService��
	 * memberOne ȣ��
	 */
	@Override
	public UserVO userOne(String id) {
		return userDao.userOne(id);
	}

	// ȸ�� ���
	@Override
	public void insertUser(UserVO vo) {
		userDao.insertUser(vo);
	}

	// ȸ������ ����ȸ
	@Override
	public UserVO viewUser(String id) {
		return userDao.viewUser(id);
	}

	// ȸ������ ����
	@Override
	public void deleteUser(String id) {
		userDao.deleteUser(id);
	}

	// ȸ������ ����
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
