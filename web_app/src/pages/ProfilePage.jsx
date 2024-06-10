import Actionbar from "../components/actionbar";
import "../css/profile-page.css"
import profile from "../images/profile-icon.png"
import Button from "../components/button";
import {useContext, useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {ApiClientContext} from "../App";

export default function ProfilePage(){

    const apiClient = useContext(ApiClientContext)

    const navigate = useNavigate()
    const [user, setUser] = useState(apiClient.profile)

    useEffect(() => {
        apiClient.getProfile()
            .then( result => {
                setUser(result)
            })
    }, [apiClient]);


    return(
        <div>
            <Actionbar className={"profile"}>
                <img className="icon" src={profile} alt="Profile Icon" onClick={() => navigate(-1)}/>
            </Actionbar>
            <div className="profile-page page">
                <div className="content row">
                    <div className="actions column">
                        <div className="action">
                            <p className="header red"  onClick={() => apiClient.requestDeleteData(navigate)}>Delete Data</p>
                            <p>Delete all saved statistics</p>
                        </div>
                        <div className="action">
                            <p className="header red" onClick={() => apiClient.requestDeleteProfile(navigate)}>Delete All</p>
                            <p>Delete all saved data and revoke access to your Spotify account</p>
                        </div>
                    </div>
                    <div className="profile column">
                        <img src={user == null ? "" : user.profilePictureURL} alt={"Profile"}/>
                        <div className={"item"}>
                            <p className={"header"}>Username</p>
                            <p>{user == null ? "none" : user.userName}</p>
                        </div>
                        <div className={"item"}>
                            <p className={"header"}>E-Mail</p>
                            <p>{user == null ? "none" : user.email}</p>
                        </div>
                        <div className={"item"}>
                            <p className={"header"}>Account Type</p>
                            <p>{user == null ? "none" : user.product}</p>
                        </div>
                        <div className={"item"}>
                            <Button color={"green"} onClick={() => window.location = user == null ? window.location : user.userUrl} scale={0.6} widthOffset={50}>Open on Spotify</Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}


