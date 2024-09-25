import React, { useState } from 'react';
import Calendar from 'react-calendar';
import Modal from 'react-modal';
import '../style/Calendar.css';
import { IoMdMore, IoMdMenu } from "react-icons/io";
import { IoHome } from "react-icons/io5";
import { FaRegBell, FaCalendarDay, FaPenNib, FaRegClock } from "react-icons/fa";
import { LuListTodo } from "react-icons/lu";
import { AiOutlineGlobal } from "react-icons/ai";
import { MdNoteAlt } from "react-icons/md";
import { FaRegFolder } from "react-icons/fa";
import { FaRepeat } from "react-icons/fa6";

Modal.setAppElement('#root');

// 일정 수정, 삭제, 취소 옵션 제공 모달
function EventOptionsModal({ onClose, onEdit, onDelete }) {
    return (
        <div className="optionsModalOverlay" onClick={onClose}>
            <div className="optionsModalContent" onClick={e => e.stopPropagation()}>
                <button onClick={onEdit}>일정 수정</button>
                <button onClick={onDelete} style={{ color: 'red' }}>일정 삭제</button>
                <button onClick={onClose}>취소</button>
            </div>
        </div>
    );
}

function Cal() {
    const [value, onChange] = useState(new Date());
    const [events, setEvents] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
    const [newEvent, setNewEvent] = useState({ title: '', memo: '', 
        startTime: '', endTime: '', date: '', repeat: 'none', 
        notification: 'none', category: '' });
    const [categories, setCategories] = useState([]);
    const [selectedEvent, setSelectedEvent] = useState(null);
    const [OptionsOpen, setOptionsOpen] = useState(false);

    const openModal = (event = null) => { // 모달 열기
        setSelectedEvent(event);
        setNewEvent(event || { title: '', memo: '', 
            startTime: '', endTime: '', date: '', repeat: 'none', 
            notification: 'none', category: '' });
        setModalOpen(true);
    };

    const closeModal = () => { // 모달 닫기
        setModalOpen(false);
        setSelectedEvent(null);
        setNewEvent({ title: '', memo: '',
            startTime: '', endTime: '', date: '', repeat: 'none', 
            notification: 'none', category: '' });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setNewEvent(prev => ({ ...prev, [name]: value }));
    };

    const saveEvent = () => { // 이벤트 추가, 기존 이벤트 수정하여 저장
        if (selectedEvent) {
            setEvents(events.map(ev => ev.id === selectedEvent.id ? { ...newEvent, id: ev.id, value: ev.value } : ev));
        } else {
            setEvents([...events, { ...newEvent, id: events.length + 1, value }]);
        }
        closeModal();
    };

	// 이벤트 삭제 (이벤트 id를 기준으로) 
    const delEvent = (id) => setEvents(events.filter(event => event.id !== id));

    const toggleOptions = (event) => {
        setSelectedEvent(event);
        setOptionsOpen(!OptionsOpen);
    };

    return (
        <div>
            // 헤더
            <div className='header'>
                <div className='logo'>로고 자리</div>
                <IoMdMenu size="40" className='menu' />
                <FaRegBell size="36" className='alarm' />
            </div>

            <div className='mid'>
                <div className='today' />
                // 달력
                <Calendar
                    onChange={onChange} // onChange -> 날짜 상태 저장                    value={value}
                    calendarType='gregory'
                    formatDay={(locale, date) => date.getDate()}
                    prev2Label={null}
                    next2Label={null}
                    showNeighboringMonth={false}
                />

                <h2>{value.toLocaleDateString('ko-KR', { year: 'numeric', month: 'long', day: 'numeric', weekday: 'long' })}</h2>

                // 이벤트 추가
                <div className='eventSec'>
                    {events
                        // 선택된 날짜에 해당하는 일정만 필터링
                        .filter(event => new Date(event.value).toDateString() === value.toDateString())
                        .map(event => (
                            <div className="eventBox" key={event.id}>
                                <div className="eventInfo">
                                    <span className="eventTitle">{event.title}</span>
                                    <span className="eventMemo">{event.memo}</span>
                                    <button className="eventOption" onClick={() => toggleOptions(event)}><IoMdMore /></button>
                                </div>
                                <div className="eventTime">
                                    <span>{event.startTime}</span><br />
                                    <span>{event.endTime}</span>
                                </div>
                            </div>
                        ))
                    }
                </div>

                {OptionsOpen && (
                    <EventOptionsModal
                        onClose={() => setOptionsOpen(false)}
                        onEdit={() => openModal(selectedEvent)}
                        onDelete={() => {
                            delEvent(selectedEvent.id);
                            setOptionsOpen(false);
                        }}
                    />
                )}

                <button className='addButton' onClick={() => openModal()}>+</button>
            </div>

			// 이벤트 추가, 수정 모달		
            <Modal
                isOpen={modalOpen}
                onRequestClose={closeModal}
                contentLabel='Event Modal'
                className='modal'
                overlayClassName='overlay'
            >
                <div className='modalContents'>
                    <input
                        type='text'
                        name='title'
                        value={newEvent.title}
                        onChange={handleChange}
                        placeholder='일정 제목'
                    />
                    <FaRegClock className='icon' />
                    <input
                        type="time"
                        name="startTime"
                        value={newEvent.startTime}
                        onChange={handleChange}
                    />
                    <input
                        type="time"
                        name="endTime"
                        value={newEvent.endTime}
                        onChange={handleChange}
                    />
                    <div>
                        <FaRepeat className='icon' />
                        <select name="repeat" value={newEvent.repeat} onChange={handleChange}>
                            <option value="none">반복 없음</option>
                            <option value="daily">매일</option>
                            <option value="weekly">매주</option>
                            <option value="monthly">매달</option>
                        </select>
                    </div>
                    <div>
                        <FaRegBell className='icon' />
                        <select name="notification" value={newEvent.notification} onChange={handleChange}>
                            <option value="none">알림 없음</option>
                            <option value="5m">5분 전</option>
                            <option value="10m">10분 전</option>
                            <option value="20m">20분 전</option>
                            <option value="30m">30분 전</option>
                            <option value="40m">40분 전</option>
                            <option value="50m">50분 전</option>
                            <option value="60m">1시간 전</option>
                            <option value="120m">2시간 전</option>
                            <option value="180m">3시간 전</option>
                            <option value="240m">4시간 전</option>
                            <option value="300m">5시간 전</option>
                            <option value="360m">6시간 전</option>
                            <option value="420m">7시간 전</option>
                            <option value="480m">8시간 전</option>
                            <option value="540m">9시간 전</option>
                            <option value="600m">10시간 전</option>
                            <option value="660m">11시간 전</option>
                            <option value="720m">12시간 전</option>
                        </select>
                    </div>
                    <div>
                        <FaRegFolder className='icon' />
                        <select name="category" value={newEvent.category} onChange={handleChange}>
                            {categories.map((cat, idx) => (
                                <option key={idx} value={cat}>{cat}</option>
                            ))}
                        </select>
                    </div>
                    <MdNoteAlt className='icon' />
                    <textarea
                        name="memo"
                        value={newEvent.memo}
                        onChange={handleChange}
                        placeholder="메모"
                    />

					// selectedEvent가 존재하면 버튼 텍스트 "수정" 표기, 그렇지 않으면 버튼 텍스트 "추가"로 표기							
                    <button onClick={saveEvent}>{selectedEvent ? '수정' : '추가'}</button>
                </div>
            </Modal>

			// 하단	(임시)
            <div className='footer'>
                <div className='under'>
                    <IoHome size='30' /><span>홈</span>
                    <FaCalendarDay size='30' /><span>캘린더</span>
                    <LuListTodo size='30' /><span>투두</span>
                    <FaPenNib size='30' /><span>기록</span>
                    <AiOutlineGlobal size='30' /><span>커뮤니티</span>
                </div>
            </div>
        </div>
    );
}

export default Cal;