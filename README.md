# UNASAT SEM2 MEMORY GAME

## Groepsleden
* Rajiv Ramsing (SE/1121/046)
* Girish Oemrawsingh (SE/1121/038)
* Arvan Jagroep (SE/1121/067)
* Aditya Dhanes (SE/1121/015)

## Building
The application was compiled and tested with Java 17.

## Opdracht
Bouw een "memory game" applicatie die te spelen is in de java terminal. De applicatie taal is Java en het database management systeem is MySQL.

- De gebruiker een profiel creeren of inloggen met een bestaand profiel d.m.v. een naam en code
- Eenmaal "ingelogd" kan de gebruiker een spel starten.
- De gebruiker kan ook een overzicht van de top 10 scores en bijbehorende namen ophalen uit de database.
- Het spel wordt gespeeld met 20 kaarten, waarbij er 10 paren aanwezig zijn.
- De kaarten zijn op het veld in 4 rijen van 5.
- De bedoeling is dat de speler steeds twee kaarten opent.
- Wanneer de twee kaarten een match zijn, blijven deze open en krijgt de speler 2 punten.
- De speler mag max 10 keren een niet-matchend paar openen. Daarna eindigt het spel.
- Indien de speler alle paren heeft gevonden krijgt de speler 3 bonus punten bij de score voor 10 minus het aantal gemaakte fouten. (3 gemaakte fouten is dus een bonus score van (10-3) * 3 = 7 * 3 = 21 punten )
- Het spel slaat de score op in de database


In de database zitten er tabellen die het volgende bijhouden:
- de namen, codes en geboortedata van de gebruikers
- de scores per spel per gebruiker


Andere eisen:
- Verbinding met de database
- De applicatie moet oneindig door kunnen gaan
- Gebruik comments om toe te lichten
- Geef duidelijke instructies
- Geef duidelijke instructies aan de gebruiker
- Vermeld je naam en andere relevante gegevens in de comments

Bonus punten:
- De applicatie houdt ook het aantal afgeronde spellen van de gebruiker bij in de database
- De gebruiker kan zijn of haar gegevens wijzigen
- De applicatie is niet foutgevoelig en het houdt rekening met mogelijk Exceptions.

Inleveren:
- Het werkende Java project. LET OP: Dit betekent de volledig project directory die importeerbaar is en niet alleen Java files.
- Database scripts (data en structuur). Hiermee moeten alle nodig tabellen gegenereerd kunnen worden.
- Overzicht groepsleden en studentnr

## Updated BP (SEM4):
Maak een kaarten spel naar keuze

Eisen
- Verplicht gebruik maken van https://deckofcardsapi.com/
- Minimaal 2 design patterns
- Minimaal 2 Datastructures
- Maximaal 4 studenten per groep
- De applicatie moet werken
- Automated testing
- Presentatie
- Verdediging

GUI mag, maar is niet verplicht
