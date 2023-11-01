import React from "react";
import WriteForm from "../component/writeform/WriteForm";
import { useParams } from "react-router-dom";

function Modify(){

    const {id} = useParams();

    console.log(id)

    return(
        <div>
            <WriteForm
                action = {"modify"}
                id={id}
            />
        </div>
    )


}

export default Modify;