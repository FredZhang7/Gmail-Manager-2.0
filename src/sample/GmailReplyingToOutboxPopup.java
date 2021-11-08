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

    // this method interacts with the OutboxScene controller to 1st record the Email and 2nd add to the outbox
    public void sendOutboxReply(ActionEvent actionEvent) throws IOException {
        FXMLLoader outboxScene = new FXMLLoader(getClass().getResource("GmailOutboxScene.fxml"));
        outboxScene.load();
        GmailOutboxScene controller = outboxScene.getController();
        Email tempEmail = new Email(textReplyEmailReceiver.getText(), textReplyEmailSubject.getText(), textReplyEmailContent.getText(), true);
        tempEmail.writeToFile(controller.getCurrentGmail().getGoogleAccount().getUsername() + "'s outbox.txt");
        controller.getCurrentGmail().addToOutbox(tempEmail);

        if (controller.getCurrentGmail().getGoogleAccount().getGmailAddress().equals(textReplyEmailReceiver.getText())) {
            tempEmail.writeToFile(controller.getCurrentGmail().getGoogleAccount().getUsername() + "'s inbox.txt");
            controller.getCurrentGmail().addToInbox(tempEmail);
        }
        textReplyEmailReceiver.setText("");
        textReplyEmailSubject.setText("");
        textReplyEmailContent.clear();
    }
}
