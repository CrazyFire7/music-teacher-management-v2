import React, {useEffect} from "react";
import {useAuth} from "../context/authContext.tsx";

const AuthGuard = ({children}: { children: React.ReactNode }) => {
    const {isAuthenticated, isCheckingAuth, logout} = useAuth();

    useEffect(() => {
        if (!isCheckingAuth && !isAuthenticated) {
            logout();
        }
    }, [isAuthenticated, isCheckingAuth, logout]);

    if (isCheckingAuth) {
        return <div>Loading...</div>;
    }

    return isAuthenticated ? <>{children}</> : null;
};

export default AuthGuard;
