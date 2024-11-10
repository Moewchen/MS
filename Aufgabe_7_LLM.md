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