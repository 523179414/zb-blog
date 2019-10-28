package com.nbclass.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageNum=1;
    private int pageSize=10;
    private int size;
    private int startRow;
    private int endRow;
    private long total;
    //总页数
    private int pages;
    //前一页
    private int prePage;
    //下一页
    private int nextPage;
    //是否有前一页
    private boolean hasPreviousPage = false;
    //是否有下一页
    private boolean hasNextPage = false;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;


}
