package com.book.domain;

public class Book {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public double getVIPPrice() {
        return VIPPrice;
    }

    public void setVIPPrice(double VIPPrice) {
        this.VIPPrice = VIPPrice;
    }

    public BookInfo getInfo() {
        return info;
    }

    public void setInfo(BookInfo info) {
        this.info = info;
    }

    private int id;
    private String bookname;
    private String author;
    private String publisher;
    private int number;
    private double normalPrice;
    private double VIPPrice;
    private BookInfo info;
}
