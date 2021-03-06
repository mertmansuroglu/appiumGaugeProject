package exceptions;

public class KeywordNotFound extends Exception {
    final String message;

    public KeywordNotFound(String keyword) {

        this.message = String.format("%s keyword is not found in json file please check your keyword.", keyword);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
