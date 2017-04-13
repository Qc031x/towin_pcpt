package com.tencent.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * User: rizenguo
 * Date: 2014/11/1
 * Time: 14:06
 */
public class XMLParser {



    public static Map<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {

        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is =  Util.getStringStream(xmlString);
        Document document = builder.parse(is);

        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, Object> map = new HashMap<String, Object>();
        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                map.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }
        return map;

    }
    public static void main(String[] args) throws Exception {  
    	      // JSONObject dataJson = JSONObject.fromObject("{'access_token':'OezXcEiiBSKSxW0eoylIeODqeFE_7mwdcCedSDfcVlVNfzxUyC4ZeVa4ZVfW46U-v9uM_fzBTqU5PvEBH_W8XuuUGYQkkpuMJU3Z6j25gUJbluLJ_xhw2ch3wWgtIcZYLDFkqdDa1FgcDolismUEtg','expires_in':7200,'refresh_token':'OezXcEiiBSKSxW0eoylIeODqeFE_7mwdcCedSDfcVlVNfzxUyC4ZeVa4ZVfW46U-ypiqm8ah7UZumlBJe1pP6YSFe0ODEwi2fYtTT26iMXCuF1APJXV9LKIp5lEOsa8X6XP0HUhw3lKG73ILtI0plw','openid':'of12MuOahanflfuloCfKbI_g7ajg','scope':'snsapi_base'}");  
    	      // String openid= (String)dataJson.get("openid");
    		
    			
    			//System.out.println(openid);
    Date d1=	new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2015-11-10 13:50:26");
    	System.out.println(d1.getTime());
    	String timeStamp=String.valueOf(System.currentTimeMillis());
    	System.out.println(timeStamp.substring(0,10));
    	    }  

}