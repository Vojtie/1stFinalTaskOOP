package Wyjątki;

public class NiepoprawnyPlikException extends Exception {

    public NiepoprawnyPlikException(String plik) {
        super("Niepoprawne dane w pliku: " + plik);
    }
}
