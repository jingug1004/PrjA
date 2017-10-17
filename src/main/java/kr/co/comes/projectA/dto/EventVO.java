package kr.co.comes.projectA.dto;

import java.sql.Date;

public class EventVO {

	private int projid; //������Ʈ ����
	private int senid; //�ó����� id
	private int seq; //�̺�Ʈ ����
	private String evtdesc; //�̺�Ʈ ����
	private String evtact; //������ �׼�
	private String objid; //App ȭ�� ������Ʈ ID
	private String xy;
	private int location_x1; //x��ǥ
	private int location_x2;
	private int location_x3;
	private int location_y1; //y��ǥ
	private int location_y2;
	private int location_y3;
	private String image; //�̺�Ʈ ĸ�� ���� ��ġ
	private Date adddate; //�������
	private String adduser; //�������
	private String param; //scenario_auto���� ���޹��� param�� 
	private String filename;
	private Integer appid;
	private Integer devid;
	private String msg;
	
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
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getEvtdesc() {
		return evtdesc;
	}
	public void setEvtdesc(String evtdesc) {
		this.evtdesc = evtdesc;
	}
	public String getEvtact() {
		return evtact;
	}
	public void setEvtact(String evtact) {
		this.evtact = evtact;
	}
	public String getObjid() {
		return objid;
	}
	public void setObjid(String objid) {
		this.objid = objid;
	}
	public int getLocation_x1() {
		return location_x1;
	}
	public void setLocation_x1(int location_x1) {
		this.location_x1 = location_x1;
	}
	public int getLocation_x2() {
		return location_x2;
	}
	public void setLocation_x2(int location_x2) {
		this.location_x2 = location_x2;
	}
	public int getLocation_x3() {
		return location_x3;
	}
	public void setLocation_x3(int location_x3) {
		this.location_x3 = location_x3;
	}
	public int getLocation_y1() {
		return location_y1;
	}
	public void setLocation_y1(int location_y1) {
		this.location_y1 = location_y1;
	}
	public int getLocation_y2() {
		return location_y2;
	}
	public void setLocation_y2(int location_y2) {
		this.location_y2 = location_y2;
	}
	public int getLocation_y3() {
		return location_y3;
	}
	public void setLocation_y3(int location_y3) {
		this.location_y3 = location_y3;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Override
	public String toString() {
		return "[{\"Action\":\"" + evtact + "\"}" + 
				", {\"image\":\"" + image + "\"" +"}" + 
				", {\"desc\":\"" + evtdesc + "\"" +"}" + 
				", {\"objid\":\"" + objid + "\"" +"}" + 
				"]";
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}