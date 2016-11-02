package com.library.util;

import com.library.dao.BookDao;
import com.library.model.Book;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleHelpWrite {
    public static void getHelp(){
        System.out.println("This simple program can use only four command:\n" +
                "-add(Use add, when you wont to add book to the library)\n" +
                "-remove(Use remove, when you wont to remove book from the library\n" +
                "-editBook(Use editBook, when you wont to rename book)\n"+
                "-allBooks(Use all books, when you wont to show all books from library)\n"+
                "When you use add please enter book name and book author by space\n"+
                "IF YOU WONT EXIT - ENTER EMPTY STRING\n");
    }

    public static int decideWhatCommand(String console){
        String[] split = console.split("\\s+");
        if(split[0].equals("add")) return 1;
        else if(split[0].equals("remove")) return 2;
        else if(split[0].equals("editBook")) return 3;
        else if(split[0].equals("allBooks")) return 4;
        else return 0;
    }

    public static void consoleWriter(BookDao book){
        getHelp();
        Scanner in = new Scanner(System.in);
        while(true){
            String console = in.nextLine();
            if(console.length()==0) break;
            int type = decideWhatCommand(console);
            switch (type){
                case 1: {
                    String[] split = console.split("\\s+");
                    String bookAuthor = split[1];
                    String bookName = split[2];
                    book.add(new Book(bookAuthor,bookName));
                    break;
                }
                case 2:{
                    String[] split = console.split("\\s+");
                    String bookName = split[1];
                    book.remove(bookName);
                    break;
                }
                case 3:{
                    String[] split = console.split("\\s+");
                    String oldBookName = split[1];
                    System.out.println("Enter new book name:");
                    String newBookName = in.nextLine();
                    book.editBook(oldBookName,newBookName);
                    break;
                }
                case 4:{
                    List<Book> list = book.allBooks();
                    Collections.sort(list);
                    System.out.print("Books in library(order by name): \n"+list+"\n" );
                    break;
                }
                case 0:{
                    System.out.println("sorry command is incorrect,try again");
                    break;
                }
            }
        }
    }
}

