package Roby;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Roby {    
    
    private final ArrayList<Rob> roby;
    
    public Roby(int poczIleRobów, int rozmiarPlanszyX, int rozmiarPlanszyY, Program poczProgram, int kosztTury,
                int limitPowielania, double ułamekEnergiiRodzica, double prPowielenia, int poczEnergia) {
        
        ArrayList<Rob> roby = new ArrayList<>();
        for (int i = 0 ; i < poczIleRobów; i++)
            roby.add(new Rob(rozmiarPlanszyX, rozmiarPlanszyY, poczProgram,
                    kosztTury, limitPowielania, ułamekEnergiiRodzica,
                    prPowielenia, poczEnergia, i + 1));
        this.roby = roby;
    }

    public int dajLiczbęRobów() {
        int wynik = 0;
        if (roby != null)
            wynik = roby.size();
        return wynik;
    }
    
    public void usuńMartweRoby() {
        roby.removeIf(rob -> !rob.czyŻyje());
    }

    public void dodajRoba(Rob nowy) {
        roby.add(nowy);
    }

    public Rob dajRoba(int indeks) {
        return roby.get(indeks);
    }

    public int dajNumerOstRoba() {
        int wynik = 0;
        for (int i = 0; i < dajLiczbęRobów(); i++)
            wynik = Math.max(wynik, dajRoba(i).dajNumerRoba());
        return wynik;
    }

    public int[] dajMinMaxStatystykiRobów() {
        int minDłgPrg = 0, maxDłgPrg = 0, minEnerg = 0, maxEnerg = 0, minWiek = 0, maxWiek = 0;
        for (Rob rob : roby) {
            minDłgPrg = Math.min(minDłgPrg, rob.dajDłgPrg());
            maxDłgPrg = Math.max(maxDłgPrg, rob.dajDłgPrg());
            minEnerg = Math.min(minEnerg, rob.dajLiczbęJedEnerg());
            maxEnerg = Math.max(maxEnerg, rob.dajLiczbęJedEnerg());
            minWiek = Math.min(minWiek, rob.dajDłgŻycia());
            maxWiek = Math.max(maxWiek, rob.dajDłgŻycia());
        }
        return new int[] {minDłgPrg, maxDłgPrg, minEnerg, maxEnerg, minWiek, maxWiek};
    }

    public double[] dajŚredStatystykiRobów() {
        double wiek = 0, dłgPrg = 0, energ = 0;
        if (dajLiczbęRobów() != 0) {
            for (Rob rob : roby) {
                wiek += rob.dajDłgŻycia();
                dłgPrg += rob.dajDłgPrg();
                energ += rob.dajLiczbęJedEnerg();
            }
            wiek /= dajLiczbęRobów();
            dłgPrg /= dajLiczbęRobów();
            energ /= dajLiczbęRobów();
        }
        wiek = BigDecimal.valueOf(wiek)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        dłgPrg = BigDecimal.valueOf(dłgPrg)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        energ = BigDecimal.valueOf(energ)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        return new double[] {wiek, dłgPrg, energ};
    }
}
