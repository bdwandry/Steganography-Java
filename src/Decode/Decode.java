package Decode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Decode {
    private String FilePath;

    public void SetupDecoder(String filePath) throws IOException {
        FilePath = filePath;
        DecodeImage();
    }

    private void DecodeImage() throws IOException {
        File pictureFile = new File(FilePath);
        BufferedImage image = ImageIO.read(pictureFile);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                Color getRGBValue = new Color(pixel);

                if ((getRGBValue.getRed() == 0) && (getRGBValue.getGreen() == 0) && (getRGBValue.getBlue() == 0)) {
                    System.exit(1);
                }

                System.out.print((char) getRGBValue.getRed());
                System.out.print((char) getRGBValue.getGreen());
                System.out.print((char) getRGBValue.getBlue());
            }
        }
    }
}
