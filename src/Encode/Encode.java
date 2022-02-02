package Encode;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Encode {
    private String FilePath;
    private String TextToEncode;
    private EncodeObj [][] ImageData;


    public void SetupEncoder(String filePath, String text) throws IOException {
        FilePath = filePath;
        TextToEncode = text;
        LoadImage();
    }

    private void LoadImage() throws IOException {
        File pictureFile = new File(FilePath);
        BufferedImage image = ImageIO.read(pictureFile);
        ImageData = new EncodeObj[image.getHeight()][image.getWidth()];

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                Color getRGBValue = new Color(pixel);
                EncodeObj encodeObj = new EncodeObj();

                encodeObj.setR(getRGBValue.getRed());
                encodeObj.setG(getRGBValue.getGreen());
                encodeObj.setB(getRGBValue.getBlue());

                ImageData[y][x] = encodeObj;
            }
        }

        encodeImageData(image.getHeight(), image.getWidth());
    }

    private void encodeImageData(int imageHeight, int imageWidth) throws IOException {
        //Sets RGB Value to ASCII Character
        int XIndex = 0;
        int YIndex = 0;
        int rgbIndex = 0; //Keeps track of Sub-Pixel Information
        for (int i = 0; i < TextToEncode.length(); i++) {
            char singleCharacter = TextToEncode.charAt(i);
            int asciiVal = (int) singleCharacter;

            if (YIndex == imageWidth) {
                XIndex++;
            }

            switch (rgbIndex) {
                case 0: ;
                    ImageData[YIndex][XIndex].setR(asciiVal);
                    rgbIndex = 1;
                    break;
                case 1:
                    ImageData[YIndex][XIndex].setG(asciiVal);
                    rgbIndex = 2;
                    break;
                case 2:
                    ImageData[YIndex][XIndex].setB(asciiVal);
                    rgbIndex = 0;
                    XIndex++;
                    break;
            }
        }

        XIndex++;
        ImageData[YIndex][XIndex].setR(0);
        ImageData[YIndex][XIndex].setG(0);
        ImageData[YIndex][XIndex].setB(0);

        WriteOutImage(imageHeight, imageWidth);
    }

    private void WriteOutImage(int imageHeight, int imageWidth) throws IOException {
        BufferedImage EncodedImage = new BufferedImage(imageWidth, imageHeight, TYPE_INT_RGB);

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                System.out.println("R: " + ImageData[y][x].getR() + "; G: " + ImageData[y][x].getG() + "; B: " + ImageData[y][x].getB());
                int RGB = ImageData[y][x].getR();
                RGB = (RGB << 8) + ImageData[y][x].getG();
                RGB = (RGB << 8) + ImageData[y][x].getB();

                EncodedImage.setRGB(x, y, RGB);
            }
        }

        ImageIO.write(EncodedImage, "png", new File("test.png"));
    }
}
