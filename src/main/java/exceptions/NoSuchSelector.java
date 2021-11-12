package exceptions;

public class NoSuchSelector extends Exception {

    final String message;

    public NoSuchSelector(String selector) {

        this.message = "The selector type you send is undefined, selectorType: " + selector;
    }

    //burada get message override çok mecbur değil ama normalde mesajı editleyebilmek
    // bir de istersen RunTimeException
    // classından extrnd edebilirsin o zaman exception throw demen gerekmez
    // TODO: 11.11.2021 burayi sor 
    @Override
    public String getMessage() {
        return message;
    }
}
