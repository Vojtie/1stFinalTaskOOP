public class PowtórzenieParametruException extends Exception {

    PowtórzenieParametruException(String parametr) {
        super("Powtórzenie parametru: " + parametr);
    }
}
