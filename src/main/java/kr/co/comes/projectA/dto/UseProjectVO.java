package kr.co.comes.projectA.dto;

import java.sql.Date;

public class UseProjectVO {

	private int projid; //프로젝트 순번
	private String userid; //등록한 사용자
	private String role; //person~~~ : 책임자 0  parti~~~ : 참여자 1
	private Date adddate; //등록날짜
	private String adduser; //수정날짜

	public int getProjid() {
		return projid;
	}
	public void setProjid(int projid) {
		this.projid = projid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	public String getAdduser() {
		return adduser;
	}
	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	
	
}