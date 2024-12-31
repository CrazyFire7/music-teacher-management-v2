import {useEffect, useState} from 'react';
import moment from 'moment-timezone';
import {CustomerLessonPackage} from "../../shared/types/Lesson.ts";
import MarkAsDoneButton from "./components/MarkAsDoneButton.tsx";
import CreatePDFButton from "./components/CreatePDFButton.tsx";
import {LessonService} from "./services/lessonService.ts";

const InvoicePage = () => {
    const [lessonPackages, setLessonPackages] = useState<CustomerLessonPackage[]>([]);

    const fetchLessonPackages = async () => {
        const lessonPackages = await LessonService.getLessonPackages();

        setLessonPackages(lessonPackages);
    };

    useEffect(() => {
        fetchLessonPackages();
    }, []);

    return (
        <div className="container mx-auto bg-tertiary p-6 rounded-md">
            <h1 className="text-3xl font-bold mb-6 text-accent">Offene Lektionen</h1>
            {lessonPackages.length === 0 ? (
                <p className="text-lg text-gray-600">Keine Kunden mit 4 unbezahlten Lektionen.</p>
            ) : (
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                    {lessonPackages.map((pkg, index) => (
                        <div key={index} className="bg-white p-6 rounded-sm shadow-md">
                            <h2 className="text-xl font-semibold text-accent mb-3">
                                {pkg.customer.firstName} {pkg.customer.lastName}
                            </h2>
                            <ul className="space-y-4">
                                {pkg.lessons.map((lesson) => (
                                    <li key={lesson.id} className="border-b border-gray-300 pb-3 mb-3">
                                        <p className="font-semibold text-lg">{lesson.title}</p>
                                        <p className="text-sm text-gray-500">
                                            {moment(lesson.start).format('YYYY-MM-DD HH:mm')} -{' '}
                                            {moment(lesson.end).format('YYYY-MM-DD HH:mm')} (
                                            {moment(lesson.end).diff(moment(lesson.start), 'minutes')} Minuten)
                                        </p>
                                    </li>
                                ))}
                                <div className="flex justify-between items-center space-x-4 mt-4">
                                    <CreatePDFButton lessonPackage={pkg}/>
                                    <MarkAsDoneButton lessonPackage={pkg} onMarkedAsDone={fetchLessonPackages}/>
                                </div>
                            </ul>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default InvoicePage;