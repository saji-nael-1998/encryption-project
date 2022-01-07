import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class indexController implements Initializable {

//encryption 	
	@FXML
	private TextArea plainTAEN;

	@FXML
	private TextArea cipherTAEN;
	@FXML
	private ComboBox<String> keyEN;

	public void encryptePlainText(ActionEvent e) {
		try {
			String key = keyEN.getSelectionModel().getSelectedItem().toString();
			String plaintext = plainTAEN.getText().toString();
			Encryption encryption = new Encryption();
			cipherTAEN.setText(encryption.encryptePlaintext(plaintext, key));
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("you must select a key !!");
			alert.show();
		}
	}

	public void encrypteFile(ActionEvent e) {
		try {
			File file = openFile();
			if (file != null) {
				String plaintext = readFile(file);
				plainTAEN.setText(plaintext);
				String key = keyEN.getSelectionModel().getSelectedItem().toString();
				Encryption encryption = new Encryption();
			
				cipherTAEN.setText(encryption.encryptePlaintext(plaintext, key));
				
			}
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("you must select a key !!");
			alert.show();
		}
	}

	// encryption
	@FXML
	private TextArea plainTADE;

	@FXML
	private TextArea cipherTADE;
	@FXML
	private ComboBox<String> keyDE;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		keyEN.getItems().add("preorder");
		keyEN.getItems().add("inorder");
		keyEN.getItems().add("postorder");

		keyDE.getItems().add("preorder");
		keyDE.getItems().add("inorder");
		keyDE.getItems().add("postorder");

	}

	public void decrypteCiphertext(ActionEvent e) {
		try {
			String key = keyDE.getSelectionModel().getSelectedItem().toString();
			String ciphertext = cipherTADE.getText().toString();
			Encryption encryption = new Encryption();
			plainTADE.setText(encryption.decrypteCiphertext(ciphertext, key));
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("you must select a key !!");
			alert.show();
		}
	}

	public void decrypteFile(ActionEvent e) {
		try {
			File file = openFile();
			if (file != null) {
				String ciphertext = readFile(file);
				cipherTADE.setText(ciphertext);
				String key = keyDE.getSelectionModel().getSelectedItem().toString();
				Encryption encryption = new Encryption();
				plainTADE.setText(encryption.decrypteCiphertext(ciphertext, key));
			}
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("you must select a key !!");
			alert.show();
		}
	}

	static Stage stage = new Stage();

	public File openFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		return selectedFile;
	}

	public String readFile(File file) {
		String text = "";
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				text += scanner.nextLine();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return text;
	}
}
