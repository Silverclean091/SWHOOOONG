import React, { useState } from 'react';
import Calendar from 'react-calendar';
import '../style/Calendar.css';

function App() {
    const [value, onChange] = useState(new Date());

    return (
        <div>
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
    );
}

export default App;