public class PoleŻywieniowe extends Pole {
    // rob je pożywienie tylko jeśli wejdzie na pole na którym już jest pożywienie
    private Parametry parametry;
    private int etapRośnięcia;

    PoleŻywieniowe(Parametry parametry) {
        this.parametry = parametry;
        this.etapRośnięcia = parametry.dajIleRośnieJedzenie();
    }

    public void ustawParametry(Parametry parametry) {
        this.parametry = parametry;
    }
    
    public boolean maPożywienie() {
        return etapRośnięcia == parametry.dajIleRośnieJedzenie();
    }

    public int dajPożywienie() {
        assert (maPożywienie());
        etapRośnięcia = 0;
        return parametry.dajIleDajeJedzenie();
    }
    public void następnaTura() {
        if (!maPożywienie())
            etapRośnięcia++;
    }
}
