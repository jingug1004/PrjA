package kr.co.comes.projectA.dto;

import java.sql.Date;
import java.sql.Time;

public class ResultVO {

	private int projid;
	private String proj_name;
	private String proj_abbr;
	private int phid;
	private String ph_name;
	private String ph_abbr;
	private int senid; // 시나리오 아이디
	private String s_name; // 시나리오 이름
	private String s_abbr;
	private String c_category;
	private int resid; // 결과순번
	private String resname;
	private Integer devid;
	private String dev_name; // 디바이스 이름
	private Integer appid;
	private String app_name; // 앱 이름
	private int repeat;// 테스트 반복 횟수
	private String starttime; 
	private String endtime; // 모니터 종료시간
	private int interval; // 모니터 주기
	private String result; // Case 수행 결과 0:Pass, 1:Fail, 2:N/A, 3:N/I
	private String reason; // 실패사유
	private String seriousness; // 심각도 0:Critical, 1:Major, 2:Minor
	private String procedure; // 재현 절차
	private String status; // 상태 0:New, 1:Open, 2:Assigned, 3:Resolved,
							// 4:Closed, 5:Reopened
	private String attach; // 첨부 파일 패스
	private String systemlog; // 시스템 로그
	private String eventlog; // 이벤트 로그
	private String etclog; // 기타 로그
	private Date adddate; // 등록일자
	private Time addtime; // 등록시간
	private String adduser; // 등록한 사용자

	
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

	public String getS_abbr() {
		return s_abbr;
	}

	public void setS_abbr(String s_abbr) {
		this.s_abbr = s_abbr;
	}

	public int getProjid() {
		return projid;
	}

	public void setProjid(int projid) {
		this.projid = projid;
	}

	public String getProj_name() {
		return proj_name;
	}

	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}

	public int getPhid() {
		return phid;
	}

	public void setPhid(int phid) {
		this.phid = phid;
	}

	public String getPh_name() {
		return ph_name;
	}

	public void setPh_name(String ph_name) {
		this.ph_name = ph_name;
	}

	public int getSenid() {
		return senid;
	}

	public void setSenid(int senid) {
		this.senid = senid;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}

	public String getResname() {
		return resname;
	}

	public void setResname(String resname) {
		this.resname = resname;
	}

	public String getDev_name() {
		return dev_name;
	}

	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSeriousness() {
		return seriousness;
	}

	public void setSeriousness(String seriousness) {
		this.seriousness = seriousness;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getSystemlog() {
		return systemlog;
	}

	public void setSystemlog(String systemlog) {
		this.systemlog = systemlog;
	}

	public String getEventlog() {
		return eventlog;
	}

	public void setEventlog(String eventlog) {
		this.eventlog = eventlog;
	}

	public String getEtclog() {
		return etclog;
	}

	public void setEtclog(String etclog) {
		this.etclog = etclog;
	}

	public Date getAdddate() {
		return adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

	public Time getAddtime() {
		return addtime;
	}

	public void setAddtime(Time addtime) {
		this.addtime = addtime;
	}

	public String getAdduser() {
		return adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}
	
	
	@Override
	public String toString() {
		return "[{\"Project Title\":\"" + proj_name + "\"}" + 
				", {\"Phase Title\":\"" + ph_name + "\"" +"}" + 
				", {\"Case Title\":\"" + s_name + "\"" +"}" + 
				", {\"Result Title\":\"" + s_name + "\"" +"}" + 
				", {\"Device\":\"" + dev_name + "\"" +"}" + 
				", {\"App\":\"" + app_name + "\"" +"}" + 
				", {\"Test Procedure\":\"" + procedure + "\"" +"}" + 
				", {\"starttime\":\"" + starttime + "\"" +"}" + 
				", {\"endtime\":\"" + endtime + "\"" +"}" + 
				", {\"Reason\":\"" + reason + "\"" +"}" + 
				", {\"repeat\":\"" + repeat + "\"" +"}" + 
				", {\"interval\":\"" + interval + "\"" +"}" + 
				"]";
	}

	public String getC_category() {
		return c_category;
	}

	public void setC_category(String c_category) {
		this.c_category = c_category;
	}
	

}