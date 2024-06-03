import {useContext, useEffect, useState} from "react";
import {useNavigate, useSearchParams} from "react-router-dom";
import {ApiClientContext} from "../App";

export default function CallbackPage(){
    const navigate = useNavigate()
    const [searchParams] = useSearchParams();
    const [message, setMessage] = useState("Logging in...");

    const apiClient = useContext(ApiClientContext)


    useEffect(() => {

        if (searchParams.get("error")) {
            setMessage("Error while logging in.")
        }
    }, [searchParams]);

    useEffect(() => {
        if(!searchParams.has("error")) {

            fetch("http://localhost:8081/callback?code=" + searchParams.get("code"))
                .then((response) => {
                    return response.text()
                })
                .then(res => {
                    apiClient.setUserId(res)
                    navigate("/statistics")
                })
                .catch(err => {
                    setMessage("Error while logging in.")
                    console.log(err)
                })
        }
    }, [searchParams, navigate, apiClient]);
    return (
        <div style={{textAlign:"center", margin:"20px", fontSize:"16pt", color:"black"}}>
            {message}
        </div>
    )


}