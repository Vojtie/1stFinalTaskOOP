package Wczytywanie;

import Roby.Program;
import Symulacja.Symulacja;
import Wyjątki.NiepoprawnyPlikException;
import Wyjątki.PowtórzenieParametruException;
import Wyjątki.ZaMałoArgumentówException;
import Wyjątki.ZaMałoParametrówException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Wczytywanie {

    public static boolean czyPoprawnyParametr(int parametr) {
        return parametr >= 0;
    }
    
    public static boolean czyPoprawnyParametr(double parametr) {
        return parametr >= 0 && parametr <= 1;
    }
    
    public static ArrayList<Character> stringNaArrList(String str) {
        ArrayList<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++)
            arrayList.add(str.charAt(i));
        return arrayList;
    }

    public static Program utwórzPoczProgram(String instrukcje, ArrayList<Character> spisInstr, double prZmianyInstr,
                                            double prDodaniaInstr, double prUsunięciaInstr) {
        ArrayList<Character> listaInstr = new ArrayList<>();
        for (int i = 0; i < instrukcje.length(); i++) {
            Character instrukcja = instrukcje.charAt(i);
            if (spisInstr.contains(instrukcja))
                listaInstr.add(instrukcja);
        }
        return new Program(prDodaniaInstr, prZmianyInstr, prUsunięciaInstr, spisInstr, listaInstr);
    }

    public static void main(String[] args) throws FileNotFoundException, PowtórzenieParametruException,
            ZaMałoParametrówException, NiepoprawnyPlikException, ZaMałoArgumentówException {
        
        if (args.length < 2)
            throw new ZaMałoArgumentówException();
        Parametry parametry = new Parametry(new File(args[1]));
        Plansza plansza = new Plansza(new File(args[0]), parametry.dajIleRośnieJedzenie(),
                parametry.dajIleDajeJedzenie());
        parametry.ustawRozmiarPlanszy(plansza.dajRozmiarPlanszyX(), plansza.dajRozmiarPlanszyY());
        Symulacja symulacja = new Symulacja(parametry, plansza);
        symulacja.symuluj();
    }
}
