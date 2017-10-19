package kr.co.comes.projectA.dto;

import java.sql.Date;

public class ReportVO {

	//각 아이디값
	int projid;
	int senid;

	//리포트 최 상단 테이블
	String projname;
	int phcount;
	int secount;
	int trecount;
	int trepcount;
	int trefcount;
	Date fromdate;
	Date todate;
	String name;

	//테스트 상세 프로젝트
	/*String projabbr;
	String projdesc;
	String projuser;*/
	String user;
	String abbr;
	String desc;

	//테스트 상세 케이스
	String senaname;
	String senaabbr;
	String senadesc;
	String senacategory;
	int recount;
	int repcount;
	int refcount;

	//테스트 구간
	String devname;
	String appname;
	String result;
	String resname;

	//테스트 성공
	Date starttime;
	Date endtime;
	String cpu_min;
	String cpu_max;
	String memory_min;
	String memory_max;
	String battery_min;
	String battery_max;
	String network_min;
	String network_max;

	//테스트 실패
	String seriousness;
	String reason;
	String procedure;
	int senaid;
	int phid;
	
	
	
	
	public int getPhid() {
		return phid;
	}
	public void setPhid(int phid) {
		this.phid = phid;
	}
	public int getSenaid() {
		return senaid;
	}
	public void setSenaid(int senaid) {
		this.senaid = senaid;
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
	public void setSenid(int senid) {
		this.senid = senid;
	}
	public String getProjname() {
		return projname;
	}
	public void setProjname(String projname) {
		this.projname = projname;
	}
	public int getPhcount() {
		return phcount;
	}
	public void setPhcount(int phcount) {
		this.phcount = phcount;
	}
	public int getSecount() {
		return secount;
	}
	public void setSecount(int secount) {
		this.secount = secount;
	}
	public int getTrecount() {
		return trecount;
	}
	public void setTrecount(int trecount) {
		this.trecount = trecount;
	}
	public int getTrepcount() {
		return trepcount;
	}
	public void setTrepcount(int trepcount) {
		this.trepcount = trepcount;
	}
	public int getTrefcount() {
		return trefcount;
	}
	public void setTrefcount(int trefcount) {
		this.trefcount = trefcount;
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
	/*public String getProjabbr() {
		return projabbr;
	}
	public void setProjabbr(String projabbr) {
		this.projabbr = projabbr;
	}
	public String getProjdesc() {
		return projdesc;
	}
	public void setProjdesc(String projdesc) {
		this.projdesc = projdesc;
	}
	public String getProjuser() {
		return projuser;
	}
	public void setProjuser(String projuser) {
		this.projuser = projuser;
	}*/
	public String getSenaname() {
		return senaname;
	}
	public void setSenaname(String senaname) {
		this.senaname = senaname;
	}
	public String getSenaabbr() {
		return senaabbr;
	}
	public void setSenaabbr(String senaabbr) {
		this.senaabbr = senaabbr;
	}
	public String getSenadesc() {
		return senadesc;
	}
	public void setSenadesc(String senadesc) {
		this.senadesc = senadesc;
	}
	public String getSenacategory() {
		return senacategory;
	}
	public void setSenacategory(String senacategory) {
		this.senacategory = senacategory;
	}
	public int getRecount() {
		return recount;
	}
	public void setRecount(int recount) {
		this.recount = recount;
	}
	public int getRepcount() {
		return repcount;
	}
	public void setRepcount(int repcount) {
		this.repcount = repcount;
	}
	public int getRefcount() {
		return refcount;
	}
	public void setRefcount(int refcount) {
		this.refcount = refcount;
	}
	public String getDevname() {
		return devname;
	}
	public void setDevname(String devname) {
		this.devname = devname;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getCpu_min() {
		return cpu_min;
	}
	public void setCpu_min(String cpu_min) {
		this.cpu_min = cpu_min;
	}
	public String getCpu_max() {
		return cpu_max;
	}
	public void setCpu_max(String cpu_max) {
		this.cpu_max = cpu_max;
	}
	public String getMemory_min() {
		return memory_min;
	}
	public void setMemory_min(String memory_min) {
		this.memory_min = memory_min;
	}
	public String getMemory_max() {
		return memory_max;
	}
	public void setMemory_max(String memory_max) {
		this.memory_max = memory_max;
	}
	public String getBattery_min() {
		return battery_min;
	}
	public void setBattery_min(String battery_min) {
		this.battery_min = battery_min;
	}
	public String getBattery_max() {
		return battery_max;
	}
	public void setBattery_max(String battery_max) {
		this.battery_max = battery_max;
	}
	public String getNetwork_min() {
		return network_min;
	}
	public void setNetwork_min(String network_min) {
		this.network_min = network_min;
	}
	public String getNetwork_max() {
		return network_max;
	}
	public void setNetwork_max(String network_max) {
		this.network_max = network_max;
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getResname() {
		return resname;
	}
	public void setResname(String resname) {
		this.resname = resname;
	}
	
	
	
	
}
