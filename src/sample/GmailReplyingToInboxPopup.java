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
        controller.outboxData.add(tempEmail);
        int tempInt = Integer.parseInt(controller.sentCount.getText()) + 1;
        controller.sentCount.setText(tempInt + "");
        controller.listOutboxEmails.setItems(controller.outboxData);

        FXMLLoader inboxScene = new FXMLLoader(getClass().getResource("GmailInboxScene.fxml"));
        inboxScene.load();
        GmailInboxScene inboxController = inboxScene.getController();
        if (controller.getCurrentGmail().getGoogleAccount().getGmailAddress().equals(textReplyEmailReceiver.getText())) {
            tempEmail.writeToFile(controller.getCurrentGmail().getGoogleAccount().getUsername() + "'s inbox.txt");
            inboxController.inboxData.add(tempEmail);
            tempInt = Integer.parseInt(inboxController.inboxCount.getText()) + 1;
            inboxController.inboxCount.setText(tempInt + "");
            inboxController.listInboxEmails.setItems(inboxController.inboxData);
        }
        textReplyEmailReceiver.setText("");
        textReplyEmailSubject.setText("");
        textReplyEmailContent.clear();
    }
}
