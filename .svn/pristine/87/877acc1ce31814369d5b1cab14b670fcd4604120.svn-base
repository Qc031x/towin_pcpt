package com.sgfm.base.util;

import java.util.HashMap;
import java.util.List;

import com.sgfm.datacenter.util.JsonResponseResult;

/**
 * @author LiuPing 分页数据封装类，该类携带分页参数和当前页数据。在分页查询中通过传入和返回该类达到统一 分页操作的目的。
 */
public class Pager extends JsonResponseResult
{

    private static int PAGE_SIZE = 20;
    private int totalRows; // 总行数
    private int startRow;
    private int pageSize = Pager.PAGE_SIZE; // 每页显示的行数
    private int currentPage; // 当前页号
    private int totalPages; // 总页数
    @SuppressWarnings("unchecked")
	private List list; // 存放查询出的数据结果
    // 对外使用
    private String pageNo;
    private String pageScale;

    public Pager()
    {
    }

    public Pager(final String pageNo)
    {
        setPageNo(pageNo);
    }

    public Pager(final String pageNo, final String pageScale)
    {
        setPageNo(pageNo);
        setPageScale(pageScale);
    }

    public void setTotalRows(final HashMap<String, Object> param, final int totalRows)
    {
        this.totalRows = totalRows;
        totalPages = totalRows / pageSize;

        final int mod = totalRows % pageSize;
        if (mod > 0)
        {
            totalPages++;
        }
        setCurrentPage(this.currentPage);
        param.put("startRow", this.getStartRow());
        param.put("pageSize", this.getPageSize());
        param.put("endRow", this.getStartRow()+this.getPageSize());
    }
    
    //可设置页面显示记录条数
    public void setTotalRows(final HashMap<String, Object> param, final int totalRows,int page_size)
    {
    	this.pageSize=page_size;
        this.totalRows = totalRows;
        totalPages = totalRows / pageSize;

        final int mod = totalRows % pageSize;
        if (mod > 0)
        {
            totalPages++;
        }
        setCurrentPage(this.currentPage);
        param.put("startRow", this.getStartRow());
        param.put("pageSize", this.getPageSize());
        param.put("endRow", this.getStartRow()+this.getPageSize());
    }

    /**
     * 设置当前页
     * 
     * @param currentPage
     */
    private void setCurrentPage(final int currentPage)
    {
        // 当前页小于第一页
        if (currentPage < 1)
        {
            this.currentPage = 1;
        } else if (currentPage > this.totalPages)
        { // 当前页大于总页数
            this.currentPage = this.totalPages;
        } else
        {
            this.currentPage = currentPage;
        }
        this.pageNo = String.valueOf(this.currentPage);
        this.startRow = (this.currentPage - 1) * this.pageSize;
        if (this.currentPage == 0)
        {
            this.startRow = 0;
        }
    }

    @SuppressWarnings("unchecked")
	public List getList()
    {
        return list;
    }

    @SuppressWarnings("unchecked")
    public void setList(final List list)
    {
        this.list = list;
    }

    /**
     * @return the totalRows
     */
    public int getTotalRows()
    {
        return totalRows;
    }

    /**
     * @return the totalPages
     */
    public int getTotalPages()
    {
        return totalPages;
    }

    public void setPageNo(final String pageNo)
    {
        if (pageNo == null || "".equals(pageNo))
        {
            this.currentPage = 1;
        } else
        {
            try
            {
                this.currentPage = Integer.parseInt(pageNo);
            } catch (final Exception e)
            {
                this.currentPage = 1;
            }
        }
        this.pageNo = String.valueOf(this.currentPage);
    }

    public String getPageScale()
    {
        return pageScale;
    }

    public void setPageScale(final String pageScale)
    {
        this.pageScale = pageScale;
        if (pageScale == null || "".equals(pageScale))
        {
            this.pageSize = Pager.PAGE_SIZE;
        } else
        {
            try
            {
                this.pageSize = Integer.parseInt(pageScale);
            } catch (final Exception e)
            {
                this.pageSize = Pager.PAGE_SIZE;
            }
        }
        this.pageScale = String.valueOf(this.pageSize);
    }

    public String getPageNo()
    {
        return pageNo;
    }

    public int getStartRow()
    {
        return this.startRow;
    }

    public int getPageSize()
    {
        return this.pageSize;
    }
    public int setPageSize(int pageSz)
    {
        return this.pageSize=pageSz;
    }

}
