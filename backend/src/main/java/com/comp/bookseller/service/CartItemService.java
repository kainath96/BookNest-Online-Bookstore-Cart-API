package com.comp.bookseller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comp.bookseller.dao.CartItemDao;
import com.comp.bookseller.dto.CartItem;

@Service
public class CartItemService {

	@Autowired
	private CartItemDao cartDao;
	
	public String addToCart(CartItem cart) {
		return cartDao.addToCart(cart);
	}

	public List<CartItem> getCart() {
		// TODO Auto-generated method stub
		return cartDao.getCart();
	}

	public String clearCart() {
		// TODO Auto-generated method stub
		return cartDao.clearCart();
	}

	public String deleteCartBookById(int cartId) {
		return cartDao.deleteCartBookById(cartId);
	}

	public String updateCartItem(int cartBookId, CartItem cartItem) {
		return cartDao.updateCartItem(cartBookId,cartItem);
	}

}
