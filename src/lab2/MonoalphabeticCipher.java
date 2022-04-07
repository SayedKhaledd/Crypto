package lab2;

import lib.ReadAndWrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MonoalphabeticCipher {
    public static void main(String[] args) throws IOException {
        File plainTextFile = new File("src\\New Text Document.txt");
        String plainText = ReadAndWrite.readFromFile(plainTextFile);
        String encryptedText = encrypt(plainText);
        File encryptedTextFile = ReadAndWrite.writeToFile("encrypted", encryptedText);
        ReadAndWrite.writeToFile("decrypted", decrypt(ReadAndWrite.readFromFile(encryptedTextFile)));
        attack(encryptedText);
    }

    public static Character[] myChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static Character[] myCharsEncrypted = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o',
            'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k',
            'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};


    public static String encrypt(String text) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(myChars[i], myCharsEncrypted[i]);
        }
        return mapping(text, map).toString();
    }


    public static String decrypt(String text) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(myCharsEncrypted[i], myChars[i]);
        }

        return mapping(text, map).toString();
    }

    public static StringBuilder mapping(String text, Map<Character, Character> map) {
        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i), k;


            if (c == ' ' || c == '\n') {
                s.append(c);
                continue;
            }
            k = map.get(c);

            if (Character.isUpperCase(c)) {
                k = Character.toUpperCase(k);
            } else {
                k = Character.toLowerCase(k);

            }
            s.append(k);
        }
        return s;
    }

    public static void attack(String text) throws FileNotFoundException {
        StringBuilder s = new StringBuilder("");
        File plainTextFile = new File("src\\New Text Document.txt");
        String plainText = ReadAndWrite.readFromFile(plainTextFile);
        File encrytedTextFile = new File("src\\encrypted.txt");
        String encryptedText = ReadAndWrite.readFromFile(encrytedTextFile);
        Character[] c = new Character[26];
        for (int i = 0; i < 26; i++) {
            if (Character.isLetter(plainText.charAt(i)))
                c[plainText.charAt(i) - 97] = encryptedText.charAt(i);
        }

        for (int i = 0; i < 26; i++) {
            System.out.print(c[i]);
        }


    }
}
