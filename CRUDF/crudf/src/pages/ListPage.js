import axios from "axios";
import React, {useState, useEffect} from "react";

function ListPage(){

    const [list, SetList] = useState([]);


    useEffect(()=>{
        axios.get("/api/get").then(res=>{
            console.log(res)
            SetList(res.data);
        })
    },[])

    const makelist = () =>{
        var array = []

        for(var i of list){
            array.push(
                <div>
                    <p>{i.id}</p>
                    <p>{i.title}</p>
                    <p>{i.content}</p>
                </div>
            )
        }

        return array;
    }


    return(
        <div className="list">
            <p>Post</p>

            {
                makelist()
            }
        </div>
    )
}

export default ListPage;