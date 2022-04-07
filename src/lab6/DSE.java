package lab6;

import lib.ReadAndWrite;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class DSE {
    public static void main(String[] args) throws IOException {
        DSE.keyGeneration("133457799BBCDFF1");
    }
    static char[] key = new char[64];
    static char[] keyPc1 = new char[56];
    static char[][] keyLShifts = new char[16][28];
    static char[][] keyRShifts = new char[16][28];
    static char[][] keyLR = new char[16][56];
    static char[][] keyPc2 = new char[16][48];
    static final char[] PC1 = {57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4};
    static final char[] LEFT_SHIFTS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    static final char[] PC2 = {14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32};

    public static void keyGeneration(String hexKey) throws IOException {
        key = hexToBinary(hexKey);
        pc1(key);
        char[] keyL = new char[28];
        char[] keyR = new char[28];

        leftKey(keyL, keyPc1);
        rightKey(keyR, keyPc1);

        leftKeyShift(keyLShifts, keyL);
        rightKeyShift(keyRShifts, keyR);
        leftRightConcKeys(keyLShifts, keyRShifts);
        pc2(keyLR);
        printKeys(keyPc2);

    }

    public static char[] hexToBinary(String hexKey) {
        BigInteger value = new BigInteger(hexKey, 16);

        String s = value.toString(2);
        StringBuilder ss = new StringBuilder(s);
        int l = 64 - ss.length();
        for (int i = 0; i < l; i++) {
            ss.insert(0, 0);
        }
        return ss.toString().toCharArray();
    }

    public static void pc1(char[] key) {
        for (int i = 0; i < PC1.length; i++) {
            keyPc1[i] = key[PC1[i] - 1];

        }
        System.out.println("key PC1 " + Arrays.toString(keyPc1));

    }

    public static void leftKey(char[] keyL, char[] key) {
        System.arraycopy(key, 0, keyL, 0, key.length / 2);
        System.out.println("key left is " + Arrays.toString(keyL));

    }

    public static void rightKey(char[] keyR, char[] key) {
        if (key.length - key.length / 2 >= 0)
            System.arraycopy(key, key.length / 2, keyR, 0, key.length - key.length / 2);
        System.out.println("key left is " + Arrays.toString(keyR));

    }

    public static void leftKeyShift(char[][] keyLShifts, char[] key) {

        int numOfLeftShifts = LEFT_SHIFTS[0];
        for (int j = 0; j < numOfLeftShifts; j++) {
            char c = key[0];
            System.arraycopy(key, 1, keyLShifts[0], 0, key.length - 1);
            keyLShifts[0][key.length - 1] = c;

        }


        for (int i = 1; i < keyLShifts.length; i++) {
            numOfLeftShifts = LEFT_SHIFTS[i];
            for (int j = 0; j < numOfLeftShifts; j++) {
                char c = keyLShifts[i - 1][0];
                System.arraycopy(keyLShifts[i - 1], 1, keyLShifts[i], 0, keyLShifts[i - 1].length - 1);
                keyLShifts[i][key.length - 1] = c;

            }
        }
        System.out.println("key left/right shifts 0:" + Arrays.toString(keyLShifts[0]));
        System.out.println("key left/right shifts 1:" + Arrays.toString(keyLShifts[0]));


    }

    public static void rightKeyShift(char[][] keyRShifts, char[] key) {
        leftKeyShift(keyRShifts, key);

    }

    public static void leftRightConcKeys(char[][] keyL, char[][] keyR) {
        for (int i = 0; i < keyLR.length; i++) {
            System.arraycopy(keyL[i], 0, keyLR[i], 0, keyLR[i].length / 2);
            if (keyLR[i].length - 1 - keyLR[i].length / 2 >= 0)
                System.arraycopy(keyR[i], 0, keyLR[i], keyLR[i].length / 2, keyLR[i].length - keyLR[i].length / 2);
        }

        System.out.println("Key LR " + Arrays.toString(keyLR[0]));
        System.out.println("Key LR " + Arrays.toString(keyLR[1]));

    }

    public static void pc2(char[][] keyLR) {
        for (int k = 0; k < keyLR.length; k++) {
            for (int i = 0; i < PC2.length; i++) {
                keyPc2[k][i] = keyLR[k][PC2[i] - 1];

            }
        }

        System.out.println("key PC2 " + Arrays.toString(keyPc2[0]));
        System.out.println("key PC2 " + Arrays.toString(keyPc2[1]));

    }

    public static void printKeys(char[][] keyPc2) throws IOException {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < keyPc2.length; i++) {
            char[] chars = keyPc2[i];
            s.append("K").append(i + 1).append("= ");
            for (int j = 0; j < chars.length; j++) {
                if (j % 6 == 0) {
                    s.append(" ");
                }

                s.append(chars[j]);

            }
            s.append("\n");
        }
        writeToFile("myKeys", s.toString());
    }

    public static File writeToFile(String fileName, String text) throws IOException {
        File encryptedFile = new File("src\\"+fileName+".txt");
        if (!encryptedFile.createNewFile())
            System.out.println("couldn't create the file");
        PrintWriter op = new PrintWriter(encryptedFile);
        op.print(text);
        op.close();
        return encryptedFile;
    }
}
