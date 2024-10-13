# MS
Moderne Softwareentwicklung  

Branches und ihre Nutzung:

Branches:
![image info](./images/branch.jpg)
Nutzen:

- isolierte Entwicklungsstraenge -> Aenderungen am Code ohne Hauptcode zu beeintraechtigen  
- paralleles Arbeiten moeglich  
- testen von neuem code + bugfix Bereinigung ohne Hauptcode zu veraendern -> Integration in Hauptcode nach erfolgreichem Test  

Branch erstellen:  
Der branch wird inhaltlich aus dem branch erstellt von dem er erzeugt wird. Nach der Erstellung wechselt man direkt hinein.  
Mit <p><code>git checkout -b neuer-branch-name</code></p> wir ein neuer branch erstellt. Zu diesem Zeitpunkt ist der nur lokal aktiv.  
Danach muss der branch in git hochgeladen werden: <p><code>git push -u origin neuer-branch-name</code></p>  
Mit <p><code>git checkout branch-name</code></p> wir in den angegebenen branch gewechselt.  
Mit <p><code>git fetch --all</code></p> werden alle branches aus GitHub geholt.  

Merge  

Nach der Funktionserweiterung oder Bugbehebung wird der Branch wieder in den Hauptstrang gemerged.  
Hierfuer muss man sich in dem Ziel-Branch befinden in dem die Aenderungen gemerged werden sollen. 
Bevor ein merge stattfindet sollten sich mit einem <p><code>git pull origin main</code></p> die neusten Aenderungen gezogen werden, um sicher zu gehen, dass der lokale Branch auf dem neusten Stand ist.  
Danach wir mit einem <p><code>git merge neuer-branch-name</code></p> der Quell-Branch in den Ziel-Branch germerged.  

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