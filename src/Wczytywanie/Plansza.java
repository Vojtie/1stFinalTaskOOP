package Wczytywanie;

import Pola.*;
import Wyjątki.NiepoprawnyPlikException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Plansza {

    private final Pole[][] plansza;

    Plansza(File plikPlansza, int ileRośnieJedzenie, int ileDajeJedzenie) throws NiepoprawnyPlikException, FileNotFoundException {
        Scanner scan = new Scanner(plikPlansza);
        if (!scan.hasNextLine())
            throw new NiepoprawnyPlikException("plansza.txt");
        String wiersz = scan.nextLine();
        int długośćWiersza = wiersz.length(), liczbaWierszy = 1;
        if (długośćWiersza <= 0)
            throw new NiepoprawnyPlikException("plansza.txt");
        while (scan.hasNextLine()) {
            scan.nextLine();
            liczbaWierszy++;
        }
        scan = new Scanner(plikPlansza);
        Pole[][] plansza = new Pole[liczbaWierszy][długośćWiersza];
        for (int i = 0; i < liczbaWierszy; i++) {
            wiersz = scan.nextLine();
            if (wiersz.length() != długośćWiersza)
                throw new NiepoprawnyPlikException("plansza.txt");
            for (int j = 0; j < długośćWiersza; j++) {
                switch (wiersz.charAt(j)) {
                    case ' ':
                        plansza[i][j] = new PolePuste();
                        break;
                    case 'x':
                        plansza[i][j] = new PoleŻywieniowe(ileRośnieJedzenie, ileDajeJedzenie);
                        break;
                    default:
                        throw new NiepoprawnyPlikException("plansza.txt");
                }
            }
        }
        this.plansza = plansza;
    }
    
    public int dajRozmiarPlanszyX() {
        return plansza.length;
    }

    public int dajRozmiarPlanszyY() {
        return plansza[0].length;
    }

    public Pole dajPole(int[] koordy) {
        assert(koordy.length == 2 && koordy[0] < dajRozmiarPlanszyX()
                && koordy[1] < dajRozmiarPlanszyY());
        return plansza[koordy[0]][koordy[1]];
    }

    public int dajLiczbęPólZŻyw() {
        int wynik = 0;
        for (final Pole[] tabPól : plansza)
            for (final Pole pole : tabPól)
                if (pole instanceof PoleŻywieniowe && ((PoleŻywieniowe) pole).maPożywienie())
                    wynik++;
        return wynik;
    }

    public boolean czyPoleZPożywieniem(int[] koordy) {
        assert (koordy.length == 2 && koordy[0] < dajRozmiarPlanszyX() && koordy[1] < dajRozmiarPlanszyY());
        Pole pole = plansza[koordy[0]][koordy[1]];
        return pole instanceof PoleŻywieniowe && ((PoleŻywieniowe) plansza[koordy[0]][koordy[1]]).maPożywienie();
    }
    
    public void następnaTura() {
        for (final Pole[] tabPól : plansza)
            for (final Pole pole : tabPól)
                if (pole instanceof PoleŻywieniowe)
                    ((PoleŻywieniowe) pole).następnaTura();
    }

    /** Oznaczenia:
     *    '$' - pole żywieniowe z pożywieniem,
     *    '#' - pole żywieniowe bez pożywienia,
     *    ' ' - pole puste.
     */
    public void wypiszPlanszę() {
        int rozmiarPlanszyY = dajRozmiarPlanszyY();
        for (int i = 0; i < rozmiarPlanszyY + 2; i++)
            System.out.print("-");
        System.out.println();
        for (final Pole[] tabPól : plansza) {
            System.out.print("|");
            for (final Pole pole : tabPól) {
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
        for (int i = 0; i < rozmiarPlanszyY + 2; i++)
            System.out.print("_");
        System.out.println();
    }
}
