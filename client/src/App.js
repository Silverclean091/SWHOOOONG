import React from 'react';
import { Routes, Route, BrowserRouter } from "react-router-dom";
import './App.css';
import First from './First';
import Join from './Join';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<First />} />
                <Route path="/join" element={<Join />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
