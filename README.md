# Layered Architecture Exercise (Spring Boot & JavaFX)

Dieses Projekt wurde im Rahmen eines Universitätsprojekts entwickelt, um die Prinzipien einer 
geschichteten Architektur (Layered Architecture) zu demonstrieren. Es implementiert eine 
Client-Server-Anwendung zur Verwaltung von Personen- und Notizdaten.

## Über das Projekt
Das Hauptziel dieses Projekts ist die praktische Anwendung einer Schichtenarchitektur. 
Es besteht aus einem Backend-Server, der mit Spring Boot erstellt wurde, und einer 
Desktop-Client-Anwendung, die JavaFX verwendet. Der Client kommuniziert mit dem Server 
über eine REST-API. Dieses Projekt dient als Demonstration meiner Fähigkeiten in der 
Softwareentwicklung, insbesondere im Umgang mit Java, Spring Boot und Design Patterns 
wie der Schichtenarchitektur, und wurde mit dem Ziel erstellt, mein GitHub-Profil für 
Bewerbungszwecke aufzubauen.

## Architektur

Das Projekt ist in drei Hauptmodule unterteilt, die eine klare Trennung der Zuständigkeiten 
gewährleisten:

1.  **`common`**: Enthält gemeinsame Datenmodelle (`Person`, `Note`) und Hilfsklassen
(`PersonSortingOptions`), die sowohl vom Server als auch vom Client verwendet werden.
2.  **`server`**: Implementiert das Backend als Spring Boot-Anwendung.
    * Stellt eine REST-API zum Verwalten von Personen und Notizen bereit.
    * Verwendet Service-Klassen (`PersonService`, `NoteService`) für die Geschäftslogik. 
    Die Daten werden vorerst im Speicher gehalten.
    * Bietet CRUD-Operationen (Create, Read, Update, Delete) für Personen und Notizen.
    * Implementiert Sortierfunktionen für die Personenliste basierend auf verschiedenen 
    Feldern (ID, Vorname, Nachname, Geburtstag) und Reihenfolgen (aufsteigend, absteigend).
3.  **`client`**: Eine Desktop-Anwendung mit JavaFX als Benutzeroberfläche.
    * Interagiert mit der REST-API des Servers über Controller (`PersonController`, 
    `NoteController`), die `WebClient` verwenden.
    * Bietet eine grafische Oberfläche (`HomeScene`, `NoteScene`, `PersonScene`) zur Anzeige 
    und Bearbeitung der Daten.
    * Ermöglicht das Hinzufügen, Bearbeiten und Löschen von Personen und Notizen.
    * Stellt Personendaten in einer Tabelle dar und ermöglicht das Sortieren durch Auswahl 
    von Spalten und Reihenfolge.

## Technologien

* Java
* Spring Boot
* Spring WebFlux (`WebClient`)
* JavaFX
* Gradle (Build-Tool)

## Setup und Start

Das Projekt verwendet Gradle als Build-System.

**Voraussetzungen:**

* JDK (Java Development Kit) - Version entsprechend der im Projekt verwendeten (prüfe die `build.gradle`-Datei, falls vorhanden)
* Gradle (wird normalerweise über den Wrapper bereitgestellt)

**1. Server starten:**

* Navigiere im Terminal zum Projektverzeichnis.
* Führe den Gradle-Task zum Starten der Spring Boot-Anwendung aus. Dies ist oft:
    ```bash
    ./gradlew :server:bootRun
    ```
    * Der Server wird standardmäßig auf `http://localhost:8080` gestartet.

**2. Client starten:**

* Öffne ein neues Terminalfenster und navigiere zum Projektverzeichnis.
* Führe den Gradle-Task zum Starten der Client-Anwendung aus. Dies könnte ein Standard-Task oder ein benutzerdefinierter Task sein (prüfe die `build.gradle`). Oft ist es:
    ```bash
    ./gradlew :client:run
    ```
    * Die JavaFX-Anwendung sollte starten und sich mit dem laufenden Server verbinden.

## API Endpunkte (Server)

* **Personen:**
    * `GET /persons`: Ruft alle Personen ab (mit optionalen Sortierparametern `sortField` und `sortingOrder`).
    * `POST /persons`: Erstellt eine neue Person.
    * `PUT /persons/{personId}`: Aktualisiert eine vorhandene Person.
    * `DELETE /persons/{personId}`: Löscht eine Person.
* **Notizen:**
    * `GET /notes?secret=SecretKey`: Ruft alle Notizen ab (erfordert einen geheimen Schlüssel).
    * `POST /notes`: Erstellt eine neue Notiz.
    * `PUT /notes/{noteId}`: Aktualisiert eine vorhandene Notiz.
    * `DELETE /notes/{noteId}`: Löscht eine Notiz.

*(Hinweis: Die `build.gradle`-Datei wurde nicht direkt analysiert, daher sind die Gradle-Befehle Annahmen basierend auf Standardkonventionen für Spring Boot und JavaFX-Projekte mit Gradle.)*