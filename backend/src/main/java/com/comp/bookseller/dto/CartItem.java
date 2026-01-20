package com.comp.bookseller.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int itemid;
	@Column(name = "book_id")
	private int bookId;

	private String title;
	private String author;
	private String imageUrl;
	private String price;
	private int quantity;	
	private float totalBookPrice;
	
	
	public CartItem() {
		// TODO Auto-generated constructor stub
	}
	
	public CartItem(int itemid, int bookId, String title, String author, String imageUrl, String price, int quantity,float totalBookPrice) {
		super();
		this.itemid = itemid;
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.imageUrl = imageUrl;
		this.price = price;
		this.quantity = quantity;
		this.totalBookPrice=totalBookPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}



	public float getTotalBookPrice() {
		return totalBookPrice;
	}

	public void setTotalBookPrice(float totalBookPrice) {
		this.totalBookPrice = totalBookPrice;
	}

	@Override
	public String toString() {
		return "CartItem [itemid=" + itemid + ", id=" + bookId + ", title=" + title + ", author=" + author + ", imageUrl="
				+ imageUrl + ", price=" + price + "]";
	}

}
