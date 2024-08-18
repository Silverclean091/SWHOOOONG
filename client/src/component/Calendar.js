import React, { useState } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

function App() {
    const [value, onChange] = useState(new Date());

    return (
        <div>
            <Calendar
            onChange={onChange} value={value}
            calendarType='hebrew'
            />
        </div>
    );
}

export default App;