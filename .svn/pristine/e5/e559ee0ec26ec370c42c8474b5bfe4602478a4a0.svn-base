package com.sgfm.datacenter.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sgfm.base.action.BaseAction;
import com.sgfm.datacenter.SysConstant;
import com.sgfm.datacenter.entity.TArea;
import com.sgfm.datacenter.service.product.ProductService;
import com.sgfm.datacenter.util.Ip.IPSeeker;
import com.sgfm.datacenter.util.Ip.IpCommtool;

import net.sf.json.JSONArray;

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction {
	/**
	* 
	*/
	private static final long serialVersionUID = 9071428521753585987L;
	private Log logger = LogFactory.getLog(this.getClass());

	public String msg;
	public String url;

	public TArea area = new TArea();

	@Autowired
	private ProductService productService;

	@SuppressWarnings("unchecked")
	public void indexPage(HttpServletRequest request, HttpServletResponse response) {
		try {

			logger.info("微信state参数:------" + request.getParameter("state") + "-----" + new Date().toLocaleString());

			String cityInfo = (String) request.getSession().getAttribute(SysConstant.SYS_CITYID);
			// 如果用户是第一次返回我们的首页，session里面没有值
			if (cityInfo == null) {
				IPSeeker ipSeeker = IPSeeker.getInstance();
				String ip = ipSeeker.getIp(request);
				// String ip="219.137.150.255";
				String country = ipSeeker.getCountry(ip);
				List<HashMap<String, Object>> CityList = productService.findAllCity();
				boolean flag = false;
				for (int i = 0; i < CityList.size(); i++) {
					// area =
					// userService.findAreaById(Integer.parseInt(String.valueOf(CityList.get(i).get("ID"))));
					String cityTitle = CityList.get(i).get("TITLE").toString();
					String cityId = CityList.get(i).get("ID").toString();
					if (country.indexOf(cityTitle) > -1) {
						// 获取首页城市
						request.setAttribute("cityname", cityTitle);
						// city = String.valueOf(cityId);
						request.getSession().setAttribute(SysConstant.SYS_CITYID, cityId);
						request.getSession().setAttribute(SysConstant.SYS_CITYNAME, cityTitle);
						String cityArea = IpCommtool.getMapByCityId(cityId);
						request.getSession().setAttribute(SysConstant.SYS_CITYAREANO, cityArea);
						flag = true;
						break;
					}
				}
				if (!flag) {
					request.setAttribute("cityname", "深圳");
					// city = "10312";
					request.getSession().setAttribute(SysConstant.SYS_CITYID, "10312");
					request.getSession().setAttribute(SysConstant.SYS_CITYNAME, "深圳");
					request.getSession().setAttribute(SysConstant.SYS_CITYAREANO, "0755");

				}
			} else {
				// 如果是直接输入域名且session中已经有城市value

				// area = userService.findAreaById(Integer.parseInt(cityInfo));
				// city = String.valueOf(area.getId());
				// request.setAttribute("cityname",
				// area.getTitle().substring(0,area.getTitle().length()-1));

			}
			// 从session拿出cityId得到对应的区号，跳转到对应的城市
			cityInfo = (String) request.getSession().getAttribute(SysConstant.SYS_CITYID);
			String curr_city = IpCommtool.getMapByCityId(cityInfo);
			String toUrlCity = "/" + curr_city;

			response.sendRedirect(toUrlCity);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return super.toContentView("porduct", "succ");

	}

	public void indexPage2(HttpServletRequest request, HttpServletResponse response, String urlrew_cityno) {
		try {
			String city = IpCommtool.getMapByareaNo(urlrew_cityno);
			city = (city == null) ? "10312" : city;
			area = productService.findAreaById(Integer.parseInt(city));
			String cityname = area.getTitle().substring(0, area.getTitle().length() - 1);
			request.getSession().setAttribute(SysConstant.SYS_CITYID, city);
			request.getSession().setAttribute(SysConstant.SYS_CITYNAME, cityname);
			String cityArea = IpCommtool.getMapByCityId(city);
			request.getSession().setAttribute(SysConstant.SYS_CITYAREANO, cityArea);

			request.getRequestDispatcher("/indexMain.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}