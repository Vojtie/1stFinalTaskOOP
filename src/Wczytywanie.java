import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Wczytywanie {
    
    private static ArrayList<Character> stringToArrList(String str) {
        ArrayList<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++)
            arrayList.add(str.charAt(i));
        return arrayList;
    }

    private static Program utwórzPoczProgram(String instrukcje, ArrayList<Character> spisInstr) {
        ArrayList<Character> listaInstr = new ArrayList<>();
        for (int i = 0; i < instrukcje.length(); i++) {
            Character instrukcja = instrukcje.charAt(i);
            if (spisInstr.contains(instrukcja))
                listaInstr.add(instrukcja);
        }
        return new Program(spisInstr, listaInstr);
    }
    
    private static Pole[][] wczytajPlanszę(File plikPlansza, Parametry parametry) throws FileNotFoundException {
        Scanner scan = new Scanner(plikPlansza);
        if (!scan.hasNextLine())
            throw new Error("Niepoprawne dane pliku plansza.txt");
        String wiersz = scan.nextLine();
        int długośćWiersza = wiersz.length(), liczbaWierszy = 1;
        while (scan.hasNextLine()) {
            scan.nextLine();
            liczbaWierszy++;
        }
        scan = new Scanner(plikPlansza);
        Pole[][] plansza = new Pole[liczbaWierszy][długośćWiersza];
        parametry.ustawRozmiarPlanszy(liczbaWierszy, długośćWiersza);
        for (int i = 0; i < liczbaWierszy; i++) {
            wiersz = scan.nextLine();
            if (wiersz.length() != długośćWiersza)
                throw new Error("Niepoprawne dane pliku plansza.txt");
            for (int j = 0; j < długośćWiersza; j++) {
                switch (wiersz.charAt(j)) {
                    case ' ':
                        plansza[i][j] = new PolePuste();
                        break;
                    case 'x':
                        plansza[i][j] = new PoleŻywieniowe(parametry);
                        break;
                    default:
                        throw new Error("Niepoprawne dane w pliku plansza.txt");
                }
            }
        }
        return plansza;
    }

    private static Parametry wczytajParametry(File plikParametry) throws FileNotFoundException {
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
                        throw new Error("Powtórzenie parametru " + parametr);
                    ileTur = scan.nextInt();
                    czyIleTur = true;
                    break;
                case "pocz_ile_robów":
                    if (czyPoczRobów)
                        throw new Error("Powtórzenie parametru " + parametr);
                    poczRobów = scan.nextInt();
                    czyPoczRobów = true;
                    break;
                case "pocz_energia":
                    if (czyPoczEnergia)
                        throw new Error("Powtórzenie parametru " + parametr);
                    poczEnergia = scan.nextInt();
                    czyPoczEnergia = true;
                    break;
                case "pocz_progr":
                    if (czyPoczProgr)
                        throw new Error("Powtórzenie parametru " + parametr);
                    program = scan.next();
                    czyPoczProgr = true;
                    break;
                case "ile_daje_jedzenie":
                    if (czyIleDajeJedzenie)
                        throw new Error("Powtórzenie parametru " + parametr);
                    ileDajeJedzenie = scan.nextInt();
                    czyIleDajeJedzenie = true;
                    break;
                case "ile_rośnie_jedzenie":
                    if (czyIleRośnieJedzenie)
                        throw new Error("Powtórzenie parametru " + parametr);
                    ileRośnieJedzenie = scan.nextInt();
                    czyIleRośnieJedzenie = true;
                    break;
                case "koszt_tury":
                    if (czyKosztTury)
                        throw new Error("Powtórzenie parametru " + parametr);
                    kosztTury = scan.nextInt();
                    czyKosztTury = true;
                    break;
                case "pr_powielenia":
                    if (czyPrPowielenia)
                        throw new Error("Powtórzenie parametru " + parametr);
                    prPowielenia = scan.nextDouble();
                    czyPrPowielenia = true;
                    break;
                case "ułamek_energii_rodzica":
                    if (czyUłamekEnergiiRodzica)
                        throw new Error("Powtórzenie parametru " + parametr);
                    ułamekEnergiiRodzica = scan.nextDouble();
                    czyUłamekEnergiiRodzica = true;
                    break;
                case "limit_powielania":
                    if (czyLimitPowielania)
                        throw new Error("Powtórzenie parametru " + parametr);
                    limitPowielania = scan.nextInt();
                    czyLimitPowielania = true;
                    break;
                case "pr_usunięcia_instr":
                    if (czyPrUsunięciaInstr)
                        throw new Error("Powtórzenie parametru " + parametr);
                    prUsunięciaInstr = scan.nextDouble();
                    czyPrUsunięciaInstr = true;
                    break;
                case "pr_dodania_instr":
                    if (czyPrDodaniaInstr)
                        throw new Error("Powtórzenie parametru " + parametr);
                    prDodaniaInstr = scan.nextDouble();
                    czyPrDodaniaInstr = true;
                    break;
                case "pr_zmiany_instr":
                    if (czyPrZmianyInstr)
                        throw new Error("Powtórzenie parametru " + parametr);
                    prZmianyInstr = scan.nextDouble();
                    czyPrZmianyInstr = true;
                    break;
                case "spis_instr":
                    if (czySpisInstr)
                        throw new Error("Powtórzenie parametru " + parametr);
                    instrukcje = scan.next();
                    czySpisInstr = true;
                    break;
                case "co_ile_wypisz":
                    if (czyCoIleWypisz)
                        throw new Error("Powtórzenie parametru " + parametr);
                    coIleWypisz = scan.nextInt();
                    czyCoIleWypisz = true;
                break;
                default:
                    throw new Error("Niepoprawny parametr");
            }
        }
        if (!czyCoIleWypisz || !czyIleTur || !czyIleDajeJedzenie || !czyIleRośnieJedzenie || !czyKosztTury
                || !czyLimitPowielania || !czyPoczEnergia || !czyPoczProgr || !czyPrDodaniaInstr
                || !czyPrUsunięciaInstr || !czyPrZmianyInstr || !czyPrPowielenia || !czySpisInstr
                || !czyUłamekEnergiiRodzica || !czyPoczRobów)
            throw new Error("Za mało parametrów");
        ArrayList<Character> spisInstr = stringToArrList(instrukcje);
        return new Parametry(ileTur, poczRobów, utwórzPoczProgram(program, spisInstr),
                poczEnergia, ileDajeJedzenie, ileRośnieJedzenie, kosztTury, prPowielenia,
                ułamekEnergiiRodzica, limitPowielania, prUsunięciaInstr, prDodaniaInstr,
                spisInstr, prZmianyInstr, coIleWypisz);
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2)
            throw new Error("Za mało argumentów");
        Parametry parametry = wczytajParametry(new File(args[1]));
        Pole[][] plansza = wczytajPlanszę(new File(args[0]), parametry);
        Świat świat = new Świat(parametry, plansza);
        świat.symuluj();
    }
}
