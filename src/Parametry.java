public class Parametry {
    private final int ileTur;
    private int rozmiarPlanszyX;
    private int rozmiarPlanszyY;
    private final int poczIleRobów;
    private final Program poczProg;
    private final int poczEnergia;
    private final int ileDajeJedzenie;
    private final int ileRośnieJedzenie;
    private final int kosztTury;
    private final double prPowielenia;
    private final double ułamekEnergiiRodzica;
    private final int limitPowielania;
    private final double prUsunięciaInstr;
    private final double prDodaniaInstr;
    private final char[] spisInstr;
    private final double prZmianyInstr;
    private final int coIleWypisz;

    public Parametry(int ileTur, int poczIleRobów,
                     Program poczProg, int poczEnergia, int ileDajeJedzenie, int ileRośnieJedzenie,
                     int kosztTury, double prPowielenia, double ułamekEnergiiRodzica, int limitPowielania,
                     double prUsunięciaInstr, double prDodaniaInstr, char[] spisInstr, double prZmianyInstr,
                     int coIleWypisz) {
        
        this.ileTur = ileTur;
        this.poczIleRobów = poczIleRobów;
        this.poczProg = poczProg;
        this.poczEnergia = poczEnergia;
        this.ileDajeJedzenie = ileDajeJedzenie;
        this.ileRośnieJedzenie = ileRośnieJedzenie;
        this.kosztTury = kosztTury;
        this.prPowielenia = prPowielenia;
        this.ułamekEnergiiRodzica = ułamekEnergiiRodzica;
        this.limitPowielania = limitPowielania;
        this.prUsunięciaInstr = prUsunięciaInstr;
        this.prDodaniaInstr = prDodaniaInstr;
        this.spisInstr = spisInstr;
        this.prZmianyInstr = prZmianyInstr;
        this.coIleWypisz = coIleWypisz;
    }

    public void ustawRozmiarPlanszy(int rozmiarPlanszyX, int rozmiarPlanszyY) {
        this.rozmiarPlanszyX = rozmiarPlanszyX;
        this.rozmiarPlanszyY = rozmiarPlanszyY;
    }
    
    public int dajIleTur() {
        return ileTur;
    }

    public int dajRozmiarPlanszyX() {
        return rozmiarPlanszyX;
    }

    public int dajRozmiarPlanszyY() {
        return rozmiarPlanszyY;
    }

    public int dajPoczIleRobów() {
        return poczIleRobów;
    }

    public Program dajPoczProg() {
        return poczProg;
    }

    public int dajPoczEnergia() {
        return poczEnergia;
    }

    public int dajIleDajeJedzenie() {
        return ileDajeJedzenie;
    }

    public int dajIleRośnieJedzenie() {
        return ileRośnieJedzenie;
    }

    public int dajKosztTury() {
        return kosztTury;
    }

    public double dajPrPowielenia() {
        return prPowielenia;
    }

    public double dajUłamekEnergiiRodzica() {
        return ułamekEnergiiRodzica;
    }

    public int dajLimitPowielania() {
        return limitPowielania;
    }

    public double dajPrUsunięciaInstr() {
        return prUsunięciaInstr;
    }

    public double dajPrDodaniaInstr() {
        return prDodaniaInstr;
    }

    public char[] dajSpisInstr() {
        return spisInstr;
    }

    public double dajPrZmianyInstr() {
        return prZmianyInstr;
    }

    public int dajCoIleWypisz() {
        return coIleWypisz;
    }
}
