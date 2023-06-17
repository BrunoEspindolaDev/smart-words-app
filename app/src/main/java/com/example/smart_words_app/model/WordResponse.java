package com.example.smart_words_app.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WordResponse {
    @JsonProperty("data")
    private Word[] wordList;

    @JsonProperty("meta")
    private WordMeta meta;

    public Word[] getWordList() {
        return wordList;
    }

    public void setWordList(Word[] wordList) {
        this.wordList = wordList;
    }

    public WordMeta getMeta() {
        return meta;
    }

    public void setMeta(WordMeta meta) {
        this.meta = meta;
    }
}


class WordMeta {
    @JsonProperty("pagination")
    private WordPagination pagination;

    public WordPagination getPagination() {
        return pagination;
    }

    public void setPagination(WordPagination pagination) {
        this.pagination = pagination;
    }
}

class WordPagination {
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
