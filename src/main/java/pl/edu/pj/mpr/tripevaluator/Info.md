Stwórz nowy projekt o nazwie TripEvaluator.
W projekcie zaimplementuj trzy modele bazodanowe - Trip, Review oraz User.
Trip ma posiadać id, tytul, destynacje, listę review + JAKIEŚ WŁASNE POLA
Review ma posiadać id, treść, autora + JAKIEŚ WŁASNE POLA
Autor ma posiadać id, imie  + JAKIEŚ WŁASNE POLA
Zaimplementuj pełną logikę od controllera, aż po repository wraz z obsługą bazy danych H2
Dla modelu TRIP do zaimplementowania są metody:
* save - zapisuje nową wycieczkę, która ma listę review. Każde review ma autora
* addReview - dodaje review (które ma autora) do istniejącej wycieczki
* findAll - zwraca wszystkie wycieczki
* findById - parametr ID ma być przyjęty z adresu i przekazany dalej
* delete - parametr ID ma być przyjęty z adresu i przekazany dalej