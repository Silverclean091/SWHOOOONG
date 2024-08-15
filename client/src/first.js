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
                <Link to='/log_in' className='log_in'>
                    <h3>
                        로그인
                    </h3>
                </Link>
            </div>
        </div>
    );
}

export default first;