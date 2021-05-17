public class Symulacja extends Świat {
    
    Symulacja(Parametry parametry, Plansza plansza) {
        super(parametry, plansza);
    }

    private void wypiszPodstawoweDaneSymulacji(int numerTury) {
        System.out.println(numerTury + dajBieżącyStanŚwiata());
    }

    private void wypiszBieżącyStanSymulacji() {
        System.out.println("Stan robów:");
        for (int i = 0; i < roby.dajLiczbęRobów(); i++) {
            Rob rob = roby.dajRoba(i);
            System.out.println("rob nr " + rob.dajNumerRoba() + ":\t" + rob.toString());
        }
    }
    
    private void wypiszPlanszę() {
        
    }

    private void następnaTura() {
        int liczbaRobów = roby.dajLiczbęRobów();
        for (int i = 0; i < liczbaRobów; i++) {
            Rob rob = roby.dajRoba(i);
            rob.wykonajInstrukcje(this);
            if (rob.czyŻyje() && rob.czyPowiela())
                roby.dodajRoba(rob.powiel(roby.dajNumerOstRoba() + 1));
            rob.następnaTura();
        }
        roby.usuńMartweRoby();
        plansza.następnaTura();
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
