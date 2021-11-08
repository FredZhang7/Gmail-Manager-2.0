package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.*;

public class GmailReplyingToInboxPopup {
    public Label textReplyEmailReceiver;
    public Label textReplyEmailSubject;
    public VBox vBoxReplyEmailContent;
    public TextArea textReplyEmailContent = new TextArea();   // this isn't declared in any FXML file

    // completed => reviewed
    // this method interacts with the OutboxScene controller to 1st record the Email, 2nd add to the outbox, and 3rd if the receiver is the sender, then add to the inbox
    public void sendInboxReply(ActionEvent actionEvent) throws IOException {
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
