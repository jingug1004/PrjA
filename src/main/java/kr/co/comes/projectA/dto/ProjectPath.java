package kr.co.comes.projectA.dto;

public class ProjectPath {
	
	 //서버단
	 
	static private String  project_path = "/project/project/";
	static private String  phase_path="/project/phase/";
	static private String  case_path="/project/case/";
	static private String  download_path="/project/download/";
	static private String  license_path="/project/license/";
	static private String  issue_path="/project/issue/";
	static private String  result_path="/project/result/";
	static private String  event_path="/project/event/";
	
	//로컬
/*	static private String project_path = "C:\\zzz\\project";
	static private String phase_path="C:\\zzz\\phase";
	static private String case_path="C:\\zzz\\case";
	static private String download_path="C:\\zzz\\download\\";
	static private String license_path="C:\\zzz\\license";
	static private String issue_path="C:\\zzz\\issue";
	static private  String result_path="C:\\zzz\\result";
	static private String event_path="C:\\zzz\\event";*/
	
	public static String getProject_path() {
		return project_path;
	}
	public static void setProject_path(String project_path) {
		ProjectPath.project_path = project_path;
	}
	public static String getPhase_path() {
		return phase_path;
	}
	public static void setPhase_path(String phase_path) {
		ProjectPath.phase_path = phase_path;
	}
	public static String getCase_path() {
		return case_path;
	}
	public static void setCase_path(String case_path) {
		ProjectPath.case_path = case_path;
	}
	public static String getDownload_path() {
		return download_path;
	}
	public static void setDownload_path(String download_path) {
		ProjectPath.download_path = download_path;
	}
	public static String getLicense_path() {
		return license_path;
	}
	public static void setLicense_path(String license_path) {
		ProjectPath.license_path = license_path;
	}
	public static String getIssue_path() {
		return issue_path;
	}
	public static void setIssue_path(String issue_path) {
		ProjectPath.issue_path = issue_path;
	}
	public static String getResult_path() {
		return result_path;
	}
	public static void setResult_path(String result_path) {
		ProjectPath.result_path = result_path;
	}
	public static String getEvent_path() {
		return event_path;
	}
	public static void setEvent_path(String event_path) {
		ProjectPath.event_path = event_path;
	}
	
	
	
}
