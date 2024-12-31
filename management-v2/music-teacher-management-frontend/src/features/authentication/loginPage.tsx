import {useState} from "react";
import {useAuth} from "../../shared/context/authContext.tsx";

const LoginPage = () => {
    const {login, isSubmitting, errorMessage} = useAuth();
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const handleInputChange = (
        setter: React.Dispatch<React.SetStateAction<string>>,
        value: string
    ) => {
        setter(value);
    };

    const handleSubmit = async (formEvent: React.FormEvent<HTMLFormElement>) => {
        formEvent.preventDefault();
        const success = await login(email, password);
        if (!success) {
            setEmail("");
            setPassword("");
        }
    };

    return (
        <div className="flex-grow flex items-center justify-center text-secondary mb-[75px]">
            <div className="bg-orange-50 p-8 shadow-lg w-full max-w-md rounded-md">
                <h1 className="text-3xl font-bold mb-8 text-center text-accent">Login</h1>
                <form onSubmit={handleSubmit}>
                    <div className="mb-6">
                        <label className="block mb-2 text-sm font-medium text-accent">Username</label>
                        <input
                            type="text"
                            value={email}
                            onChange={(e) => handleInputChange(setEmail, e.target.value)}
                            required
                            className="w-full p-3 border border-gray-400 rounded focus:outline-none focus:ring-2 focus:ring-accent text-black"
                        />
                    </div>
                    <div className="mb-6">
                        <label className="block mb-2 text-sm font-medium text-accent">Password</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => handleInputChange(setPassword, e.target.value)}
                            required
                            className="w-full p-3 border border-gray-400 rounded focus:outline-none focus:ring-2 focus:ring-accent text-black"
                        />
                    </div>
                    {errorMessage && <p className="text-red-500 mb-4">{errorMessage}</p>}
                    <button
                        type="submit"
                        className="w-full bg-accent text-white py-2 rounded-sm hover:bg-opacity-90 transition duration-300 ease-in-out"
                        disabled={isSubmitting}
                    >
                        {isSubmitting ? "Submitting..." : "Login"}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default LoginPage;