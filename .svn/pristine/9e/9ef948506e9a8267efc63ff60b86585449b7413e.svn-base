package com.sgfm.datacenter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the S_CONSIGNEE_ADDRESS database table.
 * 
 */
@Entity
@Table(name="S_CONSIGNEE_ADDRESS")
@NamedQuery(name="SConsigneeAddress.findAll", query="SELECT s FROM SConsigneeAddress s")
public class SConsigneeAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long caid;

	private String address;

	private Integer city;

	private Integer county;

	@Column(name="IS_DEFAULT")
	private Integer isDefault;

	private Integer mid;

	private String mobile;

	private String name;

	private Integer province;

	public SConsigneeAddress() {
	}

	public long getCaid() {
		return this.caid;
	}

	public void setCaid(long caid) {
		this.caid = caid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getCounty() {
		return this.county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}

	public Integer getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProvince() {
		return this.province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

}