Bitte die README im Backend durchlesen. Sie enthält alle relevanten Informationen für den Kompetenznachweis.

# Musiklehrer-Verwaltung-Frontend (v1, Aaron Kögel)

Eine React-Frontend-Anwendung

Autor: Aaron Kögel

Ich hatte schon grundlegende Kenntnisse in React und nutzte Tailwind CSS in all meinen bisherigen Projekten.

Frontend: Manuelle Entwicklung ohne Chat-GPT mit Ausnahme des FullCalendars (zu komplex). Ausserdem wurden Dinge wie
Authentication Contexts, Tailwind Designs oder Guards von anderen privaten Projekten genutzt und geändert.

- Frontend geschätzter Aufwand: 8h

Backend: Entwicklung mit der Hilfe von Chat-GPT (Springboot)

- Backend geschätzter Aufwand: 9h

Total: 17h

## Features

- Authentifizierung: Anmeldung, Abmeldung und Zugriffsschutz über JWT-Token und Guards.
- Kundenverwaltung: Verwaltung von Kundeninformationen (Hinzufügen, Bearbeiten, Löschen).
- Rechnungsmanagement: Erstellung, Markierung und Export von Rechnungen als PDF.
- Unterrichtsverwaltung: Kalenderbasierte Planung und Bearbeitung von Unterrichtsstunden.

## Technologien

- **React und TypeScript**: Entwickelt für eine robuste und skalierbare Anwendung.
- **Vite**: Nutzen von Vite für Entwicklungs- und Build-Prozesse.
- **Tailwind CSS**: Responsives und modernes Design mit massgeschneiderter Anpassung.
- **Feature-basierte Struktur**: Klare Organisation des Codes für eine bessere Wartbarkeit und Erweiterbarkeit.
- **Fullcalendar**: Integrierter Kalender für eine Terminverwaltung.
- **Manuelle Entwicklung**: Vollständig eigenentwickelt, mit Ausnahme von Fullcalendar (mit Chat-GPT) aufgrund seiner
  Komplexität.

## Herausforderungen

Die Implementierung der Authentifizierung mit dem AuthContext war zeitaufwändig, insbesondere aufgrund von Problemen mit
dem Seitenrendering und der Stateverwaltung.

## Voraussetzungen

- Node.js (v20 oder höher)
- Docker und Docker Compose

## Start mit Docker Compose

1. Stellen Sie sicher, dass Docker und Docker Compose auf Ihrem System installiert sind.
2. Starten Sie die Anwendung:
   ```bash
   docker-compose up --build
3. Greifen Sie auf die Anwendung unter http://localhost:5173 zu.
4. Automatischer Admin User wurde erstellt:
    Username: admin@admin.com
    Password: admin

## Projektstruktur

- **src/**: Haupt-Quellcode mit Features, einschliesslich Komponenten, Hooks und Seiten.
- **public/**: Statische Ressourcen wie Bilder oder andere öffentliche Dateien.
- **index.html**: Einstiegspunkt der Anwendung.
- **tailwind.config.js**: Konfigurationsdatei für Tailwind CSS.
- **vite.config.ts**: Konfigurationsdatei für den Vite-Build.

## Verfügbare Skripte

- `npm run dev`: Startet den Entwicklungsserver.
- `npm run build`: Erstellt das Projekt für die Produktion.
- `npm run preview`: Vorschau des Produktions-Builds lokal anzeigen.

## Verwendete Technologien

- React
- TypeScript
- Tailwind CSS
- Vite