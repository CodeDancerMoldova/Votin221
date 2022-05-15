package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.singleton.VotingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Controller {

    @FXML
    private TextField nameFieldId;

    @FXML
    private TextField descriptionFieldId;

    @FXML
    private TextField option1Id;

    @FXML
    private TextField option2Id;

    @FXML
    private Button submitVoteForm;

    private Stage stage;

    private Scene scene;

    private Parent root;

    private VotingService votingService = VotingService.getInstance();

    public void switchToScene1(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void createVotingForm(ActionEvent actionEvent) {

        VotingForm votingForm = new VotingForm.VotingFormBuilder()
                .description(descriptionFieldId.getText())
                .name(nameFieldId.getText())
                .build();

        votingService.createVotingForm(votingForm,option1Id.getText(),option2Id.getText());

    }



}
