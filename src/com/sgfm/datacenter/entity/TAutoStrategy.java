package com.sgfm.datacenter.entity;

import java.math.BigDecimal;

/**
 * TAutoStrategy entity. @author MyEclipse Persistence Tools
 */

public class TAutoStrategy implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private BigDecimal esid;
	private String type;
	private String name;
	private BigDecimal person_count;
	private String hour;
	private String minute;
	private String second;

	// Constructors

	/** default constructor */
	public TAutoStrategy() {
	}

	/** minimal constructor */
	public TAutoStrategy(BigDecimal id, BigDecimal esid, String type) {
		this.id = id;
		this.esid = esid;
		this.type = type;
	}

	/** full constructor */
	public TAutoStrategy(BigDecimal id, BigDecimal esid, String type,
			String name, BigDecimal personCount, String hour, String minute,
			String second) {
		this.id = id;
		this.esid = esid;
		this.type = type;
		this.name = name;
		this.person_count = personCount;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	// Property accessors

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getEsid() {
		return this.esid;
	}

	public void setEsid(BigDecimal esid) {
		this.esid = esid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPersonCount() {
		return this.person_count;
	}

	public void setPersonCount(BigDecimal personCount) {
		this.person_count = personCount;
	}

	public String getHour() {
		return this.hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return this.minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecond() {
		return this.second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

}