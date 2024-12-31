import React from 'react';
import moment from "moment";
import {Lesson, LessonStatus} from "../../../shared/types/Lesson.ts";
import {Customer} from "../../../shared/types/Customer.ts";

interface LessonModalProps {
    lesson: Lesson;
    onClose: () => void;
    onSubmit: () => void;
    onChange: (field: string, value: any) => void;
    onDelete?: () => void;
    isCreating: boolean;
    customers: Customer[];
}

const LessonModal: React.FC<LessonModalProps> = ({ lesson, onClose, onSubmit, onChange, onDelete, isCreating, customers }) => {
    const handleDateTimeChange = (field: string, value: string) => {
        const newTime = moment(value);
        const startTime = field === "start" ? newTime : moment(lesson.start);
        let endTime = field === "end" ? newTime : moment(lesson.end);

        if (field === "start" && startTime.isAfter(endTime)) {
            endTime = startTime.clone().add(45, "minutes");
            onChange("end", endTime.toISOString());
        }

        if (field === "end" && endTime.isBefore(startTime)) {
            alert("Endzeit darf nicht vor der Startzeit liegen.");
            return;
        }

        if (endTime.diff(startTime, "minutes") > 45) {
            alert("Die maximale Dauer eines Termins beträgt 45 Minuten.");
            return;
        }

        if (startTime.isSame(endTime, "day") === false) {
            alert("Ein Termin darf nicht über mehrere Tage gehen.");
            return;
        }

        onChange(field, value);
    };

    const isFormValid = lesson.title.trim() !== '' && lesson.customerId !== null;

    return (
        <div className="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-50">
            <div className="bg-white text-gray-800 rounded-lg shadow-lg p-6 max-w-lg w-full m-6">
                <h3 className="text-lg font-bold mb-4">{isCreating ? 'Neue Lektion erstellen' : 'Lektion bearbeiten'}</h3>

                <label className="block mb-4">
                    <span className="text-sm font-semibold">Titel:</span>
                    <input
                        type="text"
                        className="w-full p-2 mt-1 bg-white border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        value={lesson.title}
                        onChange={(e) => onChange('title', e.target.value)}
                    />
                </label>

                <label className="block mb-4">
                    <span className="text-sm font-semibold">Startzeit:</span>
                    <input
                        type="datetime-local"
                        className="w-full p-2 mt-1 bg-white border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        value={moment(lesson.start).format('YYYY-MM-DDTHH:mm')}
                        onChange={(e) => handleDateTimeChange('start', e.target.value)}
                    />
                </label>

                <label className="block mb-4">
                    <span className="text-sm font-semibold">Endzeit:</span>
                    <input
                        type="datetime-local"
                        className="w-full p-2 mt-1 bg-white border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        value={moment(lesson.end).format('YYYY-MM-DDTHH:mm')}
                        onChange={(e) => handleDateTimeChange('end', e.target.value)}
                    />
                </label>

                <label className="block mb-4">
                    <span className="text-sm font-semibold">Kunde:</span>
                    <select
                        className="w-full p-2 mt-1 bg-white border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        value={lesson.customerId ?? 0}
                        onChange={(e) => {
                            const selectedValue = parseInt(e.target.value, 10);
                            onChange('customerId', selectedValue === 0 ? null : selectedValue);
                        }}
                    >
                        <option value="0">Bitte wählen</option>
                        {customers.map((customer) => (
                            <option key={customer.id} value={customer.id}>
                                {customer.firstName} {customer.lastName}
                            </option>
                        ))}
                    </select>
                </label>

                <label className="block mb-4">
                    <span className="text-sm font-semibold">Status:</span>
                    <select
                        className="w-full p-2 mt-1 bg-white border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        value={lesson.status}
                        onChange={(e) => onChange('status', e.target.value as LessonStatus)}
                    >
                        <option value={LessonStatus.OFFEN}>Offen</option>
                        <option value={LessonStatus.STATTGEFUNDEN}>Stattgefunden</option>
                        <option value={LessonStatus.BEZAHLT}>Bezahlt</option>
                    </select>
                </label>

                <div className="flex justify-between items-center mt-6">
                    {!isCreating && (
                        <button onClick={onDelete} className="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded-md transition">
                            Löschen
                        </button>
                    )}
                    <div className="flex justify-end space-x-2">
                        <button onClick={onClose} className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-md transition">
                            Abbrechen
                        </button>
                        <button
                            onClick={onSubmit}
                            className={`bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-md transition ${
                                isFormValid ? '' : 'opacity-50 cursor-not-allowed'
                            }`}
                            disabled={!isFormValid}
                        >
                            {isCreating ? 'Erstellen' : 'Speichern'}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LessonModal;