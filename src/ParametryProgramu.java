import java.util.ArrayList;

public class ParametryProgramu {
    
    private final double prDodaniaInstr;
    private final double prZmianyInstr;
    private final double prUsunięciaInstr;
    private final ArrayList<Character> spisInstr;
    
    public ParametryProgramu(double prDodaniaInstr, double prZmianyInstr,
                             double prUsunięciaInstr, ArrayList<Character> spisInstr) {
        this.prDodaniaInstr = prDodaniaInstr;
        this.prZmianyInstr = prZmianyInstr;
        this.prUsunięciaInstr = prUsunięciaInstr;
        this.spisInstr = spisInstr;
    }
    
    public double dajPrDodaniaInstr() {
        return prDodaniaInstr;
    }

    public double dajPrZmianyInstr() {
        return prZmianyInstr;
    }

    public double dajPrUsunięciaInstr() {
        return prUsunięciaInstr;
    }

    public ArrayList<Character> dajSpisInstr() {
        return spisInstr;
    }
}
