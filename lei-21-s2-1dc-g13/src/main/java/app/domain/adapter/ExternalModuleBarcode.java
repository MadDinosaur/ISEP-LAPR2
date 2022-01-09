package app.domain.adapter;

import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public interface ExternalModuleBarcode {
    BufferedImage barcodeGenerator(String barcodeText) throws BarcodeException, OutputException;
    void saveBarcode(BufferedImage barcodeJPG, String barcodeText) throws IOException;
}
