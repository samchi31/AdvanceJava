package myBatisTest;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import oracle.sql.CharacterSet;

public class MyBatisTest {
	public static void main(String[] args) {
		// MyBatis를 이용하여 DB 데이터를 처리하는 작업 순서
		// 1. MyBatis의 환경설정 파일을 읽어와 실행시킨다

		SqlSessionFactory sqlSessionFactory = null;

		try {
			// 1-1. xml 설정파일 읽어오기
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);

			Reader rd = Resources.getResourceAsReader("mybatis-config.xml");

			// 1-2 위에서 읽어온 Reader객체를 이용하여 SqlSessionFactory 객체 생성하기
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);

			rd.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		////////////////////////////////////////////////////////////
		// 2. 실행할 SQL 문에 맞는 쿼리문을 호출하여 원하는 작업을 수행

//		// 2-1. insert 연습
//		System.out.println("insert 작업 시작..");
//
//		// 1) 저장할 데이터를 VO에 담는다
//		MemberVO mv = new MemberVO("d001", "강감찬", "010-1111-1111", "경남 진주시");

		// 2) SqlSession 객체를 이용하여 해당 쿼리문을 실행
		SqlSession sqlSession = sqlSessionFactory.openSession();

//		try {
//			int cnt = sqlSession.insert("memberTest.insertMember", mv);
//
//			if (cnt > 0) {
//				System.out.println("insert 작업 성공");
//			} else {
//				System.out.println("insert 작업 실패");
//			}
//			// 커밋
//			sqlSession.commit();
//		} catch (PersistenceException e) {
//			sqlSession.rollback();
//			throw new RuntimeException("데이터 등록중 예외발생", e);
//		} finally {
//			sqlSession.close(); // 세션종료
//		}
//
//		// 2-2. update 작업 연습
//		System.out.println("update 작업 시작..");
//
//		// 1) 저장할 데이터를 VO에 담는다
//		mv = new MemberVO("d001", "냥냥냥", "010-1111-1111", "대전");
//
//		// 2) SqlSession 객체를 이용하여 해당 쿼리문을 실행
//		sqlSession = sqlSessionFactory.openSession();
//
//		try {
//			int cnt = sqlSession.update("memberTest.updateMember", mv);
//
//			if (cnt > 0) {
//				System.out.println("update 작업 성공");
//			} else {
//				System.out.println("update 작업 실패");
//			}
//			// 커밋
//			sqlSession.commit();
//		} catch (PersistenceException e) {
//			sqlSession.rollback();
//			throw new RuntimeException("데이터 수정중 예외발생", e);
//		} finally {
//			sqlSession.close(); // 세션종료
//		}

		// 2-3. delete 연습
		System.out.println("delete 작업 시작..");

		sqlSession = sqlSessionFactory.openSession();

		try {
			// delete 메서드의 반환값 : 성공한 레코드 수
			int cnt = sqlSession.delete("memberTest.deleteMember", "d001"); // (xml, 매개변수)
			if (cnt > 0) {
				System.out.println("delete 작업 성공");
				// 커밋
				sqlSession.commit();
			} else {
				System.out.println("delete 작업 실패");
			}
		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("데이터 등록중 예외발생", e);
		} finally {
			sqlSession.close(); // 세션종료
		}

		// 2-4. select 연습
		// 1) 응답의 결과가 여러개일 경우
		System.out.println("select 연습 (결과가 여러개인 경우)..");

		sqlSession = sqlSessionFactory.openSession();

		List<MemberVO> memList = new ArrayList<MemberVO>();

		try {
			// 응답결과가 여러개인 경우에는 selectList 메서드를 사용한다
			memList = sqlSession.selectList("memberTest.selectAllMember");

			if (memList.size() == 0) {
				System.out.println("정보없음");
			} else {
				for (MemberVO mv3 : memList) {
					System.out.println(" ID : " + mv3.getMemId());
					System.out.println(" 이름 : " + mv3.getMemName());
					System.out.println(" 전화 : " + mv3.getMemTel());
					System.out.println(" 주소 : " + mv3.getMemAddr());
				}
			}

			System.out.println("출력 끝 ...");
		} finally {
			sqlSession.close();
		}

		// 2) 응답의 결과가 한개일 경우
		System.out.println("select 연습 (결과가 1개인 경우)..");

		sqlSession = sqlSessionFactory.openSession();

		try {
			// 응답결과가 1개인 경우에는 selectOne 메서드를 사용한다
			MemberVO mv = (MemberVO) sqlSession.selectOne("memberTest.selectMember", "a002");

			if (memList.size() == 0) {
				System.out.println("정보없음");
			} else {
				System.out.println(" ID : " + mv.getMemId());
				System.out.println(" 이름 : " + mv.getMemName());
				System.out.println(" 전화 : " + mv.getMemTel());
				System.out.println(" 주소 : " + mv.getMemAddr());
			}

		} finally {
			sqlSession.close();
		}
	}
}
