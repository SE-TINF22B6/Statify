import {useEffect} from "react";
import {useNavigate, useParams, useSearchParams} from "react-router-dom";

export default function CallbackPage(){
    const navigate = useNavigate()
    const [searchParams, setSearchParams] = useSearchParams();

    useEffect(() => {
        console.log(searchParams.get("code"))

        fetch("http://localhost:8081/callback?code=" + searchParams.get("code"))
            .then((res) => {
                navigate("/statistics")
            })
    }, []);
    return (
        <div>
            Logging in...
        </div>
    )


}