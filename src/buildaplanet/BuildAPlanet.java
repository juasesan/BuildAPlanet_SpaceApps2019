package buildaplanet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuildAPlanet extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        PaneOrganizer juego = new PaneOrganizer(primaryStage);       
        
        Scene scene = new Scene(juego.getRootMenu(), 300, 250);
        
        primaryStage.setTitle("Build a Planet");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
