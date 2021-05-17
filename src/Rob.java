import java.util.Random;

public class Rob implements SpisInstrukcji {

    private final Parametry parametry;
    private final int numerRoba;
    private Kierunek kierunek; // gora/prawo/dol/lewo
    private final Program program; // kod/ciag instrukcji
    private int energia; // jednostek energii
    private int[] koordy;
    private int długośćŻycia;

    /** rob tworzony na początku */
    Rob (Parametry parametry, int numerRoba) {
        assert (parametry != null);
        this.parametry = parametry;
        this.kierunek = Kierunek.losujKierunek();
        this.program = parametry.dajPoczProg();
        this.energia = parametry.dajPoczEnergia();
        this.koordy = new int[] {new Random().nextInt(parametry.dajRozmiarPlanszyX()),
                                 new Random().nextInt(parametry.dajRozmiarPlanszyY())};
        this.długośćŻycia = 0;
        this.numerRoba = numerRoba;
    }

    /** rob powielony */
    Rob(Parametry parametry, Program program, int energia, Kierunek kierunek, int[] koordy, int numerRoba) {
        assert (parametry != null && koordy.length == 2);
        this.parametry = parametry;
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
                + długośćŻycia + ", dłg programu: " + dajDłgPrg();
    }
    
    public Program dajProgram() {
        return program;
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

    public boolean czyŻyje() {
        return energia >= 0;
    }

    public void wykonajInstrukcje(Świat świat) {
        char akcja;
        for (int i = 0; i < program.dajLiczbęInstrukcji() && czyŻyje(); i++, energia--) {
            akcja = program.dajInstrukcję(i);
            switch(akcja) {
                case 'l':
                    //wyłączyć komendy nie będące w spisInstr
                    lewo();
                    break;
                case 'p':
                    prawo();
                    break;
                case 'i':
                    idź(świat);
                    break;
                case 'w':
                    wąchaj(świat.dajPlanszę());
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
        energia -= parametry.dajKosztTury();
        długośćŻycia++;
    }

    @Override
    public void idź(Świat świat) {
        if (kierunek.czyX())
            świat.przemieść(this, new int[] {kierunek.wartość, 0});
        else
            świat.przemieść(this, new int[] {0, kierunek.wartość});
        //sprawdzic
        Pole pole = świat.dajPole(new int[] {dajX(), dajY()});
        if (pole instanceof PoleŻywieniowe)
            energia += ((PoleŻywieniowe) pole).dajPożywienie();
    }

    @Override
    public void prawo() {
        kierunek = kierunek.dajObrótWPrawo();
    }

    @Override
    public void lewo() {
        kierunek = kierunek.dajObrótWLewo();
    }

    @Override
    public void wąchaj(Pole[][] plansza) {
        if (plansza[Math.floorMod(dajX() + 1, parametry.dajRozmiarPlanszyX())][dajY()] instanceof PoleŻywieniowe
            && ((PoleŻywieniowe) plansza[Math.floorMod(dajX() + 1, parametry.dajRozmiarPlanszyX())][dajY()]).maPożywienie())
            kierunek = Kierunek.PRAWO;
        else if (plansza[Math.floorMod(dajX() - 1, parametry.dajRozmiarPlanszyX())][dajY()] instanceof PoleŻywieniowe
                && ((PoleŻywieniowe) plansza[Math.floorMod(dajX() - 1, parametry.dajRozmiarPlanszyX())][dajY()]).maPożywienie())
            kierunek = Kierunek.LEWO;
        else if (plansza[dajX()][Math.floorMod(dajY() + 1, parametry.dajRozmiarPlanszyY())] instanceof PoleŻywieniowe
                && ((PoleŻywieniowe) plansza[dajX()][Math.floorMod(dajY() + 1, parametry.dajRozmiarPlanszyY())]).maPożywienie())
            kierunek = Kierunek.GÓRA;
        else if (plansza[dajX()][Math.floorMod(dajY() - 1, parametry.dajRozmiarPlanszyY())] instanceof PoleŻywieniowe
                && ((PoleŻywieniowe) plansza[dajX()][Math.floorMod(dajY() - 1, parametry.dajRozmiarPlanszyY())]).maPożywienie())
            kierunek = Kierunek.DÓŁ;
    }

    @Override
    public void jedz(Świat świat) {
        Pole[][] plansza = świat.dajPlanszę();
        int[] wektor = {0, 0};
        for (wektor[0] = -1; wektor[0] < 2; wektor[0]++) {
            for (wektor[1] = -1; wektor[1] < 2; wektor[1]++) {
                if (wektor[0] == 0 && wektor[1] == 0) continue;
                if (plansza[Math.floorMod(dajX() + wektor[0], parametry.dajRozmiarPlanszyX())][Math.floorMod(dajY() + wektor[1], parametry.dajRozmiarPlanszyY())] instanceof PoleŻywieniowe
                        && ((PoleŻywieniowe) plansza[Math.floorMod(dajX() + wektor[0], parametry.dajRozmiarPlanszyX())][Math.floorMod(dajY() + wektor[1], parametry.dajRozmiarPlanszyY())]).maPożywienie())
                {
                    świat.przemieść(this, wektor);
                    break;
                }
            }
        }
    }
    
    /**
     * powielanie
     */
    public boolean czyPowiela() {
        return (energia >= parametry.dajLimitPowielania() && new Prawdopodobieństwo().losuj(parametry.dajPrPowielenia()));
    }

    public int oddajCzęśćEnergii() {
        int energiaDziecka = (int)(energia * parametry.dajUłamekEnergiiRodzica());
        energia -= energiaDziecka;
        return energiaDziecka;
    }

    public Rob powiel(int numerRoba) {
        assert (czyŻyje());
        return new Rob(parametry, program.mutuj(), oddajCzęśćEnergii(), kierunek.dajPrzeciwnyKierunek(), koordy, numerRoba);
    }

    /** zrobic osobna klase
     * mutacje
     *
    public Program mutuj() {
        Program zmutowany = program;
        if (program != null) {
            zmutowany = new Program(parametry.dajSpisInstr(), program.zróbKopię());
            if (!zmutowany.czyPusty() && new Prawdopodobieństwo().losuj(parametry.dajPrUsunięciaInstr()))
                zmutowany.usuńOstatniąInstrukcję();
            if (new Prawdopodobieństwo().losuj(parametry.dajPrDodaniaInstr()))
                zmutowany.dodajLosowąInstrukcję();
            if (!zmutowany.czyPusty() && new Prawdopodobieństwo().losuj(parametry.dajPrZmianyInstr()))
                zmutowany.zmieńLosowąInstrukcję();
        }
        return zmutowany;
    }
     */
}
