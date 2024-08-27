import React, { Fragment, useState } from 'react';
import Calendar from 'react-calendar';
import Modal from 'react-modal';
import '../style/Calendar.css';
import { IoMdMore, IoMdMenu } from "react-icons/io";
import { IoHome } from "react-icons/io5";
import { FaRegBell, FaCalendarDay } from "react-icons/fa";
import { LuListTodo } from "react-icons/lu";
import { FaPenNib } from "react-icons/fa6";
import { AiOutlineGlobal } from "react-icons/ai";

Modal.setAppElement('#root');  // 모달을 사용하는 컴포넌트의 최상위 요소 설정

function Cal() {
    const [value, onChange] = useState(new Date()); // 날짜
    const [event, setEvent] = useState([]); // 이벤트
    const [modalOpen, setModalOpen] = useState(false); // 모달
    const [newEvent, setNewEvent] = useState({ // 이벤트
        title: '', memo: '',
        startTime: '', endTime: '', date: ''
    });
    const [selectEvent, setSelectEvent] = useState(null);
    const [openEdit, setEditOpen] = useState(false);
    const [openMenu, setMenuOpen] = useState(false);

    const openModal = () => {
        setModalOpen(true);
    };

    const closeModal = () => {
        setModalOpen(false);
        setNewEvent({title: '', memo: '',
            startTime: '', endTime: '', date: ''
        });
    };

    const menuOpen = (event) => {
        setSelectEvent(event);
        setMenuOpen(true);
    };

    const editOpen = (event) => {
        setSelectEvent(event);
        setNewEvent({
            title: event.title, memo: event.memo,
            startTime: event.startTime, endTime: event.endTime,
            date: event.date
        });
        setEditOpen(true);
    };

    const editClose = () => {
        setEditOpen(false);
        setSelectEvent(null);
        setNewEvent({title: '', memo: '', startTime: '',
            endTime: '', date: ''
        });
    };

    const reflection = (e) => { // 입력값 반영
        const {name, value} = e.target;
        setNewEvent(prev => ({...prev, [name]: value}));
    };

    const saveEvent = () => { // newEvent 속성 복사, newEvent에 고유 id 부여
        setEvent([...event, {...newEvent, id: event.length + 1, value}]);
        closeModal();
    };

    const updateEvent = () => {
        setEvent(event.map(ev => 
            ev.id === selectEvent.id ? {...newEvent, id: ev.id, value} : ev
        ));
        editClose();
    };

    const delEvent = (id) => {
        setEvent(event.filter((event) => event.id !== id));
    };

    return (
        <div>
            <div class='top'>
                <div class='logo'>
                    로고 자리
                </div>
                <div class='menu'>
                    <IoMdMenu size="40"/>
                </div>
                <div class='alarm'>
                    < FaRegBell size="36"/>
                </div>
            </div>

            <div class='mid'>
                <div class='today'>
                    ㅤ
                </div>
                <div class='cal'>
                    <Calendar
                    onChange={onChange}
                    value={value}
                    calendarType='gregory'
                    formatDay={(locale, date) => date.toLocaleString('en', 
                        {day: 'numeric'})}
                    prev2Label={null}
                    next2Label={null}
                    showNeighboringMonth={false}
                    />
                </div>

                <h2>{value.toLocaleDateString('ko-KR', 
                        {year: 'numeric', month: 'long', 
                        day: 'numeric', weekday: 'long'})}</h2>

                <div class='eventSec'>
                <div class='eventList'>
                    {event
                        .filter((event) => 
                            new Date(event.value).toDateString() === value.toDateString())
                        .map((event) => (
                        <div class="eventBox" key={event.id}>
                            <div class="eventInfo">
                                <span class="eventTitle">{event.title}</span>
                                <span class="eventMemo">{event.memo}</span>
                                <button class='eventOption' onClick={() => menuOpen(event)}><IoMdMore /></button>
                                    {openMenu && selectEvent.id === event.id && (
                                        <div class='eventMenu'>
                                        <button onClick={() => editOpen(event)}>일정 수정</button>
                                        <button onClick={() => delEvent(event.id)} style={{ color: 'red' }}>일정 삭제</button>
                                        <button onClick={editClose}>취소</button>
                                    </div>
                                )}
                            </div>
                            <div class="eventTime">
                                <span>{event.startTime}</span><br />
                                <span>{event.endTime}</span>
                            </div>
                        </div>
                    ))}
                </div>
                </div>
                
                <button class='addButton' onClick={openModal}>+</button>
            </div>
            
            <Modal
                isOpen={modalOpen}
                onRequestClose={closeModal}
                contentLabel='Modal'
                className='modal'
                overlayClassName='overlay'
            >
                <div class='modalContents'>
                    <input
                        type='text'
                        name='title'
                        value={newEvent.title}
                        onChange={reflection}
                        placeholder='일정 제목'
                    />
                    <input
                        type="time"
                        name="startTime"
                        value={newEvent.startTime}
                        onChange={reflection}
                    />
                    <input
                        type="time"
                        name="endTime"
                        value={newEvent.endTime}
                        onChange={reflection}
                    />
                    <textarea
                        name="memo"
                        value={newEvent.memo}
                        onChange={reflection}
                        placeholder="메모"
                    />
                    <button onClick={saveEvent}>추가</button>
                </div>
            </Modal>

            
            <Modal
                isOpen={openEdit}
                onRequestClose={editClose}
                contentLabel='Edit Modal'
                className='modal'
                overlayClassName='overlay'
            >
                <div class='modalContents'>
                    <input
                        type='text'
                        name='title'
                        value={newEvent.title}
                        onChange={reflection}
                        placeholder='일정 제목'
                    />
                    <input
                        type="time"
                        name="startTime"
                        value={newEvent.startTime}
                        onChange={reflection}
                    />
                    <input
                        type="time"
                        name="endTime"
                        value={newEvent.endTime}
                        onChange={reflection}
                    />
                    <textarea
                        name="memo"
                        value={newEvent.memo}
                        onChange={reflection}
                        placeholder="메모"
                    />
                    <button onClick={saveEvent}>추가</button>
                </div>
            </Modal>
            
            <Modal
                isOpen={openMenu}
                onRequestClose={() => setMenuOpen(false)}
                contentLabel='Menu Modal'
                className='menuModal'
                overlayClassName='overlay'
            >
                <div class='menuModalContents'>
                    <button onClick={() => editOpen(selectEvent)}>일정 수정</button>
                    <button onClick={() => delEvent(selectEvent.id)} style={{ color: 'red' }}>일정 삭제</button>
                    <button onClick={() => setMenuOpen(false)}>취소</button>
                </div>
            </Modal>

            <div class='under'>
                <div class='foot'>
                    <div class='homeIcon'>
                        <IoHome size='30'/>
                        <span>홈</span>
                    </div>
                    <div class='calIcon'>
                        <FaCalendarDay size='30'/>
                        <span>캘린더</span>
                    </div>
                    <div class='todoIcon'>
                        <LuListTodo size='30'/>
                        <span>투두</span>
                    </div>
                    <div class='recordIcon'>
                        <FaPenNib size='30'/>
                        <span>기록</span>
                    </div>
                    <div class='commity'>
                        <AiOutlineGlobal size='30'/>
                        <span>커뮤니티</span>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Cal;