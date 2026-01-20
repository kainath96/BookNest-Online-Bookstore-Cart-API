 import React, { useState } from "react";
 import axios from "axios";
 import "./Register.css" 

 function Register(){
   const [email,setEmail] = useState("");
   const [password,setPassword] = useState("");
   const [message,setMessage] = useState("")

   function handleRegister(e){
    e.preventDefault();

    const registerData={
        email:email,
        password:password
    }

    axios.post("http://localhost:8080/user/register",registerData)
    .then(function (response){
        alert("registeration successfull")
    })
     .catch(function(error) {
    alert("failed");
  });
   }

   return(
    <form onSubmit={handleRegister}> 
        <h1>Register</h1>
        <label>Email</label>
        <input type="email" value={email} onChange={function(e) {setEmail(e.target.value)}}></input>
        <br/>
        <label>Password</label>
        <input type="password" value={password} onChange={function(e) {setPassword(e.target.value)}}></input>
        <button>Submit</button>
    </form>
   )
 }
 
 export default Register