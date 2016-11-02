package com.library.model;

import javax.persistence.*;

@Entity
public class Book implements Comparable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "BookName")
    private String bookName;

    @Column(name = "BookAuthor")
    private String bookAuthor;

    Book() {
    }

    public Book(String bookName, String bookAuthor) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Override
    public String toString() {
        return "("+this.bookName+" "+this.bookAuthor+") ";
    }

    @Override
    public int compareTo(Object o) {
        Book book = (Book) o;
        return this.bookName.compareTo(book.bookName);
    }
}
