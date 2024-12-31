import React, {useEffect, useState} from 'react';
import {PencilSquareIcon, TrashIcon} from '@heroicons/react/24/outline';
import {useNavigate} from 'react-router-dom';
import {CustomerService} from "./services/customerService.ts";
import {Lesson} from "../../shared/types/Lesson.ts";
import {LessonService} from "./services/lessonService.ts";
import DeleteModal from "./components/DeleteModal.tsx";
import {Customer} from "../../shared/types/Customer.ts";

const CustomerPage = () => {
    const [customers, setCustomers] = useState<Customer[]>([]);
    const [lessons, setLessons] = useState<Lesson[]>([]);
    const [newCustomer, setNewCustomer] = useState<Customer>({
        id: 0,
        firstName: '',
        lastName: '',
        email: '',
        strasse: '',
        postleitzahl: '',
        ort: ''
    });
    const [isEditing, setIsEditing] = useState(false);
    const [deleteModalOpen, setDeleteModalOpen] = useState(false);
    const [customerToDelete, setCustomerToDelete] = useState<Customer | null>(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCustomers = async () => {
            const customers = await CustomerService.getCustomers();
            console.log(customers)
            setCustomers(customers);

            if (customers.length > 0) {
                const firstCustomerId = customers[0].id;
                fetchLessonsByCustomerId(firstCustomerId);
            }
        };

        fetchCustomers();
    }, [navigate]);

    const fetchLessonsByCustomerId = async (customerId: number) => {
        const lessons: Lesson[] = await LessonService.getLessonsByCustomerId(customerId)
        setLessons(lessons);
    };

    const handleCreateCustomer = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const addedCustomer = await CustomerService.createCustomer(newCustomer);
            setCustomers((prevCustomers) => [...prevCustomers, addedCustomer]);
            resetForm();
        } catch (error) {
            console.error("Error creating customer:", error);
        }
    };

    const handleUpdateCustomer = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const updatedCustomer = await CustomerService.updateCustomer(newCustomer);
            setCustomers((prevCustomers) =>
                prevCustomers.map((customer) =>
                    customer.id === updatedCustomer.id ? updatedCustomer : customer
                )
            );
            resetForm();
        } catch (error) {
            console.error("Error updating customer:", error);
        }
    };

    const resetForm = () => {
        setNewCustomer({id: 0, firstName: '', lastName: '', email: '', strasse: '', postleitzahl: '', ort: ''});
        setIsEditing(false);
    };

    const handleDeleteClick = (customer: Customer) => {
        setCustomerToDelete(customer);
        setDeleteModalOpen(true);
    };

    const confirmDelete = async () => {
        if (customerToDelete) {
            await CustomerService.deleteCustomer(customerToDelete.id);
            setCustomers((prevCustomers) => prevCustomers.filter((customer) => customer.id !== customerToDelete.id));
            setDeleteModalOpen(false);
        }
    };

    const handleEditClick = (customer: Customer) => {
        setNewCustomer(customer);
        setIsEditing(true);
    };

    const handleCancelEdit = () => {
        setNewCustomer({id: 0, firstName: '', lastName: '', email: '', strasse: '', postleitzahl: '', ort: ''});
        setIsEditing(false);
    };

    return (
        <div>
            <div className="container mx-auto">
                <div className="bg-tertiary p-6 rounded-md shadow-md mb-4">
                    <h2 className="text-xl font-semibold text-gray-700 mb-4">{isEditing ? 'Kunden bearbeiten' : 'Neuen Kunden hinzufügen'}</h2>
                    <form onSubmit={isEditing ? handleUpdateCustomer : handleCreateCustomer} className="space-y-4">
                        <div>
                            <label className="block text-gray-600 font-semibold">Vorname</label>
                            <input
                                type="text"
                                value={newCustomer.firstName}
                                onChange={(e) => setNewCustomer({...newCustomer, firstName: e.target.value})}
                                className="w-full p-3 border border-gray-300 rounded-sm focus:outline-none focus:ring-2 focus:ring-accent text-black"
                                required
                            />
                        </div>
                        <div>
                            <label className="block text-gray-600 font-semibold">Nachname</label>
                            <input
                                type="text"
                                value={newCustomer.lastName}
                                onChange={(e) => setNewCustomer({...newCustomer, lastName: e.target.value})}
                                className="w-full p-3 border border-gray-300 rounded-sm focus:outline-none focus:ring-2 focus:ring-accent text-black"
                                required
                            />
                        </div>
                        <div>
                            <label className="block text-gray-600 font-semibold">Email</label>
                            <input
                                type="email"
                                value={newCustomer.email}
                                onChange={(e) => setNewCustomer({...newCustomer, email: e.target.value})}
                                className="w-full p-3 border border-gray-300 rounded-sm focus:outline-none focus:ring-2 focus:ring-accent text-black"
                                required
                            />
                        </div>
                        <div>
                            <label className="block text-gray-600 font-semibold">Strasse</label>
                            <input
                                type="text"
                                value={newCustomer.strasse}
                                onChange={(e) => setNewCustomer({...newCustomer, strasse: e.target.value})}
                                className="w-full p-3 border border-gray-300 rounded-sm focus:outline-none focus:ring-2 focus:ring-accent text-black"
                                required
                            />
                        </div>
                        <div>
                            <label className="block text-gray-600 font-semibold">Postleitzahl</label>
                            <input
                                type="text"
                                value={newCustomer.postleitzahl}
                                onChange={(e) => setNewCustomer({...newCustomer, postleitzahl: e.target.value})}
                                className="w-full p-3 border border-gray-300 rounded-sm focus:outline-none focus:ring-2 focus:ring-accent text-black"
                                required
                            />
                        </div>
                        <div>
                            <label className="block text-gray-600 font-semibold">Ort</label>
                            <input
                                type="text"
                                value={newCustomer.ort}
                                onChange={(e) => setNewCustomer({...newCustomer, ort: e.target.value})}
                                className="w-full p-3 border border-gray-300 rounded-sm focus:outline-none focus:ring-2 focus:ring-accent text-black"
                                required
                            />
                        </div>
                        <div className="flex justify-between">
                            {isEditing && (
                                <button
                                    type="button"
                                    onClick={handleCancelEdit}
                                    className="bg-gray-500 text-white px-4 py-2 rounded-sm hover:bg-gray-600 transition"
                                >
                                    Abbrechen
                                </button>
                            )}
                            <button type="submit"
                                    className="bg-green-700 text-white px-4 py-2 rounded-sm hover:bg-blue-600 transition">
                                {isEditing ? 'Speichern' : 'Kunde hinzufügen'}
                            </button>
                        </div>
                    </form>
                </div>

                <div className="mb-8 rounded-md bg-tertiary p-6">
                    <h2 className="text-xl font-semibold text-gray-600 mb-4">Kunden-Liste</h2>
                    <ul className="space-y-4">
                        {customers.map((customer) => (
                            <li key={customer.id}
                                className="bg-white p-4 rounded-sm shadow-sm flex justify-between items-center border border-gray-200">
                                <div>
                                    <span
                                        className="font-semibold text-gray-800">{customer.firstName} {customer.lastName}</span>
                                    <span className="block text-gray-500 text-sm">{customer.email}</span>
                                    <span
                                        className="block text-gray-500 text-sm">Anzahl Termine: {lessons.length}</span>
                                </div>
                                <div className="flex flex-col space-x-2 space-y-2 md:space-y-0 md:flex-row">
                                    <button
                                        onClick={() => handleEditClick(customer)}
                                        className="bg-yellow-500 text-white px-4 py-2 rounded-sm hover:bg-yellow-600 transition flex items-center space-x-1"
                                    >
                                        <PencilSquareIcon className="w-5 h-5"/> <span>Edit</span>
                                    </button>
                                    <button
                                        onClick={() => handleDeleteClick(customer)}
                                        className="bg-red-500 text-white px-4 py-2 rounded-smlg hover:bg-red-600 transition flex items-center space-x-1"
                                    >
                                        <TrashIcon className="w-5 h-5"/> <span>Delete</span>
                                    </button>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>

            <DeleteModal
                isOpen={deleteModalOpen}
                onConfirm={confirmDelete}
                onCancel={() => setDeleteModalOpen(false)}
            />
        </div>
    );
};

export default CustomerPage;