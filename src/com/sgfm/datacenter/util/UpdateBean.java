package com.sgfm.datacenter.util;

/**
 * 数据同步时，用于封装Update信息的Bean.<br>
 * 相对于DynaBean而言，新增了条件字符串，表示执行Update语句时用作的条件字段
 * 
 * @author Feiqiumin
 * @date 2011-6-10
 */
public class UpdateBean
{
    /* 实体对象或动态Bean */
    private Object object;

    /* 用逗号隔开的条件字符串 */
    private String condiction;

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }

    public String getCondiction()
    {
        return condiction;
    }

    public void setCondiction(String condiction)
    {
        this.condiction = condiction;
    }

}
