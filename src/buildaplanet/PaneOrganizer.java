package buildaplanet;

import static buildaplanet.Util.dialogoMaterial;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaneOrganizer {
    
    private Stage primaryStage;
    private StackPane root;
    
    
    public PaneOrganizer(Stage primaryStage){
        this.primaryStage = primaryStage;
       
        System.out.println(getRutaCssFile());
        System.out.println(this.primaryStage.getScene());
        if(this.primaryStage.getScene() != null) {
            System.out.println("HOLA");
            this.primaryStage.getScene().getStylesheets().clear();
            this.primaryStage.getScene().getStylesheets().add(getRutaCssFile());
        }
        root=setMenu();
        
    }
    
    /**
     * @return Escena del menu principal
     */  
    public StackPane setMenu(){
        StackPane rootMenu = new StackPane();
        BackgroundImage myBI= new BackgroundImage(new Image("recursos/PantallaPrincipal/fondoPrincipal.gif",1280,720,true,true),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
        //then you set to your node
        rootMenu.setBackground(new Background(myBI));
        //Desarrollo del menu principal
        
        VBox center=new VBox();
        GridPane cells=new GridPane();
        cells.setAlignment(Pos.CENTER);
        cells.setVgap(30);
        //cells.setVgap(20);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(68);
        
        ImageView img = new ImageView(new Image("/recursos/PantallaPrincipal/logoNasa2.png", 480, 203, true, true));
        
        center.getChildren().addAll(img,cells);
        
        
        JFXButton play=new JFXButton("Jugar");
        JFXButton close=new JFXButton("Salir");
        JFXButton starGame=new JFXButton("Crear sistema");
        starGame.getStyleClass().add("jfx-button-dialog");
        starGame.setOnAction((t)->{this.primaryStage.getScene().setRoot(new GamePane(this.primaryStage).getRoot());});
        close.setOnAction((t)->{this.primaryStage.close();});
        play.setOnAction((t)->{
            
            dialogoMaterial((Pane)root,"Configuración del sistema",(Node)getSubPanelInicio(),starGame);
        //Pane panel,String titulo, Node cuerpo, JFXButton boton
            
        });
        
        
        cells.addRow(0, play);
        cells.addRow(1, close);
        
        rootMenu.getChildren().add(center);
        rootMenu.setFocusTraversable(true);
        return rootMenu;
    }

    public StackPane getRoot() {
        return root;
    }
    
    public String getRutaCssFile(){
        return PaneOrganizer.class.getResource("/recursos/panelPrincipal.css").toExternalForm();
    }
    
    public VBox getSubPanelInicio(){
       
        VBox systemForm=new VBox();
        systemForm.setAlignment(Pos.CENTER);
        systemForm.setSpacing(20);
        systemForm.setPadding(new Insets(10));
        
        VBox starForm=new VBox();
        starForm.setAlignment(Pos.CENTER);
        starForm.setSpacing(20);
        
        JFXTextField systemName=new JFXTextField("Ingrese nombre de su sistema");
        
        Label starLabel=new Label("Datos de estrella principal");
        
        JFXTextField dato1=new JFXTextField("dato1");
        JFXTextField dato2=new JFXTextField("dato1");
        JFXTextField dato3=new JFXTextField("dato1");
        JFXTextField dato4=new JFXTextField("dato1");
        starForm.getChildren().addAll(dato1,dato2,dato3,dato4);
        
        systemForm.getChildren().addAll(systemName,starLabel,starForm);
        
        return systemForm;
    }
    
}
