import React from "react";
import styles from './first.css'

function First() {
    return (
        <div className="First">
            <div className={styles.parent}>
                <div className={styles.child}>
                    <img className={styles.logo} alt="logo" src="img/cat.jpg" width='400px'/>
                    <h2 className={styles.join}>회원가입</h2>
                    <h3 className={styles.log_in}>로그인</h3>
                </div>
            </div>
        </div>
    );
}

export default First;