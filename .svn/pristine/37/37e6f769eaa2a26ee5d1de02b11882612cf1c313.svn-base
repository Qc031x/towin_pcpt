package com.sgfm.datacenter.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author LIFUPING
 * @email forint.lee@gmail.com
 * @describe XML数据解析与封装通用Bean
 * @date 2011-6-11 下午12:41:45
 * @version 1.0
 */

public class XMLBean {
    private final static Logger log = Logger.getLogger(XMLBean.class);
    /* 节点名 */
    private String nodeName;
    /* 节点内容 */
    private String content;
    /* 属性 */
    private Map<String, String> attr = new HashMap<String, String>();
    /* 子节点 */
    private List<XMLBean> child = new ArrayList<XMLBean>();

    public XMLBean() {
    }

    public XMLBean(String nodeName) {
        this.nodeName = nodeName;
    }

    public XMLBean(String nodeName, String content) {
        this.nodeName = nodeName;
        this.content = content;
    }

    public void addttribute(String key, String value) {
        this.attr.put(key, value);
    }

    public Map<String, String> getAttr() {
        return this.attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<XMLBean> getChild() {
        return this.child;
    }

    public void setChild(List<XMLBean> child) {
        this.child = child;
    }

    public void addChild(XMLBean child) {
        this.child.add(child);
    }

    public String toXML() {
        Document doc = DocumentHelper.createDocument();
        Element elment = doc.addElement(this.nodeName);
        this.makeElement(this, elment);
        return doc.asXML();
    }

    private void makeElement(XMLBean xmlbean, Element element) {
        Map<String, String> map = xmlbean.getAttr();
        for (String key : map.keySet()) {
            element.addAttribute(key, map.get(key));
        }
        List<XMLBean> child = xmlbean.getChild();
        if (child.size() > 0) {
            for (XMLBean childBean : child) {
                Element el = element.addElement(childBean.getNodeName());
                this.makeElement(childBean, el);
            }
        } else {
            if (xmlbean.getContent() != null && !xmlbean.getContent().equals("")) {
                element.setText(xmlbean.getContent());
            }
        }
    }

    /**
     * 将xml解析成一个XMLBean
     * 
     * @param xml
     * @return XMLBean
     * @throws DocumentException
     */
    public static XMLBean parser(String xml) throws DocumentException {
        XMLBean xmlbean = new XMLBean();
        Document doc = DocumentHelper.parseText(xml);
        Element elment = doc.getRootElement();
        Long st = System.currentTimeMillis();
        XMLBean.markXMLBean(xmlbean, elment);
        Long et = System.currentTimeMillis();
        // XMLBean.log.info("将XML包装成Bean所花时间" + (et - st) + "毫秒");
        return xmlbean;
    }

    private static void markXMLBean(XMLBean xmlbean, Element el) {
        List<Attribute> atr = el.attributes();
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < atr.size(); i++) {
            map.put(atr.get(i).getName(), atr.get(i).getValue());
        }
        xmlbean.setAttr(map);
        xmlbean.setNodeName(el.getName());
        if (el.getText() != null && !el.getText().trim().equals("")) {
            xmlbean.setContent(el.getText());
        } else {
            List<Element> list = el.elements();
            List<XMLBean> child = new ArrayList<XMLBean>();
            for (Element element : list) {
                XMLBean b = new XMLBean();
                child.add(b);
                XMLBean.markXMLBean(b, element);
            }
            xmlbean.setChild(child);
        }
    }

    public static void main(String[] args) {
        /************************ 封装 ********************************/
        XMLBean xml = new XMLBean("resp");
        xml.addttribute("tid", "001");
        xml.addttribute("gid", "002");

        XMLBean bean1 = new XMLBean("rec", null);
        bean1.addttribute("a", "123");
        bean1.addttribute("b", "456");

        XMLBean bean2 = new XMLBean("rec", null);
        bean2.addttribute("a", "123");
        bean2.addttribute("b", "456");

        xml.addChild(bean1);
        xml.addChild(bean2);

        System.out.println(xml.toXML());
        /************************ 解析 ********************************/

        try {
            XMLBean bean = XMLBean.parser(xml.toXML());
            List<XMLBean> list = bean.getChild();
            for (XMLBean xmlBean : list) {
                Map<String, String> map = xmlBean.getAttr();
                System.out.println(map.get("a"));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
