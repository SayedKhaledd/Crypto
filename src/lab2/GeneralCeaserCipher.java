package lab2;

import lib.ReadAndWrite;

import java.io.File;
import java.io.IOException;

public class GeneralCeaserCipher {

    public static void main(String[] args) throws IOException {
        int key = 2;
        File plainTextFile = new File("src\\New Text Document.txt");
        String plainText = ReadAndWrite.readFromFile(plainTextFile);
        String encryptedText = encrypt(plainText, key);
        File encryptedTextFile = ReadAndWrite.writeToFile("encrypted", encryptedText);
        ReadAndWrite.writeToFile("decrypted", decrypt(ReadAndWrite.readFromFile(encryptedTextFile), key));
    }


    public static String encrypt(String text, int key) {
        StringBuilder s = new StringBuilder("");
        key += 97;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i), k;


            if (c == ' ' || c == '\n') {
                s.append(c);
                continue;
            }
            if (Character.isUpperCase(c)) {

                key = Character.toUpperCase(key);
                k = (char) (((key + c) - 65 * 2) % 26 + 65);

            } else {

                key = Character.toLowerCase(key);

                k = (char) (((key + c) - 97 * 2) % 26 + 97);
            }
            s.append(k);
        }
        return s.toString();
    }

    public static String decrypt(String text, int key) {
        StringBuilder s = new StringBuilder("");
        key += 97;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i), k;

            if (c == ' ' || c == '\n') {
                s.append(c);
                continue;
            }
            if (Character.isUpperCase(c)) {
                key = Character.toUpperCase(key);
                k = (char) ((c + 26 - key) % 26 + 65);

            } else {
                key = Character.toLowerCase(key);

                k = (char) ((c + 26 - key) % 26 + 97);
            }
            s.append(k);
        }
        return s.toString();
    }

    public static int getKey(String text) {
        return 0;
    }


}
