public class ZaMałoParametrówException extends Exception {

    ZaMałoParametrówException() {
        super("Niewystarczająca ilość parametrów w pliku parametry.txt");
    }
}
