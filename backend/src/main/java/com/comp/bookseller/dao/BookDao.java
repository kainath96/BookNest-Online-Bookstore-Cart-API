package com.comp.bookseller.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.comp.bookseller.entity.Book;
import com.comp.bookseller.repo.BookRepo;

@Repository
public class BookDao {

	@Autowired
	private BookRepo repo;
	
	public String saveBook(@RequestBody Book book) {
		Book dbBook = repo.save(book);
			return "Book saved";
	}

	public List<Book> getBooks() {
		List<Book> dbBooks = repo.findAll();
		return dbBooks;
	}

	public Book updateBook(long bookId, Book book) {
		Optional<Book> optional = repo.findById(bookId);
		
		if(optional.isPresent()) {
			book.setBookId(bookId);
			return repo.save(book);
		}
		return null;
	}

	
	
	

}
