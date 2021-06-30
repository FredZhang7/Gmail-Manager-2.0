package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.*;

public class GmailReplyingToOutboxPopup {
    public Label textReplyEmailReceiver;
    public Label textReplyEmailSubject;
    public VBox vBoxReplyEmailContent;
    public TextField textReplyEmailContent;

    // completed => reviewed
    // this method interacts with the OutboxScene controller to 1st record the Email and 2nd add to the outbox
    public void sendOutboxReply(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("GmailOutboxScene.fxml"));
        GmailOutboxScene controller = fxmlLoader.getController();
        Email tempEmail = new Email(textReplyEmailReceiver.getText(), textReplyEmailSubject.getText(), textReplyEmailContent.getText(), true);
        tempEmail.writeToFile(controller.getCurrentGmail().getGoogleAccount().getUsername() + "'s outbox.txt");
        controller.outboxData.add(tempEmail);
        int tempInt = Integer.parseInt(controller.sentCount.getText()) + 1;
        controller.sentCount.setText(tempInt + "");
        controller.listOutboxEmails.setItems(controller.outboxData);
        textReplyEmailReceiver.setText("");
        textReplyEmailSubject.setText("");
        textReplyEmailContent.clear();
    }
}
