package kr.co.comes.projectA.dto;

public class ReplayVO {

	private int projid; //������Ʈ ����
	private int senid; //�ó����� id
	private int resid; //��� ����
	private int repeatnum; //���÷��̼���
	private int seq; //�̺�Ʈ ����
	private String payload;
	private String result; //�̺�Ʈ ���
	private String param; //��ü �Ķ� ��
	private String msg; // msg
	
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
	public int getResid() {
		return resid;
	}
	public void setResid(int resid) {
		this.resid = resid;
	}
	public int getRepeatnum() {
		return repeatnum;
	}
	public void setRepeatnum(int repeatnum) {
		this.repeatnum = repeatnum;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	
	
	
}