package kr.co.comes.projectA.dto;

import java.sql.Date;

public class ListCriteria extends Criteria{
	
	private char user_role; //사용자 권한
	private String user; // 사용자
	private String keyword; // 검색 키워드
	private String ph_keyword;
	private String n_keyword;
	private String sort; // 정렬 
	private String[] category; // 정렬 카테고리
	private String[] seriousness;
	private String[] status;
	private int projid;
	private int phid;
	private int senid;
	private Date fromdate;
	private Date todate;
	private String result;
	
	
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String[] getSeriousness() {
		return seriousness;
	}
	public void setSeriousness(String[] seriousness) {
		this.seriousness = seriousness;
	}
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}
	public char getUser_role() {
		return user_role;
	}
	public void setUser_role(char user_role) {
		this.user_role = user_role;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String[] getCategory() {
		return category;
	}
	public void setCategory(String[] category) {
		this.category = category;
	}
	
	public int getProjid() {
		return projid;
	}
	public void setProjid(int projid) {
		this.projid = projid;
	}
	
	public int getPhid() {
		return phid;
	}
	public void setPhid(int phid) {
		this.phid = phid;
	}
	public String getPh_keyword() {
		return ph_keyword;
	}
	public void setPh_keyword(String ph_keyword) {
		this.ph_keyword = ph_keyword;
	}

	public String getN_keyword() {
		return n_keyword;
	}
	public void setN_keyword(String n_keyword) {
		this.n_keyword = n_keyword;
	}

	@Override
	public String toString(){
		return "*****ProjectListVO : [user_role]:"+user_role+"  [user]:"+user+
				"  [keyword]:"+keyword+"  [category]:"+category+"  [sort]:"+sort;
	}
	public int getSenid() {
		return senid;
	}
	public void setSenid(int senid) {
		this.senid = senid;
	}
	
	
}