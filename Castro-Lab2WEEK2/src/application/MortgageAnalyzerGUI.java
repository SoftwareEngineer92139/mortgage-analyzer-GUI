// MortgageAnalyzer.java
// MortgageAnalyzer.java Main app class that loads and displays the Mortgage Analyzer's GUI
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MortgageAnalyzerGUI extends Application{
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MortgageAnalyzerGUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Welcome to your Mortgage Analyzer App!");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
