import {useEffect, useState} from 'react';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import momentPlugin from '@fullcalendar/moment';
import momentTimezonePlugin from '@fullcalendar/moment-timezone';
import LessonModal from './LessonModal.tsx';
import moment from 'moment-timezone';
import {DateSelectArg, EventApi, EventClickArg, EventContentArg} from "@fullcalendar/core";
import {Customer} from "../../../shared/types/Customer.ts";
import {CalendarEvent, Lesson, LessonStatus} from "../../../shared/types/Lesson.ts";
import {CustomerService} from "../services/customerService.ts";
import {LessonService} from "../services/lessonService.ts";

// Source: https://fullcalendar.io/docs/react

const Calendar = () => {
    const [events, setEvents] = useState<CalendarEvent[]>([]);
    const [customers, setCustomers] = useState<Customer[]>([]);
    const [selectedLesson, setSelectedLesson] = useState<Lesson | null>(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isCreating, setIsCreating] = useState(false);

    useEffect(() => {
        fetchLessons()
            .then(data => {
                const lessons = data.map((lesson: Lesson) => ({
                    title: lesson.title,
                    start: moment.tz(lesson.start, 'Europe/Zurich').format(),
                    end: moment.tz(lesson.end, 'Europe/Zurich').format(),
                    id: lesson.id.toString(),
                    allDay: isAllDayEvent(lesson.start, lesson.end),
                    backgroundColor: lesson.status === LessonStatus.STATTGEFUNDEN ? '#6b7280' :
                        lesson.status === LessonStatus.BEZAHLT ? '#3b82f6' : '#10b981',
                    extendedProps: {
                        status: lesson.status,
                        customerId: lesson.customerId !== null ? lesson.customerId : 0
                    }
                }));
                setEvents(lessons);
            })

        fetchCustomers()
            .then(customers => setCustomers(customers))
    }, []);

    const fetchCustomers = async () => {
        return await CustomerService.getCustomers()
    };

    const handleEventClick = (clickInfo: EventClickArg) => {
        const lessonId = clickInfo.event.id;
        const lesson = events.find(ev => ev.id === lessonId);
        if (lesson) {
            setSelectedLesson({
                id: parseInt(lesson.id),
                title: lesson.title,
                start: lesson.start,
                end: lesson.end,
                status: lesson.extendedProps.status,
                customerId: lesson.extendedProps.customerId
            });
            setIsCreating(false);
            setIsModalOpen(true);
        }
    };

    const handleDateSelect = (selectInfo: DateSelectArg) => {
        setSelectedLesson({
            id: 0,
            title: '',
            start: moment.tz(selectInfo.start, 'Europe/Zurich').set({ hour: 8, minute: 0, second: 0 }).format(),
            end: moment.tz(selectInfo.start, 'Europe/Zurich').set({ hour: 8, minute: 45, second: 0 }).format(),
            status: LessonStatus.OFFEN,
            customerId: null
        });
        setIsCreating(true);
        setIsModalOpen(true);
    };

    const handleSubmit = () => {
        if (selectedLesson) {
            if (isCreating) {
                createLesson(selectedLesson)
                    .then((newLesson) => {
                        //runs after successfully creating lesson
                        setEvents((prevLessons) => [
                            ...prevLessons,
                            {
                                id: newLesson.id.toString(),
                                title: newLesson.title,
                                start: moment.tz(newLesson.start, 'Europe/Zurich').format(),
                                end: moment.tz(newLesson.end, 'Europe/Zurich').format(),
                                backgroundColor: '#10b981',
                                allDay: isAllDayEvent(newLesson.start, newLesson.end),
                                extendedProps: {
                                    status: newLesson.status,
                                    customerId: newLesson.customerId ?? 0
                                },
                            },
                        ]);
                        setIsModalOpen(false);
                    })
                    .catch(error => console.error('Error creating lesson:', error));
            } else {
                updateLesson(selectedLesson)
                    .then(() => {
                        //runs after successfully updating lesson
                        setIsModalOpen(false);
                        setEvents((prevLessons) =>
                            prevLessons.map((event) =>
                                event.id === selectedLesson.id.toString()
                                    ? {
                                        ...event,
                                        title: selectedLesson.title,
                                        start: moment.tz(selectedLesson.start, 'Europe/Zurich').format(),
                                        end: moment.tz(selectedLesson.end, 'Europe/Zurich').format(),
                                        backgroundColor: selectedLesson.status === LessonStatus.STATTGEFUNDEN
                                            ? '#6b7280'
                                            : selectedLesson.status === LessonStatus.BEZAHLT
                                                ? '#3b82f6'
                                                : '#10b981',
                                        extendedProps: {
                                            status: selectedLesson.status,
                                            customerId: selectedLesson.customerId ?? 0
                                        },
                                    }
                                    : event
                            )
                        );
                    })
                    .catch(error => console.error('Error updating lesson:', error));
            }
        }
    };

    const fetchLessons = async () => {
        try {
            return await LessonService.getLessons();
        } catch (error: any) {
            if (error.status === 401) {
                window.location.reload();
            }
            throw error;
        }
    };


    const createLesson = async (lesson: Lesson) => {
        return await LessonService.createLesson(lesson);
    };

    const updateLesson = async (lesson: Lesson) => {
        await LessonService.updateLesson(lesson);
    };

    const deleteLesson = async (lessonId: number) => {
        await LessonService.deleteLesson(lessonId);
    };


    const handleDeleteLesson = () => {
        if (selectedLesson) {
            deleteLesson(selectedLesson.id)
                .then(() => {
                    setEvents(prevLessons => prevLessons.filter(prevLesson => prevLesson.id !== selectedLesson.id.toString()));
                    setIsModalOpen(false);
                })
        }
    };
    const handleClose = () => {
        setIsModalOpen(false);
        setSelectedLesson(null);
    };

    const handleEventDidMount = (info: { event: EventApi; el: HTMLElement }) => {
        const {status} = info.event.extendedProps;
        info.el.style.backgroundColor = status === LessonStatus.STATTGEFUNDEN
            ? '#6b7280'
            : status === LessonStatus.BEZAHLT
                ? '#3b82f6'
                : '#10b981';
        info.el.style.color = 'white';
    };

    return (
        <div>
            <FullCalendar
                plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin, momentPlugin, momentTimezonePlugin]}
                initialView="dayGridMonth"
                timeZone="Europe/Zurich"
                locale="de"
                firstDay={1}
                events={events}
                selectable={true}
                select={handleDateSelect}
                eventClick={handleEventClick}
                eventDidMount={handleEventDidMount}
                views={{
                    dayGridMonth: {buttonText: 'Monat'},
                    timeGridWeek: {buttonText: 'Woche'},
                    timeGridDay: {buttonText: 'Tag'}
                }}
                headerToolbar={{
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                }}
                buttonText={{
                    today: 'Heute',
                }}
                height="auto"
                eventContent={(info) => renderEventContent(info, customers)}
            />

            {isModalOpen && selectedLesson && (
                <LessonModal
                    lesson={selectedLesson}
                    onClose={handleClose}
                    onSubmit={handleSubmit}
                    onChange={(field, value) => setSelectedLesson(prev => prev ? {...prev, [field]: value} : prev)}
                    onDelete={handleDeleteLesson}
                    isCreating={isCreating}
                    customers={customers}
                />
            )}
        </div>
    );
};

function isAllDayEvent(start: string, end: string): boolean {
    const startTime = moment(start).format('HH:mm:ss');
    const endTime = moment(end).format('HH:mm:ss');
    return startTime === '00:00:00' && (endTime === '23:59:59' || endTime === '00:00:00');
}

function renderEventContent(eventInfo: EventContentArg, customers: Customer[]) {
    const {status, customerId} = eventInfo.event.extendedProps;

    const backgroundColor = status === LessonStatus.STATTGEFUNDEN
        ? 'gray'
        : status === LessonStatus.BEZAHLT
            ? 'blue'
            : 'green';

    const customer = customerId ? customers.find(customer => customer.id === customerId) : null;
    const customerName = customer ? `${customer.firstName} ${customer.lastName}` : 'Unbekannt';

    if (eventInfo.event.allDay) {
        return (
            <div style={{backgroundColor, paddingLeft: '10px'}}>
                {eventInfo.event.title} - {customerName}
            </div>
        );
    }

    const isMonthView = eventInfo.view.type === 'dayGridMonth';
    const timeText = isMonthView ? moment(eventInfo.event.start).format('HH:mm') : '';

    return (
        <div style={{backgroundColor, paddingLeft: '10px'}}>
            {timeText && <span style={{marginRight: '5px'}}>{timeText}</span>}
            {eventInfo.event.title} - {customerName}
        </div>
    );
}

export default Calendar;