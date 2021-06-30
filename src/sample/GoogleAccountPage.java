package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;

public class GoogleAccountPage {

    public Label textGetGoogleAccountFullName;
    public Label textGetGmailAddress;
    public Label textGetGmailPassword;
    private static Stage mainStage;

    public static void assignStage(Stage stage) {
        mainStage = stage;
    }

    public void playMiniGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader diceGameLoader = new FXMLLoader(getClass().getResource("DiceGame.fxml"));
        Parent root = diceGameLoader.load();
        Stage newStage = new Stage();
        newStage.setTitle("Dice Game");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    public void googleAccountSignOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loginAgain = new FXMLLoader(getClass().getResource("GmailLoginEmail.fxml"));
        Parent root = loginAgain.load();
        mainStage.setTitle("Login Gmail");
        mainStage.setScene(new Scene(root));
    }

    public void returnToGmail(ActionEvent actionEvent) {
        Main.setLatestGmailScene();
        Main.setLatestSceneTitle();
    }
}
