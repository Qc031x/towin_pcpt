package com.sgfm.datacenter.login.model;

import java.util.Map;

/**
 * 登录用户的权限系统信息
 * 
 * @概述：
 * @author 张孟楚
 * @时间：2011-6-18 下午03:20:14
 */
public class AuthorizedInfo {

    /**
     * 用户页面url权限
     */
    private Map<String, String> urlMap;

    /**
     * 用户菜单权限
     */
    private Map<String, String> rightCodeMap;
    /**
     * 用户操盘授权
     */
   /* private List<DataGrant> dataGrantList;

    public List<DataGrant> getDataGrantList() {
        return dataGrantList;
    }

    public void setDataGrantList(List<DataGrant> dataGrantList) {
        this.dataGrantList = dataGrantList;
    }*/

    public Map<String, String> getUrlMap() {
        return urlMap;
    }

    public void setUrlMap(Map<String, String> urlMap) {
        this.urlMap = urlMap;
    }

    public Map<String, String> getRightCodeMap() {
        return rightCodeMap;
    }

    public void setRightCodeMap(Map<String, String> rightCodeMap) {
        this.rightCodeMap = rightCodeMap;
    }

}
