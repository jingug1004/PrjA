package kr.co.comes.projectA.dto;

import java.sql.Date;

/**
 * @author USER : DB에 저장된 데이터를 불러오도록 변수 선언
 * 
 *
 */
public class UserVO {

	private String id;
	private String pwd;
	private String name;
	private String role;
	private int clntid;
	private String telno;
	private String email;
	private Date adddate;
	private Date upddate;
	private String adduser;
	private String upduser;
	private Integer appid;
	private Integer devid;
	
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	public Integer getDevid() {
		return devid;
	}
	public void setDevid(Integer devid) {
		this.devid = devid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getClntid() {
		return clntid;
	}
	public void setClntid(int clntid) {
		this.clntid = clntid;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	public Date getUpddate() {
		return upddate;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	public String getAdduser() {
		return adduser;
	}
	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}
	public String getUpduser() {
		return upduser;
	}
	public void setUpduser(String upduser) {
		this.upduser = upduser;
	}
	
	@Override
	public String toString() {
		return "[{\"ID\":\"" + id + "\"}" + 
				", {\"Password\":\"" + pwd + "\"" +"}" + 
				", {\"Name\":\"" + name + "\"" +"}" + 
				", {\"Phone\":\"" + telno + "\"" +"}" + 
				", {\"email\":\"" + email + "\"" +"}" + 
				"]";
	}
}
