# Hausaufgabe 11

## Aufgabe 1a

> Gegeben sei ein binärer Suchbaum mit ganzzahligen Schlüsseln. Reichern Sie die Datenstruktur so an, dass Sie den 
> Mittelwert der Schlüssel in einem beliebigen Unterbaum in konstanter Zeit berechnen können, falls die Wurzel des 
> Unterbaums gegeben ist.
> 
> Welche zusätzlichen Informationen müssen an den Knoten des Suchbaums gespeichert werden, und was muss beim Einfügen 
> und Löschen von Knoten beachtet werden, damit die Information immer korrekt ist?
> 
> Beschreiben Sie die Lösch- und Einfügeoperation schriftlich als Text (zusätzlich zum Code in Aufgabenteil b).

Um den Mittelwert der Schlüssel in einem beliebigen Unterbaum in konstanter Zeit berechnen zu können, können wir
zusätzliche Informationen in den Knoten des binären Suchbaums speichern. Jeder Knoten sollte die folgenden Informationen
enthalten:

1. Schlüssel: Der Wert des Schlüssels im Knoten.
2. Linker Unterbaum: Ein Zeiger auf den Wurzelknoten des linken Unterbaums.
3. Rechter Unterbaum: Ein Zeiger auf den Wurzelknoten des rechten Unterbaums.
4. Anzahl der Knoten: Die Anzahl der Knoten im Unterbaum, einschließlich des aktuellen Knotens.
5. Summe der Schlüssel: Die Summe der Schlüsselwerte aller Knoten im Unterbaum, einschließlich des aktuellen Knotens.

Beim Einfügen eines neuen Knotens in den binären Suchbaum müssen wir sicherstellen, dass die zusätzlichen Informationen
korrekt aktualisiert werden. Hier ist der Algorithmus für die Einfügeoperation:

1. Wenn der Baum leer ist, wird der neue Knoten als Wurzelknoten eingefügt. Die Anzahl der Knoten wird auf 1 gesetzt,
   und die Summe der Schlüssel wird auf den Wert des neuen Knotens gesetzt.
2. Andernfalls vergleichen wir den Schlüssel des einzufügenden Knotens mit dem Schlüssel des aktuellen Knotens.
    - Wenn der Schlüssel kleiner ist, gehen wir in den linken Unterbaum und wiederholen den Einfügevorgang.
    - Wenn der Schlüssel größer ist, gehen wir in den rechten Unterbaum und wiederholen den Einfügevorgang.
    - Wenn der Schlüssel bereits im Baum vorhanden ist, können wir entscheiden, ob wir den Knoten ignorieren oder die
      Anzahl der Knoten und die Summe der Schlüssel aktualisieren möchten, um Duplikate zu berücksichtigen. In diesem
      Fall müssten wir die zusätzlichen Informationen entsprechend aktualisieren.
3. Nachdem der neue Knoten erfolgreich eingefügt wurde, müssen wir die zusätzlichen Informationen im gesamten Pfad von
   der Wurzel zum eingefügten Knoten aktualisieren. Dazu gehen wir den Pfad von der Wurzel zum eingefügten Knoten zurück
   und aktualisieren die Anzahl der Knoten und die Summe der Schlüssel in jedem besuchten Knoten. Dies kann in
   konstanter Zeit erfolgen, da die zusätzlichen Informationen in jedem Knoten gespeichert sind.

Beim Löschen eines Knotens aus dem binären Suchbaum müssen wir ebenfalls sicherstellen, dass die zusätzlichen
Informationen korrekt aktualisiert werden.

1. Wenn der zu löschende Knoten ein Blattknoten (ohne Kinder) ist, kann er einfach entfernt werden. In diesem Fall
   müssen wir die zusätzlichen Informationen im gesamten Pfad von der Wurzel zum gelöschten Knoten aktualisieren. Dazu
   gehen wir den Pfad von der Wurzel zum gelöschten Knoten zurück und aktualisieren die Anzahl der Knoten und die Summe
   der Schlüssel in jedem besuchten Knoten. Dies kann in konstanter Zeit erfolgen, da die zusätzlichen Informationen in
   jedem Knoten gespeichert sind.
2. Wenn der zu löschende Knoten einen oder zwei Kinder hat, müssen wir ihn durch den geeigneten Nachfolger oder Vorgänger ersetzen und dann den Nachfolger oder Vorgänger löschen. Der
Nachfolger oder Vorgänger kann in konstanter Zeit gefunden werden, da die zusätzlichen Informationen im Baum vorhanden
sind. Nachdem der Nachfolger oder Vorgänger gelöscht wurde, müssen wir die zusätzlichen Informationen im gesamten Pfad
von der Wurzel zum gelöschten Knoten aktualisieren, indem wir den Pfad zurückgehen und die Anzahl der Knoten und die
Summe der Schlüssel in jedem besuchten Knoten aktualisieren.

Durch das Speichern der Anzahl der Knoten und der Summe der Schlüssel in jedem Knoten können wir den Mittelwert eines
beliebigen Unterbaums in konstanter Zeit berechnen, indem wir die Summe der Schlüssel durch die Anzahl der Knoten
teilen.