import './Join.css';

function join() {
    return (
        <div>
            <span className="arrow_prev"></span>
            <h4 className='top'>회원가입</h4>
            <input className='middle' placeholder='이메일 등록'></input>
            <input className='middle' placeholder='비밀번호 등록'></input>
        </div>
    );
}

export default join;
