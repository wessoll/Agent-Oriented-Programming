Agent-Oriented-Programming
==========================

AOP Project

Dit programma simuleert het wegverkeer over een aantal snelwegen. 

Er is een World waarin een aantal Vertices aan elkaar gekoppeld zijn d.m.v. Edges. Er zijn Voertuig-Agents die een navigatiesysteem bevatten (een TomTom). De TomTom vraagt aan de InfoAgent een RoadMap op. Deze RoadMap is een simpele kopie van de World. Het verschil zit hem erin dat de InfoAgent deze RoadMap ook update (bijv. bij een gesloten weg). Als we een RoadMap opvragen dan weten we dus zeker dat het de meest up-to-date versie is. Op deze manier kunnen de Voertuig-Agents ook hun eigen "weight" meegeven aan Edges. De TomTom (die gebruik maakt van Dijkstra's algoritme) kan dan zo routes bepalen die verschillend zijn voor de voertuigen.

Wanneer er een ongeval op de weg gebeurt en de weg (=Edge) moet worden gesloten, dan stuurt de MatrixBoardAgent een bericht naar de InfoAgent die de RoadMap update. Dit bericht zou ook moeten worden gestuurd naar (nabije) Voertuig-Agents zodat zij ook hun RoadMap kunnen updaten, maar is nog niet geimplementeerd. Er is voor gekozen om de Voertuig-Agents een eigen RoadMap te geven om het decentrale principe van Agents te ondersteunen (er is niet 1 God klasse die de RoadMap bevat).

Iedere Edge bevat tevens een of meerdere Lanes die zijn geimplementeerd m.b.v. Queue's. Zo sluiten voertuigen netjes achter elkaar aan. De Voertuig-Agents mogen wel een eigen kopie hebben van de RoadMap, maar ze gebruiken de World om op te rijden. De object referenties van World worden onderling gedeeld om dit mogelijk te maken. Wanneer de Lanes vol dreigen te raken, dan kan een auto ook niet verder en blijft dus staan wachten. Dit is bijv. het geval wanneer een Edge gesloten is, alle auto's die nog niet hun route hebben aangepast en toch op de Lane komen veroorzaken file.

Het was de bedoeling van deze Lanes dat een Noodvoertuig-Agent een bericht zou sturen naar nabije Voertuig-Agents, zodat deze laatste konden schakelen tussen de Lanes om de Noodvoertuig-Agent door te laten. Helaas is dit nog maar deels gerealiseerd.

