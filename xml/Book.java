package com.iotec.xml;

/**
 * Created on 2019/10/17 0017.
 */
public class Book {
    private String bookNo;
    private String title;
    private String author;
    private double price;

    public Book() {
    }

    public Book(String bookNo, String title, String author, double price) {
        this.bookNo = bookNo;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                "bookNo='" + bookNo + "\t" +
                ", title='" + title + "\t" +
                ", author='" + author + "\t" +
                ", price=" + price;
    }
}
