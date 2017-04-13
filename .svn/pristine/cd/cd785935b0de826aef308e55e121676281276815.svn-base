package com.sgfm.datacenter.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

/**
 * 树节点对象。
 * 
 * @author 罗军林
 * 
 */
public class TreeNode
{
    /** 节点信息 */
    private final TreeNodeData data;

    /** 对象原数据 */
    private final Map<String, Object> metadata = new HashMap<String, Object>();

    /** 该节点的孩子节点列表 */
    private final List<TreeNode> children = new ArrayList<TreeNode>();

    /**
     * 构造一个有标题的节点。
     * 
     * @param title
     */
    public TreeNode(final String title)
    {
        super();
        data = new TreeNodeData(title);
    }

    public TreeNode(final String title, final String url)
    {
        super();
        data = new TreeNodeData(title);
        data.setUrl(url);
    }

    public void setHaschild(final Boolean haschild)
    {
        this.data.setHaschild(haschild);
    }

    public void setUrl(final String url)
    {
        this.data.setUrl(url);
    }

    public TreeNodeData getData()
    {
        return data;
    }

    public Map<String, Object> getMetadata()
    {
        return metadata;
    }

    public void addMetadata(final String key, final Object val)
    {
        metadata.put(key, val);
    }

    /** 将对象各字段信息附加到树节点中，以备调用 */
    public void addMetadata(final Object bean)
    {
        @SuppressWarnings("unchecked")
        final Map<String, Object> temp = BeanMap.create(bean);
        this.metadata.putAll(temp);
    }

    public List<TreeNode> getChildren()
    {
        return children;
    }

    public void addChildren(final TreeNode note)
    {
        children.add(note);
        this.data.setHaschild(true);
    }

    @Override
    public String toString()
    {
        return "TreeNote [data=" + data + "]";
    }

}