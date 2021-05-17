package Pola;

public class PoleŻywieniowe extends Pole {

    private final int ileRośnieJedzenie;
    private final int ileDajeJedzenie;
    private int etapRośnięcia;

    public PoleŻywieniowe(int ileRośnieJedzenie, int ileDajeJedzenie) {
        this.ileRośnieJedzenie = ileRośnieJedzenie;
        this.ileDajeJedzenie = ileDajeJedzenie;
        this.etapRośnięcia = ileRośnieJedzenie;
    }

    public boolean maPożywienie() {
        return etapRośnięcia == ileRośnieJedzenie;
    }

    public int dajPożywienie() {
        assert (maPożywienie());
        etapRośnięcia = 0;
        return ileDajeJedzenie;
    }
    
    public void następnaTura() {
        if (!maPożywienie())
            etapRośnięcia++;
    }
}
