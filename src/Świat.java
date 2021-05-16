import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Parametry: (kazdy rob dostaje ten sam parametr)
 *
 * świat:
 * - ile_tur
 * - rozmiar_planszy_x, rozmiar_planszy_y
 * - pocz_ile_robów
 *
 * roby:
 * - pocz_progr // każdego roba - wykonuje go w całości w każdej turze
 * - pocz_energia
 * - koszt_tury
 * - pr_powielenia
 * - ułamek_energii_rodzica
 * - limit_powielania
 * / nowo powstaly rob jest skierowany w poprzednia strone co rodzic
 *
 * pola:
 * - ile_daje_jedzenie // każde pole żywieniowe
 * - ile_rośnie_jedzenie
 *
 * mutacje:
 * - pr_usunięcia_instr
 * - pr_dodania_instr
 * - spis_instr // mozliwych
 * - pr_zmiany_instr
 *
 *
 * - co_ile_wypisz
 *
 * pytania
 * czy ułamek_energii_rodzica to współczynnik czy jednostki energii
 * czy świat powinien poruszać robem czy rob sam sobą
 * czy wypisywac staty martwych robow
 *
 * założenia:
 * - ułamek_energii_rodzica i prawd \in [0,1]
 * - rob może umrzeć przy porodzie
 *
 *
 */
public class Świat {

    private final Parametry parametry;
    private final Pole[][] plansza;
    private final ArrayList<Rob> roby;

    Świat(Parametry parametry, Pole[][] plansza) {
        this.parametry = parametry;
        this.plansza = plansza;
        this.roby = generujRoby();
    }
/*
    public Pole[][] utwórzPlanszę() {
        Pole[][] plansza = new Pole[parametry.dajRozmiarPlanszyX()][parametry.dajRozmiarPlanszyY()];
        Random random = new Random();
        int los;
        for (int x = 0; x < parametry.dajRozmiarPlanszyX(); x++) {
            for (int y = 0; y < parametry.dajRozmiarPlanszyY(); y++) {
                los = random.nextInt(10);
                if (los == 0)
                    plansza[x][y] = new PoleŻywieniowe(parametry);
                else
                    plansza[x][y] = new PolePuste();
            }
        }
        return plansza;
    }
*/
    public ArrayList<Rob> dajRoby() {
        return roby;
    }

    public int dajLiczbęRobów() {
        int wynik = 0;
        if (dajRoby() != null)
            wynik = roby.size();
        return wynik;
    }

    public Rob dajRoba(int indeks) {
        return roby.get(indeks);
    }
    // dodaje roba na koniec listy
    public void dodajRoba(Rob nowy) {
        roby.add(nowy);
    }

    public void usuńMartweRoby() {
        roby.removeIf(rob -> !rob.czyŻyje());
    }

    public ArrayList<Rob> generujRoby() {
        ArrayList<Rob> roby = new ArrayList<>();
        for (int i = 0 ; i < parametry.dajPoczIleRobów(); i++)
            roby.add(new Rob(parametry, i + 1));
        return roby;
    }

    public int dajNumerOstRoba() {
        int wynik = 0;
        for (int i = 0; i < dajLiczbęRobów(); i++)
            wynik = Math.max(wynik, dajRoba(i).dajNumerRoba());
        return wynik;
    }

    public void przemieść(Rob rob, int[] wektor) {
        assert(wektor.length == 2);
        rob.ustawKoordy(new int[] {Math.floorMod(rob.dajX() + wektor[0], parametry.dajRozmiarPlanszyX()),
                Math.floorMod(rob.dajY() + wektor[1], parametry.dajRozmiarPlanszyY())});
    }

    public Pole[][] dajPlanszę() {
        return plansza;
    }

    public Pole dajPole(int[] koordy) {
        assert(koordy.length == 2);
        return dajPlanszę()[koordy[0]][koordy[1]];
    }

    public int dajLiczbęPólZŻyw() {
        int wynik = 0;
        for (final Pole[] tabPól : dajPlanszę())
            for (final Pole pole : tabPól)
                if (pole instanceof PoleŻywieniowe && ((PoleŻywieniowe) pole).maPożywienie())
                    wynik++;
        return wynik;
    }

    public int dajMinWiek() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.min(wynik, rob.dajDłgŻycia());
        return wynik;
    }

    public double dajŚredWiek() {
        double wynik = 0;
        if (dajLiczbęRobów() != 0) {
            for (Rob rob : dajRoby())
                wynik += rob.dajDłgŻycia();
            wynik /= dajLiczbęRobów();
        }
        return wynik;
    }

    public int dajMaxWiek() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.max(wynik, rob.dajDłgŻycia());
        return wynik;
    }

    public int dajMinDłgPrg() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.min(wynik, rob.dajDłgPrg());
        return wynik;
    }

    public double dajŚredDłgPrg() {
        double wynik = 0;
        if (dajLiczbęRobów() != 0) {
            for (Rob rob : dajRoby())
                wynik += rob.dajDłgPrg();
            wynik /= dajLiczbęRobów();
        }
        return wynik;
    }

    public int dajMaxDłgPrg() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.max(wynik, rob.dajDłgPrg());
        return wynik;
    }

    public int dajMinEnerg() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.min(wynik, rob.dajLiczbęJedEnerg());
        return wynik;
    }

    public double dajŚredEnerg() {
        double wynik = 0;
        if (dajLiczbęRobów() != 0) {
            for (Rob rob : dajRoby())
                wynik += rob.dajLiczbęJedEnerg();
            wynik /= dajLiczbęRobów();
        }
        return wynik;
    }

    public int dajMaxEnerg() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.max(wynik, rob.dajLiczbęJedEnerg());
        return wynik;
    }

    public void wypiszPlanszę() {
        for (int i = 0; i < parametry.dajRozmiarPlanszyY() + 2; i++)
            System.out.print("-");
        System.out.println();
        for (Pole[] tabPól : dajPlanszę()) {
            System.out.print("|");
            for (Pole pole : tabPól) {
                if (pole instanceof PoleŻywieniowe) {
                    if (((PoleŻywieniowe) pole).maPożywienie())
                        System.out.print("$");
                    else
                        System.out.print("#");
                }
                else
                    System.out.print(" ");
            }
            System.out.println("|");
        }
        for (int i = 0; i < parametry.dajRozmiarPlanszyY() + 2; i++)
            System.out.print("_");
        System.out.println();
    }

    public void wypiszStanSymulacji() {
        System.out.println("Stan robów:");
        for (int i = 0; i < dajLiczbęRobów(); i++) {
            Rob rob = dajRoba(i);
            System.out.println("rob nr " + rob.dajNumerRoba() + ":\t" + rob.toString());
        }
        System.out.println("Plansza:");
        wypiszPlanszę();
    }

    public void wypiszPoTurze(int numerTury) {
        System.out.println(numerTury + ", rob: " + dajLiczbęRobów()
                + ", żyw: " + dajLiczbęPólZŻyw() + ", prg: " + dajMinDłgPrg()
                + "/" + dajŚredDłgPrg() + "/" + dajMaxDłgPrg() + ", energ: "
                + dajMinEnerg() + "/" + dajŚredEnerg() + "/" + dajMaxEnerg()
                + ", wiek: " + dajMinWiek() + "/" + dajŚredWiek() + "/"
                + dajMaxWiek());
    }

    public void symulujTurę() {
        int liczbaRobów = dajLiczbęRobów();
        for (int i = 0; i < liczbaRobów; i++) {
            Rob rob = dajRoba(i);
            rob.wykonajInstrukcje(this);
            if (rob.czyŻyje() && rob.czyPowiela())
                dodajRoba(rob.powiel(dajNumerOstRoba() + 1));
            rob.następnaTura();
        }
        usuńMartweRoby();
        for (final Pole[] tabPól : dajPlanszę())
            for (final Pole pole : tabPól)
                if (pole instanceof PoleŻywieniowe)
                    ((PoleŻywieniowe) pole).następnaTura();
    }

    public void symuluj() {
        wypiszStanSymulacji();
        for (int tura = 1; tura <= parametry.dajIleTur(); wypiszPoTurze(tura), tura++) {
            symulujTurę();
            if (tura % parametry.dajCoIleWypisz() == 0)
                wypiszStanSymulacji();
        }
        wypiszStanSymulacji();
    }

    public static void main(String[] args) {
        /*
        int ileTur = 100, rozmiarPlanszyX = 30, rozmiarPlanszyY = 30, poczRobów = 10,
                poczEnergia = 10, ileDajeJedzenie = 15, ileRośnieJedzenie = 5,
                kosztTury = 5, limitPowielania = 20, coIleWypisz = 10;
        double prPowielenia = 0.1, ułamekEnergiiRodzica = 0.3, prUsunięciaInstr = 0.1,
                prDodaniaInstr = 0.8, prZmianyInstr = 0.5;
        char[] spisInstr = {'l', 'p', 'i', 'w', 'j'};
        Program poczProgr = new Program(spisInstr, new ArrayList<>(Arrays.asList('l', 'p', 'j', 'w', 'i', 'j', 'i')));
        Parametry parametry = new Parametry(ileTur, rozmiarPlanszyX, rozmiarPlanszyY, poczRobów, poczProgr,
                poczEnergia, ileDajeJedzenie, ileRośnieJedzenie, kosztTury, prPowielenia,
                ułamekEnergiiRodzica, limitPowielania, prUsunięciaInstr, prDodaniaInstr,
                spisInstr, prZmianyInstr, coIleWypisz);

        Świat świat = new Świat(parametry);
        świat.symuluj(); */
    }
}
