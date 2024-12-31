import Cookies from 'js-cookie';

const API_BASE_URL = 'http://localhost:8080/api/lessons';
const token = Cookies.get('token');

export const LessonService = {
    getLessonsByCustomerId: async (customerId: number) => {
        const response = await fetch(`${API_BASE_URL}?customerId=${customerId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
            },
        });

        if (!response.ok) throw new Error('Failed to fetch customers');
        return response.json();
    },
};