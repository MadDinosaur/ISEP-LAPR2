package app.domain.adapter;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import static net.sourceforge.barbecue.BarcodeFactory.createUPCA;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class ExternalModuleBarcodeAdapter implements ExternalModuleBarcode {

    @Override
    public BufferedImage barcodeGenerator(String barcodeText) throws BarcodeException, OutputException {
        Barcode barcode = createUPCA(barcodeText);
        barcode.setPreferredBarHeight(100);
        return BarcodeImageHandler.getImage(barcode);
    }

    @Override
    public void saveBarcode(BufferedImage barcodeJPG, String barcodeText) throws IOException {
        File outputfile = new File("barcodes\\barcode" + barcodeText + ".jpg");
        ImageIO.write(barcodeJPG, "jpg", outputfile);
    }
}
