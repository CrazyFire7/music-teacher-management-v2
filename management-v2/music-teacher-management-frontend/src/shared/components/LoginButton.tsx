import React from 'react';
import { KeyIcon } from '@heroicons/react/24/solid';
import { Link } from 'react-router-dom';

interface LoginButtonProps {
    onClick?: () => void;
}

const LoginButton: React.FC<LoginButtonProps> = ({ onClick }) => {
    return (
        <Link to="/login">
            <button
                onClick={onClick}
                className="flex items-center bg-accent hover:bg-opacity-90 text-white font-bold py-2 px-4 rounded-sm shadow-lg transition duration-300 ease-in-out"
            >
                <KeyIcon className="h-5 w-5 mr-2" />
                Login
            </button>
        </Link>
    );
};

export default LoginButton;