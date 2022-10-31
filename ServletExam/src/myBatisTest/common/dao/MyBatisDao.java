package myBatisTest.common.dao;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import myBatisTest.util.MyBatisUtil;

public class MyBatisDao {
	public <T> T selectOne(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		T obj = null;
		try {
			obj = sqlSession.selectOne(statement, parameter);
			
		} catch ( PersistenceException ex) {
			throw new RuntimeException("데이터 조회중 예외발생", ex);
		} finally {
			sqlSession.close();
		}
		
		return obj;
	}
	
	public <T> List<T> selectList(String statement, Object parameter){
		List<T> list = Collections.emptyList();
		
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		try {
			if (parameter == null) {
				list = sqlSession.selectList(statement);
			} else {
				list = sqlSession.selectList(statement, parameter);
			}
			
		} catch ( PersistenceException ex) {
			throw new RuntimeException("데이터 목록 조회중 예외발생", ex);
		} finally {
			sqlSession.close();
		}
		
		return list;
	}
	
	public int insert(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = sqlSession.insert(statement, parameter);
			
			sqlSession.commit();			
		} catch ( PersistenceException ex) {
			sqlSession.rollback();
			throw new RuntimeException("데이터 insert 조회중 예외발생", ex);
		} finally {
			sqlSession.close();
		}
		
		return cnt;
	}
	
	public int update(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = sqlSession.update(statement, parameter);
			
			sqlSession.commit();			
		} catch ( PersistenceException ex) {
			sqlSession.rollback();
			throw new RuntimeException("데이터 update 조회중 예외발생", ex);
		} finally {
			sqlSession.close();
		}
		
		return cnt;
	}
	
	public int delete(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = sqlSession.delete(statement, parameter);
			
			sqlSession.commit();			
		} catch ( PersistenceException ex) {
			sqlSession.rollback();
			throw new RuntimeException("데이터 delete 조회중 예외발생", ex);
		} finally {
			sqlSession.close();
		}
		
		return cnt;
	}
	
	public <T> List<T> search(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<T> list = Collections.emptyList();
		
		try {
			list = sqlSession.selectList(statement, parameter);
			
			sqlSession.commit();			
		} catch ( PersistenceException ex) {
			sqlSession.rollback();
			throw new RuntimeException("데이터 search 조회중 예외발생", ex);
		} finally {
			sqlSession.close();
		}
		
		return list;
	}
}
