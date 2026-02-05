package com.comp.bookseller.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.comp.bookseller.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long>{
}
