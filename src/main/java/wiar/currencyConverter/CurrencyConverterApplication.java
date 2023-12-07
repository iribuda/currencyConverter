package wiar.currencyConverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wiar.currencyConverter.logic.Currency;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CurrencyConverterApplication extends Application {
    private Stage primaryStage;
    private ResourceBundle bundle;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("currencyConverter.fxml"));

        Locale locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle("resources", locale);

        // Set the resource bundle to the FXMLLoader
        loader.setResources(bundle);

        Parent root = loader.load();
        CurrencyConverterController controller = loader.getController();
        controller.setApp(this); // Set the reference to the application in the controller
        controller.setResourceBundle(bundle);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Set the localized title for the primaryStage
        primaryStage.setTitle(bundle.getString("title.app"));
        primaryStage.show();
    }

    // Method to change the locale dynamically
    public void changeLocale() {
        bundle = ResourceBundle.getBundle("resources", Locale.getDefault());
        primaryStage.setTitle(bundle.getString("title.app"));
    }
}