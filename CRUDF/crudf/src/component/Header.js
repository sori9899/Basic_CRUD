import React from "react";
import "./HeaderStyle.css";
import { Link } from "react-router-dom";

function Header(){
    return(
        <div className="header">
            <p>CRUD</p> 
        

            <div className="childPage">
                <Link to="/list">List</Link>
                <Link to="/login"> login</Link>
            </div>
            
        </div>
    )
}

export default Header;