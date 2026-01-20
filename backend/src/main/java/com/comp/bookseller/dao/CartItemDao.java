package com.comp.bookseller.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comp.bookseller.dto.Book;
import com.comp.bookseller.dto.CartItem;
import com.comp.bookseller.repo.CartRepo;

@Repository
public class CartItemDao {
	
	@Autowired
	private CartRepo repo;

	public String addToCart(CartItem cart) {
		CartItem dbBook = repo.findByBookId(cart.getBookId());
		if(dbBook!=null) {
			return "already present in cart";
		}
		else {
		repo.save(cart);
		System.out.println("hiiiiiiii" + dbBook);
		return "saved to cart";
		}
	}

	public List<CartItem> getCart() {
		return repo.findAll();
	}   

	public String clearCart() {
		repo.deleteAll();
		return "Cart cleared";
	}

	public String deleteCartBookById(int cartId) {
		Optional<CartItem> optional = repo.findById(cartId);
		
		if(optional.isPresent()) {
			CartItem dbCartBook = optional.get();
			repo.delete(dbCartBook);
			return "Cart Book Deleted";
		}
		System.out.println("in cart dao");
		return "Cart Book Failed To Delete";
	}

	public String updateCartItem(int cartBookId, CartItem cartItem) {
		Optional<CartItem> optional = repo.findById(cartBookId);
		if(optional.isPresent()) {
			  cartItem.setItemid(cartBookId);
			  repo.save(cartItem);
		}
		return null;
	}

}
