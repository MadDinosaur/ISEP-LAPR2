package app.domain.model.exceptions;

import java.util.NoSuchElementException;

public class UnregisteredBarcodeException extends NoSuchElementException {
    public UnregisteredBarcodeException() {
        super("There is no test associated with the sample barcode!");
    }
    public UnregisteredBarcodeException(String message) {
        super(message);
    }
}
