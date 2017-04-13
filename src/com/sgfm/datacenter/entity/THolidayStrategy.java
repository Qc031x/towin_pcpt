package com.sgfm.datacenter.entity;

import java.math.BigDecimal;

/**
 * THolidayStrategy entity. @author MyEclipse Persistence Tools
 */

public class THolidayStrategy implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private BigDecimal esid;
	private String type;
	private String name;
	private String startdate;
	private String enddate;

	// Constructors

	/** default constructor */
	public THolidayStrategy() {
	}

	/** minimal constructor */
	public THolidayStrategy(BigDecimal id, BigDecimal esid, String type) {
		this.id = id;
		this.esid = esid;
		this.type = type;
	}

	/** full constructor */
	public THolidayStrategy(BigDecimal id, BigDecimal esid, String type,
			String name, String startdate, String enddate) {
		this.id = id;
		this.esid = esid;
		this.type = type;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
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

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

}