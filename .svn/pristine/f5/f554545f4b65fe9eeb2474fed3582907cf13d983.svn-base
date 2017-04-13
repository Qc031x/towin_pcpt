package com.sgfm.datacenter.action.shop;


import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sgfm.base.action.BaseAction;
import com.sgfm.datacenter.SysConstant;
import com.sgfm.datacenter.entity.CgVariable;
import com.sgfm.datacenter.exception.AppException;
import com.sgfm.datacenter.service.shop.ShopService;
import com.sgfm.datacenter.util.JsonResponseResult;
import com.sgfm.datacenter.util.SysUtils;

@Controller
@Scope("prototype")
public class ShopAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ShopService shopService;
	
	public CgVariable cgVariable = new CgVariable();
	
	/**
	 * 跳转至机构列表页面
	 * @return
	 * @author JingZeKuan
	 */
	public String toEntityShopListPage(){
		 try {
			 String cityId = super.getRequest().getSession().getAttribute(SysConstant.SYS_CITYID).toString();
			 
			 String distance = super.getRequest().getParameter("distance");
			 
			 HashMap<String, Object> param = new HashMap<String, Object>();
			 param.put("categoryid", cgVariable.getCategoryidFour());
			 param.put("county", cgVariable.getCountyid());
			 param.put("paixu", cgVariable.getPaixu());
			 param.put("brandId", cgVariable.getId());
			 param.put("city", cityId);
	         param.put("distance", distance);
	            
             super.getRequest().setAttribute("cgVariable", cgVariable);
             super.getRequest().setAttribute("distance", distance);

             /*if(null==super.getRequest().getSession().getAttribute("LOCATION_POINT_LNG")){
                return super.toContentView("shop", "getPoint");
             }*/
             param.put("locationPoint_lng", super.getRequest().getSession().getAttribute("LOCATION_POINT_LNG"));
             param.put("locationPoint_lat", super.getRequest().getSession().getAttribute("LOCATION_POINT_LAT"));

             HashMap<String, Object> infoMap = shopService.toShopCenter(param);
             super.getRequest().setAttribute("countryList", infoMap.get("countryList"));
             super.getRequest().setAttribute("branchTypeList", infoMap.get("branchTypeList"));

             HashMap<String, Object> MMap = shopService.getEntityShopList(param);

             super.getRequest().setAttribute("pages", MMap.get("pages"));
             super.getRequest().setAttribute("medList", JSONArray.fromObject(MMap.get("list")).toString());

	        } catch (final AppException app) {
	            this.logger.error(app);
	        } catch (final Exception e) {
	            this.logger.error(e);
	        }
	        return super.toContentView("shop", "entityShopList");	
	}
	
	 /**
     * 将用户当前坐标存入session
     * 
     * @return
     */
    public String setLocationPoint() {

        JsonResponseResult result = null;
        try {
            // 将用户坐标存入session
            super.getRequest().getSession().setAttribute("LOCATION_POINT_LNG", super.getRequest().getParameter("locationPoint_lng"));
            super.getRequest().getSession().setAttribute("LOCATION_POINT_LAT", super.getRequest().getParameter("locationPoint_lat"));
            super.getRequest().getSession().setAttribute("CITY", super.getRequest().getParameter("city"));
            super.getRequest().getSession().setAttribute("DISTRICT", super.getRequest().getParameter("district"));
            super.getRequest().getSession().setAttribute("ADDRESS", super.getRequest().getParameter("address"));

        } catch (final AppException app) {
            logger.error(app);
            final String msg = getText(app.getMessage());
            result = JsonResponseResult.createFalied(msg);
            super.jsonResult = JSONObject.fromObject(result).toString();
        } catch (final Exception e) {
            e.printStackTrace();
            logger.error(e);
            final String msg = getText("操作失败！");
            result = JsonResponseResult.createFalied(msg);
            super.jsonResult = JSONObject.fromObject(result).toString();
        }
        return BaseAction.JSON;
    }
	
	/**
     * 机构列表页面的加载更多
     */
    public String ajaxMedLoadMore() {
        JsonResponseResult result = null;
        try {
            String cityId = super.getRequest().getSession().getAttribute(SysConstant.SYS_CITYID).toString();

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("categoryid", cgVariable.getCategoryidFour());
            param.put("county", cgVariable.getCountyid());
            param.put("paixu", cgVariable.getPaixu());
            param.put("brandId", cgVariable.getId());
            param.put("city", cityId);
            param.put("pageNo", cgVariable.getCurrentPage());
            
            String distance = super.getRequest().getParameter("distance");
            param.put("distance", distance);
            super.getRequest().setAttribute("distance", distance);

            param.put("locationPoint_lng", super.getRequest().getSession().getAttribute("LOCATION_POINT_LNG"));
            param.put("locationPoint_lat", super.getRequest().getSession().getAttribute("LOCATION_POINT_LAT"));
            
            super.getRequest().setAttribute("cgVariable", cgVariable);
            HashMap<String, Object> MMap = shopService.getEntityShopList(param);

            super.jsonResult = JSONArray.fromObject(MMap.get("list")).toString();
        } catch (final AppException app) {
            logger.error(app);
            final String msg = getText(app.getMessage());
            result = JsonResponseResult.createFalied(msg);
            super.jsonResult = JSONObject.fromObject(result).toString();
        } catch (final Exception e) {
            e.printStackTrace();
            logger.error(e);
            final String msg = getText("操作失败！");
            result = JsonResponseResult.createFalied(msg);
            super.jsonResult = JSONObject.fromObject(result).toString();
        }
        return BaseAction.JSON;
    }
    
    /**
     * 跳转到分院详情页
     * @return
     * @author kangliangyu
     */
	public String toBranchDetails(){
		
		try {
			 	String esid = super.getRequest().getParameter("esid");
			 	if(esid == null){
			 		this.getResponse().sendError(404);
	                return "";
			 	}
			 	
			 	HashMap<String, Object> map = new HashMap<String, Object>();
			 	map.put("esid", esid);
	            map.put("city", super.getRequest().getSession().getAttribute(SysConstant.SYS_CITYID).toString());

	            if (null == super.getRequest().getSession().getAttribute("LOCATION_POINT_LNG")) {
	            	return super.toContentView("shop", "getPoint");
	            }
	            map.put("LOCATION_POINT_LNG", super.getRequest().getSession().getAttribute("LOCATION_POINT_LNG"));
	            map.put("LOCATION_POINT_LAT", super.getRequest().getSession().getAttribute("LOCATION_POINT_LAT"));
	            HashMap<String, Object> info = shopService.findMECdetail(map);
	            
	            if(info == null || info.isEmpty()){
	            	this.getResponse().sendError(404);
	                return "";
	            }

	            super.getRequest().setAttribute("info", info);
        } catch (final AppException app) {
            this.logger.error(app);
        } catch (final Exception e) {
            this.logger.error(e);
        }
		
	    return super.toContentView("shop", "branchDetails");
	}
	
	public String findMECtaocan() {

        JsonResponseResult result = null;
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("cgVariable", cgVariable);
            param.put("page", super.getRequest().getParameter("page"));
            HashMap<String, Object> info = shopService.findMECtaocan(param);

            result = JsonResponseResult.createSuccess();

            result.addData(info);

            result.setReturncode(0);
        } catch (final Exception e) {
            e.printStackTrace();
            final String msg = this.getText("操作失败！");
            result = JsonResponseResult.createFalied(msg);
        }
        super.jsonResult = JSONObject.fromObject(result).toString();
        logger.info(super.jsonResult);

        return BaseAction.JSON;
    }
	
	
	
	
	
	
	
	
	
	/**
     * 跳转微信内置地图页
     * @return
     * @author 秦光耀
     */
	public String toWXmap(){
		JsonResponseResult result = null;
        try {
        	String esid=super.getRequest().getParameter("esid");
        	HashMap<String, Object> config=new HashMap<String, Object>();
 		   long timestamp =CreatenTimestamp();
 		   String nonceStr= CreatenNonce_str();
 		   String signature =signature(nonceStr,timestamp,esid);
 		   config.put("appId", SysConstant.WX_APPID);
 		   config.put("timestamp", timestamp);
 		   config.put("nonceStr", nonceStr);
 		   config.put("signature", signature);
 		   logger.info("随机字符串------------"+nonceStr);
 		   logger.info("时间戳---------------"+timestamp);
 		   logger.info("签名signature----------------"+signature);
            result = JsonResponseResult.createSuccess();
            result.addData(config);
            result.setReturncode(0);
        } catch (final Exception e) {
            e.printStackTrace();
            final String msg = this.getText("操作失败！");
            result = JsonResponseResult.createFalied(msg);
        }
        super.jsonResult = JSONObject.fromObject(result).toString();
        logger.info(super.jsonResult);

        return BaseAction.JSON;
	}
	
	//获取微信access_token
	public String getAccessToken(){
		Object sessionToken=super.getRequest().getSession().getAttribute(SysConstant.WX_ACCESS_TOKEN);
		if(!SysUtils.isEmpty(sessionToken)){
			return sessionToken.toString();
		}
		String result=null;
		String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx44a26b8a7ef26809&secret=ce5575b427a386ce491e2549a515c4b0";
		HttpPost httpPost = new HttpPost(url);
		DefaultHttpClient httpClient=new DefaultHttpClient();
		HttpResponse response;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String getReturn = EntityUtils.toString(entity, "UTF-8");
			if(getReturn.indexOf("access_token")>=0){
				result= JSONObject.fromObject(getReturn).get("access_token").toString();
				super.getRequest().getSession().setAttribute(SysConstant.WX_ACCESS_TOKEN, result);
				logger.info("成功获取access_token！");
			}else{
				logger.info("获取access_token失败！");
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//获取微信ticket
	public String getTicket(){
		Object sessionTicket=super.getRequest().getSession().getAttribute(SysConstant.WX_TICKET);
		if(!SysUtils.isEmpty(sessionTicket)){
			return sessionTicket.toString();
		}
		String result=null;
		String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+getAccessToken()+"&type=jsapi";
		HttpPost httpPost = new HttpPost(url);
		DefaultHttpClient httpClient=new DefaultHttpClient();
		HttpResponse response;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String getReturn = EntityUtils.toString(entity, "UTF-8");
			if(getReturn.indexOf("ticket")>=0){
				result= JSONObject.fromObject(getReturn).get("ticket").toString();
				super.getRequest().getSession().setAttribute(SysConstant.WX_TICKET, result);
				logger.info("成功获取access_token！");
			}else{
				logger.info("获取access_token失败！");
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//创建时间戳
	public long CreatenTimestamp(){
		return System.currentTimeMillis();
	}
	
	private String[] strs = new String[]{
		"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
		"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
	};
	//创建随机字符串
	public String CreatenNonce_str(){
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		int length = strs.length;
		for (int i = 0; i < 15; i++)
		{
		sb.append(strs[r.nextInt(length - 1)]);
		}
		return sb.toString();
	}
	/// 微信权限签名( sha1 算法 )
	public String signature(String noncestr,long timesamp,String esid)
	{
		String jsapi_ticket = getTicket(); //获取jsapi_ticket
		logger.info("ticket----------------"+jsapi_ticket);
		String strSha1 = String.format("jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s", jsapi_ticket, noncestr, timesamp, "http://p.51towin.com/core/shop.toBranchDetails.do?esid="+esid);
		logger.info("签名前参数字符串---------------------"+strSha1);
		return SHA1(strSha1);
	}
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
