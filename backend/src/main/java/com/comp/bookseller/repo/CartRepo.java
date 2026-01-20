package com.comp.bookseller.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comp.bookseller.dto.CartItem;

public interface CartRepo extends JpaRepository<CartItem, Integer>{
	public CartItem findByBookId(int bookId);
}
