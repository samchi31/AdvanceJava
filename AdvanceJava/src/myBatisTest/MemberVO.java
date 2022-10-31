package myBatisTest;

import java.util.Date;

/**
 * DB 테이블에 존재하는 컬럼이름을 기준으로 데이터를 객체화한 클래스
 * @author PC-07
 *
 * <p>
 * DB 테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행 <br>
 * </p>
 */
public class MemberVO {
	private String memId;
	private String memName;
	private String memTel;
	private String memAddr;
	private Date regDate;
	public MemberVO() {
	}
	public MemberVO(String memId, String memName, String memTel, String memAddr) {
		this.memId = memId;
		this.memName = memName;
		this.memTel = memTel;
		this.memAddr = memAddr;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return String.format("MemberVO [memId=%s, memName=%s, memTel=%s, memAddr=%s, regDate=%s]", memId, memName,
				memTel, memAddr, regDate);
	}
	
}
