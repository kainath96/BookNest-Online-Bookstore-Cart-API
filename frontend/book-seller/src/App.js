import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router,Routes,Route } from 'react-router-dom'
import Register from './Component/Register'
import LoginForm from './Component/LoginForm'
import Book from './Component/Book';
import DashboardLogin from './Component/DashboardLogin';
import CartItems from './Component/CartItems';

function App() {
  return (
    <div className="App">                       
      <Router>
        <Routes>
          <Route path='/' element={<Register/>}></Route>
          <Route path='/login' element={<LoginForm/>}></Route>
          <Route path='/dashboard' element={<DashboardLogin/>}></Route>
          <Route path='/book' element={<Book/>}></Route>
          <Route path='/cartItem' element={<CartItems />}></Route> 
        </Routes>
      </Router>
    </div>
  );
}

export default App;
