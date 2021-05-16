import java.util.ArrayList;
import java.util.Random;

public class Program {

    private final ArrayList<Character> spisIntr;
    private final ArrayList<Character> instrukcje;

    Program(ArrayList<Character> spisIntr, ArrayList<Character> instrukcje) {
        this.spisIntr = spisIntr;
        this.instrukcje = instrukcje;
    }

    public char dajInstrukcję(int numer) {
        assert(numer >= 0 && numer <= dajLiczbęInstrukcji());
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
        return new ArrayList<Character>(instrukcje);
    }

    public void usuńOstatniąInstrukcję() {
        assert(dajLiczbęInstrukcji() > 0);
        instrukcje.remove(dajLiczbęInstrukcji() - 1);
    }

    private Character losujInstrukcję() {
        int numer = new Random().nextInt(spisIntr.size());
        return spisIntr.get(numer);
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
}
