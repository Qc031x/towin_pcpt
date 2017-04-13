package com.sgfm.datacenter.util;

/**
 * html代码生成器 .<br/>
 * 服务器端结合数据动态生成HTML代码
 * 
 * @author Feiqiumin
 * @date 2011-5-25
 */
public class HtmlMaker
{
    private StringBuffer html = new StringBuffer();

    public void divBegin(String clas)
    {
        html.append("<div").append(" class='").append(clas).append("'>");
    }

    public void divEnd()
    {
        html.append("</div>");
    }

    public void pBegin(String clas)
    {
        html.append("<p").append(" class='").append(clas).append("'>");
    }

    public void pEnd()
    {
        html.append("</P>");
    }

    public void labelBegin(String style)
    {
        if ("".equals(style))
        {
            html.append("<label>");
        } else
        {
            html.append("<label").append(" style='").append(style).append("'>");
        }
    }

    public void lableEnd()
    {
        html.append("</label>");
    }

    public void input(String id, String name, String type, boolean checked,
            String clas, String value)
    {
        html.append("<input id='").append(id).append("' name='").append(name)
                .append("' type='").append(type).append("'");
        if (checked)
        {
            html.append(" checked='checked'");
        }
        html.append(" class='").append(clas).append("'");
        html.append(" value='").append(value).append("'/>");
    }

    public void br()
    {
        html.append("<br/>");
    }

    public void text(String text)
    {
        html.append(text);
    }

    public StringBuffer getHtml()
    {
        return html;
    }

    public void setHtml(StringBuffer html)
    {
        this.html = html;
    }

}
