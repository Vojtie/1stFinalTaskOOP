package zad1.Wyjątki;

public class ZaMałoArgumentówException extends Exception {

    public ZaMałoArgumentówException() {
        super("Niewystarczająca ilość argumentów programu");
    }
}
