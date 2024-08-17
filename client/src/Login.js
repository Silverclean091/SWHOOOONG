import './Join.css'; //회원가입과 같은 css사용
import './First.css';
import {Link} from "react-router-dom";

function Login() {
    return (
        <div>
            <span className="arrow_prev" onClick={go_back}></span>
            <h4 className='top'>로그인</h4>
            <input className='middle1' placeholder='이메일'></input>
            <input className='middle1' placeholder='비밀번호'></input>
            <button className='middle2'>확인</button>
            <Link to='/find_password' className='underbar_text'>
                <h5>비밀번호 찾기</h5>
            </Link>
            <div className='peristalsis'>
                <img alt="kakao" src="img/kakao.png"/>
                <img alt="google" src="img/google.png"/>
                <img alt="kakao" src="img/naver.png"/>
            </div>
        </div>
    );
}

function go_back() {
    window.history.back();
}

export default Login;
