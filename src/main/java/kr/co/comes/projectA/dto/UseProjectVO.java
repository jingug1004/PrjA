package kr.co.comes.projectA.dto;

import java.sql.Date;

public class UseProjectVO {

	private int projid; //������Ʈ ����
	private String userid; //����� �����
	private String role; //person~~~ : å���� 0  parti~~~ : ������ 1
	private Date adddate; //��ϳ�¥
	private String adduser; //������¥
	
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