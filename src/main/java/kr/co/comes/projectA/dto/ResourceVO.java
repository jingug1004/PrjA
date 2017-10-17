package kr.co.comes.projectA.dto;

public class ResourceVO {

	private int projid;
	private int senid;
	private int resid;
	private int monitorno;
	private String cpu;
	private float cpu_user;
	private float cpu_kernel;
	private String memory;
	private String battery;
	private int battery_free;
	private float bat_tempo;
	private String network;
	private String action;
	private String param;
	private String cpu_min;
	private String cpu_max;
	private String memory_min;
	private String memory_max;
	private String battery_min;
	private String battery_max;
	private String network_min;
	private String network_max;
	
	
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
	public int getMonitorno() {
		return monitorno;
	}
	public void setMonitorno(int monitorno) {
		this.monitorno = monitorno;
	}
	public float getCpu_user() {
		return cpu_user;
	}
	public void setCpu_user(float cpu_user) {
		this.cpu_user = cpu_user;
	}
	public float getCpu_kernel() {
		return cpu_kernel;
	}
	public void setCpu_cernel(float cpu_kernel) {
		this.cpu_kernel = cpu_kernel;
	}
	public int getBattery_free() {
		return battery_free;
	}
	public void setBattery_free(int battery_free) {
		this.battery_free = battery_free;
	}
	public float getBat_tempo() {
		return bat_tempo;
	}
	public void setBat_tempo(float bat_tempo) {
		this.bat_tempo = bat_tempo;
	}
	
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public void setCpu_kernel(float cpu_kernel) {
		this.cpu_kernel = cpu_kernel;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
	
}