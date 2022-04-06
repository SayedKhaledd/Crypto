package lab2;

import java.io.File;
import java.io.IOException;

public class AffineCeaserCipher {

    public static void main(String[] args) throws IOException {
        int key1 = 17, key2 = 20;
        File plainTextFile = new File("src\\New Text Document.txt");
        String plainText = ReadAndWrite.readFromFile(plainTextFile);
        String encryptedText = encrypt(plainText, key1, key2);
        File encryptedTextFile = ReadAndWrite.writeToFile("encrypted", encryptedText);
        ReadAndWrite.writeToFile("decrypted", decrypt(ReadAndWrite.readFromFile(encryptedTextFile), key1, key2));
        attack(encryptedText);

    }


    public static String encrypt(String text, int key1, int key2) {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i), k;


            if (c == ' ' || c == '\n') {
                s.append(c);
                continue;
            }
            if (Character.isUpperCase(c)) {

                k = (char) (((c - 65) * key1 + key2) % 26 + 65);


            } else {

                k = (char) (((c - 97) * key1 + key2) % 26 + 97);
            }
            s.append(k);
        }
        return s.toString();
    }

    public static String decrypt(String text, int key1, int key2) {

        StringBuilder s = new StringBuilder("");
        int invKey1 = 0;
        for (int i = 0; i < 26; i++) {
            if ((key1 * i) % 26 == 1) {
                invKey1 = i;
            }
        }
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i), k;
            if (c == ' ' || c == '\n') {
                s.append(c);
                continue;
            }
            if (Character.isUpperCase(c)) {
                k = (char) ((invKey1 * ((c + 65 - key2)) % 26) + 65);

            } else {
                int x = c - 97 - key2;
                if (x < 0)
                    x += 26;
                k = (char) (((invKey1 * x) % 26) + 97);

            }
            s.append(k);
        }
        return s.toString();
    }

    public static int getKey(String text) {
        return 0;
    }

    public static void attack(String text) throws IOException {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                s.append("key =  " + i + " , " + j + "  \n" + decrypt(text, i, j) + "\n \n");
            }
        }
        ReadAndWrite.writeToFile("decryptedattack", s.toString());

    }


}
