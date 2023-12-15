import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Home from "views/Home";
import MyInfo from "views/MyInfo";
import Enroll from "views/Enroll";
import Dict from "views/Dict";
import DictDetail from "views/DictDetail";
import PlantDiary from "views/PlantDiary";
import DiaryDetail from "views/DiaryDetail";
import DiaryEdit from "views/DiaryEdit";
import Chatbot from "views/Chatbot";
import DiagAI from "views/DiagAI";
import Login from "views/Login";
import getCookie from "utils/getCookie";
import { Navigate, Outlet } from "react-router-dom";

const PrivateRoute = () => {
  if (window.location.href === window.location.origin) {
  } else {
    const token = getCookie("Authorization");
    return token ? <Outlet /> : <Navigate replace to="/login" />;
  }
};

export default function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route>
          <Route path="/" element={<Home />} />
          <Route path="/my-info" element={<MyInfo />}></Route>
          <Route path="/my-info/enroll" element={<Enroll />}></Route>
          <Route path="/my-info/enroll/:plantId" element={<Enroll />}></Route>
          <Route path="/dict" element={<Dict />}></Route>
          <Route path="/dict/:id" element={<DictDetail />}></Route>
          <Route path="/diary" element={<PlantDiary />}></Route>
          <Route path="/diary/:id" element={<DiaryDetail />}></Route>
          <Route path="/diary/edit" element={<DiaryEdit />}></Route>
          <Route path="/diary/edit/:id" element={<DiaryEdit />}></Route>
          <Route path="/chatbot" element={<Chatbot />}></Route>
          <Route path="/diag-ai" element={<DiagAI />}></Route>
        </Route>
        <Route path="/login" element={<Login />}></Route>
      </Routes>
    </BrowserRouter>
  );
}
