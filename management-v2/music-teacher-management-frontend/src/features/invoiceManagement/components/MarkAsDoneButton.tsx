import React from 'react';
import {CustomerLessonPackage, Lesson, LessonStatus} from "../../../shared/types/Lesson.ts";
import {LessonService} from "../services/lessonService.ts";

interface MarkAsDoneButtonProps {
    lessonPackage: CustomerLessonPackage;
    onMarkedAsDone: () => void;
}

const MarkAsDoneButton: React.FC<MarkAsDoneButtonProps> = ({lessonPackage, onMarkedAsDone}) => {

    const handleMarkAsPayed = async (): Promise<void> => {
        const updatePromises = lessonPackage.lessons.map(async (lesson: Lesson) => {
            lesson.status = LessonStatus.BEZAHLT;

            await LessonService.updateLesson(lesson);
            onMarkedAsDone();
        });

        await Promise.all(updatePromises);
        console.log('All lessons marked as paid');
    };

    return (
        <button
            onClick={handleMarkAsPayed}
            className="bg-green-700 hover:bg-green-600 text-white font-bold py-2 px-4 rounded-sm"
        >
            Als bezahlt markieren
        </button>
    );
};

export default MarkAsDoneButton;