import './First.css';
import {Link} from 'react-router-dom'

function first() {
    return (
        <div className='First'>
            <div className='container'>
                <img className='logo' alt="logo" src="img/cat.jpg" width='400px'/>
                <Link to='/join' className='join'>
                    회원가입
                </Link>
                <Link to='/login' className='underbar_text'>
                    <h3>
                        로그인
                    </h3>
                </Link>
            </div>
        </div>
    );
}

export default first;