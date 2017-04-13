package com.sgfm.datacenter.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_USER_MSGRECORD database table.
 * 
 */
@Entity
@Table(name="T_USER_MSGRECORD")
@NamedQuery(name="TUserMsgrecord.findAll", query="SELECT t FROM TUserMsgrecord t")
public class TUserMsgrecord implements Serializable {
	private static final long serialVersionUID = 1L;

	public TUserMsgrecord() {
	}
	
	private Integer id;
	
	private Integer bussinessType;
	
	private Integer userId;
	
	private Integer companyId;
	
	private Integer batchId;
	
	private String createDate;
	
	private String code;
	
	private String mobile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBussinessType() {
		return bussinessType;
	}

	public void setBussinessType(Integer bussinessType) {
		this.bussinessType = bussinessType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}