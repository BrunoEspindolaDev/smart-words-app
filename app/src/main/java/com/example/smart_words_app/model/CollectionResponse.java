package com.example.smart_words_app.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectionResponse {
    @JsonProperty("data")
    private Collection[] collectionList;

    @JsonProperty("meta")
    private CollectionMeta meta;

    public Collection[] getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(Collection[] collectionList) {
        this.collectionList = collectionList;
    }

    public CollectionMeta getMeta() {
        return meta;
    }

    public void setMeta(CollectionMeta meta) {
        this.meta = meta;
    }
}


class CollectionMeta {
    @JsonProperty("pagination")
    private CollectionPagination pagination;

    public CollectionPagination getPagination() {
        return pagination;
    }

    public void setPagination(CollectionPagination pagination) {
        this.pagination = pagination;
    }
}

class CollectionPagination {
    @JsonProperty("page")
    private int page;

    @JsonProperty("pageSize")
    private int pageSize;

    @JsonProperty("pageCount")
    private int pageCount;

    @JsonProperty("total")
    private int total;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
