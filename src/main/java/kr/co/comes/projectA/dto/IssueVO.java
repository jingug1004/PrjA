package kr.co.comes.projectA.dto;

import java.sql.Date;

public class IssueVO {

	
	private int projid; // ������Ʈ ����
	private String proj_name;
	private int seq; // �̽� ����
	private int phid; // Phase ����
	private String ph_name;
	private String isudesc; // �̽� ����
	private Date adddate; // �������
	private Date upddate; // ��������
	private String adduser; //����� �����
	private String upduser; //������ �����
	private String isuname; // �̽���
	private String category; // �з� 0:note, 1:issue, 2:defect
	private String seriousness; // �ɰ��� 0:Critical, 1:Major, 2:Minor
	private String procedure; //��������
	private String status; // ���� 0:New, 1:Open, 2:Assigned, 3:Resolved, 4:Closed, 5:Reopened
	private String attach; //÷�� ���� �н�
	private String attachFullname;
	
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
	public int getProjid() {
		return projid;
	}
	public void setProjid(int projid) {
		this.projid = projid;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getPhid() {
		return phid;
	}
	public void setPhid(int phid) {
		this.phid = phid;
	}
	public String getIsudesc() {
		return isudesc;
	}
	public void setIsudesc(String isudesc) {
		this.isudesc = isudesc;
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
	public String getIsuname() {
		return isuname;
	}
	public void setIsuname(String isuname) {
		this.isuname = isuname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getAttachFullname() {
		return attachFullname;
	}
	public void setAttachFullname(String attachFullname) {
		this.attachFullname = attachFullname;
	}
	
	@Override
	public String toString() {
		return "[{\"Project Title\":\"" + proj_name + "\"}" + 
				", {\"Phase Title\":\"" + ph_name + "\"" +"}" + 
				", {\"Note Title\":\"" + isuname + "\"" +"}" + 
				", {\"Note Description\":\"" + isudesc + "\"" +"}" + 
				", {\"Note Category\":\"" + category + "\"" +"}" + 
				", {\"Defect Seriousness\":\"" + seriousness + "\"" +"}" + 
				", {\"Test Procedure\":\"" + procedure + "\"" +"}" + 
				", {\"Defect Status\":\"" + status + "\"" +"}" + 
				"]";
	}

}