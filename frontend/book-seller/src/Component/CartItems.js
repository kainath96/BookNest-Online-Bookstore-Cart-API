import axios from "axios";
import React, { useEffect } from "react";
import { useState } from "react";
import './CartItems.css';

function CartItems(){

    const [cartItem,setcartItem] = useState([]);

   function fetchCart(){
         axios.get("http://localhost:8080/cart/get")
         .then(function(response){
            setcartItem(response.data);
         }).catch(function(error){
            alert("failed to fetch the cart details");
         });

    }

    useEffect(function cartRender(){
        fetchCart();
    },[])

    function increaseCount(book){
        console.log("book",book);
        axios.put(`http://localhost:8080/cart/updateItem?cartBookId=${book.itemid}`,{
            ...book,
            quantity:book.quantity+1
        })
        .then(function(response){
            alert("quantity increased"+book.quantity)
            fetchCart();
        })
        .catch(function(error){
            alert("failed to increase the quantity")
        })

    }

    function decreaseCount(book){
        console.log("book1",book);
       if(book.quantity===1){
        axios.delete(`http://localhost:8080/cart/deleteById/${book.itemid}`)
        .then(function(response){
            alert("book removed from the cart")
            fetchCart();
        })
        .catch(function(error){
            alert("failed to remove book from the cart")
        })
       }
       else{
        axios.put(`http://localhost:8080/cart/updateItem?cartBookId=${book.itemid}`,{
            ...book,
            quantity:book.quantity-1
        })
        .then(function(response){
            alert("quantity decreased");
            fetchCart();
        })
        .catch(function(error){
            alert("failed to decrease the count")
        })
       }
    }

    function clearCartFunc(){
        axios.delete("http://localhost:8080/cart/clear")
        .then(function(response){
            alert("cart cleared")
            fetchCart()
        })
        .catch(function(error){
            alert("failed to clear the cart")
        })
    }
   
function deleteBook(book){
    console.log("in delete"+book.itemid)
    axios.delete(`http://localhost:8080/cart/deleteById/${book.itemid}`)
    .then(function(response){
        alert("deleted cart book");
        fetchCart();
    })
    .catch(function(error){
        alert("failed to delete cart item");
    })
}

    return(
    <div className="cart-container">
    
    <div className="clear-cart-wrapper">
        <button className="clear-cart-btn " onClick={()=>clearCartFunc()}>Clear Cart</button>
    </div>

  {cartItem.map((book, index) => (
    
    <div key={index} className="cart-card"> 
      <h3 className="book-title">{book.title}</h3>
      <img className="book-image" src={ book.imageUrl
      ? require(`../images/${book.imageUrl}`)
      : require(`../images/default.jpg`)}  // fallback)} 
      alt={book.title} />
      <h3 className="book-price">Price: ₹{book.price}</h3> 
      <h3 className="book-total">Total: ₹{book.quantity * book.price}</h3>

      <div className="button-row">
        <div className="qty-buttons">
          <button onClick={() => decreaseCount(book)}>-</button>
          <h3 className="book-quantity">{book.quantity}</h3>
          <button onClick={() => increaseCount(book)}>+</button>
        </div>
        <div className="remove-button">
          <button onClick={() => deleteBook(book)}>Remove</button>
        </div>
      </div>
    </div>
  ))}
</div>
    );
}

export default CartItems;
 