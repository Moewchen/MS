# MS
## Was ist Git und warum sollte es verwendet werden?
- ständigen Änderungen bei Softwareprojekten (Beheben von Fehlern, Hinzufügen von Funktionen, strukturelle Verbesserungen) --> Versionen
- Versionskontrollprogramm
- Verzeichnisbäume verfolgen, verwalten und teilen
- Unterschiede bei Dateiänderungen verfolgen/vollständigen Verlauf aller Änderungen
- Snapshot der Dateien
- lokale Arbeit
- Funktionalität einer Zeitmaschine (Synchronisierung)
- Änderungen wieder rückgängig machen
- auf Performance, Sicherheit und Flexibilität ausgerichtet
- am weitesten verbreitetes Tool
- viele Softwaretools und -services von Drittanbietern bereits in Git integriert
## Grundlegende Git-Befehle
### Repositories anlegen
- git init [project-name]: Legt ein neues lokales Repository mit dem angegebenen Namen an
- git clone [url]: Klont ein Projekt und lädt seine gesamte Versionshistorie herunter
### Änderungen vornehmen
- git status: Listet alle zum Commit bereiten neuen oder geänderten Dateien auf
- git diff: Zeigt noch nicht indizierte Dateiänderungen an
- git add [file]: Indiziert den derzeitigen Stand der Datei für die Versionierung
- git commit -m"[descriptive message]": Nimmt alle derzeit indizierten Dateien permanent in die Versionshistorie auf
### Änderungen gruppieren
- git branch: Listet alle lokalen Branches im aktuellen Repository auf
- git branch [branch-name]: Erzeugt einen neuen Branch
- git merge [branch-name]: Fasst die Historie des angegeben Branches mit der des aktuell ausgecheckten Branches zusammen
### Dateinamen refaktorisieren
- git rm [file]: Löscht die Datei aus dem Arbeitsverzeichnis und wird im Index zur Löschung markiert, dadurch wird die Datei beim nächsten commit aus der Versionskontrolle gelöscht
- git mv [file-original] [file-renamed]: Ändert den Namen der Datei und bereitet diese für den Commit vor
### Historie überprüfen
- git log: Listet die Versionshistorie für den aktuellen Branch auf
### Änderungen synchronisieren
- git push [remote] [branch]: Pusht alle Commits auf dem lokalen Branch zu GitHub
- git pull: Pullt die Historie vom externen Repository und integriert die Änderungen
## Quellen
- Studienmodul MS: https://moodle.oncampus.de/mod/loop/view.php?id=1303451
- Git Dokumentation: https://git-scm.com/doc
- Git Sheet: https://training.github.com/downloads/de/github-git-cheat-sheet/
