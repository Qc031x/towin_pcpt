package com.sgfm.datacenter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the S_ORDER database table.
 * 
 */
@Entity
@Table(name="S_ORDER")
@NamedQuery(name="SOrder.findAll", query="SELECT s FROM SOrder s")
public class SOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String oid;

	@Column(name="F_WHERE")
	private String fWhere;

	private String ip;

	@Column(name="IS_VALID")
	private Integer isValid;

	private Integer mid;

	@Column(name="POST_DATE")
	private String postDate;

	@Column(name="SUM_PRICE")
	private Double sumPrice;
	
	@Column(name="PAY_STATUS")
	private Integer payStatus;
	
	@Column(name="ORDER_TYPE")
	private Integer orderType;
	
	private Integer caid;
	
	private Integer payType;
	
	private String payedTime;
	
	private Double surplus;
	
	private String tradeNote;


	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPayedTime() {
		return payedTime;
	}

	public void setPayedTime(String payedTime) {
		this.payedTime = payedTime;
	}

	public Double getSurplus() {
		return surplus;
	}

	public void setSurplus(Double surplus) {
		this.surplus = surplus;
	}

	public String getTradeNote() {
		return tradeNote;
	}

	public void setTradeNote(String tradeNote) {
		this.tradeNote = tradeNote;
	}

	public Integer getCaid() {
		return caid;
	}

	public void setCaid(Integer caid) {
		this.caid = caid;
	}

	public SOrder() {
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getFWhere() {
		return this.fWhere;
	}

	public void setFWhere(String fWhere) {
		this.fWhere = fWhere;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getPostDate() {
		return this.postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public Double getSumPrice() {
		return this.sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getfWhere() {
		return fWhere;
	}

	public void setfWhere(String fWhere) {
		this.fWhere = fWhere;
	}

	
}