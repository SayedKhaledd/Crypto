package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadAndWrite {
    public static String readFromFile(File plaintextFile) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scan = new Scanner(plaintextFile);
        while (scan.hasNextLine()) {
            text.append(scan.nextLine() + "\n");
        }

        return text.toString();
    }

    public static File writeToFile(String fileName,String text) throws IOException {
        File encryptedFile = new File("src\\"+fileName+".txt");
        if (!encryptedFile.createNewFile())
            System.out.println("couldn't create the file");
        PrintWriter op = new PrintWriter(encryptedFile);
        op.print(text);
        op.close();
        return encryptedFile;
    }

}
