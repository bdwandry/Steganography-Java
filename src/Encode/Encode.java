package Encode;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Encode {
    private String FilePath;
    private String TextToEncrypt;
    private EncodeObj [][] ImageData;


    public void SetupEncoder(String filePath, String text) throws IOException {
        FilePath = filePath;
        TextToEncrypt = text;
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

        //For testing purposes
//        for (int y = 0; y < image.getHeight(); y++) {
//            for (int x = 0; x < image.getWidth(); x++) {
//                System.out.println(ImageData[y][x].getR());
//                System.out.println(ImageData[y][x].getG());
//                System.out.println(ImageData[y][x].getB());
//            }
//        }
        encodeImageData(image.getHeight(), image.getWidth());
    }

    private void encodeImageData(int imageHeight, int imageWidth) throws IOException {
        //Do stuff here later

        WriteOutImage(imageHeight, imageWidth);
    }

    private void WriteOutImage(int imageHeight, int imageWidth) throws IOException {
        BufferedImage EncodedImage = new BufferedImage(imageWidth, imageHeight, TYPE_INT_RGB);

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                int RGB = ImageData[y][x].getR();
                RGB = (RGB << 8) + ImageData[y][x].getG();
                RGB = (RGB << 8) + ImageData[y][x].getB();

                EncodedImage.setRGB(x, y, RGB);
            }
        }

        ImageIO.write(EncodedImage, "png", new File("test.png"));
    }
}
