import './Join.css';
import './First.css';
import React from "react";
import { useNavigate } from "react-router-dom";

function Join() {
    const [modal, set_modal] = React.useState(0);
    const navigate = useNavigate();

    const first_modal = ()=> { /*코드 입력창*/
        set_modal(1);
    };
    const close_modal = () => { /*모달 닫기*/
        set_modal(0);
    };
    const next_modal = () => { /*이메일 인증 이후*/
        set_modal(2);
        setTimeout( () => {
            navigate('/sign_up');
        }, 2000);
    };

    return (
        <div>
            <span className='arrow_prev' onClick={GoBack}></span>
            <h4 className='top'>회원가입</h4>
            <input className='middle1' placeholder='이메일 등록'></input>
            <input className='middle1' placeholder='비밀번호 등록'></input>
            <button className='middle2' onClick={first_modal}>이메일 인증</button>

            {modal !== 0 && <div className='modal_overly' />}
            {modal === 1 && <Modal closeModal={close_modal} next_modal={next_modal}/>}
            {modal === 2 && <Modal2 />}

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

function Modal({next_modal}){
    const [input_code, set_input_code] = React.useState('');
    const [error_message, set_error_message] = React.useState('');
    const sent_input_code = '123456';

    const verifycode = () => {
        if(input_code === sent_input_code){
            next_modal();
        }
        else{
            set_error_message('인증 코드가 올바르지 않습니다.');
        }
    };

    return (
        <div className='modal'>
            <h4>
                <div>이메일에 코드를 전송하였습니다.</div>
                <div>해당 코드를 입력하세요.</div>
                <input className='middle3' placeholder='코드 입력' value={input_code}
                    onChange={(e) => set_input_code(e.target.value)}>
                </input>
                {error_message && <div className='modal_e_error'>{error_message}</div>}
                <button className='middle4' onClick={verifycode}>확인</button>
            </h4>
        </div>
    );
}

function Modal2() {
    return (
        <div className='modal'>
            <h4>이메일 인증이 완료되었습니다.</h4>
        </div>
    );
}

export default Join;
