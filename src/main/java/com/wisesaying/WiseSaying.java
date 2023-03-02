package com.wisesaying;

public class WiseSaying {
    public static Integer count = 0;
    private Integer id;
    private String content;
    private String author;

    public WiseSaying(String content, String author) {
        this.id = ++count;
        this.content = content;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return id + " / " + author + " / " + content + " / ";
    }
}
