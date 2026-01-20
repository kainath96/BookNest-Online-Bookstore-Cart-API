package com.comp.bookseller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comp.bookseller.dto.CartItem;
import com.comp.bookseller.service.CartItemService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartItemController {
	
	@Autowired
	private CartItemService cartService;
	@PostMapping("/add")
	public String addToCart(@RequestBody CartItem cart) {
		System.out.println("controller");
		System.out.println(cart);
		return cartService.addToCart(cart);
	}
	
	    @GetMapping("/get")
	    public List<CartItem> getCart() {
	    	System.out.println("in cart get method");
	        return cartService.getCart();
	    }
	    
	    @PutMapping("/updateItem")
	    public String updateCartItem(@RequestParam int cartBookId,@RequestBody CartItem cartItem ) {
	    	System.out.println("in controller update ");
	    	return cartService.updateCartItem(cartBookId,cartItem);
	    }
	    
	    @DeleteMapping("/deleteById/{cartId}")
	    public String deleteCartBookById(@PathVariable int cartId) {
	    	System.out.println("in cart controller");
	    	return cartService.deleteCartBookById(cartId);
	    }

	    @DeleteMapping("/clear")
	    public String clearCart() {
	        return cartService.clearCart();
	    }
	}

