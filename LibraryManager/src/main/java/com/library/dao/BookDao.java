package com.library.dao;

import com.library.model.Book;

import java.util.List;

public interface BookDao {
    void add(Book book);
    void remove(String bookName);
    void editBook(String oldBookName,String newBookName);
    List<Book> allBooks();
}
