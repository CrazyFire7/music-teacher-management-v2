import Cookies from "js-cookie";

const API_BASE_URL = 'http://localhost:8080/api/invoices';
const token = Cookies.get('token');

export const InvoiceService = {
    createInvoice: async (body: Record<string, any>) => {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(body),
        });

        if (!response.ok) {
            throw new Error(`Failed to create invoice: ${response.statusText}`);
        }

        const blob = await response.blob();

        // PDF downloaden, links sollte direkt geöffnet werden
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = `invoice_${body.selectedInstrument}.pdf`;
        link.click();

        window.URL.revokeObjectURL(url);

        return 'Invoice downloaded';
    }
};