package zad1.Roby;

import zad1.Prawdopodobieństwo.Prawdopodobieństwo;

import java.util.ArrayList;
import java.util.Random;

public class Program {

    private final double prDodaniaInstr;
    private final double prZmianyInstr;
    private final double prUsunięciaInstr;
    private final ArrayList<Character> spisInstr;
    private final ArrayList<Character> instrukcje;

    public Program(double prDodaniaInstr, double prZmianyInstr, double prUsunięciaInstr,
                   ArrayList<Character> spisInstr, ArrayList<Character> instrukcje) {
        
        assert (spisInstr != null && instrukcje != null);
        this.prDodaniaInstr = prDodaniaInstr;
        this.prZmianyInstr = prZmianyInstr;
        this.prUsunięciaInstr = prUsunięciaInstr;
        this.spisInstr = spisInstr;
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
    
    public String toString() {
        StringBuilder program = new StringBuilder();
        for (Character instrukcja : instrukcje)
            program.append(instrukcja);
        return program.toString();
    }
    
    public ArrayList<Character> zróbKopię() {
        return new ArrayList<>(instrukcje);
    }

    public void usuńOstatniąInstrukcję() {
        assert(dajLiczbęInstrukcji() > 0);
        instrukcje.remove(dajLiczbęInstrukcji() - 1);
    }

    private Character losujInstrukcję() {
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

    public boolean czyNiepusty() {
        return dajLiczbęInstrukcji() > 0;
    }

    public Program mutuj() {
        Program zmutowany = new Program(prDodaniaInstr, prZmianyInstr, prUsunięciaInstr,
                spisInstr, zróbKopię());
        if (zmutowany.czyNiepusty() && new Prawdopodobieństwo().losuj(prUsunięciaInstr))
            zmutowany.usuńOstatniąInstrukcję();
        if (new Prawdopodobieństwo().losuj(prDodaniaInstr))
            zmutowany.dodajLosowąInstrukcję();
        if (zmutowany.czyNiepusty() && new Prawdopodobieństwo().losuj(prZmianyInstr))
            zmutowany.zmieńLosowąInstrukcję();
        return zmutowany;
    }
}
