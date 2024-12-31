Diese 4 Kompentenzen möchte ich im Gespräch abdecken:

## Objektorientiertes Design erstellen A1E
Klassenkandiaten, Attribute und Methoden habe ich logischerweise in der Anfangsphase des Projektes erstellt, habe mir dort Gedanken dazu gemacht.

## Objektorientiert modellieren B1E
im uml ordner befindet sich zum feature authentication ein Klassendiagramm und ein Sequenzdiagramm.

## Objektorientiert implementieren C1E
Delegation habe ich ziemlich viel benutzt, da ich das ganze feature-basierend aufgebaut habe. Ich habe probiert so viel wie mögiich die Logik in Klassen auszulagern und immer Interfaces zu benutzen.

## Objektorientiert mit Vererbung implmentieren
D1E:
Ich habe für das feature lessonManagement für die Methode CreateLesson im Code eine SubKlasse für das Modell erstellt (CreateMathLesson und CreateMusicLesson). Diese habe ich auch so benutzt das es Sinn macht. Parentklassen haben generell in meinem Projekt bis jetzt keinen Sinn gemacht mit den features, die angefordert wurden.

D2E:
Ich habe probiert Polymorphie so gut wie möglich einzubauen und Vererbung vorallem Interfaces zu nutzen, so dass Methoden individuell definiert werden können und auch einfach gefaked werden können für Tests.