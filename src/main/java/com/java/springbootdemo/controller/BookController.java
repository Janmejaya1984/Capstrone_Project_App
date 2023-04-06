package com.java.springbootdemo.controller;

import com.java.springbootdemo.exception.BookNotFoundException;
import com.java.springbootdemo.model.Book;
import com.java.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.util.List;
@RestController
@RequestMapping("/api/v1")
@Validated
public class BookController {
    @Autowired
    BookService bookService;
    @Value("${BOOK_NOT_FOUND}")
    String bookNotFound;

    @Autowired
    Environment environment;

    //http://localhost:8090/api/v1/books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> bookList = bookService.getAllBooks();
         if(bookList.size()>0){
             return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
         }else{
             return new ResponseEntity<List<Book>>(bookList,HttpStatus.NO_CONTENT);
         }
    }
    //http://localhost:8090/api/v1/books
    @PostMapping("/books")
    public ResponseEntity<String> saveBook(@Valid @RequestBody Book book){
        //Inserted successfully
        String msg = bookService.saveBook(book);
        if(null!=msg && msg.contains("Inserted successfully")){
            return new ResponseEntity<String>(msg,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //http://localhost:8090/api/v1/books/10
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable @Min(value=1,message="book id should between 1 and 100") @Max(value=100,message="bookid should between 1 and 100") int id)throws BookNotFoundException {
        Book searchedBook =  bookService.getBook(id);
        return new ResponseEntity<Book>(searchedBook, HttpStatus.OK);
    }
    //http://localhost:8090/api/v1/books/500
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id)throws BookNotFoundException{
        String msg = bookService.deleteBook(id);
        return new ResponseEntity<String>(msg,HttpStatus.OK);
    }
    //http://localhost:8090/api/v1/books
    @DeleteMapping("/books")
    public ResponseEntity<String> deleteAllBooks(){

        String msg =  bookService.deleteAllBook();
        if(null!=msg && msg.contains("successfully")){
            return  new ResponseEntity<String>(msg,HttpStatus.OK);
        }else{
            return  new ResponseEntity<String>(msg,HttpStatus.NO_CONTENT);
        }
    }
    //http://localhost:8090/api/v1/books/1
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable int id,@Valid @RequestBody Book book){
        Book bookFromDb = bookService.updateBook(id,book);
        if(null!=bookFromDb){
          return  new ResponseEntity<Book>(bookFromDb,HttpStatus.OK);
        }
        return  new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/books/name/{bookName}")
    public ResponseEntity<Book> searchByName(@PathVariable String bookName){
        Book book = bookService.getBookByName(bookName);
        return new ResponseEntity<Book>(book,HttpStatus.OK);
    }

}
