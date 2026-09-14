# Student API Practice

Lernprojekt für eine REST-API mit Spring Boot, PostgreSQL und
einem einfachen Angular-Frontend.

## Funktionen

- Studenten anlegen, auflisten, abrufen, ändern und löschen
- Eingaben validieren
- HTTP 404 bei unbekannten Studenten für GET und PUT
- Vier automatisierte Controller-Tests

## Technologien

- Java 21 und Spring Boot
- PostgreSQL
- Angular und TypeScript
- Maven

## Projektstruktur

- backend/: REST-API und Backend-Tests
- frontend/: Angular-Oberfläche

## Lokal starten

Voraussetzungen: Java 21, Node.js mit npm und PostgreSQL.

### Datenbank

PostgreSQL muss auf localhost:5432 laufen.
Benötigt werden eine Datenbank `studentdb` und ein Benutzer
`studentapi` mit Berechtigungen für diese Datenbank und ihr Schema.

### Backend

Im Ordner `backend` das Datenbankpasswort als Umgebungsvariable
setzen und die Anwendung starten:

```bash
export DB_PASSWORD='DEIN_LOKALES_PASSWORT'
./mvnw spring-boot:run
```

Den Platzhalter durch das eigene Passwort ersetzen.
Das Backend ist unter http://localhost:8080 erreichbar.

### Frontend

In einem zweiten Terminal im Ordner `frontend`:

```bash
npm ci
npm start
```

Die Oberfläche ist unter http://localhost:4200 erreichbar.

## Tests

Im Ordner `backend`:

```bash
./mvnw -Dtest=StudentControllerTest test
```

Die vier Controller-Tests benötigen keine laufende Datenbank
und keinen separat gestarteten Backend-Server.