import {useState} from 'react';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faFilePdf} from '@fortawesome/free-solid-svg-icons';
import {CustomerLessonPackage} from "../../../shared/types/Lesson.ts";
import {InvoiceService} from "../services/invoiceService.ts";

interface CreatePdfButtonProps {
    lessonPackage: CustomerLessonPackage;
}

const CreatePdfButton: React.FC<CreatePdfButtonProps> = ({lessonPackage}) => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedInstrument, setSelectedInstrument] = useState<string>('Klavier');
    const [isLoading, setIsLoading] = useState(false);

    const handleCreatePdf = async () => {
        if (!selectedInstrument) {
            console.error('No instrument selected');
            return;
        }

        setIsLoading(true);

        try {
            await InvoiceService.createInvoice({
                lessonPackage,
                selectedInstrument,
            });
            setIsModalOpen(false);
        } catch (error) {
            console.error("Failed to create PDF", error);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div>
            <button
                onClick={() => setIsModalOpen(true)}
                className="bg-accent hover:bg-opacity-90 text-white font-semibold py-2 px-4 rounded-sm shadow-md transition flex items-center space-x-2"
            >
                <FontAwesomeIcon icon={faFilePdf}/>
                <span>PDF Erstellen</span>
            </button>

            {isModalOpen && (
                <div className="m-4 fixed inset-0 flex items-center justify-center bg-opacity-50 z-50">
                    <div className="border-2 border-accent bg-white text-black rounded-sm shadow-lg p-6 max-w-sm w-full">
                        <h2 className="text-2xl font-semibold mb-4 text-accent">Wählen Sie ein Instrument</h2>

                        <div className="mb-4">
                            <label className="block">
                                <input
                                    type="radio"
                                    name="instrument"
                                    value="Klavier"
                                    checked={selectedInstrument === 'Klavier'}
                                    onChange={(e) => setSelectedInstrument(e.target.value)}
                                    className="mr-2"
                                />
                                Klavier
                            </label>
                        </div>

                        <div className="mb-4">
                            <label className="block">
                                <input
                                    type="radio"
                                    name="instrument"
                                    value="Ukulele"
                                    checked={selectedInstrument === 'Ukulele'}
                                    onChange={(e) => setSelectedInstrument(e.target.value)}
                                    className="mr-2"
                                />
                                Ukulele
                            </label>
                        </div>

                        <div className="mb-4">
                            <label className="block">
                                <input
                                    type="radio"
                                    name="instrument"
                                    value="Schlagzeug"
                                    checked={selectedInstrument === 'Schlagzeug'}
                                    onChange={(e) => setSelectedInstrument(e.target.value)}
                                    className="mr-2"
                                />
                                Schlagzeug
                            </label>
                        </div>

                        {isLoading ? (
                            <div className="flex justify-center mt-4">
                                <div
                                    className="loader border-t-4 border-accent rounded-full w-8 h-8 animate-spin"></div>
                            </div>
                        ) : (
                            <div className="flex justify-end mt-6 space-x-4">
                                <button
                                    onClick={() => setIsModalOpen(false)}
                                    className="bg-gray-500 hover:bg-gray-600 text-white py-2 px-4 rounded-sm transition"
                                >
                                    Abbrechen
                                </button>
                                <button
                                    onClick={handleCreatePdf}
                                    className="bg-accent hover:bg-opacity-90 text-white py-2 px-4 rounded-sm transition"
                                >
                                    PDF Erstellen
                                </button>
                            </div>
                        )}
                    </div>
                </div>
            )}
        </div>
    );
};

export default CreatePdfButton;