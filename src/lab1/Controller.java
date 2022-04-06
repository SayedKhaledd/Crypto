package lab1;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Controller {
    @FXML
    public TextField plaintextField;
    @FXML
    TextArea textArea;

    File file;
    String decrptedarea = "";

    @FXML
    public void browse() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterText = new FileChooser.ExtensionFilter("text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilterText);
        file = fileChooser.showOpenDialog(Main.myStage);
        plaintextField.setText(file.getName());

    }

    @FXML
    public void open() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) desktop.open(file);
    }

    @FXML
    public void encrypt() throws FileNotFoundException {

        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            decrptedarea += scan.nextLine() + "\n";
        }
        textArea.setText(decrptedarea);
    }

    @FXML
    public void decrypt() throws IOException {
        decrptedarea = textArea.getText();
        String ciphertext = Decryption.decrypt(decrptedarea);
        File decrptFile = new File(file.getParentFile().getAbsolutePath() + "\\ciphertext.txt");
        decrptFile.createNewFile();
        PrintWriter op = new PrintWriter(decrptFile);
        op.print(ciphertext);
        op.close();
    }

    @FXML
    public void clear() {
        decrptedarea = "";
        textArea.setText("");
    }
}
