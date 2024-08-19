import React, { useState } from 'react';
import Calendar from 'react-calendar';
import '../style/Calendar.css';

function Cal() {
    const [value, onChange] = useState(new Date());

    return (
        <div>
            <div class='top'>
                <div class='menu'>
                    메뉴 아이콘 자리
                </div>
                <div class='alert'>
                    알림 아이콘 자리
                </div>
            </div>
            <div class='mid'>
                <div class='today'>
                    오늘 날짜 자리
                </div>
                <div class='cal'>
                    <Calendar
                    onChange={onChange} value={value}
                    calendarType='gregory'
                    formatDay={(locale, date) => date.toLocaleString('en', 
                        {day: 'numeric'})}
                    prev2Label={null}
                    next2Label={null}
                    showNeighboringMonth={false}
                    />
                </div>
                <div class='schedule'>
                    일정
                </div>
            </div>
            <div class='under'>
                <div class='foot'>
                    하단바
                </div>
            </div>
        </div>
    );
}

export default Cal;