package Wyjątki;

public class ZaMałoParametrówException extends Exception {

    public ZaMałoParametrówException() {
        super("Niewystarczająca ilość parametrów w pliku parametry.txt");
    }
}
