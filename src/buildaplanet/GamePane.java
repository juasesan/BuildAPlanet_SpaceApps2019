/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildaplanet;

import static buildaplanet.Util.dialogoMaterial;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
/**
 *
 * @author Josue
 */
public class GamePane {
    
    private Stage primaryStage;
    private BorderPane root;
    private StackPane lastRoot;
    private StackPane gameDisplay;
    private VBox planetBox;
    private Image img;

    private ScrollPane scrollP;
    private Random rd;
    private Point2D origen;

    public GamePane() {
    }


    public GamePane(Stage primaryStage,ImageView star) {

        this.primaryStage = primaryStage;
        //this.root = root;
        this.root=new BorderPane();
        this.gameDisplay=new StackPane();
        this.lastRoot=(StackPane)primaryStage.getScene().getRoot();
        this.rd=new Random();
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.origen=new Point2D(screenSize.getWidth()/2f,screenSize.getHeight()/2f);
        this.scrollP=new ScrollPane();
        planetBox=setPlanetsBox();
        scrollP.setContent(planetBox);
        root.setLeft(scrollP);
        root.setBottom(setToolBox());
        //gameDisplay.getChildren().add(getSky());
        root.setCenter(gameDisplay);
        star.setLayoutX(origen.getX());
        star.setLayoutY(origen.getY());
        gameDisplay.getChildren().add(star);
        
    }
    
    public ImageView getSky(){
        InputStream rutaStream=getClass().getResourceAsStream("/recursos/PantallaPrincipal/fondoJuego.jpg");
        this.img = new Image(rutaStream);
        ImageView img=new ImageView(this.img);
        
        return img;
    }
    
    public VBox setPlanetsBox(){
        
        VBox planetBox=new VBox();
        planetBox.getChildren().add(new Label("Planetas"));
        planetBox.setAlignment(Pos.CENTER);
        planetBox.setSpacing(10);
        planetBox.setPadding(new Insets(5));
        return planetBox;
    }
    
    public HBox setToolBox(){
        
        HBox toolBox=new HBox();
        toolBox.setAlignment(Pos.CENTER);
        toolBox.setSpacing(10);
        toolBox.setPadding(new Insets(5));
        
        JFXButton volver=new JFXButton("Menú principal");
        volver.setOnAction((y)->{this.primaryStage.getScene().setRoot(lastRoot);});
        JFXButton addPlanetHandle=new JFXButton("Añadir planeta");
        addPlanetHandle.setOnAction((t)->{
            //logica que anade el planeta al sistema
            this.planetBox.getChildren().add(getEntity());
        });
        
        JFXButton addPlanet=new JFXButton("Añadir planeta");
        
        addPlanet.setOnAction((t)->{
            dialogoMaterial(gameDisplay,"Configuración del planeta",(Node)getPlanetForm(),addPlanetHandle);
        //Pane panel,String titulo, Node cuerpo, JFXButton boton
            
        });
        JFXButton resumeSim=new JFXButton("|>");
        JFXButton pauseSim=new JFXButton("||");
        toolBox.getChildren().addAll(volver,addPlanet,new Separator(Orientation.VERTICAL),resumeSim,pauseSim);
        return toolBox;
    }
    
    public VBox getPlanetForm(){
       
        VBox systemPlanet=new VBox();
        systemPlanet.setAlignment(Pos.CENTER);
        systemPlanet.setSpacing(20);
        systemPlanet.setPadding(new Insets(10));
        
        VBox subForm=new VBox();
        subForm.setAlignment(Pos.CENTER);
        subForm.setSpacing(20);
        
        Label starLabel=new Label("Datos del planeta");
        
        JFXTextField dato1=new JFXTextField();
        dato1.setPromptText("Masa");
        JFXTextField dato2=new JFXTextField();
        dato2.setPromptText("Diametro");
        JFXTextField dato3=new JFXTextField();
        dato3.setPromptText("Distancia a la estrella");
        subForm.getChildren().addAll(dato1,dato2,dato3);
        
        systemPlanet.getChildren().addAll(starLabel,subForm);
        
        return systemPlanet;
    }
    

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
    
    public ImageView getEntity(){
        
        File directorio=new File("src/sprites");
        //directorio.mkdirs();
        StringBuilder sb=new StringBuilder();
        LinkedList<String> rutasEncontradas=new LinkedList<>();
        for (File f:directorio.listFiles()){
            //src\sprites\1.png
            rutasEncontradas.add(f.getPath().replace("\\", "/").replace("src", ""));
        }
        
        String rutaSelected=rutasEncontradas.get(rd.nextInt(rutasEncontradas.size()));
        
        InputStream rutaStream=getClass().getResourceAsStream(rutaSelected);
        Image im=new Image(rutaStream,25,25,true,true);
        ImageView img=new ImageView(im);
        
        return img;
    }
    public ImageView getEntity(String name){
        InputStream rutaStream=getClass().getResourceAsStream("/sprites/"+name+".jpg");
        Image im=new Image(rutaStream,25,25,true,true);
        ImageView img=new ImageView(im);
        
        return img;
    }
    
}
