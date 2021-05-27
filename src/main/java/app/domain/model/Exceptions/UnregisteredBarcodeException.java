package app.domain.model.Exceptions;

import java.util.NoSuchElementException;

public class UnregisteredBarcodeException extends NoSuchElementException {
    public UnregisteredBarcodeException() {
        super("There is no test associated with the sample barcode...");
    }
    public UnregisteredBarcodeException(String message) {
        super(message);
    }
}
