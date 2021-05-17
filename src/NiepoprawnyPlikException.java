public class NiepoprawnyPlikException extends Exception {
    
    NiepoprawnyPlikException(String plik) {
        super("Niepoprawne dane w pliku: " + plik);
    }
}
