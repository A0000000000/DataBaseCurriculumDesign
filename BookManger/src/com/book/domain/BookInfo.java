package com.book.domain;

import java.util.Map;

public class BookInfo {

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Map<String, String> getChapters() {
        return chapters;
    }

    public void setChapters(Map<String, String> chapters) {
        this.chapters = chapters;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    private int bookId;
    private Map<String, String> chapters;
    private Map<String, String> content;
}
