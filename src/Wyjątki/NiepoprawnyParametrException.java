package Wyjątki;

public class NiepoprawnyParametrException extends Error {

    public NiepoprawnyParametrException(String parametr) {
        super("Niepoprawny parametr: " + parametr);
    }
}
