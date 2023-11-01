import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import MainPage from './pages/MainPage';
import Header from './component/Header';
import ListPage from './pages/ListPage';

function App() {
  return (

    <BrowserRouter>
      <Header/>
      <Routes>  
        <Route path="/" element={<MainPage/>} />
        <Route path="/list" element={<ListPage/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
