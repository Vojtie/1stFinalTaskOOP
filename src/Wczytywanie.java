import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Wczytywanie {

    private boolean czyPoprawnyParametr(int parametr) {
        return parametr >= 0;
    }
    
    private ArrayList<Character> stringNaArrList(String str) {
        ArrayList<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++)
            arrayList.add(str.charAt(i));
        return arrayList;
    }

    private Program utwórzPoczProgram(String instrukcje, ArrayList<Character> spisInstr, double prZmianyInstr,
                                             double prDodaniaInstr, double prUsunięciaInstr) {
        ArrayList<Character> listaInstr = new ArrayList<>();
        for (int i = 0; i < instrukcje.length(); i++) {
            Character instrukcja = instrukcje.charAt(i);
            if (spisInstr.contains(instrukcja))
                listaInstr.add(instrukcja);
        }
        return new Program(prDodaniaInstr, prZmianyInstr, prUsunięciaInstr, spisInstr, listaInstr);
    }

    private boolean czyPoprawnyParametr(double parametr) {
        return parametr >= 0;
    }
    
    private Parametry wczytajParametry(File plikParametry) throws FileNotFoundException, PowtórzenieParametruException, ZaMałoParametrówException {
        Scanner scan = new Scanner(plikParametry).useLocale(Locale.US);
        String program = "", instrukcje = "";
        // parametry
        int ileTur = 0, ileDajeJedzenie = 0, ileRośnieJedzenie = 0, coIleWypisz = 0,
                poczRobów = 0, poczEnergia = 0, kosztTury = 0, limitPowielania = 0;
        double prPowielenia = 0, ułamekEnergiiRodzica = 0, prUsunięciaInstr = 0,
                prDodaniaInstr = 0, prZmianyInstr = 0;
        // flagi
        boolean czyIleTur = false, czyPoczRobów = false,
                czyPoczEnergia = false, czyIleDajeJedzenie = false, czyIleRośnieJedzenie = false,
                czyKosztTury = false, czyLimitPowielania = false, czyCoIleWypisz = false, czyPrPowielenia = false,
                czyUłamekEnergiiRodzica = false, czyPrUsunięciaInstr = false, czyPrDodaniaInstr = false,
                czyPrZmianyInstr = false, czySpisInstr = false, czyPoczProgr = false;
        // wczytywanie parametrów
        while (scan.hasNextLine()) {
            String parametr = scan.next();
            switch (parametr) {
                case "ile_tur":
                    if (czyIleTur)
                        throw new PowtórzenieParametruException(parametr);
                    ileTur = scan.nextInt();
                    if (!czyPoprawnyParametr(ileTur))
                        throw new NiepoprawnyParametrException(parametr);
                    czyIleTur = true;
                    break;
                case "pocz_ile_robów":
                    if (czyPoczRobów)
                        throw new PowtórzenieParametruException(parametr);
                    poczRobów = scan.nextInt();
                    if (!czyPoprawnyParametr(poczRobów))
                        throw new NiepoprawnyParametrException(parametr);
                    czyPoczRobów = true;
                    break;
                case "pocz_energia":
                    if (czyPoczEnergia)
                        throw new PowtórzenieParametruException(parametr);
                    poczEnergia = scan.nextInt();
                    if (!czyPoprawnyParametr(poczEnergia))
                        throw new NiepoprawnyParametrException(parametr);
                    czyPoczEnergia = true;
                    break;
                case "pocz_progr":
                    if (czyPoczProgr)
                        throw new PowtórzenieParametruException(parametr);
                    program = scan.next();
                    czyPoczProgr = true;
                    break;
                case "ile_daje_jedzenie":
                    if (czyIleDajeJedzenie)
                        throw new PowtórzenieParametruException(parametr);
                    ileDajeJedzenie = scan.nextInt();
                    if (!czyPoprawnyParametr(ileDajeJedzenie))
                        throw new NiepoprawnyParametrException(parametr);
                    czyIleDajeJedzenie = true;
                    break;
                case "ile_rośnie_jedzenie":
                    if (czyIleRośnieJedzenie)
                        throw new PowtórzenieParametruException(parametr);
                    ileRośnieJedzenie = scan.nextInt();
                    if (!czyPoprawnyParametr(ileRośnieJedzenie))
                        throw new NiepoprawnyParametrException(parametr);
                    czyIleRośnieJedzenie = true;
                    break;
                case "koszt_tury":
                    if (czyKosztTury)
                        throw new PowtórzenieParametruException(parametr);
                    kosztTury = scan.nextInt();
                    if (!czyPoprawnyParametr(kosztTury))
                        throw new NiepoprawnyParametrException(parametr);
                    czyKosztTury = true;
                    break;
                case "pr_powielenia":
                    if (czyPrPowielenia)
                        throw new PowtórzenieParametruException(parametr);
                    prPowielenia = scan.nextDouble();
                    if (!czyPoprawnyParametr(prPowielenia))
                        throw new NiepoprawnyParametrException(parametr);
                    czyPrPowielenia = true;
                    break;
                case "ułamek_energii_rodzica":
                    if (czyUłamekEnergiiRodzica)
                        throw new PowtórzenieParametruException(parametr);
                    ułamekEnergiiRodzica = scan.nextDouble();
                    if (!czyPoprawnyParametr(ułamekEnergiiRodzica))
                        throw new NiepoprawnyParametrException(parametr);
                    czyUłamekEnergiiRodzica = true;
                    break;
                case "limit_powielania":
                    if (czyLimitPowielania)
                        throw new PowtórzenieParametruException(parametr);
                    limitPowielania = scan.nextInt();
                    if (!czyPoprawnyParametr(limitPowielania))
                        throw new NiepoprawnyParametrException(parametr);
                    czyLimitPowielania = true;
                    break;
                case "pr_usunięcia_instr":
                    if (czyPrUsunięciaInstr)
                        throw new PowtórzenieParametruException(parametr);
                    prUsunięciaInstr = scan.nextDouble();
                    if (!czyPoprawnyParametr(prUsunięciaInstr))
                        throw new NiepoprawnyParametrException(parametr);
                    czyPrUsunięciaInstr = true;
                    break;
                case "pr_dodania_instr":
                    if (czyPrDodaniaInstr)
                        throw new PowtórzenieParametruException(parametr);
                    prDodaniaInstr = scan.nextDouble();
                    if (!czyPoprawnyParametr(prDodaniaInstr))
                        throw new NiepoprawnyParametrException(parametr);
                    czyPrDodaniaInstr = true;
                    break;
                case "pr_zmiany_instr":
                    if (czyPrZmianyInstr)
                        throw new PowtórzenieParametruException(parametr);
                    prZmianyInstr = scan.nextDouble();
                    if (!czyPoprawnyParametr(prZmianyInstr))
                        throw new NiepoprawnyParametrException(parametr);
                    czyPrZmianyInstr = true;
                    break;
                case "spis_instr":
                    if (czySpisInstr)
                        throw new PowtórzenieParametruException(parametr);
                    instrukcje = scan.next();
                    czySpisInstr = true;
                    break;
                case "co_ile_wypisz":
                    if (czyCoIleWypisz)
                        throw new PowtórzenieParametruException(parametr);
                    coIleWypisz = scan.nextInt();
                    if (!czyPoprawnyParametr(coIleWypisz))
                        throw new NiepoprawnyParametrException(parametr);
                    czyCoIleWypisz = true;
                break;
                default:
                    throw new NiepoprawnyParametrException(parametr);
            }
        }
        if (!czyCoIleWypisz || !czyIleTur || !czyIleDajeJedzenie || !czyIleRośnieJedzenie || !czyKosztTury
                || !czyLimitPowielania || !czyPoczEnergia || !czyPoczProgr || !czyPrDodaniaInstr
                || !czyPrUsunięciaInstr || !czyPrZmianyInstr || !czyPrPowielenia || !czySpisInstr
                || !czyUłamekEnergiiRodzica || !czyPoczRobów)
            throw new ZaMałoParametrówException();
        ArrayList<Character> spisInstr = stringNaArrList(instrukcje);
        Program poczProgram = utwórzPoczProgram(program, spisInstr, prZmianyInstr, prDodaniaInstr, prUsunięciaInstr);
        return new Parametry(ileTur, poczRobów, poczProgram, poczEnergia, ileDajeJedzenie, ileRośnieJedzenie,
                kosztTury, prPowielenia, ułamekEnergiiRodzica, limitPowielania, prUsunięciaInstr, prDodaniaInstr,
                spisInstr, prZmianyInstr, coIleWypisz);
    }

    public static void main(String[] args) throws FileNotFoundException, PowtórzenieParametruException,
            ZaMałoParametrówException, NiepoprawnyPlikException, ZaMałoArgumentówException {
        
        if (args.length < 2)
            throw new ZaMałoArgumentówException();
        Wczytywanie wczytywanie = new Wczytywanie();
        Parametry parametry = wczytywanie.wczytajParametry(new File(args[1]));
        Plansza plansza = new Plansza(new File(args[0]), parametry.dajIleRośnieJedzenie(), parametry.dajIleDajeJedzenie());
        parametry.ustawRozmiarPlanszy(plansza.dajRozmiarPlanszyX(), plansza.dajRozmiarPlanszyY());
        Symulacja symulacja = new Symulacja(parametry, plansza);
        symulacja.symuluj();
    }
}
