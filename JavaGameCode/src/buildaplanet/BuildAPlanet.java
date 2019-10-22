package buildaplanet;

import Elementos.SistemaSolar;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BuildAPlanet extends Application {
    public static ArrayList<SistemaSolar> sistemas = new ArrayList();
    
    @Override
    public void start(Stage primaryStage) {
        
        PaneOrganizer juego = new PaneOrganizer(primaryStage);       
 
        primaryStage.setTitle("Build a Planet");
        primaryStage.getIcons().add(new Image(BuildAPlanet.class.getResourceAsStream("/recursos/PantallaPrincipal/icono.png")));
        primaryStage.setScene(new Scene(juego.getRoot(), 1350, 723));
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
