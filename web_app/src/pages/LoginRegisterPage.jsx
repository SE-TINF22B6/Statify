import "../css/login-register-page.css"
import ToggleButton from "../components/toggleButton";
import TextInput from "../components/textInput";
import {useState} from "react";
import Button from "../components/button";
import logo from "../images/StatifyLogo.png";
import {useNavigate} from "react-router-dom";
export default function LoginRegisterPage({initialState}) {
    const [state, setState] = useState(initialState)
    const navigate = useNavigate();

    return(
        <div className="login-register-page">
            <img className={"logo"} src={logo} alt={"Statify Logo"} onClick={() => navigate("/")}/>
            <div className="content column">
                <ToggleButton choices={["Register", "Log in"]} color={"purple"} selected={state} setSelected={setState} buttonWidth={350} textSize={24}/>
                <div className="inputs column">
                    <TextInput name="Email" type="email"/>
                    {state === 0 && <TextInput name="Username" type="text"/>}
                    <TextInput name="Password" type="password"/>
                </div>
                <Button color="purple" scale={0.8}>{state === 0 ? "Sign In" : "Log In"}</Button>
            </div>

        </div>
    )
}