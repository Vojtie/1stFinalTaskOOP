package zad1.Wczytywanie;


import zad1.Roby.Program;
import zad1.Wyjątki.NiepoprawnyParametrException;
import zad1.Wyjątki.PowtórzenieParametruException;
import zad1.Wyjątki.ZaMałoParametrówException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

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
    private final int coIleWypisz;

    public Parametry(File plikParametry) throws PowtórzenieParametruException, ZaMałoParametrówException,
            FileNotFoundException {
        
        Scanner scan = new Scanner(plikParametry).useLocale(Locale.US);
        String program = "", instrukcje = "";
        int ileTur = 0, ileDajeJedzenie = 0, ileRośnieJedzenie = 0, coIleWypisz = 0,
                poczIleRobów = 0, poczEnergia = 0, kosztTury = 0, limitPowielania = 0;
        double prPowielenia = 0, ułamekEnergiiRodzica = 0, prUsunięciaInstr = 0,
                prDodaniaInstr = 0, prZmianyInstr = 0;
        boolean czyIleTur = false, czyPoczIleRobów = false,
                czyPoczEnergia = false, czyIleDajeJedzenie = false, czyIleRośnieJedzenie = false,
                czyKosztTury = false, czyLimitPowielania = false, czyCoIleWypisz = false, czyPrPowielenia = false,
                czyUłamekEnergiiRodzica = false, czyPrUsunięciaInstr = false, czyPrDodaniaInstr = false,
                czyPrZmianyInstr = false, czySpisInstr = false, czyPoczProgr = false;
        while (scan.hasNextLine()) {
            String parametr = scan.next();
            switch (parametr) {
                case "ile_tur":
                    if (czyIleTur)
                        throw new PowtórzenieParametruException(parametr);
                    ileTur = scan.nextInt();
                    if (!Wczytywanie.czyPoprawnyParametr(ileTur))
                        throw new NiepoprawnyParametrException(parametr);
                    czyIleTur = true;
                    break;
                case "pocz_ile_robów":
                    if (czyPoczIleRobów)
                        throw new PowtórzenieParametruException(parametr);
                    poczIleRobów = scan.nextInt();
                    if (!Wczytywanie.czyPoprawnyParametr(poczIleRobów))
                        throw new NiepoprawnyParametrException(parametr);
                    czyPoczIleRobów = true;
                    break;
                case "pocz_energia":
                    if (czyPoczEnergia)
                        throw new PowtórzenieParametruException(parametr);
                    poczEnergia = scan.nextInt();
                    if (!Wczytywanie.czyPoprawnyParametr(poczEnergia))
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
                    if (!Wczytywanie.czyPoprawnyParametr(ileDajeJedzenie))
                        throw new NiepoprawnyParametrException(parametr);
                    czyIleDajeJedzenie = true;
                    break;
                case "ile_rośnie_jedzenie":
                    if (czyIleRośnieJedzenie)
                        throw new PowtórzenieParametruException(parametr);
                    ileRośnieJedzenie = scan.nextInt();
                    if (!Wczytywanie.czyPoprawnyParametr(ileRośnieJedzenie))
                        throw new NiepoprawnyParametrException(parametr);
                    czyIleRośnieJedzenie = true;
                    break;
                case "koszt_tury":
                    if (czyKosztTury)
                        throw new PowtórzenieParametruException(parametr);
                    kosztTury = scan.nextInt();
                    if (!Wczytywanie.czyPoprawnyParametr(kosztTury))
                        throw new NiepoprawnyParametrException(parametr);
                    czyKosztTury = true;
                    break;
                case "pr_powielenia":
                    if (czyPrPowielenia)
                        throw new PowtórzenieParametruException(parametr);
                    prPowielenia = scan.nextDouble();
                    if (!Wczytywanie.czyPoprawnyParametr(prPowielenia))
                        throw new NiepoprawnyParametrException(parametr);
                    czyPrPowielenia = true;
                    break;
                case "ułamek_energii_rodzica":
                    if (czyUłamekEnergiiRodzica)
                        throw new PowtórzenieParametruException(parametr);
                    ułamekEnergiiRodzica = scan.nextDouble();
                    if (!Wczytywanie.czyPoprawnyParametr(ułamekEnergiiRodzica))
                        throw new NiepoprawnyParametrException(parametr);
                    czyUłamekEnergiiRodzica = true;
                    break;
                case "limit_powielania":
                    if (czyLimitPowielania)
                        throw new PowtórzenieParametruException(parametr);
                    limitPowielania = scan.nextInt();
                    if (!Wczytywanie.czyPoprawnyParametr(limitPowielania))
                        throw new NiepoprawnyParametrException(parametr);
                    czyLimitPowielania = true;
                    break;
                case "pr_usunięcia_instr":
                    if (czyPrUsunięciaInstr)
                        throw new PowtórzenieParametruException(parametr);
                    prUsunięciaInstr = scan.nextDouble();
                    if (!Wczytywanie.czyPoprawnyParametr(prUsunięciaInstr))
                        throw new NiepoprawnyParametrException(parametr);
                    czyPrUsunięciaInstr = true;
                    break;
                case "pr_dodania_instr":
                    if (czyPrDodaniaInstr)
                        throw new PowtórzenieParametruException(parametr);
                    prDodaniaInstr = scan.nextDouble();
                    if (!Wczytywanie.czyPoprawnyParametr(prDodaniaInstr))
                        throw new NiepoprawnyParametrException(parametr);
                    czyPrDodaniaInstr = true;
                    break;
                case "pr_zmiany_instr":
                    if (czyPrZmianyInstr)
                        throw new PowtórzenieParametruException(parametr);
                    prZmianyInstr = scan.nextDouble();
                    if (!Wczytywanie.czyPoprawnyParametr(prZmianyInstr))
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
                    if (!Wczytywanie.czyPoprawnyParametr(coIleWypisz))
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
                || !czyUłamekEnergiiRodzica || !czyPoczIleRobów)
            throw new ZaMałoParametrówException();
        ArrayList<Character> spisInstr = Wczytywanie.stringNaArrList(instrukcje);
        Program poczProgram = Wczytywanie.utwórzPoczProgram(program, spisInstr, prZmianyInstr, prDodaniaInstr,
                prUsunięciaInstr);
        this.ileTur = ileTur;
        this.poczIleRobów = poczIleRobów;
        this.poczProg = poczProgram;
        this.poczEnergia = poczEnergia;
        this.ileDajeJedzenie = ileDajeJedzenie;
        this.ileRośnieJedzenie = ileRośnieJedzenie;
        this.kosztTury = kosztTury;
        this.prPowielenia = prPowielenia;
        this.ułamekEnergiiRodzica = ułamekEnergiiRodzica;
        this.limitPowielania = limitPowielania;
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

    public int dajCoIleWypisz() {
        return coIleWypisz;
    }
}
