# MS
Moderne Softwareentwicklung

Branches:
![image info](./images/branch.jpg)
Nutzen:

- isolierte Entwicklungsstraenge -> Aenderungen am Code ohne Hauptcode zu beeintraechtigen  
- paralleles Arbeiten moeglich  
- testen von neuem code + bugfix Bereinigung ohne Hauptcode zu veraendern -> Integration in Hauptcode nach erfolgreichem Test  

Branch erstellen:  
Der branch wird inhaltlich aus dem branch erstellt von dem er erzeugt wird. Nach der Erstellung wechselt man direkt hinein.
<p><code>git checkout -b neuer-branch-name</code></p>  
Danach muss der branch in git hochgeladen werden  
<p><code>git push -u origin neuer-branch-name</code></p>  
Mit 
<p><code>git checkout branch-name</code></p>  
wechselt man in einen branch.  

Mit  
<p><code>git fetch --all</code></p>  
werden alle branches aus GitHub geholt. 