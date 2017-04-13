package com.sgfm.datacenter.action.payment;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sgfm.base.action.BaseAction;
import com.sgfm.datacenter.service.order.OrderService;

@Controller
@Scope("prototype")
public class PayMentAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private Log logger = LogFactory.getLog(this.getClass()); 
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 跳转到支付成功页面
	 * @return
	 * @author 康良玉
	 */
	public String paySuccess(){
		try {
			String oid = super.getRequest().getParameter("oid");
			HashMap<String, Object> info = orderService.findPaySuccessInfoByOid(oid);
			super.getRequest().setAttribute("info", info);
		} catch (Exception e) {
			this.logger.error(e);
		}
		return super.toContentView("payment", "pay_success");
	}

}
