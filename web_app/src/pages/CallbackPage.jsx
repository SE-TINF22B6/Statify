import {useEffect, useState} from "react";
import {useNavigate, useSearchParams} from "react-router-dom";

export default function CallbackPage(){
    const navigate = useNavigate()
    const [searchParams] = useSearchParams();
    const [message, setMessage] = useState("Logging in...");


    useEffect(() => {

        if (searchParams.get("error")) {
            setMessage("Error while logging in.")
        }
    }, [searchParams]);

    useEffect(() => {
        if(!searchParams.has("error")) {

            fetch("http://localhost:8081/callback?code=" + searchParams.get("code"))
                .then(() => {
                    navigate("/statistics")
                })
                .catch(err => {
                    setMessage("Error while logging in.")
                    console.log(err)
                })
        }
    }, [searchParams, navigate]);
    return (
        <div style={{textAlign:"center", margin:"20px", fontSize:"16pt", color:"black"}}>
            {message}
        </div>
    )


}