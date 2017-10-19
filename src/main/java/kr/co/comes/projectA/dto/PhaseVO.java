package kr.co.comes.projectA.dto;

import java.sql.Date;

public class PhaseVO {

	private int phid; //phase 순번
	private String abbr; //phase 약어
	private String name; //phase 이름
	private int projid; //프로젝트 순번
	private String proj_name; //프로젝트 이름
	private Date adddate; //등록날짜
	private Date upddate; //수정날짜
	private String user; //등록한 사용자
	private String desc;
	private String upduser; //수정한 사용자
	private String status;
	private Date fromdate; //프로젝트 시작일
	private Date todate; //프로젝트 종료일
	private int case1; //케이스 갯수
	private int issue; //이슈 갯수
	private String attach;

	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getPhid() {
		return phid;
	}
	public void setPhid(int phid) {
		this.phid = phid;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getProjid() {
		return projid;
	}
	public void setProjid(int projid) {
		this.projid = projid;
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUpduser() {
		return upduser;
	}
	public void setUpduser(String upduser) {
		this.upduser = upduser;
	}
	public int getCase1() {
		return case1;
	}
	public void setCase1(int case1) {
		this.case1 = case1;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}

	public String getProj_name() {
		return proj_name;
	}
	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}
	public Date getFromdate() {
		return fromdate;
	}
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Override
	public String toString() {
		return "[{\"Project Title\":\"" + proj_name + "\"}" + 
				", {\"Phase Title\":\"" + name + "\"" +"}" + 
				", {\"Person who prepares\":\"" + user + "\"" +"}" + 
				", {\"Phase ID\":\"" + abbr + "\"" +"}" + 
				", {\"Start\":\"" + fromdate + "\"" +"}" + 
				", {\"desc\":\"" + desc + "\"" +"}" + 
				"]";
	}
}