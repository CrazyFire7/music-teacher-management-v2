import './App.css'
import Header from "./shared/components/Header.tsx";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./features/authentication/loginPage.tsx";
import CustomerPage from "./features/customerManagement/customerPage.tsx";
import CalendarPage from "./features/lessonManagement/calendarPage.tsx";
import InvoicePage from "./features/invoiceManagement/invoicePage.tsx";

import AuthGuard from "./shared/guards/authGuard.tsx";
import {AuthProvider} from "./shared/context/authContext.tsx";
import LoginGuard from "./shared/guards/loginGuard.tsx";

function App() {
    return (
        <>
            <BrowserRouter>
                <AuthProvider>
                    <Header/>
                    <main
                        className="main-content flex-grow flex flex-col p-6 bg-primary"
                        style={{minHeight: 'calc(100vh - 75px)'}}
                    >
                        <Routes>
                            <Route path="/" element={<LoginPage/>}/>
                            <Route path="/login" element={
                                <LoginGuard>
                                    <LoginPage/>
                                </LoginGuard>}/>
                            <Route path="/calendar" element={
                                <AuthGuard>
                                    <CalendarPage/>
                                </AuthGuard>}/>
                            <Route path="/customer-overview" element={
                                <AuthGuard>
                                    <CustomerPage/>
                                </AuthGuard>}/>
                            <Route path="/invoices" element={
                                <AuthGuard>
                                    <InvoicePage/>
                                </AuthGuard>}/>
                        </Routes>
                    </main>
                </AuthProvider>
            </BrowserRouter>
        </>
    )
}

export default App
