package kr.co.comes.projectA.dto;

import java.sql.Date;

public class CaseVO {

	private int projid; //������Ʈ ����
	private String proj_name; //������Ʈ ��
	private String proj_abbr; //������Ʈ ��
	private int senid; //�ó����� id
	private int phid; //phase ����
	private String ph_name; //Phase ��
	private String ph_abbr; //Phase ��
	private String senaabbr; //�ó����� ���
	private String senaname; //�ó�����  �̸�
	private String senadesc; //�ó����� ����
	private Integer devid; // �⺻ ����̽� ����
	private String dev_name; // ����̽� �̸�
	private Integer appid; //App ����
	private String app_name; //App name
	private int result; //��� ����
	private Date adddate; //��ϳ�¥
	private Date upddate; //������¥
	private String user; //����� �����
	private String upduser; //������ �����
	private String expect; //���� ���
	private String attach; //÷�� ���� �н�
	private String s_category; // �з� 0:�Ŵ��� �׽�Ʈ, 1:���ҽ� ����͸�, 2:�׽�Ʈ �ڵ�ȭ
	
	
	
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