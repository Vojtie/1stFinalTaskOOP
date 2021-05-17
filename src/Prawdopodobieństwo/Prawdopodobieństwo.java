package Prawdopodobieństwo;

import java.util.Random;

public class Prawdopodobieństwo {
    
    public boolean losuj(double prawdop) {
        return new Random().nextDouble() <= prawdop;
    }
}
