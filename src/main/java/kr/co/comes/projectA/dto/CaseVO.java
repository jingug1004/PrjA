package kr.co.comes.projectA.dto;

import java.sql.Date;

public class CaseVO {

	private int projid; //프로젝트 순번
	private String proj_name; //프로젝트 명
	private String proj_abbr; //프로젝트 명
	private int senid; //시나리오 id
	private int phid; //phase 순번
	private String ph_name; //Phase 명
	private String ph_abbr; //Phase 명
	private String senaabbr; //시나리오 약어
	private String senaname; //시나리오  이름
	private String senadesc; //시나리오 설명
	private Integer devid; // 기본 디바이스 순번
	private String dev_name; // 디바이스 이름
	private Integer appid; //App 순번
	private String app_name; //App name
	private int result; //결과 갯수
	private Date adddate; //등록날짜
	private Date upddate; //수정날짜
	private String user; //등록한 사용자
	private String upduser; //수정한 사용자
	private String expect; //예상 결과
	private String attach; //첨부 파일 패스
	private String s_category; // 분류 0:매뉴얼 테스트, 1:리소스 모니터링, 2:테스트 자동화


	public String getProj_name() {
		return proj_name;
	}
	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}
	public String getPh_name() {
		return ph_name;
	}
	public void setPh_name(String ph_name) {
		this.ph_name = ph_name;
	}
	public String getDev_name() {
		return dev_name;
	}
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public int getProjid() {
		return projid;
	}
	public void setProjid(int projid) {
		this.projid = projid;
	}
	public int getSenid() {
		return senid;
	}
	public void setSenid(int senaid) {
		this.senid = senaid;
	}
	public int getPhid() {
		return phid;
	}
	public void setPhid(int phid) {
		this.phid = phid;
	}
	public Integer getDevid() {
		return devid;
	}
	public void setDevid(Integer devid) {
		this.devid = devid;
	}
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
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
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getS_category() {
		return s_category;
	}
	public void setS_category(String s_category) {
		this.s_category = s_category;
	}
	public String getSenaabbr() {
		return senaabbr;
	}
	public void setSenaabbr(String senaabbr) {
		this.senaabbr = senaabbr;
	}
	public String getSenaname() {
		return senaname;
	}
	public void setSenaname(String senaname) {
		this.senaname = senaname;
	}
	public String getSenadesc() {
		return senadesc;
	}
	public void setSenadesc(String senadesc) {
		this.senadesc = senadesc;
	}
	public String getProj_abbr() {
		return proj_abbr;
	}
	public void setProj_abbr(String proj_abbr) {
		this.proj_abbr = proj_abbr;
	}
	public String getPh_abbr() {
		return ph_abbr;
	}
	public void setPh_abbr(String ph_abbr) {
		this.ph_abbr = ph_abbr;
	}

	@Override
	public String toString() {
		return "[{\"Project Title\":\"" + proj_name + "\"}" +
				", {\"Phase Title\":\"" + ph_name + "\"" +"}" +
				", {\"Test Case Title\":\"" + senaname + "\"" +"}" +
				", {\"Test Case abbr\":\"" + senaabbr + "\"" +"}" +
				", {\"Test Case Description\":\"" + senadesc + "\"" +"}" +
				", {\"Expected Result\":\"" + expect + "\"" +"}" +
				", {\"App\":\"" + app_name + "\"" +"}" +
				", {\"Device\":\"" + dev_name + "\"" +"}" +
				"]";
	}


}