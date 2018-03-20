package ui_fx.clientUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Fresher on 20/03/2018.
 */
public class Scene implements Initializable {

    @FXML
    private Button button;

    @FXML
    private TextField showDateTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // do nothings here

        System.out.println();

    }


    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }


    public TextField getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(TextField showDateTime) {
        this.showDateTime = showDateTime;
    }

    public void showDateTime(ActionEvent event) {
        System.out.println("Btn clicked");
        showDateTime.setText(System.currentTimeMillis() / 1000L + "");
    }
}
