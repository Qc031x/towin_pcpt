package com.sgfm.datacenter.entity;

/**
 * TMember entity. @author MyEclipse Persistence Tools
 */

public class TMember implements java.io.Serializable {

	// Fields

	private Integer mid;
	private String account;
	private String password;
	private String openid;
	private String mobile;
	private String idcard;
	private Integer MType;
	private Integer employeeId;
	private String registerDate;
	private String lastLogin;
	private Integer status;
	private String name;

	// Constructors

	/** default constructor */
	public TMember() {
	}

	/** minimal constructor */
	public TMember(Integer mid, Integer status) {
		this.mid = mid;
		this.status = status;
	}

	/** full constructor */
	public TMember(Integer mid, String account, String password,
			String openid, String mobile, String idcard, Integer MType,
			Integer employeeId, String registerDate, String lastLogin,
			Integer status, String name) {
		this.mid = mid;
		this.account = account;
		this.password = password;
		this.openid = openid;
		this.mobile = mobile;
		this.idcard = idcard;
		this.MType = MType;
		this.employeeId = employeeId;
		this.registerDate = registerDate;
		this.lastLogin = lastLogin;
		this.status = status;
		this.name = name;
	}

	// Property accessors

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getMType() {
		return this.MType;
	}

	public void setMType(Integer MType) {
		this.MType = MType;
	}

	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}