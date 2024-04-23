import Actionbar from "../components/actionbar";
import "../css/profile-page.css"
import profile from "../images/profile-icon.png"
import TextInput from "../components/textInput";
import Button from "../components/button";

export default function ProfilePage(){


    return(
        <div>
            <Actionbar className={"profile"}>
                <img className="icon" src={profile} alt="Profile Icon"/>
            </Actionbar>
            <div className="profile-page page">
                <div className="content row">
                    <div className="actions column">
                        <div className="action">
                            <p className="header">Log Out</p>
                        </div>
                        <div className="action">
                            <p className="header red">Delete Data</p>
                            <p>Delete all saved statistics</p>
                        </div>
                        <div className="action">
                            <p className="header red">Delete Profile</p>
                            <p>Delete your account and all saved data</p>
                        </div>
                    </div>
                    <div className="profile column">
                        <TextInput name="Username" type="text"/>
                        <TextInput name="Email" type="email"/>
                        <TextInput name="Password" type="password"/>
                        <div className="row">
                            <Button color="purple" scale={0.7}>Save</Button>
                            <Button color="orange" scale={0.7}>Cancel</Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}


