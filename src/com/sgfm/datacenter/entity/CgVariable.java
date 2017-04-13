package com.sgfm.datacenter.entity;

/**
 * @author Administrator
 * 
 */
public class CgVariable implements java.io.Serializable {

	private String card;
	private String password;
	private String id;
	private String pid;
	private String countyid; 
	private String price;
	private String paixu;
	private String categoryidOne;
	private String categoryidTwo;
	private String categoryidFour;
	private String majorProject;
	private String startPrice;
	private String endPrice;
	private String esid; // 获取体检机构id
	private String currentPage;
	private String condition; // 获取搜索关键字
	private String mobile;
	private String person;
	private String disprice;
	private String field;
	private String brandId;
	private String shaixuan;
	private String ids; // 前台删除ID集合
	

	/*以下字段是从原先商城整合过来的*/
	private String parentid; // 获取页面的省id
	private String prosex; // 获取性别条件
	private String oid; // 订单号
	private String phone; // 手机号
	private String pname; // 发送人	
	private double integral; //积分
	private double coupon; //优惠券余额
	private double total;
	private String position; // 获取商品类型
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getDisprice() {
		return disprice;
	}

	public void setDisprice(String disprice) {
		this.disprice = disprice;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getCountyid() {
		return countyid;
	}

	public void setCountyid(String countyid) {
		this.countyid = countyid;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPaixu() {
		return paixu;
	}

	public void setPaixu(String paixu) {
		this.paixu = paixu;
	}

	public String getCategoryidOne() {
		return categoryidOne;
	}

	public void setCategoryidOne(String categoryidOne) {
		this.categoryidOne = categoryidOne;
	}

	public String getCategoryidTwo() {
		return categoryidTwo;
	}

	public void setCategoryidTwo(String categoryidTwo) {
		this.categoryidTwo = categoryidTwo;
	}

	public String getCategoryidFour() {
		return categoryidFour;
	}

	public void setCategoryidFour(String categoryidFour) {
		this.categoryidFour = categoryidFour;
	}

	public String getMajorProject() {
		return majorProject;
	}

	public void setMajorProject(String majorProject) {
		this.majorProject = majorProject;
	}

	public String getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}

	public String getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return the parentid
	 */
	public String getParentid() {
		return parentid;
	}

	/**
	 * @param parentid the parentid to set
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	/**
	 * @return the prosex
	 */
	public String getProsex() {
		return prosex;
	}

	/**
	 * @param prosex the prosex to set
	 */
	public void setProsex(String prosex) {
		this.prosex = prosex;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	/**
	 * @return the integral
	 */
	public double getIntegral() {
		return integral;
	}

	/**
	 * @param integral the integral to set
	 */
	public void setIntegral(double integral) {
		this.integral = integral;
	}

	/**
	 * @return the coupon
	 */
	public double getCoupon() {
		return coupon;
	}

	/**
	 * @param coupon the coupon to set
	 */
	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @return the shaixuan
	 */
	public String getShaixuan() {
		return shaixuan;
	}

	/**
	 * @param shaixuan the shaixuan to set
	 */
	public void setShaixuan(String shaixuan) {
		this.shaixuan = shaixuan;
	}

	/**
	 * @return the brandId
	 */
	public String getBrandId() {
		return brandId;
	}

	/**
	 * @param brandId the brandId to set
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	
}