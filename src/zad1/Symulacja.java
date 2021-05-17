package zad1;

import zad1.Świat.Świat;
import zad1.Roby.*;
import zad1.Wczytywanie.Parametry;
import zad1.Wczytywanie.Plansza;
import zad1.Wyjątki.NiepoprawnyPlikException;
import zad1.Wyjątki.PowtórzenieParametruException;
import zad1.Wyjątki.ZaMałoArgumentówException;
import zad1.Wyjątki.ZaMałoParametrówException;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Zad. nr. 1: Ewolucja niech programy piszą się same
 *
 * data: 17.05.2021r 
 * autor: Wojciech Kuzebski <wk429552@students.mimuw.edu.pl>
 *
 * Program przeprowadza symulację ewolucji kodu robów w ile_tur turach.
 * Symulacja ma miejsce na prostokątnej planszy o posklejanych brzegach.
 *
 * założenia:
 * - ułamek_energii_rodzica i prawdopodobieństwa \in [0,1]
 * - rob może umrzeć przy porodzie
 */

public class Symulacja extends Świat {
    
    public Symulacja(Parametry parametry, Plansza plansza) {
        super(parametry, plansza);
    }

    protected void wypiszPodstawoweDaneSymulacji(int numerTury) {
        int[] minMaxStatRob = roby.dajMinMaxStatystykiRobów();
        double[] średStatRob = roby.dajŚredStatystykiRobów();
        System.out.println(numerTury + ", rob: " + roby.dajLiczbęRobów()
                + ", żyw: " + plansza.dajLiczbęPólZŻyw() + ", prg: " + minMaxStatRob[0]
                + "/" + średStatRob[0] + "/" + minMaxStatRob[1] + ", energ: "
                + minMaxStatRob[2] + "/" + średStatRob[1] + "/" + minMaxStatRob[3]
                + ", wiek: " + minMaxStatRob[4] + "/" + średStatRob[2] + "/"
                + minMaxStatRob[5]);
    }

    private void wypiszStanSymulacji() {
        System.out.println("Stan robów:");
        for (int i = 0; i < roby.dajLiczbęRobów(); i++) {
            Rob rob = roby.dajRoba(i);
            System.out.println("rob nr " + rob.dajNumerRoba() + ":\t" + rob.toString());
        }
        System.out.println("Plansza:");
        plansza.wypiszPlanszę();
    }

    private void następnaTura() {
        int liczbaRobów = roby.dajLiczbęRobów();
        for (int i = 0; i < liczbaRobów; i++) {
            Rob rob = roby.dajRoba(i);
            rob.wykonajInstrukcje(this);
            if (rob.czyŻyje() && rob.czyPowiela())
                roby.dodajRoba(rob.powiel(roby.dajNumerOstRoba() + 1));
            rob.następnaTura();
        }
        roby.usuńMartweRoby();
        plansza.następnaTura();
    }
    
    public void symuluj() {
        wypiszStanSymulacji();
        for (int tura = 1; tura <= parametry.dajIleTur(); wypiszPodstawoweDaneSymulacji(tura), tura++) {
            następnaTura();
            if (tura % parametry.dajCoIleWypisz() == 0)
                wypiszStanSymulacji();
        }
        wypiszStanSymulacji();
    }

    public static void main(String[] args) throws FileNotFoundException, PowtórzenieParametruException,
            ZaMałoParametrówException, NiepoprawnyPlikException, ZaMałoArgumentówException {

        if (args.length < 2)
            throw new ZaMałoArgumentówException();
        Parametry parametry = new Parametry(new File(args[1]));
        Plansza plansza = new Plansza(new File(args[0]), parametry.dajIleRośnieJedzenie(),
                parametry.dajIleDajeJedzenie());
        parametry.ustawRozmiarPlanszy(plansza.dajRozmiarPlanszyX(), plansza.dajRozmiarPlanszyY());
        Symulacja symulacja = new Symulacja(parametry, plansza);
        symulacja.symuluj();
    }
}
