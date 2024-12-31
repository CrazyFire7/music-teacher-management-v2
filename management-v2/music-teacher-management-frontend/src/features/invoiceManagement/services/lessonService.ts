import Cookies from "js-cookie";
import {Lesson} from "../../../shared/types/Lesson.ts";
import moment from "moment-timezone";

const API_BASE_URL = 'http://localhost:8080/api/lessons';
const token = Cookies.get('token');

export const LessonService = {
    getLessonPackages: async () => {
        const response = await fetch(`${API_BASE_URL}/completed-lessons-packages`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`
            },
        });

        if (!response.ok) {
            throw new Error(`Failed to fetch lesson-packages: ${response.statusText}`);
        }

        return await response.json();
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

        if (!response.ok) {
            throw new Error(`Failed to update lesson`);
        }

        const updatedLesson: Lesson = await response.json();
        return updatedLesson;
    }
};