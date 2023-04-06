package com.java.springbootdemo.service;

import com.java.springbootdemo.exception.BookNotFoundException;
import com.java.springbootdemo.model.Book;
import com.java.springbootdemo.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepo bookRepo;
    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public String saveBook(Book book) {
        Book bookFromDb =  bookRepo.save(book);
        return "Inserted successfully in DB. and ID generated for this is: "+bookFromDb.getBookId();
    }

    @Override
    public Book getBook(int id)throws BookNotFoundException {
       return bookRepo.findById(id).orElseThrow(()->new BookNotFoundException("BOOK_NOT_FOUND"));
    }

    @Override
    public String deleteBook(int id)throws BookNotFoundException {
        Book bookFromDb = bookRepo.findById(id).orElseThrow(()->new BookNotFoundException("BOOK_NOT_FOUND"));
        bookRepo.delete(bookFromDb);
        return "Successfully Deleted the book with id : "+id;
    }

    @Override
    public String deleteAllBook() {
        List<Book> bookList = bookRepo.findAll();
        if(bookList.size()>0) {
            bookRepo.deleteAll();
            return "All Books deleted successfully!!";
        }else{
            return "No books are there to delete!!!!";
        }
    }

    @Override
    public Book updateBook(int id, Book book) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if(bookOptional.isPresent()){
            Book bookFromDb = bookOptional.get();
            bookFromDb.setBookName(book.getBookName());
            bookFromDb.setAuthorName(book.getAuthorName());
            bookRepo.save(bookFromDb);
            return bookFromDb;
        }
        return null;
    }

     public  Book getBookByName(String name) throws BookNotFoundException{
        return bookRepo.findByBookName(name).orElseThrow(()->new BookNotFoundException("BOOK_NOT_FOUNFD"));
     }

}
