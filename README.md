# Evolution - let programs write themselves
First big final task solution for OOP classes

Zad. nr 1: Ewolucja czyli niech programy piszą się same
Wer. 1.01 (10 pkt.)
Zadane: 26 IV 2021
Wstęp
W tym zadaniu zajmiemy się ewolucją. Wprawdzie ewolucja miała do dyspozycji całą powierzchnię
Ziemi i trwała kilka miliardów lat, a my do końca semestru nie mamy nawet skromnego miliona, ale
mimo to spróbujemy sobie poradzić. Przy pomocy Javy oczywiście!
Zasymulujmy ewolucję kodu robów (nie będziemy tu wnikać, czy chodzi o kod programów robotów,
czy o kod DNA robaków, czy jeszcze co innego).
Plansza (świat)
Symulacja odbywa się w (ile_tur) turach, Świat robów to prostokątna plansza składająca się z
rozmiar_planszy_x*rozmiar_planszy_y pól. Roby znajdują się na pojedynczych polach i mogą
przechodzić między nimi. Zakładamy, że plansza ma posklejane ze sobą brzegi, tak więc po ostatniej
kolumnie/wierszu następuje pierwsza/pierwszy i analogicznie przed pierwszą kolumną/pierwszym
wierszem. Wynika stąd, że każde pole ma tyle samo sąsiadów.
Na początku symulacji w losowych (nie koniecznie różnych) miejscach planszy umieszczanych jest
pocz_ile_robów robów, każdy wyposażony w początkowy program pocz_progr i pocz_energia
jednostek energii.
Pola
Na jednym polu może być dowolnie wiele (w tym zero) robów. Każde pole planszy jest jednego z dwu
rodzajów: puste lub żywieniowe. W tym drugim przypadku jest na nim na początku symulacji
pożywienie. Jeśli na takie pole wejdzie rob, to zjada to pożywienie (w całości), co daje mu energię do
życia (ile_daje_jedzenie). Pole zaczyna się regenerować, w tym czasie wchodzące roby nie mogą się
pożywić. Po ile_rośnie_jedzenie turach na polu znów pojawia się pożywienie. Zje je pierwszy rob,
który wejdzie na to pole (roby, które stoją na polu w momencie pojawienia się pożywienia nie zjadają
go, w celu zjedzenia pożywienia trzeba wejść na pole - promujemy ruch i zdrowy tryb życia :) ).
Również roby, które na początku symulacji zostaną umieszczone na polach z pożywieniem, nie mogą
go zjeść.
Roby
Każdy rob znajduje się na jednym polu planszy i jest skierowany w jednym z czterech kierunków
(góra, prawo, dól, lewo). Każdy rob ma swój program. W każdej turze wykonuje go instrukcja po
instrukcji (p. spis instrukcji) do końca (chyba że skończy mu się energia). Wykonanie każdej instrukcji
zużywa jedną jednostkę energii roba. Samo przeżycie jednej tury (nawet z pustym programem)
wymaga koszt_tury jednostek energii.
Jedynym sposobem uzupełnienia energii jest wejścia na pole z pożywieniem (tak jak napisano
wcześniej, jeśli na polu jest jedzenie, to zjedzenie go daje wtedy robowi ile_daje_jedzenie jednostek
energii). Jeśli w którymkolwiek momencie (np. w trakcie wykonywania programu) stan zapasu energii
roba spadnie poniżej zera, to rob przestaje działać/umiera i od następnej tury nie ma go już na
planszy
