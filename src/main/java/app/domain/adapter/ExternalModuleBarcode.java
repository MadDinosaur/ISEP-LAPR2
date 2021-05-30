package app.domain.adapter;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ExternalModuleBarcode {
    BufferedImage barcodeGenerator(String barcodeText) throws BarcodeException, OutputException;
    void saveBarcode(BufferedImage barcodeJPG, String barcodeText) throws IOException;
}
