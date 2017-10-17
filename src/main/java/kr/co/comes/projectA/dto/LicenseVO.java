package kr.co.comes.projectA.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class LicenseVO {
	
	MultipartFile file;
	private int serverid;
	private String commercial;
	private String adduser;
	private String upduser;
	private int maxuser;
	private int maxdevice;
	private Date enddate;
	private Date adddate;
	private Date upddate;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getServerid() {
		return serverid;
	}
	public void setServerid(int serverid) {
		this.serverid = serverid;
	}
	public String getCommercial() {
		return commercial;
	}
	public void setCommercial(String commercial) {
		this.commercial = commercial;
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
	public int getMaxuser() {
		return maxuser;
	}
	public void setMaxuser(int maxuser) {
		this.maxuser = maxuser;
	}
	public int getMaxdevice() {
		return maxdevice;
	}
	public void setMaxdevice(int maxdevice) {
		this.maxdevice = maxdevice;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
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
	
	@Override
	public String toString() {
		return "LicenseVO [commercial=" + commercial + ", adduser=" + adduser + ", upduser=" + upduser + ", maxuser="
				+ maxuser + ", maxdevice=" + maxdevice + ", enddate=" + enddate + ", adddate=" + adddate + ", upddate="
				+ upddate + "]";
	}
	
	
}
