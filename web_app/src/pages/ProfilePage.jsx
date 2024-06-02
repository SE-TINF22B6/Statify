import Actionbar from "../components/actionbar";
import "../css/profile-page.css"
import profile from "../images/profile-icon.png"
import Button from "../components/button";
import {useEffect, useState} from "react";
import {useNavigate, useSearchParams} from "react-router-dom";
import {getProfile} from "../util/dataManager";

export default function ProfilePage(){

    const navigate = useNavigate()
    const [searchParams] = useSearchParams();
    const [user, setUser] = useState({})

    useEffect(() => {
        getProfile()
            .then( result => {
                setUser(result)
            })
    });


    return(
        <div>
            <Actionbar className={"profile"}>
                <img className="icon" src={profile} alt="Profile Icon" onClick={() => navigate(-1)}/>
            </Actionbar>
            <div className="profile-page page">
                <div className="content row">
                    <div className="actions column">
                        <div className="action">
                            <p className="header red">Delete Data</p>
                            <p>Delete all saved statistics</p>
                        </div>
                        <div className="action">
                            <p className="header red">Delete All</p>
                            <p>Delete all saved data and revoke access to your Spotify account</p>
                        </div>
                    </div>
                    <div className="profile column">
                        <img src={user.profilePictureURL ?? "none"} alt={"Profile"}/>
                        <div className={"item"}>
                            <p className={"header"}>Username</p>
                            <p>{user.userName ?? "none"}</p>
                        </div>
                        <div className={"item"}>
                            <p className={"header"}>E-Mail</p>
                            <p>{user.email ?? "none"}</p>
                        </div>
                        <div className={"item"}>
                            <p className={"header"}>Account Type</p>
                            <p>{user.product ?? "none"}</p>
                        </div>
                        <div className={"item"}>
                            <Button color={"green"} onClick={() => window.location = user.userURL ?? window.location} scale={0.6} widthOffset={50}>Open on Spotify</Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}


