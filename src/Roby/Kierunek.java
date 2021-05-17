package Roby;

import java.util.Random;

public enum Kierunek {
    PRAWO(1) {
        @Override
        public String toString() {
            return "prawo";
        }
    },
    
    LEWO(-1) {
        @Override
        public String toString() {
            return "lewo";
        }
    },
    
    GÓRA(1) {
        @Override
        public String toString() {
            return "góra";
        }
    },
    
    DÓŁ(-1) {
        @Override
        public String toString() {
            return "dół";
        }
    };

    int wartość;

    Kierunek(int wartość) {
        this.wartość = wartość;
    }

    public boolean czyX() {
        return this == PRAWO || this == LEWO;
    }

    Kierunek dajPrzeciwnyKierunek() {
        switch(this) {
            case PRAWO:
                return Kierunek.LEWO;
            case LEWO:
                return Kierunek.PRAWO;
            case GÓRA:
                return Kierunek.DÓŁ;
            default:
                return Kierunek.GÓRA;
        }
    }

    public Kierunek dajObrótWLewo() {
        switch(this) {
            case PRAWO:
                return Kierunek.GÓRA;
            case LEWO:
                return Kierunek.DÓŁ;
            case GÓRA:
                return Kierunek.LEWO;
            default:
                return Kierunek.PRAWO;
        }
    }

    public Kierunek dajObrótWPrawo() {
        switch(this) {
            case PRAWO:
                return Kierunek.DÓŁ;
            case LEWO:
                return Kierunek.GÓRA;
            case GÓRA:
                return Kierunek.PRAWO;
            default:
                return Kierunek.LEWO;
        }
    }

    static public Kierunek losujKierunek() {
        switch(new Random().nextInt(4)) {
            case 0:
                return Kierunek.LEWO;
            case 1:
                return Kierunek.GÓRA;
            case 2:
                return Kierunek.DÓŁ;
            default:
                return Kierunek.PRAWO;
        }
    }
}
