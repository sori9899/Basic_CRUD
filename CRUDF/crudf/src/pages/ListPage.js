import axios from "axios";
import React, {useState, useEffect} from "react";
import "./ListStyle.css";
import { Link, useNavigate } from "react-router-dom";


function ListPage(){

    const [list, SetList] = useState([]);

    const navigater = useNavigate();


    useEffect(()=>{
        axios.get("/api/list").then(res=>{
            console.log(res)
            SetList(res.data);
        })
    },[])

    const PostDetailShow =(e)=>{
        console.log(e);
    }

    const DeletedPost = (e) =>{
        console.log(e);

        axios.post("/api/delete", null, { 
            params:{ "id" : e }
        }).then(res=>{
            console.log(res);
        }).catch(e=>{
            console.log(e);
        })
    }

    const ModifyPost = (e) =>{
        navigater("/modify/"+`${e}`);
    }

    const makelist = () =>{
        var array = []

        for(var i of list){
            array.push(
                <div className="list-data">
                    <div className="data-content" onClick={PostDetailShow.bind(this, `${i.id}`)} key={i.id}>
                        <p>{i.id}</p>
                        <p>{i.title}</p>
                        <p>{i.content}</p>
                    </div>

                    
                    <p className="modify" onClick={ModifyPost.bind(this, `${i.id}`)}>수정</p>
                    <p className="delete" onClick={DeletedPost.bind(this,`${i.id}`)}>삭제</p>
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

            <Link to="/write">작성하기</Link>
        </div>
    )
}

export default ListPage;