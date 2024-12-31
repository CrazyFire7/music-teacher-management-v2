import React from 'react';
import {ArrowRightOnRectangleIcon} from '@heroicons/react/24/solid';
import {useAuth} from "../context/authContext.tsx";


interface LogoutButtonProps {
    onClick?: () => void;
}

const LogoutButton: React.FC<LogoutButtonProps> = ({onClick}) => {
    const {logout} = useAuth();

    const handleLogout = () => {
        logout();

        if (onClick) onClick();
    };

    return (
        <button
            onClick={handleLogout}
            className="flex items-center bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded-sm shadow-lg transition duration-300 ease-in-out"
        >
            <ArrowRightOnRectangleIcon className="h-5 w-5 mr-2"/>
            Logout
        </button>
    );
};

export default LogoutButton;