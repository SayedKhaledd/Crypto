package lab1;

import java.util.Scanner;

public class Decryption {

    public static String decrypt(String text) {
        String s = "";
        Scanner scan = new Scanner(text);
        while (scan.hasNextLine()) {
            String temp = scan.nextLine();
            Scanner scan2 = new Scanner(temp);

            while (scan2.hasNext()) {
                temp = scan2.next().charAt(0) + "";
                s += temp;
            }
            s += "\n";


        }
        return s;
    }

}
