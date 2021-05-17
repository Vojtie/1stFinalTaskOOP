package Symulacja;

import Roby.Rob;
import Roby.Roby;
import Roby.Kierunek;
import Wczytywanie.Parametry;
import Wczytywanie.Plansza;

public abstract class Świat {

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
}
