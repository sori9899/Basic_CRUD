import React, { useEffect, useState } from "react";
import "./WriteStyle.css";
import WriteForm from "../component/writeform/WriteForm";

/*
function WritePage(){

    const [title, SetTitle] = useState("");
    const [content, SetContent] = useState("");


    const RegistrationPost = () =>{

        if(title === "" || content ===""){
            return;
        }

        console.log(title, content)

        const data = {
            "title" : title,
            "content" : content,
            "writter":1,
        }

        console.log(data)

        axios.post("/api/save", {
                "title" : title,
                "content" : content,
                "writter":1,
        }).then(res=>{
            console.log(res.data);
        }).catch(e=>{
            console.log(e);
        })
    }

    const onChangeTitle = (e) =>{
        SetTitle(e.target.value);
    }

    const onChangeContent = (e) =>{
        SetContent(e.target.value);
    }


    return(
        <div className="write">
        
            <label htmlFor="title">Title</label>
            <input onChange={onChangeTitle} value={title} id="title" maxLength={50}/>

            <br/>

            <label htmlFor="content">content</label>
            <input onChange={onChangeContent} value={content} id="content" maxLength={50}/>

            <p  className="register" onClick={RegistrationPost}> 등록 </p>
        </div>
    )
}
*/

function WritePage(){

    return(
        <div>

            <WriteForm
                action = {"save"}
            />
        </div>
    )
}


export default WritePage;