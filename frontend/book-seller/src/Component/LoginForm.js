 import React from "react";
 import { useState } from "react";
 import axios from "axios";
 import DashboardLogin from "./DashboardLogin";
import { useNavigate } from "react-router-dom";
import './LoginForm.css';
import securityImage from '../images/securityImage.png';
import veloxsolutions from '../images/veloxsolutions.png';

 function LoginForm(){
    const [email,setEmail] = useState("");
    const [password,setPassword] = useState("");
    const [message,setMessage] = useState("");
    const navigate = useNavigate();

    function handleSubmit(e){
        e.preventDefault();
        const loginData={
            email:email,
            password:password
        }

        axios.post("http://localhost:8080/user/login",loginData)
        .then(function(response){
            const data = response.data;
            setMessage(data.message);
           if(data.token){
            localStorage.setItem("token",data.token);
           setTimeout(function(){
            navigate('/dashboard');
           },100);
           }
        })
        .catch(function(error){
           console.log(error);
            alert("login failed")
        });
    }

    return(
        <div className="login-container">
             <div className="login-box">
        {/* LEFT: Login Form */}
        <div className="form-section">
         <div className="login-header"> <img
            src={veloxsolutions}
            alt="Velox"
            className="velox-logo"
          />
                <h3>Login</h3>
                </div>
            <form onSubmit={handleSubmit}>
                <label>Email</label>
                <br/>
                <input type="email" 
                placeholder="Enter the email"
                value={email} 
                onChange={function(e) {setEmail(e.target.value)}} required></input>
                <br/>
                <label>Password</label>
                <br/>
                <input type="password" 
                placeholder="Enter the password"
                value={password} 
                onChange={function(e) {setPassword(e.target.value)}} required></input>
                <br/>
                <div className="button-group">
                <button className="submit-btn">Submit</button>
               <button
                type="button" className="reset-btn" onClick={function reset(){
                    setEmail("");
                    setMessage("")
                    setPassword("")
                }} >Reset
               </button>
                 </div>
            </form>

            <p className="message">
                {message}
            </p>
            <p className="footer-text">
                Copy Right 2012-2025{" "}
                <a  href="https://veloxsolution.com"
              target="_blank"
              rel="noreferrer">veloxsolution
                </a>
            </p>
        </div>
        <div className="security-section">
          <img
            src={securityImage}
            alt="Security"
            className="security-icon"
          />
          <h1>
            <span className="planet">Planet</span>
            <span className="guard">Guard</span>
          </h1>
        </div>
      </div>
    </div>
    );
 }
 export default LoginForm;