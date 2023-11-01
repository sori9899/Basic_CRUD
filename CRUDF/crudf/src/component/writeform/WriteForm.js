import React, {useEffect, useState } from "react";
import "./WriteFormStyle.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function WriteForm(props){


    const [title, SetTitle] = useState("");
    const [content, SetContent] = useState("");
    const [id, SetId] = useState(0);

    const navigater = useNavigate();

    useEffect(()=>{
        if(`${props.action}` === "modify"){
            GetData();
        }
    },[])


    const onChangeTitle = (e) =>{
        SetTitle(e.target.value);
    }

    const onChangeContent = (e) =>{
        SetContent(e.target.value);
    }


    const GetData = () =>{

        console.log(`${props.id}`)

        axios.post("/api/get",  null,{
            params:{"id" : `${props.id}`}
        }).then(res=>{
            console.log(res.data);
            SetTitle(res.data.title);
            SetContent(res.data.content)
            SetId(res.data.id);
        })
    }

    const RegistrationPost = () =>{

        if(title === "" || content ===""){
            return;
        }

        axios.post("/api/save", {
                "title" : title,
                "content" : content,
                "writter":1,
        }).then(res=>{
            console.log(res.data);
            navigater("/list");
        }).catch(e=>{
            console.log(e);
        })

    }

    const ModifyPost = ()=>{
        if(title === "" || content ===""){
            return;
        }

        axios.post("/api/update",{
            "id" : id,
            "title" : title,
            "content" : content,
            "writter":1,
        }).then(res=>{
            console.log(res.data);
            navigater("/list");
        }).catch(e=>{
            console.log(e);
        })

    }


    return(
        <div className="write">
        
            <label htmlFor="title">Title</label>
            <input onChange={onChangeTitle} value={title} id="title" maxLength={50}/>

            <br/> <br/>

            <label htmlFor="content">content</label>
            <input onChange={onChangeContent} value={content} id="content" maxLength={50}/>

            {
                `${props.action}` === "save"
                ? <p  className="register" onClick={RegistrationPost}> 등록 </p>
                : <p  className="modify" onClick={ModifyPost}> 수정 </p>
            }
        </div>
    )
}


export default WriteForm;