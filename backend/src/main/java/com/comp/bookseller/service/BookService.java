package com.comp.bookseller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.comp.bookseller.dao.BookDao;
import com.comp.bookseller.dto.Book;

@Service
public class BookService {

	@Autowired
	private BookDao dao;
	
	public String saveBook(Book book) {
		return dao.saveBook(book);
	}

	public List<Book> getBooks() {
		return dao.getBooks();
	}

	public Book updateBook(long bookId, Book book) {
		return dao.updateBook(bookId,book);
	}

}
