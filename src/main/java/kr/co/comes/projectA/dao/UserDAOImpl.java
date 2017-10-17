package kr.co.comes.projectA.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.UserVO;

// 현재 클래스를 DAO bean으로 등록시킴
@Repository
public class UserDAOImpl implements UserDAO{
	
	// SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
    // 의존관계 주입(Dependency Injection, DI)
    // 느스한 결함
    // IoC(Inversion of Control, 제어의 역전)
    @Inject
    // Inject애노테이션이 없으면 sqlSession은 null상태이지만
    // Inject애노테이션이 있으면 외부에서 객체를 주입시켜주게 된다. 
    // try catch문, finally문, 객체를 close할 필요가 없어졌다.
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
     * userMapper에 있는 userList 사용
     * 회원 목록
     */
    @Override
    public List<UserVO> userList(ListCriteria vo) {
        return sqlSession.selectList("kr.co.comes.projectA.userMapper.userList",vo);
    }
 
	 /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.PhaseDAO#phase_listCount(ListCriteria vo)
     * userMapper에 있는 listCount 사용
     * 전체 회원 수
     */
	@Override
	public int listCount(ListCriteria vo) throws Exception {
		return sqlSession.selectOne("kr.co.comes.projectA.userMapper.listCount", vo);
	}
    
    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#userOne(String id)
     * userMapper에 있는 userOne 사용
     * 회원 한줄
     */
    @Override
    public UserVO userOne(String id) {
    	return sqlSession.selectOne("kr.co.comes.projectA.userMapper.userOne",id);
    }

    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#insertUser(kr.co.comes.projectA.dto.UserVO)
     * userMapper에 있는 insertUser 사용
     * 회원 등록
     */
    @Override
    public void insertUser(UserVO vo) {
    	sqlSession.insert("kr.co.comes.projectA.userMapper.insertUser",vo);
    }
 
    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#viewUser(java.lang.String)
     * userMapper에 있는 viewUser 사용
     * 회원 상세 정보
     */
    @Override
    public UserVO viewUser(String id) {
        return sqlSession.selectOne("kr.co.comes.projectA.userMapper.viewUser", id);
    }
 
    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#deleteUser(java.lang.String)
     * userMapper에 있는 deleteUser 사용
     * 회원 삭제
     */
    @Override
    public void deleteUser(String id) { 
    	sqlSession.delete("kr.co.comes.projectA.userMapper.deleteUser", id);
    }
 
    /* (non-Javadoc)
     * @see kr.co.comes.projectA.dao.UserDAO#updateUser(kr.co.comes.projectA.dto.UserVO)
     * userMapper에 있는 updateUser 사용
     * 회원 정보 수정
     */
    @Override
    public void updateUser(UserVO vo) {
    	sqlSession.update("kr.co.comes.projectA.userMapper.updateUser", vo);
    }


}
