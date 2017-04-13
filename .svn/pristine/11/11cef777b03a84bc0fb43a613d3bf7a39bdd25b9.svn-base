package com.sgfm.datacenter.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;

/**
 * 封装XML文件的节点信息.<br>
 * 
 * @author Feiqiumin
 * @date 2011-6-10
 */
public class XmlElement
{

    /* 节点名称 */
    private String eleName;
    /* 子节点列表 */
    private List<BottomElement> children;

    /* 属性列表 */
    private Map<String, Object> attrs;

    public XmlElement(String eleName)
    {
        this.eleName = eleName;
    }

    public void addAtribute(String key, Object value)
    {
        if (this.attrs == null)
        {
            this.attrs = new HashMap<String, Object>();
        }
        this.attrs.put(key, value);
    }

    /**
     * 添加一个子节点<br>
     * 这种方式对于调用者来说，有点痛苦
     * 
     * @param dBean
     */
    // public void addChild(DynaBean dBean)
    // {
    // BottomElement bEle = new BottomElement();
    // bEle.setDynaBean(dBean);
    // if (this.children == null)
    // {
    // this.children = new ArrayList<BottomElement>();
    // }
    // this.children.add(bEle);
    // }

    /**
     * 添加一个子节点<br>
     * 这种方式对于调用者来说，有点痛苦
     * 
     * @param dBean
     */
    // public void addChild(DynaBean dBean, String text)
    // {
    // BottomElement bEle = new BottomElement();
    // bEle.setDynaBean(dBean);
    // bEle.setText(text);
    // if (this.children == null)
    // {
    // this.children = new ArrayList<BottomElement>();
    // }
    // this.children.add(bEle);
    // }

    /**
     * 创建带正文的子节点<br>
     * 
     */
    public void addChild(Map<String, ?> map, String tagName, String text)
    {
        BottomElement bEle = new BottomElement();
        DynaBean dBean = BeanMapUtil.createDynaBean(map, tagName);
        bEle.setDynaBean(dBean);
        bEle.setText(text);
        if (this.children == null)
        {
            this.children = new ArrayList<BottomElement>();
        }
        this.children.add(bEle);
    }

    /****
     * 创建不带正文的子节点
     * 
     * @param map
     * @param tagName
     */
    public void addChild(Map<String, ?> map, String tagName)
    {
        BottomElement bEle = new BottomElement();
        DynaBean dBean = BeanMapUtil.createDynaBean(map, tagName);
        bEle.setDynaBean(dBean);
        if (this.children == null)
        {
            this.children = new ArrayList<BottomElement>();
        }
        this.children.add(bEle);
    }

    /**
     * 得到所当前元素所有的子节点<br>
     * 
     * @return
     */
    public List<BottomElement> getChildren()
    {
        return children;
    }

    public void setChildren(List<BottomElement> children)
    {
        this.children = children;
    }

    public Map<String, ?> getAttrs()
    {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs)
    {
        this.attrs = attrs;
    }

    public String getEleName()
    {
        return eleName;
    }

    public void setEleName(String eleName)
    {
        this.eleName = eleName;
    }

}
