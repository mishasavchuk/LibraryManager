package com.library.dao.impl;

import com.library.dao.BookDao;
import com.library.model.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void add(Book book) {
        Session session = getSessionFactory().openSession();
        session.save(book);
        session.flush();
        session.close();
    }

    @Override
    public void remove(String bookName) {
        Scanner in = new Scanner(System.in);
        Session session = getSessionFactory().openSession();
        List<Book> personList = session.createQuery("from Book where BookName= :name ").setString("name",bookName).list();
        if(personList.size()>1) {
            System.out.println("We have few books with such name please choose one: "+personList+"\n Choose author witch book remove");
            String author = in.nextLine();
            Query query = session.createQuery("delete from Book where BookName= :name and BookAuthor=:authorName");
            query.setString("name", bookName);
            query.setString("authorName",author);
            query.executeUpdate();
        }
        else {
            Query query = session.createQuery("delete from Book where BookName= :name");
            query.setString("name", bookName);
            query.executeUpdate();
        }

        session.close();
    }

    @Override
    public void editBook(String oldBookName,String newBookName) {
        Session session = getSessionFactory().openSession();
        Scanner in = new Scanner(System.in);
        List<Book> personList = session.createQuery("from Book where BookName= :name ").setString("name",oldBookName).list();
        if(personList.size()>1) {
            System.out.println("We have few books with such name please choose one: "+personList+"\n Choose author witch book edit");
            String author = in.nextLine();
        Query query = session.createQuery("update Book set BookName = :newBookName where BookName = :oldBookName and BookAuthor =:author");
        query.setString("oldBookName", oldBookName);
        query.setString("newBookName",newBookName);
            query.setString("author", author);
            query.executeUpdate();
        }
        else {
            Query query = session.createQuery("update Book set BookName = :newBookName where BookName = :oldBookName");
            query.setString("oldBookName", oldBookName);
            query.setString("newBookName",newBookName);
            query.executeUpdate();
        }
        session.close();
    }

    @Override
    public List<Book> allBooks() {
        Session session = getSessionFactory().openSession();
        List<Book> personList = session.createQuery("from Book").list();
        session.close();
        return personList;
    }
}
