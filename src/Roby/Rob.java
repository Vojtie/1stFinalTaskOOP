package Roby;

import Pola.*;
import Prawdopodobieństwo.Prawdopodobieństwo;
import Świat.Świat;
import Wczytywanie.Plansza;

import java.util.Random;

public class Rob implements SpisInstrukcji {

    private final int rozmiarPlanszyX;
    private final int rozmiarPlanszyY;
    private final Program poczProgram;
    private final int kosztTury;
    private final int limitPowielania;
    private final double ułamekEnergiiRodzica;
    private final double prPowielenia;
    private final int poczEnergia;
    private final int numerRoba;
    private Kierunek kierunek;
    private final Program program;
    private int energia;
    private int[] koordy;
    private int długośćŻycia;

    /** rob tworzony na początku */
    Rob (int rozmiarPlanszyX, int rozmiarPlanszyY, Program poczProgram, int kosztTury,
         int limitPowielania, double ułamekEnergiiRodzica, double prPowielenia, int poczEnergia,
         int numerRoba) {
        
        this.rozmiarPlanszyX = rozmiarPlanszyX;
        this.rozmiarPlanszyY = rozmiarPlanszyY;
        this.poczProgram = poczProgram;
        this.kosztTury = kosztTury;
        this.limitPowielania = limitPowielania;
        this.ułamekEnergiiRodzica = ułamekEnergiiRodzica;
        this.prPowielenia = prPowielenia;
        this.poczEnergia = poczEnergia;
        this.kierunek = Kierunek.losujKierunek();
        this.program = poczProgram;
        this.energia = poczEnergia;
        this.koordy = new int[] {new Random().nextInt(rozmiarPlanszyX),
                                 new Random().nextInt(rozmiarPlanszyY)};
        this.długośćŻycia = 0;
        this.numerRoba = numerRoba;
    }

    /** rob powielony */
    Rob (int rozmiarPlanszyX, int rozmiarPlanszyY, Program poczProgram, int kosztTury,
         int limitPowielania, double ułamekEnergiiRodzica, double prPowielenia, int poczEnergia,
         Program program, int energia, Kierunek kierunek, int[] koordy, int numerRoba) {
        
        assert (koordy.length == 2);
        this.rozmiarPlanszyX = rozmiarPlanszyX;
        this.rozmiarPlanszyY = rozmiarPlanszyY;
        this.poczProgram = poczProgram;
        this.kosztTury = kosztTury;
        this.limitPowielania = limitPowielania;
        this.ułamekEnergiiRodzica = ułamekEnergiiRodzica;
        this.prPowielenia = prPowielenia;
        this.poczEnergia = poczEnergia;
        this.program = program;
        this.energia = energia;
        this.kierunek = kierunek;
        this.koordy = koordy;
        this.długośćŻycia = 0;
        this.numerRoba = numerRoba;
    }
    
    public int dajNumerRoba() {
        return numerRoba;
    }

    public String toString() {
        return "kierunek: " + kierunek.toString() + ", energia: " + energia
                + ", pozycja: (" + koordy[0] + ", " + koordy[1] + "), wiek: "
                + długośćŻycia + ", dłg programu: " + dajDłgPrg() + ", program: " + program.toString();
    }
    
    public int dajDłgPrg() {
        return program.dajDłgPrg();
    }

    public int dajLiczbęJedEnerg() {
        return energia;
    }

    public int dajX() {
        return koordy[0];
    }

    public int dajY() {
        return koordy[1];
    }

    public int dajDłgŻycia() {
        return długośćŻycia;
    }

    public void ustawKoordy(int[] koordy) {
        assert(koordy.length == 2);
        this.koordy = koordy;
    }
    
    public void ustawKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }
    
    public boolean czyŻyje() {
        return energia >= 0;
    }

    public void wykonajInstrukcje(Świat świat) {
        char akcja;
        for (int i = 0; i < program.dajLiczbęInstrukcji() && czyŻyje(); i++, energia--) {
            akcja = program.dajInstrukcję(i);
            switch(akcja) {
                case 'l':
                    lewo(świat);
                    break;
                case 'p':
                    prawo(świat);
                    break;
                case 'i':
                    idź(świat);
                    break;
                case 'w':
                    wąchaj(świat);
                    break;
                case 'j':
                    jedz(świat);
                    break;
                default:
                    break;
            }
        }
    }

    public void następnaTura() {
        energia -= kosztTury;
        długośćŻycia++;
    }

    @Override
    public void idź(Świat świat) {
        if (kierunek.czyX())
            świat.przemieśćRoba(this, new int[] {kierunek.wartość, 0});
        else
            świat.przemieśćRoba(this, new int[] {0, kierunek.wartość});
        Pole pole = świat.dajPlanszę().dajPole(new int[] {dajX(), dajY()});
        if (pole instanceof PoleŻywieniowe)
            energia += ((PoleŻywieniowe) pole).dajPożywienie();
    }

    @Override
    public void prawo(Świat świat) {
         świat.obróćRoba(this, kierunek.dajObrótWPrawo());
    }

    @Override
    public void lewo(Świat świat) {
        świat.obróćRoba(this, kierunek.dajObrótWLewo());
    }
    
    @Override
    public void wąchaj(Świat świat) {
        Plansza plansza = świat.dajPlanszę();
        Kierunek nowyKierunek = kierunek;
        if (plansza.czyPoleZPożywieniem(new int[] {Math.floorMod(dajX() + 1, rozmiarPlanszyX), dajY()}))
            nowyKierunek = Kierunek.PRAWO;
        else if (plansza.czyPoleZPożywieniem(new int[] {Math.floorMod(dajX() - 1, rozmiarPlanszyX), dajY()}))
            nowyKierunek = Kierunek.LEWO;
        else if (plansza.czyPoleZPożywieniem(new int[] {dajX(), Math.floorMod(dajY() + 1, rozmiarPlanszyY)}))
            nowyKierunek = Kierunek.GÓRA;
        else if (plansza.czyPoleZPożywieniem(new int[] {dajX(), Math.floorMod(dajY() - 1, rozmiarPlanszyY)}))
            nowyKierunek = Kierunek.DÓŁ;
        świat.obróćRoba(this, nowyKierunek);
    }

    @Override
    public void jedz(Świat świat) {
        Plansza plansza = świat.dajPlanszę();
        int[] wektor = {0, 0};
        for (wektor[0] = -1; wektor[0] < 2; wektor[0]++) {
            for (wektor[1] = -1; wektor[1] < 2; wektor[1]++) {
                if (wektor[0] == 0 && wektor[1] == 0) continue;
                if (plansza.czyPoleZPożywieniem(new int[] {Math.floorMod(dajX() + wektor[0], rozmiarPlanszyX),
                        Math.floorMod(dajY() + wektor[1], rozmiarPlanszyY)}))
                {    
                    świat.przemieśćRoba(this, wektor);
                    break;
                }
            }
        }
    }
    
    public boolean czyPowiela() {
        return energia >= limitPowielania && new Prawdopodobieństwo().losuj(prPowielenia);
    }

    public int oddajCzęśćEnergii() {
        int energiaDziecka = (int)(energia * ułamekEnergiiRodzica);
        energia -= energiaDziecka;
        return energiaDziecka;
    }

    public Rob powiel(int numerRoba) {
        assert (czyŻyje());
        return new Rob(rozmiarPlanszyX, rozmiarPlanszyY, poczProgram, kosztTury, limitPowielania,
                ułamekEnergiiRodzica, prPowielenia, poczEnergia , program.mutuj(), oddajCzęśćEnergii(),
                kierunek.dajPrzeciwnyKierunek(), koordy, numerRoba);
    }
}
