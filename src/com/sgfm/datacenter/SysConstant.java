package com.sgfm.datacenter;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于常量定义类
 * 
 * @author 
 * @date 
 */

@SuppressWarnings("serial")
public class SysConstant { 
    // //////通用的常量//////////////////////////////////////////////

    /** 系统保留字符 */
	
	/**
	 * 保存tmember登入成功信息
	 */
    public static final String SYS_MEMBER_LOG_SUCC_INFO = "memberLogSuccInfo_pcpt";
    /**
    * 保存account信息
    */
    public static final String SYS_ACCOUNT_LOG_SUCC_INFO = "account_log_succ_info_pcpt";
    /**
     * 保存进入商品详情页地址
     */
    public static final String SYS_PRODUCT_DETAILS_URL = "product_details_url_pcpt";
    /*
     * 保存城市id
     */
    public static final String SYS_CITYID = "localCityId_pcpt";
    public static final String SYS_CITYNAME = "cityname_pcpt";
    public static final String SYS_CITYAREANO = "cityareano_pcpt";
    
    public static final String COOKIE_CITYNAME = "cookie_cityName";
    
    /**
     * Cookie生命周期
     */
    public static final int COOKIE_LIFRCYCLE_DELETE = 0; //立即删除
    public static final int COOKIE_LIFRCYCLE_NOWADAY = -1; //无生命周期
    public static final int COOKIE_LIFRCYCLE_AWEEK = 60 * 60 * 24 * 7; //一周
    public static final int COOKIE_LIFRCYCLE_AMONTH = 60 * 60 * 24 * 30; //30天
    public static final int COOKIE_LIFRCYCLE_HOUR = 60 * 30; //半小时
    
    /**
     * 短信发送限制条数
     */
    public static final int MSG_CHECKCOUNT = 100; //当天限制短信条数
    
    public static final int MSG_SINGLE_CHECKCOUNT = 20; //当天单个用户短信限制条数
    
	public static final String TW_TOKEN = "tw_token";
	
	public static final String OPENID = "openid";
	
	public static final String IS_MEMBER = "is_member"; //1没有openid记录  2有openid没有注册  3有openid并且注册
	
	
	/**
	 * 阿里大于参数
	 */
	public static final String SERVER_URL = "https://eco.taobao.com/router/rest";
	
	public static final String APP_KEY = "23655325";
	
	public static final String APP_SECRET = "21f92831c2e29964b389fff50f5e7a8f";
	
	/**
	 * 微信接口参数
	 */
	public static final String WX_APPID = "wx44a26b8a7ef26809";//微信appid
	
	public static final String WX_APP_SECRET = "ce5575b427a386ce491e2549a515c4b0";//微信appSecret
	
	public static final String WX_ACCESS_TOKEN = "wxAccessToken";//微信accessToken
	
	public static final String WX_TICKET = "wxTicket";//微信ticket
	
	
}
