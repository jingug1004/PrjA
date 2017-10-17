package kr.co.comes.projectA.dto;

/**
 * @author mins : login 입력 데이터
 * 
 *
 */

public class LoginVO {

	private String id;
	private String pwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "[{\"id\":\"" + id + "\"}" + ", {\"pwd\":\"" + pwd + "\"" +"}]";
	}
}