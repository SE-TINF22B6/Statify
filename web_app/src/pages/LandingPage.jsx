import Actionbar from "../components/actionbar";
import "../css/landing-page.css"
import Button from "../components/button";
import GitHubLogo from "../images/GitHub_Logo_White.png"
import GitHubMark from "../images/github-mark-white.svg"
import logo from "../images/StatifyLogo.png"
import {useNavigate} from "react-router-dom";

export default function LandingPage(){
    const navigate = useNavigate();

    return (
        <div>
            <Actionbar className={"landing"}>
                <div className="buttons row">
                    <Button scale={0.5} widthOffset={-50} color={"purple"} onClick={() => login()}>LOG IN</Button>
                </div>
            </Actionbar>

            <div className="page landing-page">
                <svg width={1000} height={800}>
                    <circle r={380} cx={100} cy={220}/>
                    <circle r ={120} cx={280} cy={560}/>
                    <circle r={80} cx={120} cy={620}/>
                </svg>
                <img className={"logo"} src={logo} alt={"Statify Logo"}/>
                <div className="content row">

                    <div className={"vertical column"}>
                        <svg width="435" height="127" viewBox="0 0 435 127" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M36.6265 102.536C29.5438 102.536 22.6745 101.597 16.0185 99.72C9.44784 97.7573 4.15719 95.24 0.146515 92.168L7.18651 76.552C11.0265 79.368 15.5918 81.6293 20.8825 83.336C26.1732 85.0427 31.4638 85.896 36.7545 85.896C42.6425 85.896 46.9945 85.0427 49.8105 83.336C52.6265 81.544 54.0345 79.1973 54.0345 76.296C54.0345 74.1627 53.1812 72.4133 51.4745 71.048C49.8532 69.5973 47.7198 68.4453 45.0745 67.592C42.5145 66.7387 39.0158 65.8 34.5785 64.776C27.7518 63.1547 22.1625 61.5333 17.8105 59.912C13.4585 58.2907 9.70385 55.688 6.54651 52.104C3.47451 48.52 1.93851 43.7413 1.93851 37.768C1.93851 32.5627 3.34651 27.8693 6.16251 23.688C8.97851 19.4213 13.2025 16.0507 18.8345 13.576C24.5518 11.1013 31.5065 9.86399 39.6985 9.86399C45.4158 9.86399 51.0052 10.5467 56.4665 11.912C61.9278 13.2773 66.7065 15.24 70.8025 17.8L64.4025 33.544C56.1252 28.8507 47.8478 26.504 39.5705 26.504C33.7678 26.504 29.4585 27.4427 26.6425 29.32C23.9118 31.1973 22.5465 33.672 22.5465 36.744C22.5465 39.816 24.1252 42.12 27.2825 43.656C30.5252 45.1067 35.4318 46.5573 42.0025 48.008C48.8292 49.6293 54.4185 51.2507 58.7705 52.872C63.1225 54.4933 66.8345 57.0533 69.9065 60.552C73.0638 64.0507 74.6425 68.7867 74.6425 74.76C74.6425 79.88 73.1918 84.5733 70.2905 88.84C67.4745 93.0213 63.2078 96.3493 57.4905 98.824C51.7732 101.299 44.8185 102.536 36.6265 102.536ZM128.876 97.672C126.913 99.1227 124.481 100.232 121.58 101C118.764 101.683 115.82 102.024 112.748 102.024C104.47 102.024 98.1128 99.9333 93.6755 95.752C89.2382 91.5707 87.0195 85.4267 87.0195 77.32V16.904H106.988V33.672H124.012V49.032H106.988V77.064C106.988 79.9653 107.713 82.2267 109.164 83.848C110.614 85.384 112.705 86.152 115.436 86.152C118.508 86.152 121.238 85.2987 123.628 83.592L128.876 97.672ZM207.917 32.136V101H188.845V93.064C183.895 99.0373 176.727 102.024 167.341 102.024C160.855 102.024 154.967 100.573 149.677 97.672C144.471 94.7707 140.375 90.632 137.389 85.256C134.402 79.88 132.909 73.6507 132.909 66.568C132.909 59.4853 134.402 53.256 137.389 47.88C140.375 42.504 144.471 38.3653 149.677 35.464C154.967 32.5627 160.855 31.112 167.341 31.112C176.13 31.112 182.999 33.8853 187.949 39.432V32.136H207.917ZM170.797 85.64C175.831 85.64 180.013 83.9333 183.341 80.52C186.669 77.0213 188.333 72.3707 188.333 66.568C188.333 60.7653 186.669 56.1573 183.341 52.744C180.013 49.2453 175.831 47.496 170.797 47.496C165.677 47.496 161.453 49.2453 158.125 52.744C154.797 56.1573 153.133 60.7653 153.133 66.568C153.133 72.3707 154.797 77.0213 158.125 80.52C161.453 83.9333 165.677 85.64 170.797 85.64ZM267.876 97.672C265.913 99.1227 263.481 100.232 260.58 101C257.764 101.683 254.82 102.024 251.748 102.024C243.47 102.024 237.113 99.9333 232.676 95.752C228.238 91.5707 226.02 85.4267 226.02 77.32V16.904H245.988V33.672H263.012V49.032H245.988V77.064C245.988 79.9653 246.713 82.2267 248.164 83.848C249.614 85.384 251.705 86.152 254.436 86.152C257.508 86.152 260.238 85.2987 262.628 83.592L267.876 97.672ZM279.154 32.136H299.122V101H279.154V32.136ZM289.138 22.536C285.468 22.536 282.482 21.4693 280.178 19.336C277.874 17.2027 276.722 14.5573 276.722 11.4C276.722 8.24264 277.874 5.59732 280.178 3.46398C282.482 1.33065 285.468 0.263977 289.138 0.263977C292.807 0.263977 295.794 1.28797 298.098 3.33598C300.402 5.38399 301.554 7.94398 301.554 11.016C301.554 14.344 300.402 17.1173 298.098 19.336C295.794 21.4693 292.807 22.536 289.138 22.536ZM337.11 33.672H354.774V49.032H337.622V101H317.654V30.6C317.654 22.7493 319.958 16.52 324.566 11.912C329.259 7.30397 335.83 4.99998 344.278 4.99998C347.264 4.99998 350.08 5.34132 352.726 6.02398C355.456 6.62132 357.718 7.51731 359.51 8.71198L354.262 23.176C351.958 21.5547 349.27 20.744 346.198 20.744C340.139 20.744 337.11 24.072 337.11 30.728V33.672ZM434.155 32.136V89.48C434.155 102.109 430.955 111.496 424.555 117.64C418.155 123.784 408.853 126.856 396.651 126.856C390.165 126.856 384.021 126.045 378.219 124.424C372.501 122.803 367.68 120.456 363.755 117.384L371.691 103.048C374.592 105.437 378.133 107.315 382.315 108.68C386.496 110.045 390.72 110.728 394.987 110.728C401.643 110.728 406.507 109.192 409.579 106.12C412.651 103.048 414.187 98.3547 414.187 92.04V89.736C411.627 92.296 408.597 94.2587 405.099 95.624C401.6 96.9893 397.845 97.672 393.835 97.672C384.789 97.672 377.621 95.112 372.331 89.992C367.04 84.7867 364.395 77.0213 364.395 66.696V32.136H384.363V63.752C384.363 74.9307 389.013 80.52 398.315 80.52C403.093 80.52 406.933 78.984 409.835 75.912C412.736 72.7547 414.187 68.104 414.187 61.96V32.136H434.155Z" fill="white"/>
                        </svg>
                        <p>The web application allows the connection to a user's Spotify account to access their listening history. It is used to provide statistics about their listening habits, such as most-listened-to genres, artists, and time of day, etc..</p>
                        <Button color={"orange"} scale={0.9} onClick={() => login()}>GET STARTED</Button>
                        <a href="https://github.com/SE-TINF22B6/Statify">
                            <div className={"github row"}>
                                <img src={GitHubMark} alt="GitHub Mark"/>
                                <img src={GitHubLogo} alt="GitHub Logo"/>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    )

    function login(){
        fetch("http://localhost:8081/authorize")
            .then((res) => {
                return res.text()
            })
            .catch((err) => {
                navigate("/callback?error=true")
                return null
            })
            .then((link) => {
                if(link != null) {
                    console.log(link)
                    window.location = (link)
                }
            })

    }
}
