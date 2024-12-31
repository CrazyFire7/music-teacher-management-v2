import Cookies from 'js-cookie';

const API_BASE_URL = 'http://localhost:8080/api/customers';

export const CustomerService = {
    getCustomers: async () => {
        const token = Cookies.get('token');
        if (!token) throw new Error('Unauthorized');

        const response = await fetch(`${API_BASE_URL}`, {
            method: 'GET',
            headers: {
                Authorization: `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error('Failed to fetch customers');
        }

        return response.json();
    },
};