 import React, { useState } from "react";
 import axios from "axios";

 function Book(){
     const [book,setBook] = useState({
        title:"",
        author:"",
        price:"",
        description:""
     });

     function handleSubmit(e){
        e.preventDefault();
        axios.post("http://localhost:8080/book/addBook",book)
        .then(function(response){
            alert(response.data)
        })
        .catch(function(error){
            alert("Error Adding Book")
        });
    }

function handleChange(e){
            const name = e.target.name;
            const value = e.target.value;
            
            setBook(function(prevBook){
                return{
                    ...prevBook,
                    [name]:value
                };
            })
        }

        return(
            <form onClick={handleSubmit}>
                <h2>Add Book</h2>
                <label>Title</label>
                <input type="text" 
                name="title"
                value={book.title}
                onChange={handleChange}
                />
                <label>Author</label>
                <input type="text" 
                name="author"
                value={book.author}
                onChange={handleChange}
                />
                <label>Price</label>
                <input type="number"
                name="price" 
                value={book.price}
                onChange={handleChange}/>

                <label>Description</label>
                <input type="text" 
                name="description"
                value={book.description} 
                onChange={handleChange}
                />

                <button type="submit">Submit</button>
            </form>
        );
     }    
 
 export default Book;