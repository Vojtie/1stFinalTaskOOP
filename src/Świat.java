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
 * TO DO:
 * - poszerzyc wypisywanie stanu
 * - podzielić na paczki
 * - przejrzeć styl
 * - zmienić dokładność wypisywania doubli - done!
 *
 */
public class Świat {

    protected final Parametry parametry;
    protected final Plansza plansza;
    protected final Roby roby;

    Świat(Parametry parametry, Plansza plansza) {
        this.parametry = parametry;
        this.plansza = plansza;
        this.roby = new Roby(parametry.dajPoczIleRobów(), parametry.dajRozmiarPlanszyX(),
                parametry.dajRozmiarPlanszyY(), parametry.dajPoczProg(), parametry.dajKosztTury(),
                parametry.dajLimitPowielania(), parametry.dajUłamekEnergiiRodzica(), parametry.dajPrPowielenia(),
                parametry.dajPoczEnergia());
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

    protected String dajBieżącyStanŚwiata() {
        int[] minMaxStatRob = roby.dajMinMaxStatystykiRobów();
        double[] średStatRob = roby.dajŚredStatystykiRobów();
        return ", rob: " + roby.dajLiczbęRobów()
                + ", żyw: " + plansza.dajLiczbęPólZŻyw() + ", prg: " + minMaxStatRob[0]
                + "/" + średStatRob[0] + "/" + minMaxStatRob[1] + ", energ: "
                + minMaxStatRob[2] + "/" + średStatRob[1] + "/" + minMaxStatRob[3]
                + ", wiek: " + minMaxStatRob[4] + "/" + średStatRob[2] + "/"
                + minMaxStatRob[5];
    }
}
