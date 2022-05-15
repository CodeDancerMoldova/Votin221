package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import sample.singleton.VotingService;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ControllerSecond implements Initializable {
    @FXML
    private ListView<String> lbl;

    @FXML
    private Label labelVotingForm;

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private Button submitVote;

    @FXML
    private RadioButton option1, option2;

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

    public void calculateVotes(ActionEvent actionEvent) {
        VotingForm votingForm = VotingService.getInstance()
                .getDatabaseProxy()
                .getDatabaseForVoteForms()
                .getDatabaseForVoteFormNerds()
                .get(lbl
                .getSelectionModel()
                .getSelectedItem());
        if (option1.isSelected()) {
            System.out.println(option1.getText());
            votingForm.getOptionList().get(0).incrementCountNrOfVotes();
        } else if (option2.isSelected()) {
            System.out.println(option2.getText());
            votingForm.getOptionList().get(1).incrementCountNrOfVotes();
        }
        labelVotingForm.setText(votingForm
                .toString());

        votingForm.setVoted(true);
    }

    public void deleteVoteForm(ActionEvent actionEvent){
        VotingService.getInstance()
                .getDatabaseProxy()
                .getDatabaseForVoteForms()
                .getDatabaseForVoteFormNerds().remove(lbl
                .getSelectionModel()
                .getSelectedItem());
       lbl.getItems().remove(lbl.getSelectionModel().getSelectedIndex());

    }


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int selected = lbl.getSelectionModel().getSelectedIndex();

        HashMap<String, VotingForm> databaseForVoteFormNerds = VotingService.getInstance()
                .getDatabaseProxy()
                .getDatabaseForVoteForms()
                .getDatabaseForVoteFormNerds();

        databaseForVoteFormNerds.forEach((key, value) ->
                lbl.getItems().add(value.getName()));

        lbl.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1)
                -> {
            String selectedItem = lbl
                    .getSelectionModel()
                    .getSelectedItem();
            labelVotingForm.setText(databaseForVoteFormNerds
                    .get(selectedItem)
                    .toString());
            option1.setText(databaseForVoteFormNerds
                    .get(selectedItem)
                    .getOptionList().get(0).getName());

            option2.setText(databaseForVoteFormNerds
                    .get(selectedItem)
                    .getOptionList().get(1).getName());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (databaseForVoteFormNerds.get(selectedItem).isVoted()) {
                            submitVote.setVisible(false);
                            option1.setDisable(true);
                            option2.setDisable(true);
                            break;
                        } else {

                            submitVote.setVisible(true);
                            option1.setDisable(false);
                            option2.setDisable(false);

                        }
                    }
                }
            }).start();

        });

        lbl.getSelectionModel().select(0);
    }

}
