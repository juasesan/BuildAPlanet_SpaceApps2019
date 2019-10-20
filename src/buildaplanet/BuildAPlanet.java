package buildaplanet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuildAPlanet extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        PaneOrganizer juego = new PaneOrganizer(primaryStage);       
 
        primaryStage.setTitle("Build a Planet");
        
        primaryStage.setScene(new Scene(juego.getRoot(), 1280, 720));
        primaryStage.getScene().getStylesheets().add(getRutaCssFile());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public String getRutaCssFile(){
        return BuildAPlanet.class.getResource("/recursos/panelPrincipal.css").toExternalForm();
    }
    
}
