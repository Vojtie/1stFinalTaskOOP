import java.util.ArrayList;
import java.util.Random;

public class Program {
    
    private final ParametryProgramu parametry;
    private final ArrayList<Character> instrukcje;

    Program(ParametryProgramu parametry, ArrayList<Character> instrukcje) {
        assert (parametry != null && instrukcje != null);
        this.parametry = parametry;
        this.instrukcje = instrukcje;
    }

    public char dajInstrukcję(int numer) {
        assert (numer >= 0 && numer <= dajLiczbęInstrukcji());
        return instrukcje.get(numer);
    }

    public int dajDłgPrg() {
        return instrukcje.size();
    }

    public int dajLiczbęInstrukcji() {
        return instrukcje.size();
    }
    
    public ArrayList<Character> zróbKopię() {
        // sprawdzic czy to tworzy nowy arraylist z elementami instrukcje
        // shallow copy
        return new ArrayList<>(instrukcje);
    }

    public void usuńOstatniąInstrukcję() {
        assert(dajLiczbęInstrukcji() > 0);
        instrukcje.remove(dajLiczbęInstrukcji() - 1);
    }

    private Character losujInstrukcję() {
        ArrayList<Character> spisInstr = parametry.dajSpisInstr();
        int numer = new Random().nextInt(spisInstr.size());
        return spisInstr.get(numer);
    }
    // dodaje na koniec
    public void dodajLosowąInstrukcję() {
        instrukcje.add(losujInstrukcję());
    }

    public void zmieńLosowąInstrukcję() {
        assert (dajLiczbęInstrukcji() > 0);
        int doZmiany = new Random().nextInt(dajLiczbęInstrukcji());
        instrukcje.set(doZmiany, losujInstrukcję());
    }

    public boolean czyPusty() {
        return dajLiczbęInstrukcji() > 0;
    }

    public Program mutuj() {
        Program zmutowany = new Program(parametry, zróbKopię());
        if (!zmutowany.czyPusty() && new Prawdopodobieństwo().losuj(parametry.dajPrUsunięciaInstr()))
            zmutowany.usuńOstatniąInstrukcję();
        if (new Prawdopodobieństwo().losuj(parametry.dajPrDodaniaInstr()))
            zmutowany.dodajLosowąInstrukcję();
        if (!zmutowany.czyPusty() && new Prawdopodobieństwo().losuj(parametry.dajPrZmianyInstr()))
            zmutowany.zmieńLosowąInstrukcję();
        return zmutowany;
    }
}
