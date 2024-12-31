import React from "react";
import {useAuth} from "../context/authContext.tsx";
import {useNavigate} from "react-router-dom";

const LoginGuard = ({children}: { children: React.ReactNode }) => {
    const {isAuthenticated, isCheckingAuth} = useAuth();
    const navigate = useNavigate();

    if (isAuthenticated) {
        navigate("/calendar");
    }

    if (isCheckingAuth) {
        return <div>Loading...</div>;
    }

    return !isAuthenticated ? <>{children}</> : null;
};

export default LoginGuard;
