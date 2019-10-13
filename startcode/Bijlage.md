ICSS compiler implementatie van Erik Knaake (598368). 13 Oktober 2019.

# Bijlage 1

## Eisen waaraan de opdracht voldoet:
Alle eisen zoals gesteld in ASSIGNMENT.md zijn geimplementeerd.

## Taaluitbreidingen:

1. Meerdere selectoren voor 1 stylerule worden ondersteund: ```div, span { ... }```.
2. Er zijn composiet selectoren toegevoegd, zoals ```p:active``` of ```div > p```
Hierbij is de optie om de spatie te gebruiken (```p a```) buiten beschouwing gebleven.
Hiervoor is ook een transform toegevoegd die kijkt of een id een direct kind moet zijn van iets anders
(```html > #id```), indien dit het geval is, is dit namelijk altijd te optimaliseren naar alleen het id.
3. Er is een optionele else tak toegevoegd aan if statements. ```if[Condition] { ... } else { ... }```.
Hiervoor zijn de tests van niveau 3 aangepast, zodat de toegevoegde class ```IfStatement``` daarin worden gebruikt.
Deze class houdt namelijk bij of er een else tak bij een if clause hoort.

In overleg met de docent zijn deze taaluitbreidingen 10 punten waard.