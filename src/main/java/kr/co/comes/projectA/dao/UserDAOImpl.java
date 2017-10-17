package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.UserVO;

// ���� Ŭ������ DAO bean���� ��Ͻ�Ŵ
@Repository
public class UserDAOImpl implements UserDAO{
	
	// SqlSession ��ü�� ���������� �����Ͽ� ���Խ����ش�.
    // �������� ����(Dependency Injection, DI)
    // ������ ����
    // IoC(Inversion of Control, ������ ����)
    @Inject
    // Inject�ֳ����̼��� ������ sqlSession�� null����������
    // Inject�ֳ����̼��� ������ �ܺο��� ��ü�� ���Խ����ְ� �ȴ�. 
    // try catch��, finally��, ��ü�� close�� �ʿ䰡 ��������.
    SqlSession sqlSession;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    

	@Override
	public List<UserVO> list() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("kr.co.comes.projectA.userMapper.list");
	}
    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#userList()
     * userMapper�� �ִ� userList ���
     * ȸ�� ���
     */
    @Override
    public List<UserVO> userList(ListCriteria vo) {
        return sqlSession.selectList("kr.co.comes.projectA.userMapper.userList",vo);
    }
 
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_listCount(ListCriteria vo)
     * userMapper�� �ִ� listCount ���
     * ��ü ȸ�� ��
     */
	@Override
	public int listCount(ListCriteria vo) throws Exception {
		return sqlSession.selectOne("kr.co.comes.projectA.userMapper.listCount", vo);
	}
    
    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#userOne(String id)
     * userMapper�� �ִ� userOne ���
     * ȸ�� ����
     */
    @Override
    public UserVO userOne(String id) {
    	return sqlSession.selectOne("kr.co.comes.projectA.userMapper.userOne",id);
    }

    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#insertUser(kr.co.comes.projectA.dto.UserVO)
     * userMapper�� �ִ� insertUser ���
     * ȸ�� ���
     */
    @Override
    public void insertUser(UserVO vo) {
    	sqlSession.insert("kr.co.comes.projectA.userMapper.insertUser",vo);
    }
 
    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#viewUser(java.lang.String)
     * userMapper�� �ִ� viewUser ���
     * ȸ�� �� ����
     */
    @Override
    public UserVO viewUser(String id) {
        return sqlSession.selectOne("kr.co.comes.projectA.userMapper.viewUser", id);
    }
 
    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#deleteUser(java.lang.String)
     * userMapper�� �ִ� deleteUser ���
     * ȸ�� ����
     */
    @Override
    public void deleteUser(String id) { 
    	sqlSession.delete("kr.co.comes.projectA.userMapper.deleteUser", id);
    }
 
    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#updateUser(kr.co.comes.projectA.dto.UserVO)
     * userMapper�� �ִ� updateUser ���
     * ȸ�� ���� ����
     */
    @Override
    public void updateUser(UserVO vo) {
    	sqlSession.update("kr.co.comes.projectA.userMapper.updateUser", vo);
    }


}
