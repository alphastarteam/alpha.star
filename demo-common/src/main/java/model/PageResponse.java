package model;

import java.util.ArrayList;
import java.util.List;

public class PageResponse<T> {

    public PageResponse() {
        this.data = new ArrayList<T>();
    }

    private Long totalCount;
    private int pageCount;
    private List<T> data;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
