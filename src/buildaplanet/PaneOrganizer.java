package buildaplanet;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PaneOrganizer {
    private Stage primaryStage;
    
    PaneOrganizer(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    
    /**
     * @return Escena del menu principal
     */  
    public Pane getRootMenu(){
        Pane rootMenu = new Pane();
        //Desarrollo del menu principal
        
        return rootMenu;
    }
    
    /**
     * @return Escena del juego
     */
    public Pane getRootJuego(){
        Pane rootJuego = new Pane();
        //Desarrollo del juego
        
        return rootJuego;
    }
}
