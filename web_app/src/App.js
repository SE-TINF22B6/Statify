import LoginRegisterPage from "./pages/LoginRegisterPage";
import {BrowserRouter, Route, Routes} from "react-router-dom"
import LandingPage from "./pages/LandingPage";
import ProfilePage from "./pages/ProfilePage";
import StatisticsPage from "./pages/StatisticsPage";
function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LandingPage/>}/>
                <Route path="/login" element={<LoginRegisterPage initialState={1}/>}/>
                <Route path="/register" element={<LoginRegisterPage initialState={0}/>}/>
                <Route path="/profile" element={<ProfilePage/>}/>
                <Route path={"/statistics"} element={<StatisticsPage/>}/>
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
