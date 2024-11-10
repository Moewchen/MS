Blatt 4 Aufgabe
================
Aufgabe 4 - Refaktorisierung
---------------------------
Zur Verbesserung der Codequalität und der Testabdeckung für die bisher entwickelten Klassen werden die Unit-Tests erweitert, um Randfälle und Fehlerbedingungen abzudecken. Darüber hinaus wird die Implementierung überarbeitet, um die Lesbarkeit und Robustheit zu verbessern, die Übersichtlichkeit zu erhöhen und Redundanzen zu vermeiden. Das Refactoring zielt darauf ab, den Code übersichtlicher und besser wartbar zu machen, ohne die bestehende Funktionalität zu verändern.

Wir nehmen einige allgemeine Verbesserungen vor:
- Verbesserte Ausnahmebehandlung: Wenn eine ID nicht existiert, wird eine spezielle verbesserte Exceptio Handling ausgelöst.
- Parametervalidierung - die Eingabeparameter werden überprüft, um sicherzustellen, dass keine Nullwerte akzeptiert werden, wenn sie keinen Sinn ergeben.

Um die Tests für die Klassen rund um die Handhabung und das Refactoring zu erweitern, werden die Tests so gestaltet, dass sie verschiedene Szenarien und Randfälle abdecken. Es wird auf Nullwerte, leere Felder, ungültige Daten und übermäßige Werte getestet und sichergestellt, dass die Implementierung gegenüber solchen Eingaben robust ist. Anschließend werden die Domänenklassen refaktorisiert, um die Lesbarkeit und Wartbarkeit zu verbessern, ohne die Funktionalität zu verändern.

Aufgabe 5 - Modularität und Testbarkeit CI/CD
--------------------------------------------

Um die Modularität zu gewährleisten, wurde die Struktur auf Grundlage der identifizierten Bounded Contexts angelegt. Für jede Domäne gibt es eine Entity-Klasse, einen Service und ein Repository, mit dem Ziel, den fachlichen Code von den technischen Aspekten zu trennen. Die Entity-Klasse spiegelt das Modell wider und beinhaltet die zu speichernden Daten. Das Repository interagiert mit der (noch zu integrierenden) Datenbank und mithilfe des Services können die Daten manipuliert bzw. verändert werden.

Für die automatischen Tests wird das Testframework JUnit verwendet. Für jede erstellte Klasse wurde eine eigene Testklasse erstellt, welche die entsprechenden Testfunktionen für die übergeordnete Klasse beinhaltet. Die Entity-Klasse „Nutzer“ wird beispielsweise durch die Klasse „NutzerTest“ validiert. Wenn ein Test fehlschlägt, kann dieser direkt der passenden Klasse zugeordnet werden.

Mithilfe des Tools Maven können die geschriebenen Tests einfach in die CI-Pipeline von GitHub Actions integriert werden. Die entsprechenden Maven-Befehle für das Erstellen und Testen des Codes, welche auch lokal genutzt werden, können direkt in GitHub Actions integriert werden. Die POM-/XML-Datei enthält dafür alle Informationen über das Projekt und die Konfigurationsdetails. Der Befehlt „mvn package“ sorgt dafür, dass Abhängigkeiten heruntergeladen, Klassen erstellt und Tests ausgeführt werden. Die Jobs der CI-Pipeline werden beim Puschen oder einem Pull Request auf main ausgeführt und nur wenn der Build und alle Tests erfolgreich durchlaufen, werden die Änderungen integriert.

Aufgabe 6 - Refklektion zu TDD und DDD
--------------------------------------

Test-Driven Design (TDD) und Domain-Driven Design (DDD) haben unsere Herangehensweise an das Projekt in wesentlichen Bereichen beeinflusst, vor allem in Bezug auf Struktur und Qualität - aber auch der Zeitplanung.

Durch TDD wurden wir dazu gezwungen, den Entwicklungsprozess zu verlangsamen und uns stärker auf die Qualität und Funktionsweise jedes Codesegments zu konzentrieren. Der TDD-Ansatz führte dazu, dass wir uns intensiver mit den Anforderungen und der Funktionalität auseinandersetzen mussten, bevor wir zur Implementierung überging. Das half uns, Bugs frühzeitig zu identifizieren und unseren Code modular und testbar zu halten. Aus unserer Sicht liegt der wesentliche Vorteil von TDD in der Sicherheit, die wiederholbare Tests bieten, um probleme bei Anpassungen schnell zu erkennen. Eine Herausforderung ist jedoch der höhere Zeitaufwand am Anfang des Entwicklungsprozesses, der sich allerdings aus unserer Sicht bei dem Umfang des Projektes und der herausforderung im Team daran zu arbeiten gelohnt hat.

DDD hatte ebenfalls einen großen Einfluss, vor allem auf die Struktur und Verständlichkeit der Domäne. Die Konzentration auf das Domänenmodell und die Zusammenarbeit im Projektteam führte dazu, dass die Kernlogik des Systems detaillierter und klarer formuliert wurden. Durch DDD konnten wir das Design stärker an den Geschäftszielen ausrichten und das System in klar abgegrenzte, sinnvolle Module aufteilen. Dies erleichterte nicht nur die Kommunikation und Zusammenarbeit im Team, sondern förderte auch die Wartbarkeit des Codes.

Insgesamt haben DDD und TDD dazu beigetragen, eine klar strukturierte, gut testbare und auf die Domäne fokussierte Softwarelösung zu entwickeln. Die Herausforderungen bestehen jedoch im Zeitaufwand und der Disziplin, die für eine erfolgreiche Umsetzung notwendig sind.

Aufgabe 7 - Einsatz eines Large Language Model (LLM)
---------------------------------------------------
Blatt 4 Aufgabe 7 - Einsatz eines Large Language Model (LLM)
==========================================================
Ein Large Language Model (LLM) kann beim Debugging und Optimierung des eigenen Codes enorm helfen. Neben einer automatischen Vervollständigung beim Erstellen des Codes, was die Erstellung deutlich beschleunigt, kann sie auch beim Erstellen von Algorithmen helfen und sogar Tests komplett selbständig implementieren.
Je nach verwendeten IDE und LLM Plugin kann auch die Ausgabe des LLM unterschiedlich verwendet werden. In diesem Projekt wurde IntelliJ und Visual Studio Code als IDE verwendet und GitHub Copilot als LLM (Claude 3.5 Sonnet (Preview) und GPT 4o). Bei IntelliJ wird in einem separaten Fenster die LLM Konversation abgehalten. Bei Visual Studio Code wird, je nach Suche auch das Ergebnis komplett in einem separaten Fenster gezeigt oder, bei kleinen direkten Änderungen, ein zweites Fenster im Hauptbildschirm geöffnet mit direkten Änderungen im Code, die per Knopfdruck sofort implementiert werden.
Für das Debugging und der Algorithmensuche wurde das LLM in dieser Übung nicht angewendet. Hierfür ist der geschriebene Code noch nicht komplex genug. Für eine Codeoptimierung und Testentwicklung hat das LLM jedoch interessanten Input geliefert. Wesentliche Impulse gibt das LLM gerade in Bereichen in denen man wenig oder keine Kenntnisse besitzt. Beispielsweise bei fortgeschrittenen Java-Techniken in denen Streams, Generics und Lamdas verwendet werden sollen gab mir das LLM Ideen wie dies umgesetzt werden könnte. Analysiert ein LLM einen Code, bei dem fortgeschrittene Java-Techniken angewendet wurden, wird dies auch positiv genannt:

![LLM_Stream.png](images%2FLLM_Stream.png)

Beim Analysieren von Codeschnipseln kann LLM auch fehlerhafte Namenskonventionen erkennen und auch auf die Benutzung von neueren und moderneren API Nutzung hinweisen:

![LLM_Name.png](images%2FLLM_Name.png)
![LLM_Date.png](images%2FLLM_Date.png)
Neben generellen Hinweisen am Anfang der Antwort zeigt ein LLM auch innerhalb des Codes wie die Vorschläge verarbeitet werden können:

![LLM_Entity.png](images%2FLLM_Entity.png)

![LLM_Vital.png](images%2FLLM_Vital.png)

Neben einzelner Klassen kann ein LLM auch das ganze Projekt analysieren und dem Benutzer ein Feedback geben. Wird diese Art der Unterstützung gewählt fokussiert sich das LLM auch eher auf generelle Implementierungen und geht weniger auf spezifischere Umsetzungen innerhalb der Klassen ein. Beispielsweise wurde mir bei einer kompletten Analyse nicht mehr der detaillierte Hinweis des oberen Beispiels bzgl. des Date Handlings gegeben. Vielmehr weist das LLM hier auf generelle Exception Handling hin, auf vernünftiges Logging und noch fehlende Java-Annotations.

Positives nach kompletten Analyse:

![LLM_Positiv.png](images%2FLLM_Positiv.png)

Verbesserungsvorschläge:

![LLM_Negativ.png](images%2FLLM_Negativ.png)

Besonders diese Hinweise helfen uns, unser Programm besser zu implementieren, da hier die meisten Erfahrungen fehlen.

Zusammenfassend hilft uns das LLM fehlende Kenntnisse zu füllen und schneller zu arbeiten. Bei Komplexen Problemen wird ein LLM wahrscheinlich allein jedoch nicht ausreichend sein. Hier wird eigenes Wissen benötigt. 