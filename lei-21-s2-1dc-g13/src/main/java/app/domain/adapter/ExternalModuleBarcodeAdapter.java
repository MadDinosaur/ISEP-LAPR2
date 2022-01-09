package app.domain.adapter;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static net.sourceforge.barbecue.BarcodeFactory.createUPCA;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class ExternalModuleBarcodeAdapter implements ExternalModuleBarcode {

    /**
     * creates BufferedImage of the barcode
     * @param barcodeText barcode digit code
     * @return barcode
     * @throws BarcodeException
     * @throws OutputException
     */
    @Override
    public BufferedImage barcodeGenerator(String barcodeText) throws BarcodeException, OutputException {
        Barcode barcode = createUPCA(barcodeText);
        barcode.setPreferredBarHeight(100);
        return BarcodeImageHandler.getImage(barcode);
    }

    /**
     * saves the barcode as a .jpg file in to a specific folder
     * @param barcodeJPG barcode image
     * @param barcodeText barcode digit code
     * @throws IOException
     */
    @Override
    public void saveBarcode(BufferedImage barcodeJPG, String barcodeText) throws IOException {
        File outputfile = new File("barcodes\\barcode" + barcodeText + ".jpg");
        ImageIO.write(barcodeJPG, "jpg", outputfile);
    }
}
