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
 

Branches und ihre Nutzung:

Branches:
![image info](./images/branch.jpg)
Nutzen:

- isolierte Entwicklungsstraenge -> Aenderungen am Code ohne Hauptcode zu beeintraechtigen  
- paralleles Arbeiten moeglich  
- testen von neuem code + bugfix Bereinigung ohne Hauptcode zu veraendern -> Integration in Hauptcode nach erfolgreichem Test  

Branch erstellen:  
Der branch wird inhaltlich aus dem branch erstellt von dem er erzeugt wird. Nach der Erstellung wechselt man direkt hinein.  
Mit<p><code>git checkout -b neuer-branch-name</code></p>wird ein neuer branch erstellt. Zu diesem Zeitpunkt ist der nur lokal aktiv.  
Danach muss der branch in git hochgeladen werden:<p><code>git push -u origin neuer-branch-name</code></p> Mit<p><code>git checkout branch-name</code></p>wird in den angegebenen branch gewechselt.  
Mit<p><code>git fetch --all</code></p>werden alle branches aus GitHub geholt.  

Merge  

Nach der Funktionserweiterung oder Bugbehebung wird der Branch wieder in den Hauptstrang gemerged.  
Hierfuer muss man sich in dem Ziel-Branch befinden in dem die Aenderungen gemerged werden sollen. 
Bevor ein merge stattfindet sollte mit einem<p><code>git pull origin main</code></p>die neusten Aenderungen gezogen werden, um sicher zu gehen, dass der lokale Branch auf dem neusten Stand ist.  
Danach wir mit einem<p><code>git merge neuer-branch-name</code></p>der Quell-Branch in den Ziel-Branch germerged.  

Merge-Konflikte  

Es kann durchaus vorkommen, dass Merge-Konflikte entstehen, meistens dann, wenn zwei Branches gleichzeitig Aenderungen an derselben Datei vornimmt und Git diese nicht automatisch zusammen fuehren kann.  
Passiert dies, erkennt Git Konflikte und zeigt die betroffenen Dateien.  
Oeffnet man die betroffenen Dateien sind die folgenden Zeichen zu sehen: <p><code> <<<<<<<, ======= und >>>>>>> </code></p>. Alles zwischen <p><code> <<<<<< und ====== </code></p> stammt von dem aktuellen Ziel-Branch und alles zwischen <p><code> ======== und >>>>>>> </code></p> stammt vom Quell-Branch.  
Die Konflikte muessen manuell geloest werden. Die Aenderungen muessen haendisch zusammen gefuegt werden oder einer der Bloeke muss entfernt werden.  
Danach muss die Datei mit einem <p><code>git add dateiname</code></p> dem Ziel-Branch hinzugefuegt werden.  
Der Merge wird danach per <p><code>git commit</code></p> und bei einem Remote-Repository mit <p><code>git push origin Ziel-Branch</code></p> abgeschlossen.  

Weitere Tipps zum Umgang mit Merge-Konflikten:  
- Verwendung von Diff-Tools um Unterschiede und Konflikte uebersichtlich darzustellen
- Konflikte frueh erkennen und haeufig pullen um sie direkt zu loesen bevor es grosse Konflikte werden
- kleinteilige Commits um den neu erstellten Codekontext zu vertehen

#Git mit IntelliJ

## Nützliche Git-Tools und Plattformen

Git hat sich als eines der wichtigsten Versionskontrollsysteme etabliert und ist ein unverzichtbares Werkzeug für Entwickler und Teams, die an Softwareprojekten arbeiten. Die Wahl der richtigen Tools und Plattformen kann den Entwicklungsprozess erheblich erleichtern und die Zusammenarbeit verbessern. In diesem Text werden nützliche Git-Tools und Plattformen vorgestellt, die dir helfen können, deine Projekte effizient zu verwalten und den Überblick über Änderungen und Versionen zu behalten.

### Git-Plattformen

Git-Plattformen bieten Hosting-Dienste für Git-Repositories und ermöglichen die Zusammenarbeit zwischen Entwicklern durch Funktionen wie Pull Requests, Issues und Integrationen mit anderen Tools. Hier sind einige der bekanntesten und nützlichsten Git-Plattformen:

1. **GitHub**
   GitHub ist die bekannteste Plattform für die Verwaltung von Git-Repositories. Sie bietet Funktionen wie Pull Requests, Issues und Code Reviews, die die Zusammenarbeit in Teams erleichtern. Mit GitHub Actions können CI/CD-Workflows automatisiert werden.

2. **GitLab**
   GitLab ist eine umfassende DevOps-Plattform, die Git-Hosting sowie CI/CD-Funktionen integriert. Mit Merge Requests und einem eingebauten Issue-Tracker ist GitLab besonders für Teams geeignet, die eine All-in-One-Lösung suchen.

3. **Bitbucket**
   Bitbucket bietet Git-Hosting und ist besonders gut mit Atlassian-Tools wie Jira integriert. Es enthält auch Pipelines für CI/CD und unterstützt die Zusammenarbeit an Codeprojekten in einer benutzerfreundlichen Umgebung.

### Git-Tools

Git-Tools sind Softwareanwendungen, die die Nutzung von Git erleichtern, sei es durch grafische Benutzeroberflächen oder zusätzliche Funktionen. Hier sind einige nützliche Git-Tools, die dir helfen können, deine Repositories effektiv zu verwalten:

1. **SourceTree**
   SourceTree ist ein kostenloser Git-Client für Windows und macOS, der eine grafische Oberfläche für Git bietet. Es erleichtert das Management von Repositories, Branches und Commits, ohne dass die Kommandozeile benötigt wird.

2. **GitKraken**
   GitKraken ist ein leistungsstarker Git-Client mit einer modernen Benutzeroberfläche. Er bietet eine visuelle Darstellung von Branches und Commits und integriert Tools für Code Reviews und Teamkommunikation.

3. **Git Extensions**
   Git Extensions ist ein Open-Source-Git-Client für Windows, der eine umfangreiche Benutzeroberfläche und zusätzliche Funktionen bietet, um die Arbeit mit Git zu erleichtern. Es unterstützt auch die Integration mit Visual Studio.

4. **Git LFS (Large File Storage)**
   Git LFS ist eine Erweiterung für Git, die es ermöglicht, große Dateien effizienter zu verwalten. Anstatt große Dateien direkt im Repository zu speichern, werden sie in einem separaten Speicher abgelegt, was die Performance verbessert.

5. **Tower**
   Tower ist ein kostenpflichtiger Git-Client für macOS und Windows, der sich durch eine intuitive Benutzeroberfläche und umfassende Features auszeichnet. Er bietet Funktionen wie Undo-Commits und Stash-Management.

6. **Git Command Line**
   Die Git-Befehlszeile ist ein unverzichtbares Tool für Entwickler, die tiefere Kontrolle über ihre Repositories benötigen. Sie bietet die größte Flexibilität und ist für viele fortgeschrittene Operationen notwendig.

