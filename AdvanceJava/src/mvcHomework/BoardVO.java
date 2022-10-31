package mvcHomework;

import java.util.Date;

/**
 * DB 테이블에 존재하는 컬럼이름을 기준으로 데이터를 객체화한 클래스
 * @author PC-07
 *
 * <p>
 * DB 테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행 <br>
 * </p>
 */
public class BoardVO {
	private int no;
	private String title;
	private String writer;
	private String content;
	private Date date;
	public BoardVO() {
	}	
	
	public BoardVO(int no, String title, String writer, String content, Date date) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.date = date;
	}

	public BoardVO(int no, String title, String writer, String content) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("BoardVO [no=%s, title=%s, writer=%s, content=%s, date=%s]", no, title, writer, content,
				date);
	}	
	
}
