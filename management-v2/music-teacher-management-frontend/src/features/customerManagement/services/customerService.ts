import Cookies from 'js-cookie';
import {Customer} from "../../../shared/types/Customer.ts";

const API_BASE_URL = 'http://localhost:8080/api/customers';
const token = Cookies.get('token');

export const CustomerService = {
    getCustomers: async () => {
        const response = await fetch(`${API_BASE_URL}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`
            },
        });

        if (!response.ok) throw new Error('Failed to fetch customers');
        return response.json();
    },

    createCustomer: async (customer: Customer) => {
        const response = await fetch(`${API_BASE_URL}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`
            },
            body: JSON.stringify(customer),
        });

        if (!response.ok) throw new Error('Failed to add customer');
        return response.json();
    },

    updateCustomer: async (customer: Customer) => {
        const response = await fetch(`${API_BASE_URL}/${customer.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`
            },
            body: JSON.stringify(customer),
        });

        if (!response.ok) throw new Error('Failed to update customer');
        return response.json();
    },

    deleteCustomer: async (customerId: number) => {
        const response = await fetch(`${API_BASE_URL}/${customerId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
        });

        if (!response.ok) throw new Error('Failed to delete customer');
    },
};