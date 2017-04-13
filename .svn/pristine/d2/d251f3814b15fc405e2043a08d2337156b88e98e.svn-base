package com.sgfm.datacenter.util;

/**
 * 封装实体数据.<br>
 * 
 * @author Feiqiumin
 * @date 2011-6-16
 */
public class SynEntityBean
{
    /** 操作类型: 1:新增 2:修改 3:删除 4:非数据库数据同步 */
    private int option;

    /** 实体对象 */
    private Object obj;

    /** 更新实体时，表示更新条件字段 */
    private String con;
    /**
     * 默认:空值全部不做处理<br>
     * <ul>
     * <li>1.空值全部做处理</li>
     * <li>2.只处理我传入的字段</li>
     * <li>3.只有我传入的字段不做处理</li>
     * </ul>
     */
    private int operateFlag = 0;

    private String fields;

    public SynEntityBean(int option)
    {
        this.option = option;
    }

    public Object getObj()
    {
        return obj;
    }

    public void setObj(Object obj)
    {
        this.obj = obj;
    }

    public int getOption()
    {
        return option;
    }

    public String getFields()
    {
        return fields;
    }

    public void setFields(String fields)
    {
        this.fields = fields;
    }

    public int getOperateFlag()
    {
        return operateFlag;
    }

    public void setOperateFlag(int operateFlag)
    {
        this.operateFlag = operateFlag;
    }

    public String getCon()
    {
        return con;
    }

    public void setCon(String con)
    {
        this.con = con;
    }

    public void setOption(int option)
    {
        this.option = option;
    }
}
