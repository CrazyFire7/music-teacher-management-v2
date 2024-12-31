import {useState} from 'react';
import {Link, useLocation} from 'react-router-dom';
import LogoutButton from "./LogoutButton.tsx";
import LoginButton from "./LoginButton.tsx";
import {useAuth} from "../context/authContext.tsx";

const Header = () => {
    const location = useLocation();
    const {isAuthenticated} = useAuth();
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    const isActive = (path: string) => location.pathname === path;

    return (
        <header className="bg-primary text-secondary py-4 border-b border-gray-700 shadow-md relative h-[75px]">
            <nav className="container mx-auto flex justify-between items-center px-6 flex-wrap">
                <div className="flex items-center space-x-2 text-xl md:text-2xl font-bold tracking-wider text-accent">
                    <img
                        src="/logo.png"
                        alt="Logo"
                        className="w-8 h-8 object-contain"
                    />
                    <span>Instrumentalunterricht</span>
                </div>

                <button
                    className={`block md:hidden hover:text-accent transition duration-200 text-secondary focus:outline-none z-50 ${isMenuOpen ? 'fixed top-4 right-4' : 'relative'}`}
                    onClick={() => setIsMenuOpen(!isMenuOpen)}
                >
                    <svg
                        className="w-6 h-6"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        {isMenuOpen ? (
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                  d="M6 18L18 6M6 6l12 12"/>
                        ) : (
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                  d="M4 6h16M4 12h16m-7 6h7"/>
                        )}
                    </svg>
                </button>


                <ul className="hidden md:flex items-center space-x-6">
                    {isAuthenticated ? (
                        <>
                            <li>
                                <Link
                                    to="/calendar"
                                    className={`hover:text-accent transition duration-200 ${
                                        isActive('/calendar') ? 'text-accent' : ''
                                    }`}
                                >
                                    Kalender
                                </Link>
                            </li>
                            <li>
                                <Link
                                    to="/customer-overview"
                                    className={`hover:text-accent transition duration-200 ${
                                        isActive('/customer-overview') ? 'text-accent' : ''
                                    }`}
                                >
                                    Kunden-Management
                                </Link>
                            </li>
                            <li>
                                <Link
                                    to="/invoices"
                                    className={`hover:text-accent transition duration-200 ${
                                        isActive('/invoices') ? 'text-accent' : ''
                                    }`}
                                >
                                    Rechnungen
                                </Link>
                            </li>
                            <li>
                                <LogoutButton onClick={() => setIsMenuOpen(false)}/>
                            </li>
                        </>
                    ) : (
                        <li>
                            <LoginButton onClick={() => setIsMenuOpen(false)}/>
                        </li>
                    )}
                </ul>

                <div
                    className={`fixed top-0 right-0 w-52 h-full bg-primary text-secondary z-40 transform border border-tertiary ${
                        isMenuOpen ? 'translate-x-0' : 'translate-x-full'
                    } transition-transform duration-300 ease-in-out`}
                >
                    <ul className="mt-16 space-y-4 px-6">
                        {isAuthenticated ? (
                            <>
                                <li>
                                    <Link
                                        to="/calendar"
                                        className={`block hover:text-accent transition duration-200 ${
                                            isActive('/calendar') ? 'text-accent' : ''
                                        }`}
                                        onClick={() => setIsMenuOpen(false)}
                                    >
                                        Kalender
                                    </Link>
                                </li>
                                <li>
                                    <Link
                                        to="/customer-overview"
                                        className={`block hover:text-accent transition duration-200 ${
                                            isActive('/customer-overview') ? 'text-accent' : ''
                                        }`}
                                        onClick={() => setIsMenuOpen(false)}
                                    >
                                        Kunden-Management
                                    </Link>
                                </li>
                                <li>
                                    <Link
                                        to="/invoices"
                                        className={`block hover:text-accent transition duration-200 ${
                                            isActive('/invoices') ? 'text-accent' : ''
                                        }`}
                                        onClick={() => setIsMenuOpen(false)}
                                    >
                                        Rechnungen
                                    </Link>
                                </li>
                                <li>
                                    <LogoutButton onClick={() => setIsMenuOpen(false)}/>
                                </li>
                            </>
                        ) : (
                            <li>
                                <LoginButton onClick={() => setIsMenuOpen(false)}/>
                            </li>
                        )}
                    </ul>
                </div>
            </nav>
        </header>
    );
};

export default Header;