import React, {createContext, useCallback, useContext, useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';

interface AuthContextType {
    isAuthenticated: boolean;
    isCheckingAuth: boolean;
    isSubmitting: boolean;
    errorMessage: string;
    checkAuth: () => Promise<void>;
    login: (email: string, password: string) => Promise<boolean>;
    logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({children}: { children: React.ReactNode }) => {
    const navigate = useNavigate();
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isCheckingAuth, setIsCheckingAuth] = useState(true);
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [errorMessage, setErrorMessage] = useState<string>("");

    const checkAuth = useCallback(async () => {
        setIsCheckingAuth(true);
        try {
            const token = Cookies.get('token');
            if (!token) {
                logout();
                setIsAuthenticated(false);
                return;
            }
            const response = await axios.get('http://localhost:8080/api/authenticate/check-auth', {
                headers: {Authorization: `Bearer ${token}`},
            });
            setIsAuthenticated(response.status === 200);
        } catch {
            setIsAuthenticated(false);
        } finally {
            setIsCheckingAuth(false);
        }
    }, []);

    const login = useCallback(async (email: string, password: string): Promise<boolean> => {
        setIsSubmitting(true);
        setErrorMessage("");
        try {
            const response = await axios.post('http://localhost:8080/api/authenticate/login', {
                email,
                password,
            });

            if (response.status === 200 && response.data.token) {
                Cookies.set('token', response.data.token, {secure: true, sameSite: 'Strict'});
                setIsAuthenticated(true);
                navigate('/calendar');
                return true;
            } else {
                setErrorMessage("Anmeldung fehlgeschlagen. Bitte überprüfen Sie Ihre Zugangsdaten.");
                return false;
            }
        } catch (error) {
            setErrorMessage("Anmeldung fehlgeschlagen. Bitte überprüfen Sie Ihre Zugangsdaten.");
            return false;
        } finally {
            setIsSubmitting(false);
        }
    }, [navigate]);

    const logout = useCallback(async () => {
        Cookies.remove('token');
        setIsAuthenticated(false);
        navigate('/login');
    }, [navigate]);

    // Interceptet alle HTTP-Anfragen und überprüft auf 401
    useEffect(() => {
        const interceptor = axios.interceptors.response.use(
            response => response,
            error => {
                if (error.response?.status === 401) {
                    logout();
                }
                return Promise.reject(error);
            }
        );

        return () => axios.interceptors.response.eject(interceptor);
    }, [logout]);

    //Prüfung ob Benutzer eingeloggt ist beim Laden der Seite
    useEffect(() => {
        checkAuth();
    }, [checkAuth]);

    return (
        <AuthContext.Provider
            value={{
                isAuthenticated,
                isCheckingAuth,
                isSubmitting,
                errorMessage,
                checkAuth,
                login,
                logout,
            }}
        >
            {children}
        </AuthContext.Provider>
    );
};

//Auth hook
export const useAuth = (): AuthContextType => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('Authprovider error');
    }
    return context;
};