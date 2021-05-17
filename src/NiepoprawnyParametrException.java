public class NiepoprawnyParametrException extends Error {

    NiepoprawnyParametrException(String parametr) {
        super("Niepoprawny parametr: " + parametr);
    }
}
