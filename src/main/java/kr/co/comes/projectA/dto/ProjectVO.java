package kr.co.comes.projectA.dto;

import java.sql.Date;

public class ProjectVO {

	private int projid; //프로젝트 순번
	private String abbr; //약어
	private String name; //프로젝트 이름
	private String desc; //프로젝트 설명
	private String user; //등록한 사용자
	private Date adddate; //등록날짜
	private Date upddate; //수정날짜
	private String upduser; //수정한 사용자
	private String status; //0:Ongoing 1:Hold 2:End
	private Date fromdate; //프로젝트 시작일
	private Date todate; //프로젝트 종료일
	private String phase; //단계
	private int phid; //단계 순번
	private int case1; //케이스 갯수
	private int issue; //이슈 갯수
	private String attach;

	
	public int getProjid() {
		return projid;
	}

	public void setProjid(int projid) {
		this.projid = projid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String dese) {
		this.desc = dese;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public String getUpduser() {
		return upduser;
	}

	public void setUpduser(String upduser) {
		this.upduser = upduser;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}
	
	public int getPhid() {
		return phid;
	}

	public void setPhid(int phid) {
		this.phid = phid;
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

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Override
	public String toString() {
		return "[{\"Project Title\":\"" + name + "\"}" + 
				", {\"Person in charge\":\"" + user + "\"" +"}" + 
				", {\"Project ID\":\"" + abbr + "\"" +"}" + 
				", {\"Start\":\"" + fromdate + "\"" +"}" + 
				", {\"desc\":\"" + desc + "\"" +"}" + 
				"]";
	}

}