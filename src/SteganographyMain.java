//This program is a java implementation of steganography of a picture.
//Its goal is to encrypt/decrypt text into a picture, this program can also be used to decode said image as well.
//Developed on Linux

import Decode.Decode;
import Encode.Encode;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SteganographyMain {
    private static int Option;
    private static String FilePath;
    private static String Text;

    private static void EncodeData() throws IOException {
        Encode encode = new Encode();
        encode.SetupEncoder(FilePath, Text);
    }

    private static void DecodeData() throws IOException {
        Decode decode = new Decode();
        decode.SetupDecoder(FilePath);
    }


    public static void main(String[] args) throws IOException {
        if (args.length == 3) {
            Option = Integer.parseInt(args[1]);
            FilePath = args[2];

            if (Option == 1) {
                Text = args[3];
            }
        } else {
            Scanner in = new Scanner(System.in);

            System.out.println("java -jar Steganography [Encode(1)/Decode(2)] [Filepath] [Text to encrypt (if encode is selected)]");
            System.out.print("Encode <1> or Decode <2>: ");
            Option = in.nextInt(); in.nextLine(); //Needed for flushing purposes

            System.out.print("Enter Filepath: ");
            FilePath = in.nextLine();
            checkFileExist();

            if (Option == 1) {
                System.out.print("Enter Text to Encrypt: ");
                Text = in.nextLine();
            }
        }

        if (Option == 1) {
            EncodeData();
        }

        if (Option == 2) {
            DecodeData();
        }
    }

    private static void checkFileExist() {
        File pictureFile = new File(FilePath);
        while (!pictureFile.isFile()) {
            System.out.println("ERROR: FILE DOES NOT EXIST!");
            Scanner in = new Scanner(System.in);
            System.out.print("Enter new File Location: ");
            FilePath = in.nextLine();
            pictureFile = new File(FilePath);
        }
    }
}
