import LoginRegisterPage from "./pages/LoginRegisterPage";
import {BrowserRouter, Route, Routes} from "react-router-dom"
import LandingPage from "./pages/LandingPage";
import ProfilePage from "./pages/ProfilePage";
import StatisticsPage from "./pages/StatisticsPage";
import CallbackPage from "./pages/CallbackPage";
import TrackPage from "./pages/TrackPage";
import {createContext} from "react";
import ApiClient from "./util/apiClient";

export const ApiClientContext = createContext(ApiClient)

function App() {
    return (
        <div className="App">
            <ApiClientContext.Provider value={new ApiClient()}>
                <BrowserRouter>
                    <Routes>
                        <Route path="/" element={<LandingPage/>}/>
                        <Route path="/login" element={<LoginRegisterPage initialState={1}/>}/>
                        <Route path="/register" element={<LoginRegisterPage initialState={0}/>}/>
                        <Route path="/profile" element={<ProfilePage/>}/>
                        <Route path={"/statistics"} element={<StatisticsPage/>}/>
                        <Route path={"/callback"} element={<CallbackPage/>}/>
                        <Route path={"/track"} element={<TrackPage/>}/>
                    </Routes>
                </BrowserRouter>
            </ApiClientContext.Provider>

        </div>
    );
}

export default App;
