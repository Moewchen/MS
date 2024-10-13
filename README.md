# MS
## Was ist Git und warum sollte es verwendet werden?
Softwareprojekte unterliegen ständigen Änderungen durch das Beheben von Fehlern, die Weiterentwicklung und das Hinzufügen von Funktionen sowie durch struktuelle Veränderungen der Software. Um alle Änderungen zu protokollieren und nachvollziehen zu können, ist die Nutzung eines Versionskontrollprograms erforderlich. Zusätzlich ist es wichtig, dass Teams gemeinsam an einem Projekt arbeiten können, ohne dass es zu Konflikten kommt oder Änderungen verloren gehen.

Git ist ein Open-Source-Revisionskontrollsystem und das bisher am weitesten verbreitete Tool für die Versionskontrolle von Softwareprojekten. In Git werden nicht nur die Änderungen eines Projekts gespeichert, sondern Snapshots der Dateien erstellt, wodurch Dateiänderungen besser nachvollzogen und ggf. wieder rückgängig gemacht werden können. Zusätzlich sind in Git viele Softwaretools von Drittanbietern integriert und die Funktionalitäten sind auf Performance, Sicherheit und Flexibilität ausgerichtet. 

Aus diesen Gründen ist Git zu einem unverzichtbaren Tool für Entwickler geworden und wird in vielen Unternehmen als Standard eingesetzt.
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
