import './Join.css'; //회원가입과 같은 css사용
import './First.css';
import {Link} from "react-router-dom";
import React from "react";

function Login() {
    return (
        <div>
            <span className="arrow_prev" onClick={GoBack}></span>
            <h4 className='top'>로그인</h4>
            <input className='middle1' placeholder='이메일'></input>
            <input className='middle1' placeholder='비밀번호'></input>
            <button className='middle2'>확인</button>
            <Link to='/find_password' className='underbar_text'>
                <h5>비밀번호 찾기</h5>
            </Link>
            <div className='peristalsis'>
                <img alt="kakao" src="img/btn_kakao.svg"/>
                <img alt="google" src="img/btn_google.svg"/>
                <img alt="kakao" src="img/btn_naver.svg"/>
            </div>
        </div>
    );
}

function GoBack() {
    window.history.back();
}

export default Login;
