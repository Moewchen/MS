Blatt 7 Aufgabe
================
Vorteile der funktionalen Implementierung
-----------------------------------------
Die Einführung funktionaler Programmierung führte zu einer deutlichen Verbesserung der Codequalität. 
Besonders in der Behandlung von Datenströmen und bei der Fehlerbehandlung zeigte sich der Mehrwert.
Eine klarere Trennung von Verantwortlichkeiten und reduzierte die Fehleranfälligkeit durch Null-Pointer-Exceptions erheblich.

Beispiel 1 - upsert-Methode in VitaldatenService:
---------------------------------------------------
<table><tr><td><img src="/images/vorVitaldatenService.PNG" /></td><td><img src="/images/nachVitaldatenService.PNG" /></td></tr></table>
Erwartete Verbesserungen:

- Verbesserte Lesbarkeit und Wartbarkeit
- Reduzierte Fehleranfälligkeit (Bessere Null-Safety durch Optional)
- Klare Verbesserung von Validation, Business-Logik und Event Handling
- Bessere Erweiterbarkeit für neue Validierungen

Beispiel 2 - VitaldatenController
---------------------------------------------------



Beispiel 3 -
---------------------------------------------------

Technologieeinsatz und LLM-Unterstützung
----------------------------------------
Bei der Implementierung funktionaler Konzepte spielten LLMs eine zentrale Rolle. Insbesondere GitHub Copilot und Claude 3.5 Sonnet unterstützten uns bei der Umstellung von imperativem zu funktionalem Code. Die LLMs halfen nicht nur bei der Code-Generierung, sondern auch beim Verständnis funktionaler Konzepte und Best Practices.
Die Java Stream API und Optional-Klasse wurden intensiv genutzt, wobei die LLMs besonders bei der Optimierung von Collection-Operationen und der Implementierung von Method-Chaining wertvolle Unterstützung leisteten.

Auswirkungen auf Codequalität und Lesbarkeit
--------------------------------------------
Die funktionale Programmierung führte zu:

- Kompakterem und aussagekräftigerem Code
- Besserer Wartbarkeit durch klare Datenflüsse
- Reduzierter Fehleranfälligkeit bei der Null-Behandlung
- Verbesserter Testbarkeit durch reine Funktionen

Herausforderungen
-----------------

Die Einführung funktionaler Konzepte stellte uns vor einige Herausforderungen:
Die größten Herausforderungen lagen im Umdenken von imperativen zu funktionalen Konzepten.
Die Umstellung bestehender Code-Basis war aufwendig und erforderte ein tiefes Verständnis funktionaler Prinzipien.
Wir mussten nach der Implementierung teils die Tests und andere Code-Abschnitte erneut anpassen.
