package program;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import program.controllers.PersonEditingController;
import program.controllers.PersonOverviewController;
import program.models.Person;
import program.utils.RestAPI;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    private RestAPI apiSession;

    public Main() {
        apiSession = new RestAPI();
        this.updateTable();
    }

    public void updateTable() {
        personData.clear();
        personData.addAll(apiSession.getPersons());
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Address Application");

        initRootLayout();

        showPersonOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/root.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/main.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);

            PersonOverviewController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/personEditDialog.fxml"));
            AnchorPane personEditDialog = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(personEditDialog);
            dialogStage.setScene(scene);

            PersonEditingController controller = loader.getController();
            controller.setDialogStage(dialogStage, this, apiSession);
            controller.setPerson(person);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public RestAPI getApiSession() {
        return apiSession;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
