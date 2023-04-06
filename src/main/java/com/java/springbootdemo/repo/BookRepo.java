package com.java.springbootdemo.repo;

import com.java.springbootdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Integer> {
    Optional<Book> findByBookName(String name);
}
