package com.java.springbootdemo.service;

import com.java.springbootdemo.exception.BookNotFoundException;
import com.java.springbootdemo.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    String saveBook(Book book);

    Book getBook(int id)throws BookNotFoundException;

    String deleteBook(int id)throws BookNotFoundException;

    String deleteAllBook();

    Book updateBook(int id, Book book)throws BookNotFoundException;

    Book getBookByName(String name);
}
