import React from 'react';
import { Routes, Route, BrowserRouter } from "react-router-dom";
import './App.css';
import First from './First';
import Join from './Join';
import Login from './Login';
import SignUp from './SignUp';

function App() {
    return (
        <div className="container">
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<First />} /> //시작페이지
                    <Route path="/join" element={<Join />} /> //회원가입
                    <Route path="/sign_up" element={<SignUp />} /> //회원가입-등록
                    <Route path="/login" element={<Login />} /> //로그인
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
