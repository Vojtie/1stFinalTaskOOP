package zad1.Świat;

import zad1.Roby.Rob;
import zad1.Roby.Roby;
import zad1.Roby.Kierunek;
import zad1.Wczytywanie.Parametry;
import zad1.Wczytywanie.Plansza;

public abstract class Świat {

    protected final Parametry parametry;
    protected final Plansza plansza;
    protected final Roby roby;

    public Świat(Parametry parametry, Plansza plansza) {
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
}
