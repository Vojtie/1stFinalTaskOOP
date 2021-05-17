import java.util.ArrayList;

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

    protected final Parametry parametry;
    private final Plansza plansza;
    private final ArrayList<Rob> roby;

    Świat(Parametry parametry, Plansza plansza) {
        this.parametry = parametry;
        this.plansza = plansza;
        this.roby = generujRoby();
    }

    private ArrayList<Rob> dajRoby() {
        return roby;
    }

    protected int dajLiczbęRobów() {
        int wynik = 0;
        if (dajRoby() != null)
            wynik = roby.size();
        return wynik;
    }

    protected Rob dajRoba(int indeks) {
        return roby.get(indeks);
    }

    private void dodajRoba(Rob nowy) {
        roby.add(nowy);
    }

    private void usuńMartweRoby() {
        roby.removeIf(rob -> !rob.czyŻyje());
    }

    private ArrayList<Rob> generujRoby() {
        ArrayList<Rob> roby = new ArrayList<>();
        for (int i = 0 ; i < parametry.dajPoczIleRobów(); i++)
            roby.add(new Rob(parametry.dajRozmiarPlanszyX(), parametry.dajRozmiarPlanszyY(), parametry.dajPoczProg(),
                    parametry.dajKosztTury(), parametry.dajLimitPowielania(), parametry.dajUłamekEnergiiRodzica(),
                    parametry.dajPrPowielenia(), parametry.dajPoczEnergia(), i + 1));
        return roby;
    }

    private int dajNumerOstRoba() {
        int wynik = 0;
        for (int i = 0; i < dajLiczbęRobów(); i++)
            wynik = Math.max(wynik, dajRoba(i).dajNumerRoba());
        return wynik;
    }

    public void przemieśćRoba(Rob rob, int[] wektor) {
        assert(wektor.length == 2);
        rob.ustawKoordy(new int[] {Math.floorMod(rob.dajX() + wektor[0], parametry.dajRozmiarPlanszyX()),
                Math.floorMod(rob.dajY() + wektor[1], parametry.dajRozmiarPlanszyY())});
    }
    
    public void obróćRoba(Rob rob, Kierunek nowyKierunek) {
        assert (rob != null);
        rob.ustawKierunek(nowyKierunek);
    }
    
    public Plansza dajPlanszę() {
        return plansza;
    }

    private int dajMinWiek() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.min(wynik, rob.dajDłgŻycia());
        return wynik;
    }

    private double dajŚredWiek() {
        double wynik = 0;
        if (dajLiczbęRobów() != 0) {
            for (Rob rob : dajRoby())
                wynik += rob.dajDłgŻycia();
            wynik /= dajLiczbęRobów();
        }
        return wynik;
    }

    private int dajMaxWiek() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.max(wynik, rob.dajDłgŻycia());
        return wynik;
    }

    private int dajMinDłgPrg() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.min(wynik, rob.dajDłgPrg());
        return wynik;
    }

    private double dajŚredDłgPrg() {
        double wynik = 0;
        if (dajLiczbęRobów() != 0) {
            for (Rob rob : dajRoby())
                wynik += rob.dajDłgPrg();
            wynik /= dajLiczbęRobów();
        }
        return wynik;
    }

    private int dajMaxDłgPrg() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.max(wynik, rob.dajDłgPrg());
        return wynik;
    }

    private int dajMinEnerg() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.min(wynik, rob.dajLiczbęJedEnerg());
        return wynik;
    }

    private double dajŚredEnerg() {
        double wynik = 0;
        if (dajLiczbęRobów() != 0) {
            for (Rob rob : dajRoby())
                wynik += rob.dajLiczbęJedEnerg();
            wynik /= dajLiczbęRobów();
        }
        return wynik;
    }

    private int dajMaxEnerg() {
        int wynik = 0;
        for (Rob rob : dajRoby())
            wynik = Math.max(wynik, rob.dajLiczbęJedEnerg());
        return wynik;
    }

    protected String dajOpisStanuŚwiata() {
        return ", rob: " + dajLiczbęRobów()
                + ", żyw: " + plansza.dajLiczbęPólZŻyw() + ", prg: " + dajMinDłgPrg()
                + "/" + dajŚredDłgPrg() + "/" + dajMaxDłgPrg() + ", energ: "
                + dajMinEnerg() + "/" + dajŚredEnerg() + "/" + dajMaxEnerg()
                + ", wiek: " + dajMinWiek() + "/" + dajŚredWiek() + "/"
                + dajMaxWiek();
    }
    
    protected void następnaTura() {
        int liczbaRobów = dajLiczbęRobów();
        for (int i = 0; i < liczbaRobów; i++) {
            Rob rob = dajRoba(i);
            rob.wykonajInstrukcje(this);
            if (rob.czyŻyje() && rob.czyPowiela())
                dodajRoba(rob.powiel(dajNumerOstRoba() + 1));
            rob.następnaTura();
        }
        usuńMartweRoby();
        plansza.następnaTura();
    }
}
