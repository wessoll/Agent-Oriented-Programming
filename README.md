Agent-Oriented-Programming
==========================

AOP Project

Dit programma simuleert het wegverkeer over een aantal snelwegen. Verschillende soorten voertuigen rijden over de weg. De TomTom berekend hiervoor de snelste route om naar de bestemming te komen. Het is mogelijk dat er een wegversperring ontstaat. De RDW stuurt in dit geval een bericht naar de verschillende voertuigen om hun kaart te updaten en mogelijk een nieuwe route te plannen.

Het programma berekend de routes voor de auto's op basis van Dijkstra's algoritme. 

Met JADE worden er verschillende agents aangemaakt die onderdeel zijn van deze simulatie: personenauto's, een ambulance, een matrixboard en de roadmap.

De voortuigen staan op de lane die ze volgens hun route moeten volgen. Een lane heeft maar beperkte capaciteit en vol = vol. Een ambulance heeft voorrang op de lane. Daarom hebben we gekozen om een lane te implementeren als een priorityqueue.
