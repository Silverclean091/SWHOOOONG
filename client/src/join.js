import './Join.css';
import './First.css';

function join() {
    return (
        <div>
            <span className="arrow_prev" onClick={go_back}></span>
            <h4 className='top'>회원가입</h4>
            <input className='middle1' placeholder='이메일 등록'></input>
            <input className='middle1' placeholder='비밀번호 등록'></input>
            <button className='middle2' onClick={popup}>이메일 인증</button>
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

function popup() {
    window.open("Email_popup.js", "이메일 인증", "width=600, height=400, left=400, top=100")
}

export default join;
