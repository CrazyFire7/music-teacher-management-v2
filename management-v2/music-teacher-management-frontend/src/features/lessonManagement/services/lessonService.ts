import Cookies from 'js-cookie';
import {Lesson} from "../../../shared/types/Lesson.ts";
import moment from "moment-timezone";

const API_BASE_URL = 'http://localhost:8080/api/lessons';
const token = Cookies.get('token');

export const LessonService = {
    getLessons: async () => {
        const response = await fetch(`${API_BASE_URL}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`
            },
        });

        if (!response.ok) {
            throw {
                status: response.status,
                statusText: response.statusText,
                message: `Failed to fetch customers`,
            };
        }

        return response.json();
    },

    createLesson: async (lesson: Lesson) => {
        const response = await fetch(`${API_BASE_URL}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({
                ...lesson,
                start: moment(lesson.start).format('YYYY-MM-DDTHH:mm:ss'),
                end: moment(lesson.end).format('YYYY-MM-DDTHH:mm:ss'),
            }),
        });

        if (!response.ok) throw new Error('Failed to create lesson');

        const createdLesson: Lesson = await response.json();
        return createdLesson;
    },

    updateLesson: async (lesson: Lesson) => {
        if (lesson.customerId == 0) {
            lesson.customerId = null;
        }

        const response = await fetch(`${API_BASE_URL}/${lesson.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({
                ...lesson,
                start: moment(lesson.start).format('YYYY-MM-DDTHH:mm:ss'),
                end: moment(lesson.end).format('YYYY-MM-DDTHH:mm:ss'),
            }),
        });

        if (!response.ok) throw new Error('Failed to update lesson');

        const updatedLesson: Lesson = await response.json();
        return updatedLesson;
    },

    deleteLesson: async (lessonId: number) => {
        const response = await fetch(`${API_BASE_URL}/${lessonId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            },
        });

        if (!response.ok) throw new Error('Failed to delete lesson');
    }
};