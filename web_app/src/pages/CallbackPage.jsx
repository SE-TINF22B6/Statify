import {useEffect, useState} from "react";
import {useNavigate, useSearchParams} from "react-router-dom";
import {arrayOf} from "prop-types";

export default function CallbackPage(){
    const navigate = useNavigate()
    const [searchParams, _] = useSearchParams();
    const [message, setMessage] = useState("Logging in...");


    useEffect(() => {

        if (searchParams.get("error")) {
            setMessage("Error while logging in.")
        }
    }, []);

    useEffect(() => {
        if(!searchParams.has("error")) {
            console.log(searchParams.get("code"))

            fetch("http://localhost:8081/callback?code=" + searchParams.get("code"))
                .then((res) => {
                    navigate("/statistics")
                })
                .catch(err => {
                    setMessage("Error while logging in.")
                })
        }
    }, []);
    return (
        <div style={{textAlign:"center", margin:"20px", fontSize:"16pt", color:"black"}}>
            {message}
        </div>
    )


}