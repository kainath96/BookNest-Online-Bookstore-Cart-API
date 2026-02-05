package com.comp.bookseller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comp.bookseller.entity.Book;
import com.comp.bookseller.service.BookService;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "*")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@PostMapping("/addBook")
	String saveBook(@RequestBody Book book) {
		return service.saveBook(book);
	}
	
	@GetMapping("/getBooks")
	public List<Book> getBooks(){
		return service.getBooks();
	}
	
	@PutMapping("/updateBooks")
	public Book updateBook(@RequestParam long bookId, @RequestBody Book book) {
		return service.updateBook(bookId,book);
	}
	
	
}
