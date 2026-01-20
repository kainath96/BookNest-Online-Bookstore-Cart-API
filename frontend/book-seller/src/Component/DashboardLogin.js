 import React, { useState } from "react";
 import {  Navigate, useLocation, useNavigate } from "react-router-dom";
 import {useEffect} from "react";
import axios from "axios";
import './DashboardLogin.css';
import CartItems from "./CartItems";



 function DashboardLogin(){
   const [books,setBooks] = useState([]);
   const [selectedBook,setSelectedBook] = useState(null);
   const [isModalOpen,setIsModalOpen] = useState(false);
   const navigate = useNavigate();
   const [loading,setLoading] = useState(true);
   const [cartItemDetails,setCartItemDetails] = useState([]);


   //fetching cart items
  function fetchCartItems(){
        axios.get("http://localhost:8080/cart/get")
        .then(function(response){
            setCartItemDetails(response.data);
        })
        .catch(function(error){
            alert("failed to fetch the data")
        })
   };

 

   //incresing book quantity
   function increaseQuantity(book){
    console.log("Updating book:", book);

        axios.put(`http://localhost:8080/cart/updateItem?cartBookId=${book.itemid}`,{
            ...book,
            quantity:book.quantity+1
        })
        .then(function(response){
            alert("in increase",response.data);
            fetchCartItems();
        })
        .catch(function(error){
            alert("failed to update the cart");
        })
   }

   //decreasing book quantity
   function decreaseQuantity(book){
        if(book.quantity===1){
            axios.delete(`http://localhost:8080/cart/deleteById/${book.itemid}`)
            .then(function(response){
                alert("book removed from cart")
                fetchCartItems();
            })
            .catch(function(error){
                alert("failed to remove book from the cart")
            })
        }else{
            axios.put(`http://localhost:8080/cart/updateItem?cartBookId=${book.itemid}`,
                {
                    ...book,
                    quantity:book.quantity-1
                }
            )
            .then(function(response){
                alert("decreased count")
                fetchCartItems();
            })
            .catch(function(error){
                alert("failed to delete the count")
            })
        }
   }

   //fetching book from the book table
      function fetchBook(){
    axios.get("http://localhost:8080/book/getBooks")
    .then(function(response){
      setBooks(response.data);
      console.log(response.data,"hiiiiiiiiiiiiii");
    })
    .catch(function(error){
        alert("error fetching books");
    });
   }

   //calling cart file for rendering to the cart ui
   function goToCart(){
    navigate("/cartItem");
   }
  
   //for invalid token
    useEffect(function(){
        console.log("Checking token...");
const token = localStorage.getItem("token");
console.log("Token at dashboard load:", token);

        if(!token || token==="undefined"){
            setTimeout(function loadTime(){
                alert("Please login again");
                navigate("/login")
            },100)
            return;
        }
        

    //validating the token 
    axios.get("http://localhost:8080/user/validate", {
        headers:{
            Authorization:`Bearer ${token}`,
        },
    })
    .then(function(response){
        const message = response.data;
        if(message.startsWith("Token is valid")){
            fetchBook();
            fetchCartItems();
            setLoading(false);
        }else{
            alert(message);
            localStorage.removeItem("token");
            navigate("/login");
        }
    })
    .catch(function(err){
        console.log("validate failed",err.response?.data);
         const msg = err.response?.data || "Unexpected error";
        alert(msg);
        localStorage.removeItem("token");
        navigate("/login");
    }
    );
},[]);

    
//    for either add to cart or to display quantity
   function renderCartSection(book){

    
   //checking book present in the cart or not
    var cartItem = null;
   for(var i=0;i<cartItemDetails.length;i++){
        if(cartItemDetails[i].bookId===book.bookId){
            cartItem=cartItemDetails[i];
            break;
        }
   }

    if(cartItem!== null){
        return(
            <div className="qty-buttons">
                <button onClick={()=>decreaseQuantity(cartItem)}>-</button>
                <span>{cartItem.quantity}</span>
                <button onClick={()=>increaseQuantity(cartItem)}>+</button>
            </div>
        )
    } else{
        return(
            <button className="btn" onClick={()=>addToCart(book)}>Add to cart</button>
        )
    }
   }

//modal for books details
    function showModal(book){
        setSelectedBook(book);
        setIsModalOpen(true);
        alert("in modal")
    }

    //adding into cart
    function addToCart(book){
        const cartBook={
            ...book,
            quantity:1
        };
         axios.post("http://localhost:8080/cart/add",cartBook)
        .then(function(response){
        alert("added to cart");
        fetchCartItems();
        })
        .catch(function(error){
        alert("failed to add in the cart");
        });
        }

        //modal closing function
    function closeModal(){
        setIsModalOpen(false);
    }

    if(loading){
        return<h3>Loading dashboard</h3>;
    }

   return(
        <div className="book-container">
            <h2>All Books</h2>
            <button onClick={goToCart}>View Cart</button>
            <div className="book-list">
                {books.map(function(book,index){


                    return(
                        <div className="book-card" key={index}>
                            <img 
                            src={require(`../images/${book.imageUrl}`)} 
                            alt={book.title} 
                            onClick={function displayModal(){
                                showModal(book);
                            }}
                            className="book-image" />

                            <h3 onClick={function displayModal(){
                                showModal(book);
                            }} >{book.title}</h3>

                            <p>Price: {book.price}</p>

                            {renderCartSection(book)}
                            
                           
                        </div>
                    );
                })}
                </div>
                        
                        {isModalOpen && selectedBook && (
  <div className="modal-overlay" onClick={closeModal}>
    <div className="modal-content" onClick={(e) => e.stopPropagation()}>
      <img
        className="modal-image"
        src={require(`../images/${selectedBook.imageUrl}`)}
        alt={selectedBook.title}
      />
      <h2>Author: {selectedBook.title}</h2>
      <p>Price: {selectedBook.price}</p>
      <p>Description: {selectedBook.description}</p>
    </div>
  </div>
)}

        </div>
   );

 }
 export default DashboardLogin;