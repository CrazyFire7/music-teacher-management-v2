import {Customer} from "./Customer.ts";

export enum LessonStatus {
    OFFEN = "OFFEN",
    STATTGEFUNDEN = "STATTGEFUNDEN",
    BEZAHLT = "BEZAHLT"
}

export interface Lesson {
    id: number;
    title: string;
    start: string;
    end: string;
    status: LessonStatus;
    customerId: number | null;
}

export interface CalendarEvent {
    id: string;
    title: string;
    start: string;
    end: string;
    allDay: boolean;
    backgroundColor: string;
    extendedProps: { status: LessonStatus, customerId: number };
}

export interface CustomerLessonPackage {
    customer: Customer;
    lessons: Lesson[];
}