public class Symulacja extends Świat {
    
    Symulacja(Parametry parametry, Plansza plansza) {
        super(parametry, plansza);
    }

    private void wypiszPodstawoweDaneSymulacji(int numerTury) {
        System.out.println(numerTury + dajOpisStanuŚwiata());
    }
    
    public void wypiszBieżącyStanSymulacji() {
        System.out.println("Stan robów:");
        for (int i = 0; i < dajLiczbęRobów(); i++) {
            Rob rob = dajRoba(i);
            System.out.println("rob nr " + rob.dajNumerRoba() + ":\t" + rob.toString());
        }
    }
    
    public void symuluj() {
        wypiszBieżącyStanSymulacji();
        for (int tura = 1; tura <= parametry.dajIleTur(); wypiszPodstawoweDaneSymulacji(tura), tura++) {
            następnaTura();
            if (tura % parametry.dajCoIleWypisz() == 0)
                wypiszBieżącyStanSymulacji();
        }
        wypiszBieżącyStanSymulacji();
    }
}
