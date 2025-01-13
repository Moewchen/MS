# Übung 6: Aspect Oriented Programming (AOP)

## AOP-Analyse
In der Applikation MediTrack gibt es verschiedene Cross-Cutting-Concerns bzw. Querschnittsbelange, welche in mehreren Aspekten des Systems bzw. einer Domäne auftreten bzw. zukünftig auftreten können. Beispielsweise enthalten die Service-Klassen verschiedene Aspekte, wo Ausnahmen (Exceptions) geworfen werden, wenn die Daten nicht valide sind. Um diese Exceptions bzw. die Validierung von Daten von der Hauptlogik zu trennen, kann ein ValidationAspect angelegt werden. Darüber hinaus können und sollten Methodenaufrufe und mögliche Fehler zur Fehlerbehebung protokolliert werden. Die Protokollierung der Methoden-Performance kann dazu beitragen, Engpässe oder ineffiziente Abläufe frühzeitig zu erkennen. Mit der Einführung eines Transaktionsmanagements könnte sichergestellt werden, dass Änderungen vollständig gespeichert und ggf. wieder zurückgerollt werden können. Auch zukünftige Sicherheitskontrollen, wie Zugriffssteuerungen und Berechtigungsprüfungen, könnten als Aspekt umgesetzt werden.

## AOP-Umsetzung
Für die Domäne Vitaldatenmanagement wurden drei bzw. vier AOP-Anwendungsfälle umgesetzt:
1. Performance-Monitoring-Aspekt für das Vitaldatenmanagement-Repository, um Engpässe oder ineffiziente Ausführungen zu identifizieren.
2. Logging-Aspekt für für das Vitaldatenmanagement-Repository, um Methodenaufrufe und Fehler zu protokollieren. Vor der Ausführung der Methode wird der Name der Methode im Log protokolliert. Wenn eine Methode eine Ausnahme wirft, wird der Fehler im Log festgehalten, inklusive des Methodennamens und der Fehlermeldung. Der Logging-Aspekt wurde zuerst für die Domäne (Model und Events) umgesetzt, was jedoch nicht funktioniert hat, weil der Event-Listener eine Spring-Bean ist und die Logik zur Verarbeitung des Events enthält.
3. Error-Handling- und Validation-Aspekt für den Vitaldatenmanagement-Service, um Ausnahmen (Exceptions) abzufangen und zu loggen sowie Daten vor der Methodenausführung zu validieren. Bei der Validierung werden die Grenzwerte der Vitalmanagement-Daten, wie die Herzfrequenz oder Temperatur, geprüft. Wenn bestimmte Daten invalide sind, wird eine InvalidVitaldatenException geworfen.

## LLM-Einsatz
