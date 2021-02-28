package program.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import program.models.Person;
import program.utils.DateUtil;
import javafx.scene.control.TextField;

import java.awt.*;

public class PersonEditingController {
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField cityText;
    @FXML
    private TextField streetText;
    @FXML
    private TextField postalCodeText;
    @FXML
    private TextField birthdayText;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    @FXML
    public void initialize(){
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;

        firstNameText.setText(person.getFirstName());
        lastNameText.setText(person.getLastName());
        cityText.setText(person.getCity());
        streetText.setText(person.getStreet());
        postalCodeText.setText(Integer.toString(person.getPostalCode()));
        birthdayText.setText(DateUtil.format(person.getBirthDay()));
        birthdayText.setPromptText("dd.mm.yyyy");
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (firstNameText.getText() == null || firstNameText.getText().length() == 0) {
            errorMessage += "The 'First Name' field is empty!\n";
        }
        if (lastNameText.getText() == null || lastNameText.getText().length() == 0) {
            errorMessage += "The 'Last Name' field is empty!\n";
        }
        if (cityText.getText() == null || cityText.getText().length() == 0) {
            errorMessage += "The 'City' field is empty!\n";
        }
        if (streetText.getText() == null || streetText.getText().length() == 0) {
            errorMessage += "The 'Street' field is empty!\n";
        }
        if (postalCodeText.getText() == null || postalCodeText.getText().length() == 0) {
            errorMessage += "The 'Postal Code' field is empty!\n";
        } else {
            try {
                Integer.parseInt(postalCodeText.getText());
            } catch (NumberFormatException e) {
                errorMessage += "The 'Postal Code' field is empty!\n";
            }
        }
        if (birthdayText.getText() == null || birthdayText.getText().length() == 0) {
            errorMessage += "The 'Birthday' field is empty!\n";
        } else {
            if (!DateUtil.validDate(birthdayText.getText())) {
                errorMessage += "The birthday was entered incorrectly. Please, use the format 'dd.mm.yyyy'!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Please, correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk(){
        if (isInputValid()) {
            person.setFirstName(firstNameText.getText());
            person.setLastName(lastNameText.getText());
            person.setCity(cityText.getText());
            person.setStreet(streetText.getText());
            person.setPostalCode(Integer.parseInt(postalCodeText.getText()));
            person.setBirthDay(DateUtil.parse(birthdayText.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
