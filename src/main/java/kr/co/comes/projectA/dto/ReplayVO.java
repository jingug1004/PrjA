package kr.co.comes.projectA.dto;

public class ReplayVO {

	private int projid; //프로젝트 순번
	private int senid; //시나리오 id
	private int resid; //결과 순번
	private int repeatnum; //리플레이순번
	private int seq; //이벤트 순번
	private String payload;
	private String result; //이벤트 결과
	private String param; //전체 파람 값
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