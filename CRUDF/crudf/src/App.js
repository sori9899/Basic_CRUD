import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import MainPage from './pages/MainPage';
import Header from './component/Header';
import ListPage from './pages/ListPage';
import Login from './pages/Login';
import SingUp from './pages/SignUp';
import WritePage from './pages/WritePage';
import Modify from './pages/Modify';

function App() {
  return (

    <BrowserRouter>
      <Header/>
      <Routes>  
        <Route path="/" element={<MainPage/>} />
        <Route path="/list" element={<ListPage/>} />
        <Route path="/login" element={<Login/>} />
        <Route path="/signup" element={<SingUp/>} />
        <Route path="/write" element={<WritePage/>} />
        <Route path="/modify/:id" element={<Modify/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
