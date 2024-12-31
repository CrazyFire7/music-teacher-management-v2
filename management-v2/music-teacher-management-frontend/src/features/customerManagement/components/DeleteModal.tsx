import React from "react";

interface DeleteModalProps {
    isOpen: boolean;
    onConfirm: () => void;
    onCancel: () => void;
}

const DeleteModal: React.FC<DeleteModalProps> = ({isOpen, onConfirm, onCancel}) => {
    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
            <div className="bg-white rounded-lg shadow-lg p-6 w-full max-w-md">
                <h2 className="text-xl font-semibold text-gray-800 mb-4">Kunde löschen</h2>
                <p className="text-gray-600 mb-6">Möchten Sie diesen Kunden wirklich löschen? Diese Aktion kann nicht
                    rückgängig gemacht werden.</p>
                <div className="flex justify-end space-x-4">
                    <button onClick={onCancel}
                            className="bg-gray-300 text-gray-700 px-4 py-2 rounded-md hover:bg-gray-400 transition">Abbrechen
                    </button>
                    <button onClick={onConfirm}
                            className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 transition">Löschen
                    </button>
                </div>
            </div>
        </div>
    );
};

export default DeleteModal;